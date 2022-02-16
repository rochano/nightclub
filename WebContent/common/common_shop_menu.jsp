<%@ taglib prefix="s" uri="/struts-tags" %>
<a class="ui item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/shop/"/>
	<s:property value="shopInfoId" />"><s:text name="global.shop_menu_home" /></a>
<a class="ui item <s:if test="%{menu.equalsIgnoreCase('girls')}">active</s:if>" href="<s:url value="/shop/"/>
	<s:property value="shopInfoId" />/girls"><s:text name="global.shop_menu_girls" /></a>
<a class="ui item <s:if test="%{menu.equalsIgnoreCase('system')}">active</s:if>" href="<s:url value="/shop/"/>
	<s:property value="shopInfoId" />/system"><s:text name="global.shop_menu_system" /></a>