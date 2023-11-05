<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page pageEncoding="UTF-8"%>
  <div class="ui centered grid">
	  <div class="center aligned column">
	  	<div class="inform">
	  		<s:if test="hasActionMessages()">
				<div class="ui success message green">
					<i class="close icon"></i>
					<div class="header">
						<s:actionmessage cssClass="list" />
					</div>
				</div>
			</s:if>
	  		<s:if test="homeInfo.inform != ''">
	  			<s:text name="homeInfo.inform" />
	  		</s:if>
	  	</div>
	  	<div class="ui menu inverted stackable toc left aligned">
	  		<a class="toc item"><s:text name="global.main_menu" /></a>
	  	</div>
		<div class="ui menu inverted stackable">
			<%-- <a href="<s:url value="/"/>" class="ui item"><s:text name="global.main_menu_home" /></a>
			<div class="ui dropdown item">
				<s:text name="global.main_menu_service_shop" />
				<i class="dropdown icon"></i>
				<div class="menu">
					<s:if test="#request.locale.language=='th'">
						<s:iterator value="categoryInfos">
							<s:url var="url" action="%{#base_url + categoryInfoId}" />
							<a class="item" href="<s:property value="%{url}" />">
									<s:property value="categoryNameEn" /></a>
						</s:iterator>
					</s:if>
					<s:else>
						<s:iterator value="categoryInfos">
							<s:url var="url" action="%{#base_url + categoryInfoId}" />
							<a class="item" href="<s:property value="%{url}" />">
									<s:property value="categoryNameJp" /></a>
						</s:iterator>
					</s:else>
				</div>
			</div> --%>
			<%-- <a href="<s:url value="/agents"/>" class="ui item"><s:text name="global.main_menu_agents" /></a>
			<a href="<s:url value="/freeagents"/>" class="ui item"><s:text name="global.main_menu_free_agents" /></a>
			<a href="<s:url value="/engirls"/>" class="ui item"><s:text name="global.main_menu_en_girls" /></a> --%>
			<a href="<s:url value="/howtouse"/>" class="ui item"><s:text name="global.main_menu_how_to_use" /></a>
			<a href="<s:url value="/signup"/>" class="ui item"><s:text name="global.main_menu_register" /></a>
			<a href="<s:url value="/login"/>" class="ui item"><s:text name="global.main_menu_login" /></a>
		</div>
		<div class="ui centered grid menu-slide-image">
	  		<div class="center aligned sixteen wide column slide-image">
			  	<div class="single-item">
			  		<s:iterator value="homeSlideImages" status="status">
			  			<div><img class="ui image fluid centered" src="<s:property value="slideImg" />" /></div>
			  		</s:iterator>
				</div>
			</div>
		</div>
		<%-- <div class="ui menu inverted stackable">
			<a href="<s:url value="/search"/>" class="ui item"><s:text name="global.main_menu_search" /></a>
			<a href="<s:url value="/contact"/>" class="ui item"><s:text name="global.main_menu_contact" /></a>
			<a href="<s:url value="/howtouse"/>" class="ui item"><s:text name="global.main_menu_how_to_use" /></a>
			<a href="<s:url value="/signup"/>" class="ui item"><s:text name="global.main_menu_register" /></a>
			<a href="<s:url value="/login"/>" class="ui item"><s:text name="global.main_menu_login" /></a>
		</div> --%>
		<br />
	  </div>
  </div>