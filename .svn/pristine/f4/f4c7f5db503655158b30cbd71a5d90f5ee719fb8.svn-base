package com.nightclub.result;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nightclub.view.FileSystemAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

public class BytesResult implements Result {
	private static final long serialVersionUID = -6744971978886886471L;

	public void execute(ActionInvocation invocation) throws Exception {
		FileSystemAction action = (FileSystemAction)invocation.getAction();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType(action.getContentType());
		response.getOutputStream().write(action.getImageInBytes());
		response.getOutputStream().flush();
	}
}
