<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page pageEncoding="UTF-8"%>
<div class="ui vertical top large inverted sidebar menu">
  	<a class="ui toc item title">
		<i class="cancel icon"></i>
		&nbsp;
	</a>
	<div class="ui inverted accordion">
	<s:iterator var="counter" begin="0" end="7" >
		<s:if test="categoryInfoArray[top].hideZoneFlag != 'true' && categoryInfoArray[top].categoryZones.size() > 0">
			<div class="ui item title">
				<i class="dropdown icon"></i>
				<s:property value="categoryInfoArray[top].categoryNameJp" />
			</div>
			<div class="content ui item">
				<s:iterator value="categoryInfoArray[top].categoryZones">
					<s:set name="categoryInfo" value="primaryKey.categoryInfo" />
					<s:set name="zoneInfo" value="primaryKey.zoneInfo" />
					<s:if test="zoneInfo.chkCustomUrl == 'true'">
						<s:url var="url" value="%{'http://' + zoneInfo.customUrl}" />
					</s:if>
					<s:else>
						<s:url var="url" action="%{#base_url + categoryInfo.categoryCode + '/' + zoneInfo.zoneCode}" />
					</s:else>
					<a class="item" href="<s:property value="%{url}" />">
						<s:property value="zoneInfo.zoneNameJp" /></a>
				</s:iterator>
			</div>	
		</s:if>	
		<%-- <s:else>
			<div class="ui item">
				<s:property value="categoryInfoArray[top].categoryNameJp" />
			</div>
		</s:else>	 --%>
		
	</s:iterator>
		<a href="<s:url value="/shop/login"/>" class="ui item">営業中</a>
	</div>
</div>
