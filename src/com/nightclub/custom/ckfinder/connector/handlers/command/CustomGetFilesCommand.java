package com.nightclub.custom.ckfinder.connector.handlers.command;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Element;

import com.ckfinder.connector.configuration.IConfiguration;
import com.ckfinder.connector.data.ResourceType;
import com.ckfinder.connector.data.XmlAttribute;
import com.ckfinder.connector.data.XmlElementData;
import com.ckfinder.connector.errors.ConnectorException;
import com.ckfinder.connector.handlers.command.XMLCommand;
import com.ckfinder.connector.utils.AccessControlUtil;
import com.ckfinder.connector.utils.FileUtils;
import com.ckfinder.connector.utils.ImageUtils;
import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CustomGetFilesCommand
  extends XMLCommand
{
  private static final float BYTES = 1024.0F;
  private List<Map> files;
  private String fullCurrentPath;
  private String showThumbs;
  
  public void initParams(HttpServletRequest request, IConfiguration configuration, Object... params)
    throws ConnectorException
  {
    super.initParams(request, configuration, new Object[0]);
    
    this.showThumbs = request.getParameter("showThumbs");
  }
  
  protected void createXMLChildNodes(int errorNum, Element rootElement)
    throws ConnectorException
  {
    if (errorNum == 0) {
      createFilesData(rootElement);
    }
  }
  
  protected int getDataForXml()
  {
    if (!checkIfTypeExists(this.type))
    {
      this.type = null;
      return 12;
    }
    this.fullCurrentPath = (((ResourceType)this.configuration.getTypes().get(this.type)).getPath() + this.currentFolder);
    if (!AccessControlUtil.getInstance().checkFolderACL(this.type, this.currentFolder, this.userRole, 16)) {
      return 103;
    }
    //File dir = new File(this.fullCurrentPath);
    try
    {
      /*if (!dir.exists()) {
        return 116;
      }*/
      //this.files = FileUtils.findChildrensList(dir, false);
    	this.files = new ArrayList();
    	Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "diladfres",
				  "api_key", "486787566588465",
				  "api_secret", "ltE8fUE2mSc2HCpydAW5kqmriGA"));
    	Api api = cloudinary.api();
    	Map result = api.resources(ObjectUtils.asMap("type", "upload"));
    	Logger.getLogger(CustomGetFilesCommand.class.getName()).log(
				Level.INFO, "result >> " + result.toString());
    	
    	if(result.containsKey("resources")) {
            List<Map<String,Object>> resources = (ArrayList<Map<String,Object>>) result.get("resources");
            for (Map<String,Object> resource : resources) {
                if(resource.containsKey("public_id")) {
                	this.files.add(resource);
                }
            }
        }
    }
    catch (SecurityException e)
    {
      if (this.configuration.isDebugMode()) {
        throw e;
      }
      return 104;
    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    filterListByHiddenAndNotAllowed();
    //Collections.sort(this.files);
    return 0;
  }
  
  private void filterListByHiddenAndNotAllowed()
  {
    List<Map> tmpFiles = new ArrayList();
    for (Map resource : this.files) {
    	String file = resource.get("public_id") + "." + resource.get("format");
    	if ((FileUtils.checkFileExtension(file, (ResourceType)this.configuration.getTypes().get(this.type)) == 0) && 
    			(!FileUtils.checkIfFileIsHidden(file, this.configuration))) {
    		tmpFiles.add(resource);
    	}
    }
    this.files.clear();
    this.files.addAll(tmpFiles);
  }
  
  private void createFilesData(Element rootElement)
  {
    Element element = this.creator.getDocument().createElement("Files");
    DateFormat dateFormatTaget = new SimpleDateFormat("yyyyMMddHHmm");
    DateFormat dateFormatOrigin = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    Date lastModifDate = null;
    for (Map resource : this.files)
    {
    	String filePath = resource.get("public_id") + "." + resource.get("format");
    	//File file = new File(this.fullCurrentPath, filePath);
      //if (file.exists())
      {
        XmlElementData elementData = new XmlElementData("File");
        XmlAttribute attribute = new XmlAttribute("name", filePath);
        elementData.getAttributes().add(attribute);
		try {
			lastModifDate = dateFormatOrigin.parse(resource.get("created_at").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        attribute = new XmlAttribute("date", dateFormatTaget.format(lastModifDate));
        elementData.getAttributes().add(attribute);
        attribute = new XmlAttribute("size", resource.get("bytes").toString());
        elementData.getAttributes().add(attribute);
        attribute = new XmlAttribute("version", resource.get("version").toString());
        elementData.getAttributes().add(attribute);
        if ("image".equals(resource.get("resource_type").toString()) && (isAddThumbsAttr()))
        {
          /*String attr = createThumbAttr(file);
          if (!attr.equals(""))
          {
            attribute = new XmlAttribute("thumb", attr);
            elementData.getAttributes().add(attribute);
          }*/
        }
        elementData.addToDocument(this.creator.getDocument(), element);
      }
    }
    rootElement.appendChild(element);
  }
  
  private String createThumbAttr(File file)
  {
    File thumbFile = new File(this.configuration.getThumbsPath() + File.separator + this.type + this.currentFolder, file.getName());
    if (thumbFile.exists()) {
      return file.getName();
    }
    if (isShowThumbs()) {
      return "?".concat(file.getName());
    }
    return "";
  }
  
  private String getSize(File file)
  {
    if ((file.length() > 0L) && ((float)file.length() < 1024.0F)) {
      return "1";
    }
    return String.valueOf(Math.round((float)file.length() / 1024.0F));
  }
  
  private boolean isAddThumbsAttr()
  {
    return (this.configuration.getThumbsEnabled()) && ((this.configuration.getThumbsDirectAccess()) || (isShowThumbs()));
  }
  
  private boolean isShowThumbs()
  {
    return (this.showThumbs != null) && (this.showThumbs.equals("1"));
  }
}
