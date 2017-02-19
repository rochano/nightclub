<%@ taglib prefix="s" uri="/struts-tags" %>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/management"/>"><i class="home icon"></i>Home</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('basicinfo')}">active</s:if>" href="<s:url value="/management/basicinfo"/>"><i class="options icon"></i>Basic Info</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girls')}">active</s:if>" href="<s:url value="/management/girls"/>"><i class="heart icon"></i>Girls</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('schedule')}">active</s:if>" href="<s:url value="/management/schedule"/>"><i class="calendar icon"></i>Schedule</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('system')}">active</s:if>" href="<s:url value="/management/system"/>"><i class="wait icon"></i>System</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('map')}">active</s:if>" href="<s:url value="/management/map"/>"><i class="map icon"></i>Map</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('event')}">active</s:if>" href="<s:url value="/management/event"/>"><i class="newspaper icon"></i>Event</a>