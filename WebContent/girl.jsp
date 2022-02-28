<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>THAINIGHTNAVI.COM - <s:text name="global.main_menu_free_agents" /></title>
  <%@include file="/common/common_header.jsp" %>
  <!--- Example CSS -->
  <style>
  .ui.table th, .ui.table tr:nth-child(even) td{
  	font-size: 12px;
  	vertical-align: baseline;
    word-break: break-word;
  }
  .ui.table td {
  	padding: 0;
  }
  .ui.table td input[type=button], .ui.table td button {
  	margin: auto;
  }
  @media only screen and (max-width: 767px) {
  	.ui.table:not(.unstackable) tr:nth-child(odd) {
	  box-shadow: none !important;
    }
    .ui.stackable.grid > .column:not(.row) {
      padding-top: 10px!important;
      padding-bottom: 0px!important;
    }
    .ui.stackable.grid > .row > .wide.column,
    .ui.stackable.grid > .row > .column {
      padding:1em 0!important;
    }
  }
  .segment {
  	padding: 0;
  }
  .ui.table tbody tr:nth-child(4n), .ui.table tbody tr:nth-child(4n-1) {
	background-color: rgba(0,0,50,.02);
  }
  @media only screen and (max-width: 767px) {
  	#shoplist-wrapper {overflow: scroll;}
  }
  .ui.grid > .column:not(.row) {
    padding-top: 10px;
    padding-bottom: 0;
  }
  .ui.table, .ui.table tr th, .ui.table tr td {
  	border-width:1px 0px!important; 
  	vertical-align: text-top;
  }
  </style>
  <script type="text/javascript">
  $(document)
    .ready(function() {
    	<s:if test="clientInfo != null">
		$(".toggleFavourite").click(function() {
			var favouriteIcon = $(this).find("i");
			var girlInfoId = $(this).attr("data-girlInfoId");
			var favourite = 0;
			if (favouriteIcon.hasClass("outline")) {
				favouriteIcon.removeClass("outline");
				/* toggleFavourite.addClass("red") */
				favourite = 1;
			} else {
				favouriteIcon.removeClass("red");
				/* toggleFavourite.addClass("outline") */
			}
			favouriteIcon.removeClass("heart");
			favouriteIcon.addClass("spinner");
			$.getJSON("<s:url value="/ajax/toggleFavouriteJson/" />" + girlInfoId + "/" + favourite,
			function(jsonResponse) {
				favouriteIcon.removeClass("spinner");
				favouriteIcon.addClass("heart");
				if (jsonResponse.favourite === '1') {
					favouriteIcon.removeClass("outline");
					favouriteIcon.addClass("red")
				} else {
					favouriteIcon.removeClass("red");
					favouriteIcon.addClass("outline")
				}
  			});
		});
		</s:if>
		<s:else>
		$('.toggleFavourite')
			.popup({
				on: 'click'
			})
		;
		</s:else>
	});
  </script>
