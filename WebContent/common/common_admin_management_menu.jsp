<%@ taglib prefix="s" uri="/struts-tags" %>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('index')}">active</s:if>" href="<s:url value="/admin"/>"><i class="home icon"></i>Home</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('shop')}">active</s:if>" href="<s:url value="/admin/shop"/>"><i class="building icon"></i>Shop</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('agent')}">active</s:if>" href="<s:url value="/admin/agent"/>"><i class="users icon"></i>Agent</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('freeagent')}">active</s:if>" href="<s:url value="/admin/freeagent"/>"><i class="heart icon"></i>Free Agent</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('client')}">active</s:if>" href="<s:url value="/admin/client"/>"><i class="user icon"></i>Client</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('engirl')}">active</s:if>" href="<s:url value="/admin/engirl"/>"><i class="smile icon"></i>Entertain Girl</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('zone')}">active</s:if>" href="<s:url value="/admin/zone"/>"><i class="compass icon"></i>Location</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('category')}">active</s:if>" href="<s:url value="/admin/category"/>"><i class="book icon"></i>Category</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('ads')}">active</s:if>" href="<s:url value="/admin/ads"/>"><i class="announcement icon"></i>Ads</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girlservice')}">active</s:if>" href="<s:url value="/admin/girlservice"/>"><i class="tasks icon"></i>Girl Service</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('girlsetting')}">active</s:if>" href="<s:url value="/admin/girlsetting"/>"><i class="sliders horizontal icon"></i>Girl Setting</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('howtouse')}">active</s:if>" href="<s:url value="/admin/howtouse"/>"><i class="file outline icon"></i>How to use</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('contact')}">active</s:if>" href="<s:url value="/admin/contact"/>"><i class="phone icon"></i>Contact</a>
	<a class="item <s:if test="%{menu.equalsIgnoreCase('skin')}">active</s:if>" href="<s:url value="/admin/skin"/>"><i class="adjust icon"></i>Skin</a>