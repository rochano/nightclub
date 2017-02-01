package com.nightclub.custom.ckfinder.connector.handlers.command;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ckfinder.connector.configuration.IConfiguration;
import com.ckfinder.connector.errors.ConnectorException;
import com.ckfinder.connector.handlers.command.Command;
import com.ckfinder.connector.utils.AccessControlUtil;
import com.ckfinder.connector.utils.FileUtils;

public class CustomThumbnailCommand extends Command {
  private String fileName;
  private String version;
  private File thumbFile;
  private InputStream is;
  private String url;
  private String ifNoneMatch;
  private long ifModifiedSince;
  private HttpServletResponse response;
  private String fullCurrentPath;
  private static final HashMap<String, String> imgMimeTypeMap = new HashMap(57);
  
  static
  {
    imgMimeTypeMap.put(".art", "image/x-jg");
    imgMimeTypeMap.put(".bm", "image/bmp");
    imgMimeTypeMap.put(".bmp", "image/bmp");
    imgMimeTypeMap.put(".dwg", "image/vnd.dwg");
    imgMimeTypeMap.put(".dxf", "image/vnd.dwg");
    imgMimeTypeMap.put(".fif", "image/fif");
    imgMimeTypeMap.put(".flo", "image/florian");
    imgMimeTypeMap.put(".fpx", "image/vnd.fpx");
    imgMimeTypeMap.put(".g3", "image/g3fax");
    imgMimeTypeMap.put(".gif", "image/gif");
    imgMimeTypeMap.put(".ico", "image/x-icon");
    imgMimeTypeMap.put(".ief", "image/ief");
    imgMimeTypeMap.put(".iefs", "image/ief");
    imgMimeTypeMap.put(".jut", "image/jutvision");
    imgMimeTypeMap.put(".mcf", "image/vasa");
    imgMimeTypeMap.put(".nap", "image/naplps");
    imgMimeTypeMap.put(".naplps", "image/naplps");
    imgMimeTypeMap.put(".nif", "image/x-niff");
    imgMimeTypeMap.put(".niff", "image/x-niff");
    imgMimeTypeMap.put(".pct", "image/x-pict");
    imgMimeTypeMap.put(".pcx", "image/x-pcx");
    imgMimeTypeMap.put(".pgm", "image/x-portable-graymap");
    imgMimeTypeMap.put(".pic", "image/pict");
    imgMimeTypeMap.put(".pict", "image/pict");
    imgMimeTypeMap.put(".pm", "image/x-xpixmap");
    imgMimeTypeMap.put(".png", "image/png");
    imgMimeTypeMap.put(".pnm", "image/x-portable-anymap");
    imgMimeTypeMap.put(".ppm", "image/x-portable-pixmap");
    imgMimeTypeMap.put(".ras", "image/x-cmu-raster");
    imgMimeTypeMap.put(".rast", "image/cmu-raster");
    imgMimeTypeMap.put(".rf", "image/vnd.rn-realflash");
    imgMimeTypeMap.put(".rgb", "image/x-rgb");
    imgMimeTypeMap.put(".rp", "  image/vnd.rn-realpix");
    imgMimeTypeMap.put(".svf", "image/vnd.dwg");
    imgMimeTypeMap.put(".svf", "image/x-dwg");
    imgMimeTypeMap.put(".tiff", "image/tiff");
    imgMimeTypeMap.put(".turbot", "image/florian");
    imgMimeTypeMap.put(".wbmp", "image/vnd.wap.wbmp");
    imgMimeTypeMap.put(".xif", "image/vnd.xiff");
    imgMimeTypeMap.put(".xpm", "image/x-xpixmap");
    imgMimeTypeMap.put(".x-png", "image/png");
    imgMimeTypeMap.put(".xwd", "image/x-xwindowdump");
  }
  
  public void setResponseHeader(HttpServletResponse response, ServletContext sc)
  {
    response.setHeader("Cache-Control", "public");
    String mimetype = getMimeTypeOfImage(sc, response);
    if (mimetype != null) {
      response.setContentType(mimetype);
    }
    response.addHeader("Content-Disposition", "attachment; filename=\"" + this.fileName + "\"");
    
    this.response = response;
  }
  
  private String getMimeTypeOfImage(ServletContext sc, HttpServletResponse response)
  {
    if ((this.fileName == null) || (this.fileName.length() == 0))
    {
      response.setStatus(500);
      return null;
    }
    String tempFileName = this.fileName.substring(0, this.fileName.lastIndexOf('.') + 1).concat(
      FileUtils.getFileExtension(this.fileName).toLowerCase());
    String mimeType = sc.getMimeType(tempFileName);
    if ((mimeType == null) || (mimeType.length() == 0)) {
      mimeType = (String)imgMimeTypeMap.get(this.fileName.toLowerCase().substring(this.fileName.lastIndexOf(".")));
    }
    if (mimeType == null)
    {
      response.setStatus(500);
      return null;
    }
    return mimeType;
  }
  
