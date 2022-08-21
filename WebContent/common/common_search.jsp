<%@page import="com.nightclub.common.IConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
  <style>
  .ui.inverted.form label,
  .ui.form .inline.field > label {
  	color: rgba(255, 255, 255, 0.9);
  }
  .ui.checkbox label, .ui.form select, .ui.form .field > label {
  	display: inline-block;
  	width: auto;
  }
  @media only screen and (max-width: 767px) {
  	.ui.form select, ui.form .field > label {
	  	display: block;
	  	width: 100%;
	  }
  }
  ui.form select {
    margin-top: 0em;
    margin-bottom: 0em;
    vertical-align: middle;
    font-size: 1em;
  }
  .ui.checkbox label {
  	margin: 0em 0.85714286em 0em 0em;
  }
  pic .ui.image {
	width: auto;
	height: 160px;
  }
  .ui.image img {
	height: 100%;
  }
  .ui.cards > .card > .content, .ui.card > .content {
  	flex-grow: 0;
  	-webkit-flex-grow: 0;
  	-ms-flex-positive: 0;
  }
  .ui.form .field > :first-child {
  	width: 150px;
  }
  </style>
  <script>
  $(document)
    .ready(function() {
	  $('#searchForm.ui.form')
      .form({
          fields: {}
      })
      ;
     $(".clear").click(function() {
    	 $("#frontSearch_incallOutcall").val($("#frontSearch_incallOutcall option:first").val());
     })
     $("#searchForm").submit(function() {
    	 $("#frontSearch_chkCategory").val("true");
    	 $("#frontSearch_chkAgents").val("true");
    	 $("#frontSearch_chkFreeAgents").val("true");
    	 $("#frontSearch_chkEnGirls").val("true");
     });
     $('#searchForm.ui.form').slideDown('slow');
     $("#frontSearch_countryInfoId").change(function() {
    	  	var countryInfoId = $(this).val();
    	  	loadProvince(countryInfoId, '#provinceInfos');
 	  });
    })
  ;
  function loadProvince(countryInfoId, provinceInfosElmId) {
	  $.getJSON("<s:url value="/ajax/loadProvinceByCountry/" />" + countryInfoId, 
			function(jsonResponse) {
				var provinceInfos = $(provinceInfosElmId);
				provinceInfos.empty();
			     $.each(jsonResponse.provinceInfos, function(i, obj) {
					var html = '';
					html += '<div class="column">';
					html += '	<div class="field ui checkbox">';
					html += '		<input type="checkbox" name="frontSearch.provinceInfos" id="provinceInfos_' + i + '"';
					html += '			value="' + obj.provinceInfoId + '">';
					html += '		<label for="provinceInfos_' + i + '">' + obj.provinceNameJp + '</label>';
					html += '	</div>';
					html += '</div>';
			       $(html).appendTo(provinceInfos);
			     });
			});
   }
  </script>
				<div class="center aligned column">
					<div class="ui segment header">
						<h4 class="ui top header">
							<i class="filter icon"></i>
							<s:text name="global.search_condition" />
						</h4>
					</div>
					<div class="ui grid attached segment">
						<div class="column one left aligned">
							<form class="ui inverted form" id="searchForm" method="post" action="<s:url value=""/>" style="display:none;">
								<s:set var="userTypeShop"><%=IConstants.USER_TYPE_SHOP%></s:set>
								<s:set var="userTypeAgent"><%=IConstants.USER_TYPE_AGENT%></s:set>
								<s:set var="userTypeFreeAgent"><%=IConstants.USER_TYPE_FREE_AGENT%></s:set>
								<s:set var="userTypeEnGirl"><%=IConstants.USER_TYPE_EN_GIRL%></s:set>
								<s:if test="%{#userType == #userTypeShop}">
									<div class="field">
										<label><s:text name="global.main_menu_service_shop" /></label>
										<input type="hidden" name="frontSearch.chkCategory" id="frontSearch_chkCategory" value="true" />
										<%--<s:if test="#request.locale.language=='th'">
											<select name="frontSearch.categoryInfoId" id="frontSearch_categoryInfoId">
												<option value=""></option>
												<s:iterator value="categoryInfos">
													<option value="<s:property value="categoryInfoId" />"
													<s:if test="frontSearch.categoryInfoId == categoryInfoId">selected="selected"</s:if>
													><s:property value="categoryNameEn" /></option>
												</s:iterator>
											</select>
										</s:if>
										<s:else> --%>
											<select name="frontSearch.categoryInfoId" id="frontSearch_categoryInfoId">
												<option value=""></option>
												<s:iterator value="categoryInfos">
													<option value="<s:property value="categoryInfoId" />"
													<s:if test="frontSearch.categoryInfoId == categoryInfoId">selected="selected"</s:if>
													><s:property value="categoryNameJp" /></option>
												</s:iterator>
											</select>
										<%--</s:else>--%>
									</div>
								</s:if>
								<s:if test="%{#userType == #userTypeAgent}">
									<div class="field">
										<label><s:text name="global.main_menu_agents" /></label>
										<input type="hidden" name="frontSearch.chkAgents" id="frontSearch_chkAgents" value="true" />
										<select name="frontSearch.agentInfoId" id="frontSearch_agentInfoId">
											<option value=""></option>
											<s:iterator value="agentInfos">
												<option value="<s:property value="agentInfoId" />"
												<s:if test="frontSearch.agentInfoId == agentInfoId">selected="selected"</s:if>
												><s:property value="agentName" /></option>
											</s:iterator>
										</select>
									</div>
								</s:if>
								<s:if test="%{#userType == #userTypeFreeAgent}">
									<input type="hidden" name="frontSearch.chkFreeAgents" id="frontSearch_chkFreeAgents" value="true" />
								</s:if>
								<s:if test="%{#userType == #userTypeEnGirl}">
									<input type="hidden" name="frontSearch.chkEnGirls" id="frontSearch_chkEnGirls" value="true" />
								</s:if>
								<div class="field">
									<label><s:text name="global.gender_specify" /></label>
									<s:select list="#{'':'',
										'Straight':getText('global.gender_straight'),
										'Transgender':getText('global.gender_transgender')}"
									name="frontSearch.gender"></s:select>
								</div>
								<div class="ui inverted accordion">
									<div class="title">
										<label><s:text name="global.nationality" /></label>
										<i class="dropdown icon"></i>
									</div>
									<div class="content">
										<div class="ui four column grid doubling">
											<%--<s:if test="#request.locale.language=='th'">
												<s:iterator value="nationalityInfos" status="rowstatus">
													<div class="column">
														<div class="field ui checkbox">
															<input type="checkbox" name="frontSearch.nationalityInfos" id="nationalityInfos_<s:property value="#rowstatus.count" />"
																<s:iterator value="frontSearch.nationalityInfos" >
																	<s:property value="top" />
																	<s:if test="top == nationalityInfoInfoId">checked="checked"</s:if>
																</s:iterator>
																value="<s:property value="nationalityInfoInfoId" />">
															<label for="nationalityInfoInfos_<s:property value="#rowstatus.count" />"><s:property value="nationalityNameEn" /></label>
														</div>
													</div>
												</s:iterator>
											</s:if>
											<s:else>--%>
												<s:iterator value="nationalityInfos" status="rowstatus">
													<div class="column">
														<div class="field ui checkbox">
															<input type="checkbox" name="frontSearch.nationalityInfos" id="nationalityInfos_<s:property value="#rowstatus.count" />"
																<s:iterator value="frontSearch.nationalityInfos" >
																	<s:property value="top" />
																	<s:if test="top == nationalityInfoId">checked="checked"</s:if>
																</s:iterator>
																value="<s:property value="nationalityInfoId" />">
															<label for="nationalityInfoInfos_<s:property value="#rowstatus.count" />"><s:property value="nationalityNameJp" /></label>
														</div>
													</div>
												</s:iterator>
											<%--</s:else>--%>
										</div>
									</div>
								</div>
								<br/>
								<div class="field">
									<label><s:text name="global.incall_outcall_specify" /></label>
									<select name="frontSearch.incallOutcall" id="frontSearch_incallOutcall">
										<option value=""></option>
										<option value="incall" <s:if test="frontSearch.incallOutcall == 'incall'">selected="selected"</s:if>><s:text name="global.incall" /></option>
										<option value="outcall" <s:if test="frontSearch.incallOutcall == 'outcall'">selected="selected"</s:if>><s:text name="global.outcall" /></option>
									</select>
								</div>
								<%-- <div class="ui inverted accordion">
									<div class="title">
										<label><s:text name="global.location_specify" /></label>
										<i class="dropdown icon"></i>
									</div>
									<div class="content">
										<div class="ui four column grid doubling">
											<s:if test="#request.locale.language=='th'">
												<s:iterator value="zoneInfos" status="rowstatus">
													<div class="column">
														<div class="field ui checkbox">
															<input type="checkbox" name="frontSearch.zoneInfos" id="zoneInfos_<s:property value="#rowstatus.count" />"
																<s:iterator value="frontSearch.zoneInfos" >
																	<s:property value="top" />
																	<s:if test="top == zoneInfoId">checked="checked"</s:if>
																</s:iterator>
																value="<s:property value="zoneInfoId" />">
															<label for="zoneInfos_<s:property value="#rowstatus.count" />"><s:property value="zoneNameEn" /></label>
														</div>
													</div>
												</s:iterator>
											</s:if>
											<s:else>
												<s:iterator value="zoneInfos" status="rowstatus">
													<div class="column">
														<div class="field ui checkbox">
															<input type="checkbox" name="frontSearch.zoneInfos" id="zoneInfos_<s:property value="#rowstatus.count" />"
																<s:iterator value="frontSearch.zoneInfos" >
																	<s:property value="top" />
																	<s:if test="top == zoneInfoId">checked="checked"</s:if>
																</s:iterator>
																value="<s:property value="zoneInfoId" />">
															<label for="zoneInfos_<s:property value="#rowstatus.count" />"><s:property value="zoneNameJp" /></label>
														</div>
													</div>
												</s:iterator>
											</s:else>
										</div>
									</div>
								</div> --%>
								<div class="field">
									<s:select list="countryInfos"
										listKey="countryInfoId" listValue="countryNameJp"
										key="global.country" 
										headerKey="" headerValue=""
										name="frontSearch.countryInfoId">
									</s:select>
								</div>
								<div class="ui inverted accordion">
									<h4 class="title">
										<s:text name="global.province" /> :
										<i class="dropdown icon"></i>
									</h4>
									<div class="content">
										<div class="ui four column grid doubling" id="provinceInfos">
											<s:iterator value="provinceInfos" status="rowstatus">
												<div class="column">
													<div class="field ui checkbox">
														<input type="checkbox" name="frontSearch.provinceInfos" id="provinceInfos_<s:property value="#rowstatus.count" />"
															<s:iterator value="frontSearch.provinceInfos" >
																<s:property value="top" />
																<s:if test="top == provinceInfoId">checked="checked"</s:if>
															</s:iterator>
															value="<s:property value="provinceInfoId" />">
														<label for="provinceInfos_<s:property value="#rowstatus.count" />"><s:property value="provinceNameJp" /></label>
													</div>
												</div>
											</s:iterator>
										</div>
									</div>
								</div>
								<s:hidden name="action" value="search"></s:hidden>
								<div class="ui error message"></div>
								<div class="ui right aligned one column grid">
									<div class="column">
										<div class="ui small button submit blue">Search</div>
										<div class="ui small button clear">Clear</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>