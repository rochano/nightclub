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
  #provinceInfos {
  	margin-top:0;
  	margin-left:0;
  }
  [name=provinceInfos] {
  	display: none;
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
		var countryInfos = $("#frontSearch_countryInfoId");
		countryInfos.empty();
		var html = '';
		html += '<option value=""></option>';
		$(html).appendTo(countryInfos);
		var provinceInfos = $("#provinceInfos");
		provinceInfos.empty();
     })
     $("#searchForm").submit(function() {
    	 $("#frontSearch_chkCategory").val("true");
    	 $("#frontSearch_chkAgents").val("true");
    	 $("#frontSearch_chkFreeAgents").val("true");
    	 $("#frontSearch_chkEnGirls").val("true");
     });
     $('#searchForm.ui.form').slideDown('slow');
     $("#frontSearch_countryClassification").change(function() {
 	  	var countryClassification = $(this).val();
 	  	loadCountry(countryClassification, '#frontSearch_countryInfoId', '#provinceInfos');
	  });
     $("#frontSearch_countryInfoId").change(function() {
		var countryInfoId = $(this).val();
		loadProvince(countryInfoId, '#provinceInfos', 'zoneInfos');
 	  });
     $("[name='frontSearch.zoneInfos']").change(function() {
   	  	 var parents = $(this).parents(".accordion:first");
         if(parents.find("[name='frontSearch.zoneInfos']:checked").length == 0) {
       	  parents.find("[name=provinceInfos]").prop('checked',false );
         } else {
       	  parents.find("[name=provinceInfos]").prop('checked',true );
         }
     });
    })
  ;
  function loadCountry(countryClassification, countryInfosElmId, provinceInfosElmId) {
	  $.getJSON("<s:url value="/ajax/loadCountryByClassification/" />" + countryClassification, 
			function(jsonResponse) {
				var countryInfos = $(countryInfosElmId);
				countryInfos.empty();
				var html = '';
				html += '<option value=""></option>';
				$(html).appendTo(countryInfos);
				$.each(jsonResponse.countryInfos, function(i, obj) {
					html = '';
					html += '<option value="' + obj.countryInfoId + '">' + obj.countryNameEn + '</option>';
					$(html).appendTo(countryInfos);
				});
				if (countryClassification == '1') {
					countryInfos.val(jsonResponse.countryInfoIdThai)
				}
				var provinceInfos = $(provinceInfosElmId);
				if (countryClassification != '1') {
					provinceInfos.empty();
				} else {
					loadProvince(jsonResponse.countryInfoIdThai, '#provinceInfos', 'zoneInfos');
				}
			});
   }
  function loadProvince(countryInfoId, provinceInfosElmId, girlLocationsId) {
	  $.getJSON("<s:url value="/ajax/loadProvinceByCountry/" />" + countryInfoId, 
			function(jsonResponse) {
				var provinceInfos = $(provinceInfosElmId);
				provinceInfos.empty();
			     $.each(jsonResponse.provinceInfos, function(i, obj) {
					var html = '';
					html += '<div class="column">';
					html += '	<div class="ui">';
					html += '		<div class="ui accordion">';
					html += '			<div class="title ui">';
					html += '				<input type="checkbox" name="provinceInfos" id="provinceInfos_' + i + '"';
					html += '				value="' + obj.provinceInfoId + '">';
					html += '				<label>' + obj.provinceNameEn + '</label>';
					html += '				<i class="dropdown icon"></i>';
					html += '			</div>';
					html += '			<div class="content">';
					html += '				<div class="ui four column grid" class="zoneInfos">';
					if(obj.zoneInfos) {
						$.each(obj.zoneInfos, function(j, objZoneInfo) {
							html += '				<div class="column">';
							html += '					<div class="field ui checkbox">';
							html += '						<input type="checkbox" name="frontSearch.' + girlLocationsId + '" id="' + obj.provinceInfoId + '_' + girlLocationsId + '_' + j + '"';
							html += '						value="' + objZoneInfo.zoneInfoId + '">';
							html += '						<label for="' + obj.provinceInfoId + '_' + girlLocationsId + '_' + j + '">' + objZoneInfo.zoneNameEn + '</label>';
							html += '					</div>';
							html += '				</div>';
						});
					}
					html += '				</div>';
					html += '			</div>';
					html += '		</div>';
					html += '	</div>';
					html += '</div>';
			       $(html).appendTo(provinceInfos);
			     });
			});
   }
  </script>
				<div class="center aligned column ui accordion">
					<div class="ui segment header title">
						<h4 class="ui top header">
							<i class="filter icon"></i>
							<s:text name="global.search_condition" />
						</h4>
					</div>
					<div class="ui grid attached segment content">
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
													><s:property value="categoryNameEn" /></option>
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
									<label for="frontSearch_countryClassification" class="label"><s:text name="global.country_classication" />:</label>
									<s:select list="#{'1':getText('global.domestic'),  
														'2':getText('global.overseas')}"
										headerKey="" headerValue=""
										name="frontSearch.countryClassification">
									</s:select>
								</div>
								<div class="field">
									<s:select list="countryInfos"
										listKey="countryInfoId" listValue="countryNameEn"
										key="global.country" 
										headerKey="" headerValue=""
										name="frontSearch.countryInfoId">
									</s:select>
								</div>
								<div class="ui inverted accordion">
									<div class="title">
										<s:text name="global.province" /> :
										<i class="dropdown icon"></i>
									</div>
									<div class="content">
										<div class="ui one column grid doubling" id="provinceInfos">
											<s:iterator value="provinceInfos" status="rowstatus">
												<div class="column">
													<div class="ui">
														<div class="ui accordion">
															<div class="title ui">
																<input type="checkbox" name="provinceInfos" id="provinceInfos_<s:property value="#rowstatus.count" />"
																<s:iterator value="frontSearch.provinceInfos" >
																	<s:property value="top" />
																	<s:if test="top == provinceInfoId">checked="checked"</s:if>
																</s:iterator>
																value="<s:property value="provinceInfoId" />">
																<label><s:property value="provinceNameEn" /></label>
																<i class="dropdown icon"></i>
															</div>
															<div class="content">
																<div class="ui four column grid" class="zoneInfos">
																	<s:iterator value="top.zoneInfos" status="rowstatus">
																		<div class="column">
																			<div class="field ui checkbox">
																				<input type="checkbox" name="frontSearch.zoneInfos" id="<s:property value="provinceInfoId" />_zoneInfos_<s:property value="#rowstatus.count" />"
																				<s:iterator value="frontSearch.zoneInfos" >
																					<s:property value="top" />
																					<s:if test="top == zoneInfoId">checked="checked"</s:if>
																				</s:iterator>
																				value="<s:property value="zoneInfoId" />">
																				<label for="<s:property value="provinceInfoId" />_zoneInfos_<s:property value="#rowstatus.count" />"><s:property value="zoneNameEn" /></label>
																			</div>
																		</div>
																	</s:iterator>
																</div>
															</div>
														</div>
													</div>
												</div>
											</s:iterator>
										</div>
									</div>
								</div>
								<br />
								<div class="field">
									<label><s:text name="global.incall_outcall_specify" /></label>
									<select name="frontSearch.incallOutcall" id="frontSearch_incallOutcall">
										<option value=""></option>
										<option value="incall" <s:if test="frontSearch.incallOutcall == 'incall'">selected="selected"</s:if>><s:text name="global.incall" /></option>
										<option value="outcall" <s:if test="frontSearch.incallOutcall == 'outcall'">selected="selected"</s:if>><s:text name="global.outcall" /></option>
									</select>
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
															<label for="nationalityInfoInfos_<s:property value="#rowstatus.count" />"><s:property value="nationalityNameEn" /></label>
														</div>
													</div>
												</s:iterator>
											<%--</s:else>--%>
										</div>
									</div>
								</div>
								<br/>
								<div class="field">
									<s:select list="genderInfos"
										listKey="genderInfoId" listValue="genderNameEn"
										key="global.gender_specify" 
										headerKey="" headerValue=""
										name="frontSearch.genderInfoId">
									</s:select>
								</div>
								<s:hidden name="action" value="search"></s:hidden>
								<s:hidden name="frontSearch.searchRandom" ></s:hidden>
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