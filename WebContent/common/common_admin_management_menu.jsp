<%@ taglib prefix="s" uri="/struts-tags" %>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/admin"/>"><i class="home icon"></i><s:i18n name="global_th"><s:text name="global.menu_home" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('statistics')}">active</s:if>" href="<s:url value="/admin/statistics"/>"><i class="chartline icon"></i><s:i18n name="global_th"><s:text name="global.menu_statistics" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('inform')}">active</s:if>" href="<s:url value="/admin/inform"/>"><i class="announcement icon"></i><s:i18n name="global_th"><s:text name="global.menu_inform" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('homeslideimage')}">active</s:if>" href="<s:url value="/admin/homeslideimage"/>"><i class="picture icon"></i><s:i18n name="global_th"><s:text name="global.menu_home_slide_image" /></s:i18n></a>
	<%-- <a class="item <s:if test="%{menu.equalsIgnoreCase('shop')}">active</s:if>" href="<s:url value="/admin/shop"/>"><i class="building icon"></i><s:i18n name="global_th"><s:text name="global.menu_shop" /></s:i18n></a> --%>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('agent')}">active</s:if>" href="<s:url value="/admin/agent"/>"><i class="users icon"></i><s:i18n name="global_th"><s:text name="global.menu_agent" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('freeagent')}">active</s:if>" href="<s:url value="/admin/freeagent"/>"><i class="heart icon"></i><s:i18n name="global_th"><s:text name="global.menu_free_agent" /></s:i18n></a>
	<%-- <a class="item <s:if test="%{menu.equalsIgnoreCase('client')}">active</s:if>" href="<s:url value="/admin/client"/>"><i class="user icon"></i><s:i18n name="global_th"><s:text name="global.menu_client" /></s:i18n></a> --%>
	<%-- <a class="item <s:if test="%{menu.equalsIgnoreCase('engirl')}">active</s:if>" href="<s:url value="/admin/engirl"/>"><i class="smile icon"></i><s:i18n name="global_th"><s:text name="global.menu_en_girl" /></s:i18n></a> --%>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girls')}">active</s:if>" href="<s:url value="/admin/girls"/>"><i class="heart icon"></i><s:i18n name="global_th"><s:text name="global.menu_girls" /></s:i18n></a>
	<%-- <a class="item <s:if test="%{menu.equalsIgnoreCase('zone')}">active</s:if>" href="<s:url value="/admin/zone"/>"><i class="compass icon"></i><s:i18n name="global_th"><s:text name="global.location" /></s:i18n></a> --%>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('category')}">active</s:if>" href="<s:url value="/admin/category"/>"><i class="book icon"></i><s:i18n name="global_th"><s:text name="global.shop_category" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('ads')}">active</s:if>" href="<s:url value="/admin/ads"/>"><i class="announcement icon"></i><s:i18n name="global_th"><s:text name="global.menu_ads" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girlservice')}">active</s:if>" href="<s:url value="/admin/girlservice"/>"><i class="tasks icon"></i><s:i18n name="global_th"><s:text name="global.menu_girl_service" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girlsetting')}">active</s:if>" href="<s:url value="/admin/girlsetting"/>"><i class="sliders horizontal icon"></i><s:i18n name="global_th"><s:text name="global.menu_girl_setting" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('howtouse')}">active</s:if>" href="<s:url value="/admin/howtouse"/>"><i class="file outline icon"></i><s:i18n name="global_th"><s:text name="global.menu_how_to_use" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('contact')}">active</s:if>" href="<s:url value="/admin/contact"/>"><i class="phone icon"></i><s:i18n name="global_th"><s:text name="global.menu_contact" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('skin')}">active</s:if>" href="<s:url value="/admin/skin"/>"><i class="adjust icon"></i><s:i18n name="global_th"><s:text name="global.skin" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('howtoinput')}">active</s:if>" href="<s:url value="/admin/howtoinput"/>"><i class="file outline icon"></i><s:i18n name="global_th"><s:text name="global.how_to_input" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('linenotify')}">active</s:if>" href="<s:url value="/admin/linenotify"/>"><i class="announcement icon"></i><s:i18n name="global_th"><s:text name="global.menu_line_notify" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('nationality')}">active</s:if>" href="<s:url value="/admin/nationality"/>"><i class="flag icon"></i><s:i18n name="global_th"><s:text name="global.nationality" /></s:i18n></a>
	<div class="ui item">
		<div class="header"><s:i18n name="global_th"><s:text name="global.country" /></s:i18n></div>
		<div class="menu">
			<a class="item <s:if test="%{menu.equalsIgnoreCase('domestic')}">active</s:if>" href="<s:url value="/admin/domestic"/>"><i class="home icon"></i><s:i18n name="global_th"><s:text name="global.domestic" /></s:i18n></a>
			<a class="item <s:if test="%{menu.equalsIgnoreCase('overseas')}">active</s:if>" href="<s:url value="/admin/overseas"/>"><i class="world icon"></i><s:i18n name="global_th"><s:text name="global.overseas" /></s:i18n></a>
		</div>
	</div>
	<%-- <a class="item <s:if test="%{menu.equalsIgnoreCase('province')}">active</s:if>" href="<s:url value="/admin/province"/>"><i class="map icon"></i><s:i18n name="global_th"><s:text name="global.province" /></s:i18n></a> --%>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('gender')}">active</s:if>" href="<s:url value="/admin/gender"/>"><i class="other gender icon"></i><s:i18n name="global_th"><s:text name="global.gender" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('description')}">active</s:if>" href="<s:url value="/admin/description"/>"><i class="list icon"></i><s:i18n name="global_th"><s:text name="global.description" /></s:i18n></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girltag')}">active</s:if>" href="<s:url value="/admin/girltag"/>"><i class="tag icon"></i><s:i18n name="global_th"><s:text name="global.girl_tag" /></s:i18n></a>
