<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>THAINIGHTNAVI.COM - <s:text
		name="global.main_menu_free_agents" /></title>
<%@include file="/common/common_header.jsp"%>
<!--- Example CSS -->
  <style>
  .ui.table th, .ui.table tr:nth-child(even) td{
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
  .ui.segment.clearing, #availableInfos {
    margin:0px;
  }
  #availableInfos {
    display: inline-grid;
  }
  #wrapper_calendar {
    margin-bottom:30px;
  }
  #mov1 {
    display: none;
  }
  #mov1.display {
    display: block;
  }
  .ui.rating .icon {
    color: #555;
  }
  .ui.leaderboard.ad {
  	height: 100%;
  	padding: 0;
  	width: 100%;
  }
  .centered form {
  	text-align: left;
  }
  </style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#infoForm').form({
			fields : {
				reserveInfo_email : {
					identifier : 'reserveInfo_email',
					rules : [ 
						{
							type   : 'empty',
						},
						{
							type : 'email',
						}
					]
				},
				reserveInfo_clientName : {
					identifier : 'reserveInfo_clientName',
					rules : [ 
						{
							type   : 'empty',
						}
					]
				}
			},
		});
		$("#reserveInfo_reserveDate").dateEntry({
			dateFormat : 'dmy/',
			spinnerImage : ''
		});
	});
