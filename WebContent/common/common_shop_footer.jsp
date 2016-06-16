<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="footer" class="ui grid centered segment inverted">
	<div  class="one column center aligned ">
		<div><p>
			<a href="<s:url value="/shop/"/>
				<s:property value="shopCode" />"><s:text name="global.shop_menu_home" /></a> |
			<a  href="<s:url value="/shop/"/>
				<s:property value="shopCode" />/girls"><s:text name="global.shop_menu_girls" /></a> |
			<a href="<s:url value="/shop/"/>
				<s:property value="shopCode" />/newface"><s:text name="global.shop_menu_newface" /></a> |
			<a href="<s:url value="/shop/"/>
				<s:property value="shopCode" />/ranking"><s:text name="global.shop_menu_ranking" /></a> |
			<a href="<s:url value="/shop/"/>
				<s:property value="shopCode" />/todayworking"><s:text name="global.shop_menu_schedule" /></a> |
			<a href="<s:url value="/shop/"/>
				<s:property value="shopCode" />/system"><s:text name="global.shop_menu_system" /></a> |
			<a href="<s:url value="/shop/"/>
				<s:property value="shopCode" />/map"><s:text name="global.shop_menu_map" /></a>
		</p></div>
		<p class="copy">COPYRIGHT(C) ALL RIGHTS RESERVED.</p>
	</div>
</div>