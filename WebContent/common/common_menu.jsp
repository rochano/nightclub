<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page pageEncoding="UTF-8"%>
  <div class="ui centered grid">
	  <div class="center aligned column">
	  	<div class="ui menu inverted stackable toc left aligned">
	  		<a class="toc item"><i class="sidebar icon"></i></a>
	  	</div>
		<div class="ui menu inverted stackable">
		  <div class="ui dropdown item">
			<s:property value="categoryInfoArray[0].categoryNameJp" />
			<s:if test="categoryInfoArray[0].hideZoneFlag != 'true'">
				<s:if test="categoryInfoArray[0].categoryZones.size() > 0">
					<i class="dropdown icon"></i>
					<div class="menu">
						<s:iterator value="categoryInfoArray[0].categoryZones">
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
			</s:if>
		  </div>
		  <div class="ui dropdown item">
			<s:property value="categoryInfoArray[1].categoryNameJp" />
			<s:if test="categoryInfoArray[1].hideZoneFlag != 'true'">
				<s:if test="categoryInfoArray[1].categoryZones.size() > 0">
					<i class="dropdown icon"></i>
					<div class="menu">
						<s:iterator value="categoryInfoArray[1].categoryZones">
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
			</s:if>
		  </div>
		  <div class="ui dropdown item">
			<s:property value="categoryInfoArray[2].categoryNameJp" />
			<s:if test="categoryInfoArray[2].hideZoneFlag != 'true'">
				<s:if test="categoryInfoArray[2].categoryZones.size() > 0">
					<i class="dropdown icon"></i>
					<div class="menu">
					  	<s:iterator value="categoryInfoArray[2].categoryZones">
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
			</s:if>
		  </div>
		  <div class="ui dropdown item">
			<s:property value="categoryInfoArray[3].categoryNameJp" />
			<s:if test="categoryInfoArray[3].hideZoneFlag != 'true'">
				<s:if test="categoryInfoArray[3].categoryZones.size() > 0">
					<i class="dropdown icon"></i>
					<div class="menu">
					  	<s:iterator value="categoryInfoArray[3].categoryZones">
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
			</s:if>
		  </div>
		  <div class="ui dropdown item">
			<s:property value="categoryInfoArray[4].categoryNameJp" />
			<s:if test="categoryInfoArray[4].hideZoneFlag != 'true'">
				<s:if test="categoryInfoArray[4].categoryZones.size() > 0">
					<i class="dropdown icon"></i>
					<div class="menu">
					  	<s:iterator value="categoryInfoArray[4].categoryZones">
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
			</s:if>
		  </div>
		</div>
		<div class="ui centered grid">
	  		<div class="center aligned sixteen wide column slide-image">
			  	<div class="single-item">
				    <div><img class="ui image fluid centered" src="<s:url value="/assets/images/slideimage/cowboy-612x246.jpg" />" /></div>
				    <div><img class="ui image fluid centered" src="<s:url value="/assets/images/slideimage/patpong2-1-612x246.jpg" />" /></div>
				    <div><img class="ui image fluid centered" src="<s:url value="/assets/images/slideimage/Thaniya-612x246.jpg" />" /></div>
				    <div><img class="ui image fluid centered" src="<s:url value="/assets/images/slideimage/nanaplaza1-612x246.jpg" />" /></div>
			  	</div>
			</div>
	  	</div>
	  	<div class="ui menu inverted stackable">
		  <div class="ui dropdown item">
			<s:property value="categoryInfoArray[5].categoryNameJp" />
			<s:if test="categoryInfoArray[5].hideZoneFlag != 'true'">
				<s:if test="categoryInfoArray[5].categoryZones.size() > 0">
					<i class="dropdown icon"></i>
					<div class="menu">
					  	<s:iterator value="categoryInfoArray[5].categoryZones">
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
			</s:if>
		  </div>
		  <div class="ui dropdown item">
			<s:property value="categoryInfoArray[6].categoryNameJp" />
			<s:if test="categoryInfoArray[6].hideZoneFlag != 'true'">
				<s:if test="categoryInfoArray[6].categoryZones.size() > 0">
					<i class="dropdown icon"></i>
					<div class="menu">
					  	<s:iterator value="categoryInfoArray[6].categoryZones">
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
			</s:if>
		  </div>
		  <div class="ui dropdown item">
			<s:property value="categoryInfoArray[7].categoryNameJp" />
			<s:if test="categoryInfoArray[7].hideZoneFlag != 'true'">
				<s:if test="categoryInfoArray[7].categoryZones.size() > 0">
					<i class="dropdown icon"></i>
					<div class="menu">
						<s:iterator value="categoryInfoArray[7].categoryZones">
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
			</s:if>
		  </div>
		  <div class="ui dropdown item">
			<s:property value="categoryInfoArray[8].categoryNameJp" />
			<s:if test="categoryInfoArray[8].hideZoneFlag != 'true'">
				<s:if test="categoryInfoArray[8].categoryZones.size() > 0">
					<i class="dropdown icon"></i>
					<div class="menu">
					  	<s:iterator value="categoryInfoArray[8].categoryZones">
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
			</s:if>
		  </div>
		  <a href="<s:url value="/shop/login"/>" class="ui item">営業中</a>
		</div>
	  </div>
  </div>