<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="three wide column">
	<div class="ui sticky" style="left: 0 !important;">
		<s:if test="%{adsInfos.size > 0 && adsInfos[0].active == 'true'}">
			<s:if test="%{adsInfos[0].AdsImg2 != ''}">
				<div class="ui wide skyscraper centered test ad active" data-text="">
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
		</s:if>
		<s:else>
			<div class="ui wide skyscraper centered test ad" data-text="ADVERTISEMENT #1"></div>
		</s:else>
	</div>
</div>