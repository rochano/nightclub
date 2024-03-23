<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="%{adsInfos.size > 0 && adsInfos[0].active == 'true'}">
	<s:if test="%{adsInfos[0].AdsImg1 != ''}">
		<div class="ui centered leaderboard test ad active" data-text="" id="adv1">
			<s:if test="adminMode == 'true'">
				<div class="ui green label" data-variation="tiny">
					<i class="icon">A1 - Small screen only (960 x 90)</i>
				</div>
			</s:if>
			<s:if test="%{adsInfos[0].customUrl != ''}">
				<a href="<s:property value="adsInfos[0].customUrl" />" target="_blank">
					<img src="<s:property value="adsInfos[0].AdsImg1" />">
				</a>
			</s:if>
			<s:else>
				<img src="<s:property value="adsInfos[0].AdsImg1" />">
			</s:else>
		</div>
	</s:if>
</s:if>
<s:else>
	<div class="ui centered leaderboard test ad" data-text="ADVERTISEMENT #1" id="adv1">
		<s:if test="adminMode == 'true'">
			<div class="ui green label" data-variation="tiny">
				<i class="icon">A1 - Small screen only (960 x 90)</i>
			</div>
		</s:if>
	</div>
</s:else>

<s:if test="%{adsInfos.size > 1 && adsInfos[1].active == 'true'}">
	<s:if test="%{adsInfos[1].AdsImg1 != ''}">
		<div class="ui centered leaderboard test ad active" data-text="" id="adv2">
			<s:if test="adminMode == 'true'">
				<div class="ui green label" data-variation="tiny">
					<i class="icon">A2 - Small screen only (960 x 90)</i>
				</div>
			</s:if>
			<s:if test="%{adsInfos[1].customUrl != ''}">
				<a href="<s:property value="adsInfos[1].customUrl" />" target="_blank">
					<img src="<s:property value="adsInfos[1].AdsImg1" />">
				</a>
			</s:if>
			<s:else>
				<img src="<s:property value="adsInfos[1].AdsImg1" />">
			</s:else>
		</div>
	</s:if>
</s:if>
<s:else>
	<div class="ui centered leaderboard test ad" data-text="ADVERTISEMENT #2" id="adv2">
		<s:if test="adminMode == 'true'">
			<div class="ui green label" data-variation="tiny">
				<i class="icon">A2 - Small screen only (960 x 90)</i>
			</div>
		</s:if>
	</div>
</s:else>

<s:if test="%{adsInfos.size > 2 && adsInfos[2].active == 'true'}">
	<s:if test="%{adsInfos[2].AdsImg1 != ''}">
		<div class="ui centered leaderboard test ad active" data-text="">
			<s:if test="adminMode == 'true'">
				<div class="ui green label" data-variation="tiny">
					<i class="icon">A3 (960 x 90)</i>
				</div>
			</s:if>
			<s:if test="%{adsInfos[2].customUrl != ''}">
				<a href="<s:property value="adsInfos[2].customUrl" />" target="_blank">
					<img src="<s:property value="adsInfos[2].AdsImg1" />">
				</a>
			</s:if>
			<s:else>
				<img src="<s:property value="adsInfos[2].AdsImg1" />">
			</s:else>
		</div>
	</s:if>
</s:if>
<s:else>
	<div class="ui centered leaderboard test ad" data-text="ADVERTISEMENT #3">
		<s:if test="adminMode == 'true'">
			<div class="ui green label" data-variation="tiny">
				<i class="icon">A3 (960 x 90)</i>
			</div>
		</s:if>
	</div>
</s:else>

<s:if test="%{adsInfos.size > 3 && adsInfos[3].active == 'true'}">
	<s:if test="%{adsInfos[3].AdsImg1 != ''}">
		<div class="ui centered leaderboard test ad active" data-text="">
			<s:if test="adminMode == 'true'">
				<div class="ui green label" data-variation="tiny">
					<i class="icon">A4 (960 x 90)</i>
				</div>
			</s:if>
			<s:if test="%{adsInfos[1].customUrl != ''}">
				<a href="<s:property value="adsInfos[3].customUrl" />" target="_blank">
					<img src="<s:property value="adsInfos[3].AdsImg1" />">
				</a>
			</s:if>
			<s:else>
				<img src="<s:property value="adsInfos[3].AdsImg1" />">
			</s:else>
		</div>
	</s:if>
</s:if>
<s:else>
	<div class="ui centered leaderboard test ad" data-text="ADVERTISEMENT #4">
		<s:if test="adminMode == 'true'">
			<div class="ui green label" data-variation="tiny">
				<i class="icon">A4 (960 x 90)</i>
			</div>
		</s:if>
	</div>
</s:else>

<s:if test="%{adsInfos.size > 4 && adsInfos[4].active == 'true'}">
	<s:if test="%{adsInfos[4].AdsImg1 != ''}">
		<div class="ui centered leaderboard test ad active" data-text="">
			<s:if test="adminMode == 'true'">
				<div class="ui green label" data-variation="tiny">
					<i class="icon">A5 (960 x 90)</i>
				</div>
			</s:if>
			<s:if test="%{adsInfos[4].customUrl != ''}">
				<a href="<s:property value="adsInfos[4].customUrl" />" target="_blank">
					<img src="<s:property value="adsInfos[4].AdsImg1" />">
				</a>
			</s:if>
			<s:else>
				<img src="<s:property value="adsInfos[4].AdsImg1" />">
			</s:else>
		</div>
	</s:if>
</s:if>
<s:else>
	<div class="ui centered leaderboard test ad" data-text="ADVERTISEMENT #5">
		<s:if test="adminMode == 'true'">
			<div class="ui green label" data-variation="tiny">
				<i class="icon">A5 (960 x 90)</i>
			</div>
		</s:if>
	</div>
</s:else>