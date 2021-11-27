<%@ taglib prefix="s" uri="/struts-tags" %>
<a class="ui item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/shop/"/>
	<s:property value="shopCode" />"><s:text name="global.shop_menu_home" /></a>
<a class="ui item <s:if test="%{menu.equalsIgnoreCase('girls')}">active</s:if>" href="<s:url value="/shop/"/>
	<s:property value="shopCode" />/girls"><s:text name="global.shop_menu_girls" /></a>
<a class="ui item <s:if test="%{menu.equalsIgnoreCase('newface')}">active</s:if>" href="<s:url value="/shop/"/>
	<s:property value="shopCode" />/newface"><s:text name="global.shop_menu_newface" /></a>
<a class="ui item <s:if test="%{menu.equalsIgnoreCase('ranking')}">active</s:if>" href="<s:url value="/shop/"/>
	<s:property value="shopCode" />/ranking"><s:text name="global.shop_menu_ranking" /></a>
<a class="ui item <s:if test="%{menu.equalsIgnoreCase('todayworking')}">active</s:if>" href="<s:url value="/shop/"/>
	<s:property value="shopCode" />/todayworking"><s:text name="global.shop_menu_schedule" /></a>
<a class="ui item <s:if test="%{menu.equalsIgnoreCase('system')}">active</s:if>" href="<s:url value="/shop/"/>
	<s:property value="shopCode" />/system"><s:text name="global.shop_menu_system" /></a>
<a class="ui item <s:if test="%{menu.equalsIgnoreCase('map')}">active</s:if>" href="<s:url value="/shop/"/>
	<s:property value="shopCode" />/map"><s:text name="global.shop_menu_map" /></a>