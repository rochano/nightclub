<%@ taglib prefix="s" uri="/struts-tags" %>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/admin"/>"><i class="home icon"></i>Home</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('shop')}">active</s:if>" href="<s:url value="/admin/shop"/>"><i class="building icon"></i>Shop</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('zone')}">active</s:if>" href="<s:url value="/admin/zone"/>"><i class="compass icon"></i>Zone</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('category')}">active</s:if>" href="<s:url value="/admin/category"/>"><i class="book icon"></i>Category</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('news')}">active</s:if>" href="<s:url value="/admin/news"/>"><i class="newspaper icon"></i>News</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('ads')}">active</s:if>" href="<s:url value="/admin/ads"/>"><i class="announcement icon"></i>Ads</a>
	