<%@ taglib prefix="s" uri="/struts-tags" %>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/management_agent"/>"><i class="home icon"></i>Home</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('agentinfo')}">active</s:if>" href="<s:url value="/management_agent/agentinfo"/>"><i class="options icon"></i>Agent Info</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girls')}">active</s:if>" href="<s:url value="/management_agent/girls"/>"><i class="heart icon"></i>Girls</a>
