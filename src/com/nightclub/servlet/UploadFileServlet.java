package com.nightclub.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.gson.JsonObject;
import com.nightclub.custom.ckfinder.connector.CustomConnectorServlet;
import com.nightclub.model.FileModel;
import com.nightclub.util.ResourceBundleUtil;

/**
 * Servlet implementation class UploadFileServlet
 */
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(getClass().getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest servletRequest = request;
	    boolean isMultipart = ServletFileUpload.isMultipartContent(servletRequest);
	    String fileName = UUID.randomUUID().toString().toUpperCase();
	    String filePath = ResourceBundleUtil.getUploadPath();
	    if (isMultipart) {
	    	FileItemFactory factory = new DiskFileItemFactory();
	    	ServletFileUpload upload = new ServletFileUpload(factory);
	    	List<FileItem> multiparts;
			try {
				multiparts = upload.parseRequest(servletRequest);
				for (FileItem item : multiparts) {
		    		if (!item.isFormField()) {
		    			fileName += FilenameUtils.EXTENSION_SEPARATOR + FilenameUtils.getExtension(item.getName());
		    			FileModel fileModel = new FileModel(item.getName(), item.get(), item.getContentType());
		    			request.getSession().setAttribute(fileName, fileModel);
		    			/*File fileToCreate = new File(fileName);
		    			item.write(fileToCreate);*/
		    			//FileUtils.writeByteArrayToFile(fileToCreate, fileModel.getImageInBytes());
		    			
		    			/*Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
		  					  "cloud_name", "diladfres",
		  					  "api_key", "486787566588465",
		  					  "api_secret", "ltE8fUE2mSc2HCpydAW5kqmriGA"));
		    			Map uploadResult = cloudinary.uploader().upload(fileToCreate, ObjectUtils.emptyMap());
		    			log_.info("uploadResult >> " + uploadResult.toString());
		    			fileName = uploadResult.get("url").toString();
		    			fileToCreate.delete();*/
		    			break;
		    		}
		    	}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    JsonObject json = new JsonObject();
		json.addProperty("fileName", fileName);
		json.addProperty("path", request.getContextPath() + "/images/temp?fileName=");
		
	    response.getWriter().println(json.toString());
	}

}
