package com.nightclub.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;

import com.nightclub.controller.GirlInfoManager;

public class IntegerConverter extends StrutsTypeConverter {
	
	private static Logger log_ = Logger.getLogger(IntegerConverter.class);
	
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
        if (values.length != 1) {
            super.performFallbackConversion(context, values, toClass);
        }

        final String numero = values[0];
 
        try {
            return Integer.parseInt(numero);
        } catch (Exception e) {
            return 0;
        }
    }
 
	@Override
    public String convertToString(Map context, Object o) {
        final NumberFormat formatter = new DecimalFormat("#0");
        if (o instanceof Integer) {
            return formatter.format(o);
        } else {
        	log_.info("[" + o + "] >> else ");
            return String.valueOf(o);
        }
    }
}
