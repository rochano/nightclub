package com.nightclub.util;

import java.io.File;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.opensymphony.xwork2.ActionContext;

public class ResourceBundleUtil {
	private static final Logger log_ = Logger.getLogger(ResourceBundleUtil.class.getName());
	
	public static String getUploadPath() {
		ResourceBundle bundle = ResourceBundle.getBundle("package", ActionContext.getContext().getLocale());
        String tmpDir = System.getenv(bundle.getString("tmp_dir_env_name"));
        
        if(tmpDir == null) {
        	tmpDir = bundle.getString("tmp_dir_default");
        }
        
        String filePath = tmpDir + bundle.getString("upload_path");
        log_.info("Server path:" + filePath);
        
        File folder = new File(filePath);
        if (!folder.isDirectory()) {
          folder.mkdirs();
        }
        
        return filePath;
	}
}
