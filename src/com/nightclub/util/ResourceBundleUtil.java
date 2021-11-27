package com.nightclub.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.opensymphony.xwork2.ActionContext;

public class ResourceBundleUtil {
	private static final Logger log_ = Logger.getLogger(ResourceBundleUtil.class.getName());
	
	public static String getUploadPath() throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("package.properties");
		// ...
		Properties properties = new Properties();
		properties.load(input);

		String tmpDir = System.getenv(properties.getProperty("tmp_dir_env_name"));
        
        if(tmpDir == null) {
        	tmpDir = properties.getProperty("tmp_dir_default");
        }
        
        String filePath = tmpDir + properties.getProperty("upload_path");
        log_.info("Server path:" + filePath);
        
        File folder = new File(filePath);
        if (!folder.isDirectory()) {
          folder.mkdirs();
        }
        
        return filePath;
	}
}
