package com.nightclub.mapper;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.mapper.ActionMapper;
import org.apache.struts2.dispatcher.mapper.ActionMapping;

import com.opensymphony.xwork2.config.ConfigurationManager;

public class CustomActionMapper implements ActionMapper {

	@Override
	public ActionMapping getMapping(HttpServletRequest arg0,
			ConfigurationManager arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionMapping getMappingFromActionName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUriFromActionMapping(ActionMapping arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
