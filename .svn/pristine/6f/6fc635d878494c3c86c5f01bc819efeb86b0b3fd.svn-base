package com.nightclub.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import org.apache.struts2.util.StrutsTypeConverter;
import com.opensymphony.xwork2.ActionContext;

public class DateConverter extends StrutsTypeConverter {
	  
	  private Logger log_ = Logger.getLogger(this.getClass().getName());
	  
	  @Override
	  public Object convertFromString(Map context, String[] values, Class toClass) {
	    
	    Date returnObject = null;
	    String value = values[0];
	    if (value != null && !value.trim().equals("")) {
	      try {
	        returnObject = new SimpleDateFormat(getDatePattern()).parse(value);
	      } catch (ParseException e) {
	        // Just to ignore the parse exception
	      }
	    }
	    return returnObject;
	  }

	  @Override
	  public String convertToString(Map context, Object o) {
	    
	    Date date = (Date) o;
	    String formatedDate = new SimpleDateFormat(getDatePattern()).format(date);
	    return formatedDate;
	  }
	  
	  private String getDatePattern() {
	    
	    ResourceBundle bundle = ResourceBundle.getBundle("package", ActionContext.getContext().getLocale());
	    String pattern = bundle.getString("text.date.format");
	    //LOG.info("current date pattern is:" + pattern);
	    return pattern;
	  }

}
