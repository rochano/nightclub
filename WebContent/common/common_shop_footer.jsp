<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="footer" class="ui grid centered segment inverted">
	<div  class="one column center aligned ">
		<div><p>
			<a href="<s:url value="/shop/"/>
				<s:property value="shopInfoId" />"><s:text name="global.shop_menu_home" /></a> |
			<a  href="<s:url value="/shop/"/>
				<s:property value="shopInfoId" />/girls"><s:text name="global.shop_menu_girls" /></a> |
			<a href="<s:url value="/shop/"/>
				<s:property value="shopInfoId" />/system"><s:text name="global.shop_menu_system" /></a>
		</p></div>
		<p class="copy">COPYRIGHT(C) ALL RIGHTS RESERVED.</p>
	</div>
</div>