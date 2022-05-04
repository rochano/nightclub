<%@ taglib prefix="s" uri="/struts-tags" %>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/management_agent"/>"><i class="home icon"></i><s:text name="global.menu_home" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('agentinfo')}">active</s:if>" href="<s:url value="/management_agent/agentinfo"/>"><i class="options icon"></i><s:text name="global.menu_agent_info" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girls')}">active</s:if>" href="<s:url value="/management_agent/girls"/>"><i class="heart icon"></i><s:text name="global.menu_girls" /></a>