</head>
<body>
<!-- Sidebar Menu -->
<s:set name="base_url" value="%{''}" />
<%@include file="/common/common_new_menu_sidebar.jsp" %>
<div class="pusher">
	<div class="ui segment very basic">
		<div class="ui centered grid">
			<div class="eleven wide column container" id="container">
				<%@include file="/common/common_statistic_info.jsp" %>
				<%@include file="/common/common_new_menu.jsp" %>
  				<br/>
				<div class="ui breadcrumb segment attached inverted">
					<a class="section" href="<s:url value="/" />" >
						<s:text name="global.shop_menu_home" />
					</a>
					<i class="right chevron icon divider"></i>
					<s:if test="%{girlInfo.agentInfoId != null}">
					<a class="section" href="<s:url value="/agents" />" >
						<s:text name="global.main_menu_agents" />
					</a>
					<i class="right chevron icon divider"></i>
					<a class="section" href="<s:url value="/agents/%{girlInfo.agentInfoId}"/>" >
						<s:text name="girlInfo.agentInfo.agentName" />
					</a>
					</s:if>
					<s:elseif test="%{girlInfo instanceof com.nightclub.model.FreeAgentGirlInfo}" >
					<a class="section" href="<s:url value="/freeagents" />" >
						<s:text name="global.main_menu_free_agents" />
					</a>
					</s:elseif>
					<s:elseif test="%{girlInfo instanceof com.nightclub.model.EnGirlInfo}" >
					<a class="section" href="<s:url value="/engirls" />" >
						<s:text name="global.main_menu_en_girls" />
					</a>
					</s:elseif>
					<i class="right chevron icon divider"></i>
					<div class="active section"><s:property value="girlInfo.nickName" /></div>
				</div>

				<div class="center aligned column">
					<div class="ui centered attached segment soft">
						<div class="column one left aligned">
							<div class="ui middle aligned stackable grid container">
								<div class="row">
									<div class="seven wide column corner labeled">
										<a class="ui right corner label toggleFavourite link huge" 
												data-girlInfoId="<s:property value="girlInfoId" />"
												data-content="Please login first" data-variation="tiny">
											<i class="heart 
												<s:if test="girlFavourites.indexOf(girlInfoId) != -1">red</s:if>
												<s:else>outline</s:else>
												icon"></i>
										</a>
										<div class="center aligned sixteen wide column slide-image">
											<div class="single-item">
												<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic1" />" /></div>
												<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic2" />" /></div>
												<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic3" />" /></div>
												<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic4" />" /></div>
												<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic5" />" /></div>
										  	</div>
										</div>
									</div>
									<div class="seven wide right floated centered column">
										<table class="ui compact table unstackable">
											<tbody>
												<s:if test="girlInfo.agentInfo != null">
												<tr>
													<td class="right aligned"><p>Agent</p></td>
													<td class="center aligned one wide"><p>:</p></td>
													<td><p><s:property value="girlInfo.agentInfo.agentName" /></p></td>
												</tr>
												</s:if>
												<tr>
													<td class="six wide right aligned"><p>Nick Name</p></td>
													<td class="center aligned one wide"><p>:</p></td>
													<td><p><s:property value="girlInfo.nickName" /></p></td>
												</tr>
												<s:if test="%{girlInfo instanceof com.nightclub.model.EnGirlInfo}" >
													<tr>
														<td class="right aligned"><p>Gender</p></td>
														<td class="center aligned one wide"><p>:</p></td>
														<td>
															<p>
																<s:if test="girlInfo.gender == 'Straight'">
																	<s:text name="global.gender_straight" />
																</s:if>
																<s:else>
																	<s:text name="global.gender_transgender" />
																</s:else>
															</p>
														</td>
													</tr>
													<tr>
														<td class="right aligned"><p>Skin</p></td>
														<td class="center aligned one wide"><p>:</p></td>
														<td>
															<p>
																<s:if test="#request.locale.language=='jp'">
																	<s:property value="girlInfo.skinInfo.skinNameJp" />
																</s:if>
																<s:else>
																	<s:property value="girlInfo.skinInfo.skinNameEn" />
																</s:else>
															</p>
														</td>
													</tr>
												</s:if>
												<tr>
													<td class="right aligned"><p>Age</p></td>
													<td class="center aligned one wide"><p>:</p></td>
													<td><p><s:property value="girlInfo.age" /></p></td>
												</tr>
												<tr>
													<td class="right aligned"><p>H / W</p></td>
													<td class="center aligned one wide"><p>:</p></td>
													<td><p><s:property value="girlInfo.height" /> / <s:property value="girlInfo.weight" /></p></td>
												</tr>
												<tr>
													<td class="right aligned"><p>Body size</p></td>
													<td class="center aligned one wide"><p>:</p></td>
													<td>
														<p>
															B:<s:property value="girlInfo.bustSize" />
															W:<s:property value="girlInfo.waistSize" />
															H:<s:property value="girlInfo.hipSize" />
														</p>
													</td>
												</tr>
												<tr>
													<td class="right aligned"><p>Location</p></td>
													<td class="center aligned one wide"><p>:</p></td>
													<td>
														<p>
															<s:if test="#request.locale.language=='jp'">
																<s:text name="girlInfo.zoneInfo.zoneNameJp" />
															</s:if>
															<s:else>
																<s:text name="girlInfo.zoneInfo.zoneNameEn" />
															</s:else>
														</p>
													</td>
												</tr>
												<tr>
													<td class="right aligned"><p><s:text name="global.job" /></p></td>
													<td class="center aligned one wide"><p>:</p></td>
													<td>
														<p>
															<s:if test="girlInfo.type == 1"><s:text name="global.en_girl_type_1" /></s:if>
															<s:if test="girlInfo.type == 2"><s:text name="global.en_girl_type_2" /></s:if>
															<s:if test="girlInfo.type == 3"><s:text name="global.en_girl_type_3" /></s:if>
														</p>
													</td>
												</tr>
												<tr>
													<td class="right aligned"><p><s:text name="global.service_charge" /></p></td>
													<td class="center aligned one wide"><p>:</p></td>
													<td>
														<p>
															<s:text name="format.integer"><s:param name="value" value="girlInfo.price"/></s:text>
														</p>
													</td>
												</tr>
												<tr>
													<td class="right aligned"><p>Line</p></td>
													<td class="center aligned one wide"><p>:</p></td>
													<td>
														<s:if test="%{girlInfo.agentInfoId != null}">
														<p><s:property value="girlInfo.lineId" /></p>
														</s:if>
														<s:elseif test="%{clientInfo != null}">
														<p><s:property value="girlInfo.lineId" /></p>
														</s:elseif>
														<s:else>
														<p>[คลิกเพื่อดูข้อมูล]</p>
														<p>[<a href="<s:url value="/signup"/>">กรุณาสมัครสมาชิก</a> หรือ <a href="<s:url value="/login"/>">เข้าสู่ระบบ</a>]</p>
														</s:else>
													</td>
												</tr>
												<s:if test="%{girlInfo.agentInfoId != null}">
												<tr>
													<td colspan="3">
														<p class="centered" style="text-align: -webkit-center;">
															<a href="https://line.me/ti/p/~<s:property value="girlInfo.lineId" />">
																<img class="ui small image" src="https://scdn.line-apps.com/n/line_add_friends/btn/th.png">
															</a>
														</p>
													</td>
												</tr>
												</s:if>
												<s:elseif test="%{clientInfo != null}">
												<tr>
													<td colspan="3">
														<p class="centered" style="text-align: -webkit-center;">
															<a href="https://line.me/ti/p/~<s:property value="girlInfo.lineId" />">
																<img class="ui small image" src="https://scdn.line-apps.com/n/line_add_friends/btn/th.png">
															</a>
														</p>
													</td>
												</tr>
												</s:elseif>
											</tbody>
										</table>
										<h5 class="ui horizontal left aligned header">
											Description
										</h5>
										<s:text name="girlInfo.description"></s:text>
									</div>
								</div>
								<s:if test="%{girlInfo instanceof com.nightclub.model.EnGirlInfo}" >
								</s:if>
								<s:else>
								<div class="row">
									<div class="four wide column">
										<h5 class="ui header left aligned inverted">
											Gender
										</h5>
										<div class="ui grid column">
											<div class="column left aligned">
												<i class="ui check square icon green"></i>
												<s:if test="girlInfo.gender == 'Straight'">
													<s:text name="global.gender_straight" />
												</s:if>
												<s:else>
													<s:text name="global.gender_transgender" />
												</s:else>
											</div>
										</div>
									</div>
									<div class="eleven wide column">
										<h5 class="ui header left aligned inverted">
											Service
										</h5>
										<div class="ui grid doubling four column">
											<s:iterator value="girlServices" status="rowstatus">
												<div class="column left aligned">
													<i class="ui check square icon green"></i>
													<s:property value="primaryKey.girlServiceInfo.girlServiceName" />
												</div>
											</s:iterator>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="column">
										<div class="ui divider"></div>
										<h5 class="ui header left aligned inverted">
											Price List
										</h5>
										<table class="ui celled center aligned celled table compact unstackable orange">
											<thead class="center aligned">
												<tr>
													<th>เวลา</th>
													<th>เรทราคา</th>
													<th>จำนวนน้ำ</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="girlInfo.chk40Mins == 'true'">
												<tr>
													<td class="center aligned">
														<label for="girlInfo_chk40Mins">40 นาที</label>
													</td>
													<td class="center aligned">
														<s:text name="format.integer"><s:param name="value" value="girlInfo.price40Mins"/></s:text>
													</td>
													<td class="center aligned">
														1
													</td>
												</tr>
												</s:if>
												<s:if test="girlInfo.chk60Mins == 'true'">
												<tr>
													<td class="center aligned">
														<label for="girlInfo_chk60Mins">60 นาที</label>
													</td>
													<td class="center aligned">
														<s:text name="format.integer"><s:param name="value" value="girlInfo.price60Mins"/></s:text>
													</td>
													<td class="center aligned">
														1
													</td>
												</tr>
												</s:if>
												<s:if test="girlInfo.chk90Mins == 'true'">
												<tr>
													<td class="center aligned">
														<label for="girlInfo_chk90Mins">90 นาที</label>
													</td>
													<td class="center aligned">
														<s:text name="format.integer"><s:param name="value" value="girlInfo.price90Mins"/></s:text>
													</td>
													<td class="center aligned">
														2
													</td>
												</tr>
												</s:if>
												<s:if test="girlInfo.chk120Mins == 'true'">
												<tr>
													<td class="center aligned">
														<label for="girlInfo_chk120Mins">120 นาที</label>
													</td>
													<td class="center aligned">
														<s:text name="format.integer"><s:param name="value" value="girlInfo.price120Mins"/></s:text>
													</td>
													<td class="center aligned">
														2
													</td>
												</tr>
												</s:if>
												<s:if test="girlInfo.chk6Hrs == 'true'">
												<tr>
													<td class="center aligned">
														<label for="girlInfo_chk6Hrs">ค้างคืน(6ชม.)</label>
													</td>
													<td class="center aligned">
														<s:text name="format.integer"><s:param name="value" value="girlInfo.price6Hrs"/></s:text>
													</td>
													<td class="center aligned">
														3
													</td>
												</tr>
												</s:if>
											</tbody>
										</table>
									</div>
								</div>
								</s:else>
							</div>
						</div>
					</div>
				</div>
			    
			</div>
		</div>
	</div>
	<%@include file="/common/common_footer.jsp" %>
</div>
</body>
</html>