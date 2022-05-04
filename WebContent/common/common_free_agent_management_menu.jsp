<%@ taglib prefix="s" uri="/struts-tags" %>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/management_free_agent"/>"><i class="home icon"></i><s:text name="global.menu_home" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('information')}">active</s:if>" href="<s:url value="/management_free_agent/information"/>"><i class="options icon"></i><s:text name="global.menu_basic_info" /></a>
	