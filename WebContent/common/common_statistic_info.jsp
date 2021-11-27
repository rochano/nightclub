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
		<div class="header item">
			THAINIGHTNAVI.COM
		</div>
	</div>
	<div class="right menu">
		<div class="ui dropdown item">
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
		  	<i class="dropdown icon"></i>
			<div class="menu">
				<div class="item">
					<div id="statistic" class="ui">
						<div class="ui" >
							<div class="ui">
								<div class="ui">
									<h4 class="ui top header inverted centered">
										<s:text name="global.statistic_header" />
									</h4>
									<div class="ui divider"></div>
									<s:if test="getText('display.statistic.today_view') == 1">
										<div>
											<div class="ui black label">
												<i class="user icon blue"></i>
												<s:text name="global.statistic_today_view" /> : 
												<div class="detail"><s:text name="format.integer"><s:param name="value" value="statisticModel.todayView"/></s:text></div>
											</div>
										</div>
									</s:if>
									<s:if test="getText('display.statistic.total_view') == 1">
										<div>
											<div class="ui black label">
												<i class="area chart icon green"></i>
												<s:text name="global.statistic_total_view" /> : 
												<div class="detail"><s:text name="format.integer"><s:param name="value" value="statisticModel.totalView"/></s:text></div>
											</div>
										</div>
									</s:if>
									<s:if test="getText('display.statistic.hits_view') == 1">
										<div>
											<i class="user icon olive"></i>
											<div class="ui black label">
												Hits Today : 
												<div class="detail">
													<s:text name="format.integer">
														<s:param name="value" value="statisticModel.hitsView"/>
													</s:text>
												</div>
											</div>
										</div>
									</s:if>
									<s:if test="getText('display.statistic.total_hits_view') == 1">
										<div>
											<i class="bar chart icon orange"></i>
											<div class="ui black label">
												Total Hits : 
												<div class="detail">
													<s:text name="format.integer">
														<s:param name="value" value="statisticModel.totalHitsView"/>
													</s:text>
												</div>
											</div>
										</div>
									</s:if>
									<s:if test="getText('display.statistic.online_view') == 1">
										<div>
											<i class="user icon teal"></i>
											<div class="ui black label">
												Who's Online : 
												<div class="detail">
													<s:text name="format.integer">
														<s:param name="value" value="statisticModel.onlineView"/>
													</s:text>
												</div>
											</div>
										</div>
									</s:if>
									<s:if test="getText('display.statistic.ip_access_dt') == 1">
										<div class="ui divider"></div>
										<div>
											<div class="ui black label">
												Your IP : 
												<div class="detail">
													<s:property value="statisticModel.ipAddress" />
												</div>
											</div>
										</div>
										<div>
											<div class="ui black label">
												Server Time : 
												<div class="detail">
													<s:property value="statisticModel.accessDt" />
												</div>
											</div>
										</div>
									</s:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	  	</div>
	</div>
</div>