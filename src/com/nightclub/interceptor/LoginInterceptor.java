package com.nightclub.interceptor;


import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -7043766645016198899L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		
		final ActionContext context = invocation.getInvocationContext();
        HttpServletResponse response = (HttpServletResponse)context.get(StrutsStatics.HTTP_RESPONSE);
        if(response!=null){
            response.setHeader("Cache-control", "no-cache, no-store");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "-1");
        }

        if (!session.containsKey("userInfo")) 
        {
            return Action.LOGIN;
        } 
        else 
        {
            return invocation.invoke();
        }
	}

}
