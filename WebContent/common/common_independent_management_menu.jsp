<%@ taglib prefix="s" uri="/struts-tags" %>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/management_independent"/>"><i class="home icon"></i><s:i18n name="global_th"><s:text name="global.menu_home" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('information')}">active</s:if>" href="<s:url value="/management_independent/information"/>"><i class="options icon"></i><s:i18n name="global_th"><s:text name="global.menu_basic_info" /></s:i18n></a>
	