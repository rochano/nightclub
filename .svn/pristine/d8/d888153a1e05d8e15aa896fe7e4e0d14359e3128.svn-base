package com.nightclub.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.model.FileModel;
import com.nightclub.util.ResourceBundleUtil;
import com.opensymphony.xwork2.ActionSupport;

public class FileSystemAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1882314627880008676L;
	private Logger log_ = Logger.getLogger(getClass().getName());
	
	private Map<String, Object> sessionMap;
	private String fileName;
	private byte[] imageInBytes;
	private String contentType;
	private String contentDisposition;
	
	public String execute() throws IOException {
	    this.log_.info("getFileName() >> " + getFileName());
	    String filePath = ResourceBundleUtil.getUploadPath();
	    
	    File file = new File(filePath + getFileName());
	    this.log_.info("file.isFile() >> " + file.isFile());
	    
	    BufferedImage originalImage = ImageIO.read(file);
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(originalImage, "jpg", baos);
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
}
