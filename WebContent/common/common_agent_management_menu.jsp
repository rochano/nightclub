<%@ taglib prefix="s" uri="/struts-tags" %>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/management_agent"/>"><i class="home icon"></i><s:i18n name="global_th"><s:text name="global.menu_home" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('agentinfo')}">active</s:if>" href="<s:url value="/management_agent/agentinfo"/>"><i class="options icon"></i><s:i18n name="global_th"><s:text name="global.menu_agent_info" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girls')}">active</s:if>" href="<s:url value="/management_agent/girls"/>"><i class="heart icon"></i><s:i18n name="global_th"><s:text name="global.menu_girls" /></s:i18n></a>
