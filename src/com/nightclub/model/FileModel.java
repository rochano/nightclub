package com.nightclub.model;

public class FileModel {
	String fileName;
	byte[] imageInBytes;
	String contentType;
	
	public FileModel(String fileName, byte[] imageInBytes, String contentType) {
		super();
		this.fileName = fileName;
		this.imageInBytes = imageInBytes;
		this.contentType = contentType;
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
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setImageInBytes(byte[] imageInBytes) {
		this.imageInBytes = imageInBytes;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