</script>
</head>
<body>
	<!-- Sidebar Menu -->
	<s:set name="base_url" value="%{''}" />
	<%@include file="/common/common_new_menu_sidebar.jsp"%>
	<div class="pusher">
		<div class="ui segment very basic">
			<div class="ui centered grid">
				<div class="eleven wide column container" id="container">
					<%@include file="/common/common_statistic_info.jsp"%>
					<%@include file="/common/common_new_menu.jsp"%>
					<br />
					<div class="ui breadcrumb segment attached inverted">
						<a class="section" href="<s:url value="/" />"> <s:text
								name="global.shop_menu_home" />
						</a> <i class="right chevron icon divider"></i>
						<s:if test="%{girlInfo.agentInfoId != null}">
							<a class="section" href="<s:url value="/agents" />"> <s:text
									name="global.main_menu_agents" />
							</a>
						</s:if>
						<s:elseif
							test="%{girlInfo instanceof com.nightclub.model.FreeAgentGirlInfo}">
							<a class="section" href="<s:url value="/freeagents" />"> <s:text
									name="global.main_menu_free_agents" />
							</a>
						</s:elseif>
						<s:elseif
							test="%{girlInfo instanceof com.nightclub.model.EnGirlInfo}">
							<a class="section" href="<s:url value="/engirls" />"> <s:text
									name="global.main_menu_en_girls" />
							</a>
						</s:elseif>
						<i class="right chevron icon divider"></i> <a class="section"
							href="<s:url value="/girl/%{girlInfo.girlInfoId}" />" >
							<s:property value="girlInfo.nickName" />
						</a> <i class="right chevron icon divider"></i>
						<div class="active section">Booking</div>
					</div>

					<div class="center aligned column">
						<div class="ui centered attached segment soft">
							<div class="column one left aligned">
								<div class="ui aligned stackable grid container">
									<div class="row">
										<div class="seven wide column corner labeled">
											<div class="center aligned sixteen wide column slide-image">
												<div class="single-item">
													<s:if test="girlInfo.pic1 != null && girlInfo.pic1 != ''">
														<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic1" />" /></div>
													</s:if>
													<s:if test="girlInfo.pic2 != null && girlInfo.pic2 != ''">
														<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic2" />" /></div>
													</s:if>
													<s:if test="girlInfo.pic3 != null && girlInfo.pic3 != ''">
														<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic3" />" /></div>
													</s:if>
													<s:if test="girlInfo.pic4 != null && girlInfo.pic4 != ''">
														<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic4" />" /></div>
													</s:if>
													<s:if test="girlInfo.pic5 != null && girlInfo.pic5 != ''">
														<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic5" />" /></div>
													</s:if>
													<s:if test="(girlInfo.pic1 == null || girlInfo.pic1 == '') 
																&& (girlInfo.pic2 == null || girlInfo.pic2 == '') 
																&& (girlInfo.pic3 == null || girlInfo.pic3 == '') 
																&& (girlInfo.pic4 == null || girlInfo.pic4 == '') 
																&& (girlInfo.pic5 == null || girlInfo.pic5 == '')">
														<img class="image ui centered" src="<s:url value="/assets/images/wireframe/square-image.png" />">
													</s:if>
											  	</div>
											</div>
											<br />
											<div class="row">
												<div class="column">
													<table class="ui celled selectable center aligned celled table compact unstackable inverted ">
														<thead class="center aligned">
															<tr>
																<s:iterator value="workingDateList" status="status">
																	<th>
																		<s:date name="date" format="MMM.dd (E)" />
																	</th>
																</s:iterator>
															</tr>
														</thead>
														<tbody>
															<tr>
																<s:iterator value="workingDateList" status="status">
																	<td>
																		<s:if test="sunday == true" >
																			<s:if test="girlInfo.chkWorkSun == 'true'" >
																				<s:property value="girlInfo.workSunTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:if>
																		<s:elseif test="monday == true" >
																			<s:if test="girlInfo.chkWorkMon == 'true'" >
																				<s:property value="girlInfo.workMonTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:elseif>
																		<s:elseif test="tuesday == true" >
																			<s:if test="girlInfo.chkWorkTue == 'true'" >
																				<s:property value="girlInfo.workTueTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:elseif>
																		<s:elseif test="wednesday == true" >
																			<s:if test="girlInfo.chkWorkWed == 'true'" >
																				<s:property value="girlInfo.workWedTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:elseif>
																		<s:elseif test="thursday == true" >
																			<s:if test="girlInfo.chkWorkThu == 'true'" >
																				<s:property value="girlInfo.workTueTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:elseif>
																		<s:elseif test="friday == true" >
																			<s:if test="girlInfo.chkWorkFri == 'true'" >
																				<s:property value="girlInfo.workFriTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:elseif>
																		<s:elseif test="saturday == true" >
																			<s:if test="girlInfo.chkWorkSat == 'true'" >
																				<s:property value="girlInfo.workSatTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:elseif>
																	</td>
																</s:iterator>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="seven wide right floated centered column">
											<h5 class="ui header left aligned inverted">Booking</h5>
											<form class="ui form inverted left aligned" id="infoForm" method="post"
												action="<s:url value="/girl/%{girlInfo.girlInfoId}/bookingadd" />">
												<div class="fields">
													<div class="field">
														<s:textfield name="reserveInfo.email"
															key="global.booking_email" />
													</div>
													<div class="field">
														<s:textfield name="reserveInfo.clientName"
															key="global.booking_name" />
													</div>
												</div>
												<br />
												<div class="grouped inline fields">
													<label><s:text name="global.contact_method" /></label>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.contactMethod"
																id="reserveInfo_contactMethod_Email" value="Email"
																class="hidden" checked> 
															<label for="reserveInfo_contactMethod_Email">Email</label>
														</div>
													</div>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.contactMethod"
																id="reserveInfo_contactMethod_TEL" value="TEL"
																class="hidden"> 
															<label for="reserveInfo_contactMethod_TEL">TEL</label>
														</div>
													</div>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.contactMethod"
																id="reserveInfo_contactMethod_LINE" value="LINE"
																class="hidden"> 
															<label for="reserveInfo_contactMethod_LINE">LINE</label>
														</div>
													</div>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.contactMethod"
																id="reserveInfo_contactMethod_WeChat" value="WeChat"
																class="hidden"> 
															<label for="reserveInfo_contactMethod_WeChat">WeChat</label>
														</div>
													</div>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.contactMethod"
																id="reserveInfo_contactMethod_WhatsApp" value="WhatsApp"
																class="hidden"> 
															<label for="reserveInfo_contactMethod_WhatsApp">WhatsApp</label>
														</div>
													</div>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.contactMethod"
																id="reserveInfo_contactMethod_Telegram" value="Telegram"
																class="hidden"> 
															<label for="reserveInfo_contactMethod_Telegram">Telegram</label>
														</div>
													</div>
												</div>
												<br />
												
												<div class="field">
													<s:textfield name="reserveInfo.contactId"
														key="global.contact_id" />
												</div>
												<div class="field">
													<label><s:text name="global.girl_name" /></label>
													<div class="ui input disabled">
														<input value="<s:property value="girlInfo.nickName" />"
															disabled />
													</div>
												</div>
												<div class="field">
													<s:textfield name="reserveInfo.reserveDate"
														key="global.booking_date" />
												</div>
												<div class="field">
													<s:textfield name="reserveInfo.reserveTime"
														key="global.booking_time" />
												</div>
												<div class="inline field">
													<div class="ui checkbox">
														<input type="checkbox" name="reserveInfo.chkFlexible" 
															id="reserveInfo_chkFlexible" value="true" class="hidden" />
														<label for="reserveInfo_chkFlexible"><s:text name="global.flexible" /></label>
													</div>
												</div>
												<br />
												<div class="fields">
													<div class="field">
														<s:textfield name="reserveInfo.hotel"
															key="global.hotel" />
													</div>
													<div class="field">
														<s:textfield name="reserveInfo.roomNo"
															key="global.room_no" />
													</div>
												</div>
												<div class="inline field">
													<div class="ui checkbox">
														<input type="checkbox" name="reserveInfo.chkNotCheckedIn" 
															id="reserveInfo_chkNotCheckedIn" value="true" class="hidden" />
														<label for="reserveInfo_chkNotCheckedIn"><s:text name="global.not_checked_in" /></label>
													</div>
												</div>
												<br />
												<div class="grouped inline fields">
													<label><s:text name="global.booking_incall_outcall" /></label>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.incallOutcall"
																id="reserveInfo_incallOutcall_Incall" value="Incall"
																class="hidden" checked> 
															<label for="reserveInfo_incallOutcall_Incall"><s:text name="global.booking_incall" /></label>
														</div>
													</div>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.incallOutcall"
																id="reserveInfo_incallOutcall_Outcall" value="Outcall"
																class="hidden"> 
															<label for="reserveInfo_incallOutcall_Outcall"><s:text name="global.booking_outcall" /></label>
														</div>
													</div>
												</div>
												<br />
												<div class="grouped inline fields">
													<label><s:text name="global.booking_duration" /></label>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.duration"
																id="reserveInfo_duration_40Mins" value="40Mins" class="hidden" checked> 
															<label for="reserveInfo_duration_40Mins"><s:text name="global.price40Mins" /></label>
														</div>
													</div>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.duration"
																id="reserveInfo_duration_60Mins" value="60Mins" class="hidden"> 
															<label for="reserveInfo_duration_60Mins"><s:text name="global.price60Mins" /></label>
														</div>
													</div>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.duration"
																id="reserveInfo_duration_90Mins" value="90Mins" class="hidden"> 
															<label for="reserveInfo_duration_90Mins"><s:text name="global.price90Mins" /></label>
														</div>
													</div>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.duration"
																id="reserveInfo_duration_120Mins" value="120Mins" class="hidden"> 
															<label for="reserveInfo_duration_120Mins"><s:text name="global.price120Mins" /></label>
														</div>
													</div>
													<div class="field">
														<div class="ui radio checkbox">
															<input type="radio" name="reserveInfo.duration"
																id="reserveInfo_duration_6Hrs" value="6Hrs" class="hidden"> 
															<label for="reserveInfo_duration_6Hrs"><s:text name="global.price6Hrs" /></label>
														</div>
													</div>
												</div>
												<br />
												<div class="grouped inline fields">
													<label>Service</label>
													<s:iterator value="girlServices" status="rowstatus">
														<div class="field">
															<div class="ui checkbox">
																<input type="checkbox" name="girlServicesInfoId"
																	id="girlServicesInfoId_<s:property value="primaryKey.girlServiceInfo.girlServiceInfoId"/>" 
																	value="<s:property value="primaryKey.girlServiceInfo.girlServiceInfoId"/>" 
																	class="hidden"> 
																<label for="girlServicesInfoId_<s:property value="primaryKey.girlServiceInfo.girlServiceInfoId"/>">
																	<s:property value="primaryKey.girlServiceInfo.girlServiceNameJp" />
																</label>
															</div>
														</div>
													</s:iterator>
												</div>
												<br />
												<div class="field">
													<label><s:text name="global.inquiry" /></label>
													<textarea name="reserveInfo.inquiry"></textarea>
												</div>
												<br />
												<input name="reserveInfo.girlInfoId" type="hidden" value="<s:property value="girlInfo.girlInfoId" />" />
												<div class="ui row grid">
													<div class="wide column centered">
														<div class="ui primary submit button">Submit</div>
														<div class="ui reset button">Reset</div>
													</div>
												</div>
												<br />
												<div class="ui error message">
													<ul class="ui list"></ul>
												</div>
											</form>
											<br />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<%@include file="/common/common_footer.jsp"%>
	</div>
</body>
</html>