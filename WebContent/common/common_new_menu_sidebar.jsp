<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page pageEncoding="UTF-8"%>
<div class="ui vertical top large inverted sidebar menu">
  	<a class="ui toc item title">
		<i class="cancel icon"></i>
		&nbsp;
	</a>
	<div class="ui inverted accordion">
		<a href="<s:url value="/"/>" class="ui item"><s:text name="global.main_menu_home" /></a>
		<div class="ui item title">
			<i class="dropdown icon"></i>
			<s:text name="global.main_menu_service_shop" />
		</div>
		<div class="ui item content">
			<div class="accordion">
				<s:if test="#request.locale.language=='jp'">
					<s:iterator value="categoryInfos">
						<s:url var="url" action="%{#base_url + categoryInfoId}" />
						<a class="item" href="<s:property value="%{url}" />">
								<s:property value="categoryNameJp" /></a>
					</s:iterator>
				</s:if>
				<s:else>
					<s:iterator value="categoryInfos">
						<s:url var="url" action="%{#base_url + categoryInfoId}" />
						<a class="item" href="<s:property value="%{url}" />">
								<s:property value="categoryNameEn" /></a>
					</s:iterator>
				</s:else>
			</div>
		</div>
		<a href="<s:url value="/agents"/>" class="ui item"><s:text name="global.main_menu_agents" /></a>
		<a href="<s:url value="/freeagents"/>" class="ui item"><s:text name="global.main_menu_free_agents" /></a>
		<a href="<s:url value="/engirls"/>" class="ui item"><s:text name="global.main_menu_en_girls" /></a>
		<a href="<s:url value="/search"/>" class="ui item"><s:text name="global.main_menu_search" /></a>
		<a href="<s:url value="/contact"/>" class="ui item"><s:text name="global.main_menu_contact" /></a>
		<a href="<s:url value="/howtouse"/>" class="ui item"><s:text name="global.main_menu_how_to_use" /></a>
		<a href="<s:url value="/signup"/>" class="ui item"><s:text name="global.main_menu_register" /></a>
		<a href="<s:url value="/login"/>" class="ui item"><s:text name="global.main_menu_login" /></a>
	</div>
</div>
