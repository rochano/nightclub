<%@ taglib prefix="s" uri="/struts-tags" %>
<style type="text/css">
	.ui.rail .ui.sticky .ui.segment {background-color: #1b1c1d; color: #FFFFFF;}
	.ui.rail .ui.segment {background-color: transparent;}
</style>
<script type="text/javascript">
$(document)
.ready(function() {
	$('.ui.sticky')
	  .sticky({
	    context: '#container'
	  })
	;
});
</script>
<div class="ui attached right rail">
	<div class="ui segment" >
		<div class="ui sticky">
			<div class="ui compact segment">
				<h4 class="ui top header inverted centered">
					<s:text name="global.statistic_header" />
				</h4>
				<div class="ui divider"></div>
				<s:if test="getText('display.statistic.today_view') == 1">
					<div>
						<i class="user icon blue"></i>
						<div class="ui black label">
							<s:text name="global.statistic_today_view" /> : 
							<div class="detail">
								<s:text name="format.integer">
									<s:param name="value" value="statisticModel.todayView"/>
								</s:text>
							</div>
						</div>
					</div>
				</s:if>
				<s:if test="getText('display.statistic.total_view') == 1">
					<div>
						<i class="area chart icon green"></i>
						<div class="ui black label">
							<s:text name="global.statistic_total_view" /> : 
							<div class="detail">
								<s:text name="format.integer">
									<s:param name="value" value="statisticModel.totalView"/>
								</s:text>
							</div>
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