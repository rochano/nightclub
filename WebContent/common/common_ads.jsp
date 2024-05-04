<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="three wide column">
	<div class="ui sticky" style="left: 0 !important;">
		<s:if test="%{adsInfos.size > 0 && adsInfos[0].active == 'true'}">
			<s:if test="%{adsInfos[0].AdsImg2 != ''}">
				<div class="ui wide skyscraper centered test ad active" data-text="">
					<s:if test="adminMode == 'true'">
						<div class="ui green attached label" data-variation="tiny">
							<i class="icon">A1 (160 x 600)</i>
						</div>
					</s:if>
					<s:if test="%{adsInfos[0].customUrl != ''}">
						<a href="<s:property value="adsInfos[0].customUrl" />" target="_blank">
							<img src="<s:property value="adsInfos[0].AdsImg2" />">
						</a>
					</s:if>
					<s:else>
						<img src="<s:property value="adsInfos[0].AdsImg2" />">
					</s:else>
				</div>
			</s:if>
			<s:elseif test="adminMode == 'true'">
				<div class="ui wide skyscraper centered test ad active" data-text="">
					<div class="ui green attached label" data-variation="tiny">
						<i class="icon">A1 (160 x 600)</i>
					</div>
				</div>
			</s:elseif>
		</s:if>
		<s:else>
			<div class="ui wide skyscraper centered test ad" data-text="ADVERTISEMENT #1">
				<s:if test="adminMode == 'true'">
					<div class="ui green attached label" data-variation="tiny">
						<i class="icon">A1 (160 x 600)</i>
					</div>
				</s:if>
			</div>
		</s:else>
	</div>
</div>