<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page pageEncoding="UTF-8"%>
<div class="ui vertical inverted sidebar menu">

<s:iterator var="counter" begin="0" end="7" >
	<s:if test="categoryInfoArray[top].hideZoneFlag != 'true' && categoryInfoArray[top].categoryZones.size() > 0">
		<div class="ui dropdown item title">
			<s:property value="categoryInfoArray[top].categoryNameJp" />
			<i class="caret down icon"></i>
		</div>
		<div class="content ui item">
			<div class="ui secondary vertical menu">
				<s:iterator value="categoryInfoArray[top].categoryZones" >
					<s:set name="categoryInfo" value="primaryKey.categoryInfo" />
					<s:set name="zoneInfo" value="primaryKey.zoneInfo" />
					<a class="item" href="<s:url value="/shoplist/" />
						<s:property value="categoryInfo.categoryCode" />/
						<s:property value="zoneInfo.zoneCode" />">
						<s:property value="zoneInfo.zoneNameJp" /></a>
				</s:iterator>
			</div>
		</div>	
	</s:if>	
	<s:else>
		<div class="ui item title">
			<s:property value="categoryInfoArray[top].categoryNameJp" />
		</div>
	</s:else>	
	
</s:iterator>
	<a href="<s:url value="/shop/login"/>" class="ui item">店管理</a>
</div>
