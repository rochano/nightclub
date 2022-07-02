<%@ taglib prefix="s" uri="/struts-tags" %>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/admin"/>"><i class="home icon"></i><s:text name="global.menu_home" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('homeslideimage')}">active</s:if>" href="<s:url value="/admin/homeslideimage"/>"><i class="picture icon"></i><s:text name="global.menu_home_slide_image" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('shop')}">active</s:if>" href="<s:url value="/admin/shop"/>"><i class="building icon"></i><s:text name="global.menu_shop" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('agent')}">active</s:if>" href="<s:url value="/admin/agent"/>"><i class="users icon"></i><s:text name="global.menu_agent" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('freeagent')}">active</s:if>" href="<s:url value="/admin/freeagent"/>"><i class="heart icon"></i><s:text name="global.menu_free_agent" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('client')}">active</s:if>" href="<s:url value="/admin/client"/>"><i class="user icon"></i><s:text name="global.menu_client" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('engirl')}">active</s:if>" href="<s:url value="/admin/engirl"/>"><i class="smile icon"></i><s:text name="global.menu_en_girl" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girls')}">active</s:if>" href="<s:url value="/admin/girls"/>"><i class="heart icon"></i><s:text name="global.menu_girls" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('zone')}">active</s:if>" href="<s:url value="/admin/zone"/>"><i class="compass icon"></i><s:text name="global.location" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('category')}">active</s:if>" href="<s:url value="/admin/category"/>"><i class="book icon"></i><s:text name="global.shop_category" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('ads')}">active</s:if>" href="<s:url value="/admin/ads"/>"><i class="announcement icon"></i><s:text name="global.menu_ads" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girlservice')}">active</s:if>" href="<s:url value="/admin/girlservice"/>"><i class="tasks icon"></i><s:text name="global.menu_girl_service" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girlsetting')}">active</s:if>" href="<s:url value="/admin/girlsetting"/>"><i class="sliders horizontal icon"></i><s:text name="global.menu_girl_setting" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('howtouse')}">active</s:if>" href="<s:url value="/admin/howtouse"/>"><i class="file outline icon"></i><s:text name="global.menu_how_to_use" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('contact')}">active</s:if>" href="<s:url value="/admin/contact"/>"><i class="phone icon"></i><s:text name="global.menu_contact" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('skin')}">active</s:if>" href="<s:url value="/admin/skin"/>"><i class="adjust icon"></i><s:text name="global.skin" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('howtoinput')}">active</s:if>" href="<s:url value="/admin/howtoinput"/>"><i class="file outline icon"></i><s:text name="global.how_to_input" /></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('linenotify')}">active</s:if>" href="<s:url value="/admin/linenotify"/>"><i class="announcement icon"></i><s:text name="global.menu_line_notify" /></a>
	