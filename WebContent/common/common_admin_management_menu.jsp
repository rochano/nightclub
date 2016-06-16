<%@ taglib prefix="s" uri="/struts-tags" %>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/admin"/>"><span>Home</span></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('shop')}">active</s:if>" href="<s:url value="/admin/shop"/>"><span>Shop</span></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('zone')}">active</s:if>" href="<s:url value="/admin/zone"/>"><span>Zone</span></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('category')}">active</s:if>" href="<s:url value="/admin/category"/>"><span>Category</span></a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('news')}">active</s:if>" href="<s:url value="/admin/news"/>"><span>News</span></a>