  public void execute(OutputStream out)
    throws ConnectorException
  {
    validate();
    createThumb();
    if (setResponseHeadersAfterCreatingFile()) {
      try
      {
    	  URL uri = new URL(url);
      	is = uri.openStream();
        printFileContentToResponse(this.is, out);
      }
      catch (IOException e)
      {
        if (this.configuration.isDebugMode()) {
          throw new ConnectorException(e);
        }
        try
        {
          this.response.sendError(403);
        }
        catch (IOException e1)
        {
          throw new ConnectorException(e1);
        }
      }
    } else {
      try
      {
        this.response.reset();
        this.response.sendError(304);
      }
      catch (IOException e1)
      {
        throw new ConnectorException(e1);
      }
    }
  }
  
  public void initParams(HttpServletRequest request, IConfiguration configuration, Object... params)
    throws ConnectorException
  {
    super.initParams(request, configuration, params);
    this.fileName = getParameter(request, "FileName");
    this.version = getParameter(request, "version");
    Logger.getLogger(CustomThumbnailCommand.class.getName()).log(
			Level.INFO, "fileName >> " + fileName + ", version >> " + version );
    try
    {
      this.ifModifiedSince = Long.valueOf(request.getDateHeader("If-Modified-Since")).longValue();
    }
    catch (IllegalArgumentException e)
    {
      this.ifModifiedSince = 0L;
    }
    this.ifNoneMatch = request.getHeader("If-None-Match");
  }
  
  private void validate()
    throws ConnectorException
  {
    if (!this.configuration.getThumbsEnabled()) {
      throw new ConnectorException(501);
    }
    if (!checkIfTypeExists(this.type))
    {
      this.type = null;
      throw new ConnectorException(12, false);
    }
    if (!AccessControlUtil.getInstance().checkFolderACL(this.type, this.currentFolder, this.userRole, 16)) {
      throw new ConnectorException(103);
    }
    if (!FileUtils.checkFileName(this.fileName)) {
      throw new ConnectorException(109);
    }
    if (FileUtils.checkIfFileIsHidden(this.fileName, this.configuration)) {
      throw new ConnectorException(117);
    }
    File typeThumbDir = new File(this.configuration.getThumbsPath() + File.separator + this.type);
    try
    {
    	Logger.getLogger(CustomThumbnailCommand.class.getName()).log(
				Level.INFO, "typeThumbDir.getName() >> " + typeThumbDir.getName() + 
						", typeThumbDir.getAbsolutePath() >> " + typeThumbDir.getAbsolutePath());
      this.fullCurrentPath = (typeThumbDir.getAbsolutePath() + this.currentFolder);
      /*if (!typeThumbDir.exists()) {
        typeThumbDir.mkdir();
      }*/
    }
    catch (SecurityException e)
    {
      throw new ConnectorException(104, e);
    }
  }
  
  private void createThumb()
    throws ConnectorException
  {
	  this.url = "http://res.cloudinary.com/diladfres/image/upload/v" + this.version + "/" + this.fileName;
	  Logger.getLogger(CustomThumbnailCommand.class.getName()).log(
				Level.INFO, "url >> " + url);
    try {
		this.thumbFile = new File( URLDecoder.decode( url, "UTF-8" ) );
		URL uri = new URL(url);
    	is = uri.openStream();
	} catch (UnsupportedEncodingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  private boolean setResponseHeadersAfterCreatingFile()
    throws ConnectorException
  {
    File file = this.thumbFile;
    try
    {
      String etag = Long.toHexString(file.lastModified()).concat("-").concat(Long.toHexString(file.length()));
      int length;
      int size = 0;
      byte[] b = new byte[2048];
		while ((length = is.read(b)) != -1) {
			size += length;
		}
		if (is != null) {
			is.close();
	        }
      Logger.getLogger(CustomThumbnailCommand.class.getName()).log(
				Level.INFO, "size" + size + 
				", file.lastModified() >> " + file.lastModified() + 
				", etag >> " + etag);
      /*if (etag.equals(this.ifNoneMatch)) {
        return false;
      }
      this.response.setHeader("Etag", etag);*/
      /*if (file.lastModified() <= this.ifModifiedSince) {
        return false;
      }*/
      Date date = new Date(System.currentTimeMillis());
      SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMMM yyyy HH:mm:ss z");
      
      this.response.setHeader("Last-Modified", df.format(date));
      
      this.response.setContentLength(size);
    }
    catch (SecurityException e)
    {
      throw new ConnectorException(104, e);
    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return true;
  }
  
  private static void printFileContentToResponse(InputStream in, OutputStream out)
		    throws IOException
		  {
		    try
		    {
		    	byte[] b = new byte[2048];
				int length;
	
				while ((length = in.read(b)) != -1) {
					out.write(b, 0, length);
				}
		      return;
		    }
		    catch (IOException e)
		    {
		      throw e;
		    }
		    finally
		    {
		      try
		      {
		        if (in != null) {
		          in.close();
		        }
		      }
		      catch (IOException e)
		      {
		        Logger.getLogger(CustomThumbnailCommand.class.getName()).log(
						Level.SEVERE, "Error when closing stream." + e);
		      }
		    }
		  }
}
