<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style type="text/css">
	.ui.rail .ui.sticky .ui.segment {background-color: #1b1c1d; color: #FFFFFF;}
	.ui.rail .ui.segment {background-color: transparent;}
	#statistic .ui.label>.detail {
		width: 20px;
		text-align: right;
	}
	@media only screen and (max-width: 767px) {
		/* #statistic {display:none;} */
		#container {width: 100% !important;}
		.ui.grid .ui.stackable.grid {
			margin-left: auto !important; margin-right: auto !important;}
	}
	.ui.menu .header.item.link a {
		font-size: 18px;
	}
</style>
<%-- <script type="text/javascript">
$(document)
.ready(function() {
	$('.ui.sticky')
	  .sticky({
	    context: '#container'
	  })
	;
});
</script> --%>
<div class="ui inverted menu fixed">
	<div class="menu">
		<div class="header item link">
			<a href="<s:url value="/"/>">THAINIGHTNAVI.COM</a>
		</div>
	</div>
	<div class="right menu">
		<div class="ui dropdown item">
			<s:if test="agentInfo != null">
			สวัสดี, คุณ<s:property value="agentInfo.agentName" />
			</s:if>
			<s:elseif test="freeAgentGirlInfo != null">
			สวัสดี, คุณ<s:property value="freeAgentGirlInfo.nickName" />
			</s:elseif>
			<s:elseif test="clientInfo != null">
			สวัสดี, คุณ<s:property value="clientInfo.firstName" /> <s:property value="clientInfo.lastName" />
			</s:elseif>
			<s:elseif test="enGirlInfo != null">
			สวัสดี, คุณ<s:property value="enGirlInfo.nickName" />
			</s:elseif>
			<s:else>
			<div class="ui label blue">
				<i class="user icon"></i>
				<s:text name="format.integer">
					<s:param name="value" value="statisticModel.todayView"/>
				</s:text>
		  	</div>
	  		<div class="ui label green">
				<i class="area chart icon"></i> 
				<s:text name="format.integer">
					<s:param name="value" value="statisticModel.totalView"/>
				</s:text>
		  	</div>
			</s:else>
		  	<i class="dropdown icon"></i>
			<div class="menu">
				<s:if test="agentInfo != null">
					<a class="ui tiny item" href="<s:url value="/management_agent/agentinfo"/>">
						<i class="setting icon teal"></i>
						Agent Info
					</a>
					<a class="ui tiny item" href="<s:url value="/logout"/>">
						<i class="sign out icon"></i>
						Logout
					</a>
					<div class="ui divider"></div>
				</s:if>
				<s:elseif test="freeAgentGirlInfo != null">
					<a class="ui tiny item" href="<s:url value="/management_free_agent/information"/>">
						<i class="setting icon teal"></i>
						Information
					</a>
					<a class="ui tiny item" href="<s:url value="/logout"/>">
						<i class="sign out icon"></i>
						Logout
					</a>
					<div class="ui divider"></div>
				</s:elseif>
				<s:elseif test="clientInfo != null">
					<a class="ui tiny item" href="<s:url value="/management_client/information"/>">
						<i class="setting icon teal"></i>
						Information
					</a>
					<a class="ui tiny item" href="<s:url value="/favourite"/>">
						<i class="setting icon red heart"></i>
						Favourite
					</a>
					<a class="ui tiny item" href="<s:url value="/logout"/>">
						<i class="sign out icon"></i>
						Logout
					</a>
					<div class="ui divider"></div>
				</s:elseif>
				<s:elseif test="enGirlInfo != null">
					<a class="ui tiny item" href="<s:url value="/management_en_girl/information"/>">
						<i class="setting icon teal"></i>
						Information
					</a>
					<a class="ui tiny item" href="<s:url value="/logout"/>">
						<i class="sign out icon"></i>
						Logout
					</a>
					<div class="ui divider"></div>
				</s:elseif>
				<div class="item">
					<div class="ui one statistics orange inverted tiny">
						<h4 class="ui top header inverted centered">
							<s:text name="global.statistic_header" />
						</h4>
						<s:if test="getText('display.statistic.today_view') == 1">
							<div class="statistic">
								<div class="value">
									<i class="user icon blue"></i>
									<s:text name="format.integer">
										<s:param name="value" value="statisticModel.todayView"/>
									</s:text>
								</div>
								<div class="label">
									<s:text name="global.statistic_today_view" />
								</div>
							</div>
						</s:if>
						<s:if test="getText('display.statistic.total_view') == 1">
							<div class="statistic yellow">
								<div class="value">
									<i class="area chart icon green"></i>
									<s:text name="format.integer">
										<s:param name="value" value="statisticModel.totalView"/>
									</s:text>
								</div>
								<div class="label yellow">
									<s:text name="global.statistic_total_view" />
								</div>
							</div>
						</s:if>
						<s:if test="getText('display.statistic.hits_view') == 1">
							<div class="statistic olive">
								<div class="value">
									<i class="user icon olive"></i>
									<s:text name="format.integer">
										<s:param name="value" value="statisticModel.hitsView"/>
									</s:text>
								</div>
								<div class="label">
									Hits Today
								</div>
							</div>
						</s:if>
						<s:if test="getText('display.statistic.total_hits_view') == 1">
							<div class="statistic">
								<div class="value">
									<i class="bar chart icon orange"></i>
									<s:text name="format.integer">
										<s:param name="value" value="statisticModel.totalHitsView"/>
									</s:text>
								</div>
								<div class="label">
									Total Hits
								</div>
							</div>
						</s:if>
						<s:if test="getText('display.statistic.online_view') == 1">
							<div class="statistic teal">
								<div class="value">
									<i class="user icon teal"></i>
									<s:text name="format.integer">
										<s:param name="value" value="statisticModel.onlineView"/>
									</s:text>
								</div>
								<div class="label">
									Who's Online
								</div>
							</div>
						</s:if>
						<s:if test="getText('display.statistic.ip_access_dt') == 1">
							<div class="statistic">
								<div class="value">
									<s:param name="value" value="statisticModel.ipAddress"/>
								</div>
								<div class="label">
									Your IP
								</div>
							</div>
							<div class="statistic">
								<div class="value">
									<s:param name="value" value="statisticModel.accessDt"/>
								</div>
								<div class="label">
									Server Time
								</div>
							</div>
						</s:if>
					</div>
				</div>
				<div class="ui divider"></div>
				<a class="ui tiny item" href="<s:url value="?request_cookie_locale=jp"/>">
					<i class="jp flag"></i>
					JP
				</a>
				<a class="ui tiny item" href="<s:url value="?request_cookie_locale=th"/>">
					<i class="th flag"></i>
					TH
				</a>
			</div>
	  	</div>
	</div>
</div>