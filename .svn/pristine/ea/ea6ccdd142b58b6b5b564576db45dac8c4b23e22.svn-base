package com.nightclub.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.nightclub.model.FileModel;

public class UploadFileUtils {
	public static String writeByteArrayToFile(Map sessionMap, String picFileName) throws IOException {
		String filePath = ResourceBundleUtil.getUploadPath();
		String extension = FilenameUtils.getExtension(picFileName);
    	FileModel fileModel = (FileModel) sessionMap.get(picFileName);
    	
    	picFileName = UUID.randomUUID().toString().toUpperCase() + "." + extension;
        File fileToCreate = new File(filePath, picFileName);
    	FileUtils.writeByteArrayToFile(fileToCreate, fileModel.getImageInBytes());
    	
    	return picFileName;
	}
	
	public static String writeByteArrayToFile(Map sessionMap, String picFileName, String defaultName) throws IOException {
		String filePath = ResourceBundleUtil.getUploadPath();
		String extension = FilenameUtils.getExtension(picFileName);
		FileModel fileModel = (FileModel) sessionMap.get(picFileName);
		
		picFileName = defaultName.substring(0, defaultName.lastIndexOf(".")) + "." + extension;
        File fileToCreate = new File(filePath, picFileName);
    	FileUtils.writeByteArrayToFile(fileToCreate, fileModel.getImageInBytes());
    	
    	return picFileName;
	}
}
