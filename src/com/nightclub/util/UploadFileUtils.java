package com.nightclub.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nightclub.model.FileModel;
import com.nightclub.model.UserInfo;

public class UploadFileUtils {
	static Logger log_ = Logger.getLogger(UploadFileUtils.class.getName());
	
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
	
	public static String uploadImageApi(String originFileName, Map sessionMap, UserInfo userInfo) throws IOException {
		String fileName = originFileName;
		if(sessionMap.containsKey(originFileName)) {
    		File fileToCreate = new File(fileName);
    		FileModel fileModel = (FileModel) sessionMap.get(fileName);
    		FileUtils.writeByteArrayToFile(fileToCreate, fileModel.getImageInBytes());
    		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
					  "cloud_name", "diladfres",
					  "api_key", "486787566588465",
					  "api_secret", "ltE8fUE2mSc2HCpydAW5kqmriGA"));
    		Map param = new HashMap();
    		param.put("folder", userInfo.getUserInfoId());
			Map uploadResult = cloudinary.uploader().upload(fileToCreate, param);
			log_.info("uploadResult >> " + uploadResult.toString());
			fileName = uploadResult.get("url").toString();
			fileToCreate.delete();
    	}
		return fileName;
	}
	
	public static String uploadImageinDescription(String description, Map sessionMap, UserInfo userInfo) throws IOException {
		Matcher matcher = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>")
        		.matcher(description);
        Pattern pattern = Pattern.compile("images\\/temp\\?fileName=(.*)");
        Matcher matcher2;
        String newFileNameinDescription;
        
        while (matcher.find()) {
    		matcher2 = pattern.matcher(matcher.group(1));
    		if(matcher2.find()) {
    			newFileNameinDescription = UploadFileUtils.uploadImageApi(matcher2.group(1), sessionMap, userInfo);
    			description = description.replace(matcher.group(1), newFileNameinDescription);
    		}
        }
        
        return description;
	}
}
