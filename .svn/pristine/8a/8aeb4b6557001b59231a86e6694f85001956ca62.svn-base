package com.nightclub.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.ckfinder.connector.configuration.ConfigurationFactory;
import com.ckfinder.connector.configuration.IConfiguration;
import com.ckfinder.connector.utils.FileUtils;
import com.nightclub.model.FileModel;
import com.nightclub.util.ResourceBundleUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

public class FileSystemAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletContextAware {
	private static final long serialVersionUID = 1882314627880008676L;
	private Logger log_ = Logger.getLogger(getClass().getName());
	
	private Map<String, Object> sessionMap;
	private String user;
	private String fileName;
	private String extension;
	private byte[] imageInBytes;
	private String contentType;
	private String contentDisposition;
	private HttpServletRequest httpRequest;
	private ServletContext servletContext;
	
	public String execute() throws IOException {
	    this.log_.info("getFileName() >> " + getFileName() + "." + getExtension());
	    String filePath = ResourceBundleUtil.getUploadPath();
	    
	    File file = new File(filePath + getFileName() + "." + getExtension());
	    this.log_.info("file.isFile() >> " + file.isFile());
	    
	    BufferedImage originalImage = ImageIO.read(file);
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(originalImage, getExtension(), baos);
	    baos.flush();
	    setImageInBytes(baos.toByteArray());
	    baos.close();
	    this.log_.info("new MimetypesFileTypeMap().getContentType(file) >> " + new MimetypesFileTypeMap().getContentType(file));
	    this.log_.info("file.getName() >> " + file.getName());
	    setContentType(new MimetypesFileTypeMap().getContentType(file));
	    setContentDisposition(file.getName());
	    
	    return SUCCESS;
	}
	
	public String temp() throws IOException {
	    this.log_.info("getFileName() >> " + getFileName());
	    
	    FileModel fileModel = (FileModel)this.sessionMap.get(getFileName());
	    
	    this.log_.info("new MimetypesFileTypeMap().getContentType(file) >> " + fileModel.getContentType());
	    this.log_.info("file.getName() >> " + getFileName());
	    
	    setImageInBytes(fileModel.getImageInBytes());
	    setContentType(fileModel.getContentType());
	    setContentDisposition(fileModel.getFileName());
	    
	    return SUCCESS;
	}
	
	public String uploadpath() throws Exception {
		
		String uri = httpRequest.getRequestURI();
		this.log_.info("httpRequest.getRequestURI() >> " + uri);
		int indexOfSemicolon = uri.indexOf(";");
	    uri = indexOfSemicolon > -1 ? uri.substring(0, indexOfSemicolon) : uri;
	    
	    setExtension(FileUtils.getFileExtension(uri));
	    
		this.log_.info("getFileName() >> " + getFileName() + "." + getExtension());
	    IConfiguration conf = ConfigurationFactory.getInstace().getConfiguration();
	    String filePath = conf.getBasePathBuilder().getBaseDir(httpRequest) + "images/";
	    this.log_.info("filePath >> " + filePath);
	    File file = new File(filePath + getFileName() + "." + getExtension());
	    this.log_.info("file.isFile() >> " + file.isFile());
	    
	    BufferedImage originalImage = ImageIO.read(file);
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(originalImage, getExtension(), baos);
	    baos.flush();
	    setImageInBytes(baos.toByteArray());
	    baos.close();
	    this.log_.info("servletContext.getMimeType(file.getName() >> " + servletContext.getMimeType(file.getName()));
	    //this.log_.info("new MimetypesFileTypeMap().getContentType(file) >> " + new MimetypesFileTypeMap().getContentType(file));
	    this.log_.info("file.getName() >> " + file.getName());
	    setContentType(servletContext.getMimeType(file.getName()));
	    setContentDisposition(file.getName());
	    
	    return SUCCESS;
	}
	
	public String getFileName() {
		return fileName;
	}
	public byte[] getImageInBytes() {
		return imageInBytes;
	}
	public String getContentType() {
		return contentType;
	}
	public String getContentDisposition() {
		return contentDisposition;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setImageInBytes(byte[] imageInBytes) {
		this.imageInBytes = imageInBytes;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
