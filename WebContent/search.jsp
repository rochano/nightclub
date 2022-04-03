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
  <title>THAINIGHTNAVI.COM - <s:text name="global.main_menu_search" /></title>
  <%@include file="/common/common_header.jsp" %>
  <!--- Example CSS -->
  <style>
  .ui.table th, .ui.table tr:nth-child(even) td{
  	font-size: 12px;
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
  .ui.grid.data > a[class*="center aligned"].column {
    border: 1px solid #000000;
    color: #FFFFFF;
  }
  .ui.grid.data > a[class*="center aligned"].column:hover {
    background: #555555;
  }
  .ui.inverted.form label,
  .ui.form .inline.field > label {
  	color: rgba(255, 255, 255, 0.9);
  }
  .ui.checkbox label, .ui.form select {
  	display: inline-block;
  	width: auto;
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
  .ui.image {
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
  .ui.form select {
  	width: 150px;
  }
  </style>
    <!--- Example Javascript -->
  <script>
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
	  $('#searchForm.ui.form')
      .form({
          fields: {}
      })
      ;
     $("#frontSearch_chkCategory").click(function() {
		if($(this).prop("checked") == true) {
			$("#frontSearch_categoryInfoId").removeAttr("disabled");
		} else {
			$("#frontSearch_categoryInfoId").attr("disabled", "disabled");
			$("#frontSearch_categoryInfoId").val($("#frontSearch_categoryInfoId option:first").val());
		}
     });
     $("#frontSearch_chkAgents").click(function() {
 		if($(this).prop("checked") == true) {
 			$("#frontSearch_agentInfoId").removeAttr("disabled");
 		} else {
 			$("#frontSearch_agentInfoId").attr("disabled", "disabled");
 			$("#frontSearch_categoryInfoId").val($("#frontSearch_categoryInfoId option:first").val());
 		}
     });
     $("#frontSearch_chkIncallOutcall").click(function() {
  		if($(this).prop("checked") == true) {
  			$("#frontSearch_incallOutcall").removeAttr("disabled");
  		} else {
  			$("#frontSearch_incallOutcall").attr("disabled", "disabled");
  			 $("#frontSearch_incallOutcall").val($("#frontSearch_incallOutcall option:first").val());
  		}
     });
     $(".clear").click(function() {
    	 $("#frontSearch_categoryInfoId").attr("disabled", "disabled");
    	 $("#frontSearch_categoryInfoId").val($("#frontSearch_categoryInfoId option:first").val());
    	 $("#frontSearch_agentInfoId").attr("disabled", "disabled");
    	 $("#frontSearch_agentInfoId").val($("#frontSearch_agentInfoId option:first").val());
    	 $("#frontSearch_incallOutcall").attr("disabled", "disabled");
    	 $("#frontSearch_incallOutcall").val($("#frontSearch_incallOutcall option:first").val());
     })
    })
  ;
  </script>
</head>
<body>
<!-- Sidebar Menu -->
<s:set name="base_url" value="%{'/'}" />
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
					<div class="active section"><s:text name="global.main_menu_search" /></div>
				</div>
				
				<div class="center aligned column">
					<div class="ui segment header">
						<h4 class="ui top header">
							<i class="filter icon"></i>
							<s:text name="global.search_condition" />
						</h4>
					</div>
					<div class="ui grid attached segment">
						<div class="column one left aligned">
							<form class="ui inverted form" id="searchForm" method="post" action="<s:url value="/search"/>">
								<div class="field">
									<label><s:text name="global.service_type_specify" /></label>
								</div>
								<div class="inline field">
									<div class="ui checkbox">
										<input type="checkbox" name="frontSearch.chkCategory" id="frontSearch_chkCategory"
											<s:if test="frontSearch.chkCategory == 'true'">checked="checked"</s:if>
											value="true" />
										<label for="frontSearch_chkCategory"><s:text name="global.main_menu_service_shop" /></label>
										<s:if test="#request.locale.language=='th'">
											<select name="frontSearch.categoryInfoId" id="frontSearch_categoryInfoId" 
												<s:if test="frontSearch.chkCategory == 'true'"></s:if>
												<s:else>disabled="disabled"</s:else>
												>
												<option value=""></option>
												<s:iterator value="categoryInfos">
													<option value="<s:property value="categoryInfoId" />"
													<s:if test="frontSearch.categoryInfoId == categoryInfoId">selected="selected"</s:if>
													><s:property value="categoryNameEn" /></option>
												</s:iterator>
											</select>
										</s:if>
										<s:else>
											<select name="frontSearch.categoryInfoId" id="frontSearch_categoryInfoId" 
												<s:if test="frontSearch.chkCategory == 'true'"></s:if>
												<s:else>disabled="disabled"</s:else>
												>
												<option value=""></option>
												<s:iterator value="categoryInfos">
													<option value="<s:property value="categoryInfoId" />"
													<s:if test="frontSearch.categoryInfoId == categoryInfoId">selected="selected"</s:if>
													><s:property value="categoryNameJp" /></option>
												</s:iterator>
											</select>
										</s:else>
									</div>
								</div>
								<div class="inline field">
									<div class="ui checkbox">
										<input type="checkbox" name="frontSearch.chkAgents" id="frontSearch_chkAgents"
											<s:if test="frontSearch.chkAgents == 'true'">checked="checked"</s:if>
											value="true" />
										<label for="frontSearch_chkAgents"><s:text name="global.main_menu_agents" /></label>
										<select name="frontSearch.agentInfoId" id="frontSearch_agentInfoId"
											<s:if test="frontSearch.chkAgents == 'true'"></s:if>
											<s:else>disabled="disabled"</s:else>
											>
											<option value=""></option>
											<s:iterator value="agentInfos">
												<option value="<s:property value="agentInfoId" />"
												<s:if test="frontSearch.agentInfoId == agentInfoId">selected="selected"</s:if>
												><s:property value="agentName" /></option>
											</s:iterator>
										</select>
									</div>
								</div>
								<div class="inline field">
									<div class="ui checkbox">
										<input type="checkbox" name="frontSearch.chkFreeAgents" id="frontSearch_chkFreeAgents"
											<s:if test="frontSearch.chkFreeAgents == 'true'">checked="checked"</s:if>
											value="true" />
										<label for="frontSearch_chkFreeAgents"><s:text name="global.main_menu_free_agents" /></label>
									</div>
								</div>
								<div class="inline field">
									<div class="ui checkbox">
										<input type="checkbox" name="frontSearch.chkEnGirls" id="frontSearch_chkEnGirls"
											<s:if test="frontSearch.chkEnGirls == 'true'">checked="checked"</s:if>
											value="true" />
										<label for="frontSearch_chkEnGirls"><s:text name="global.main_menu_en_girls" /></label>
									</div>
								</div>
								<br/>
								<div class="inline field">
									<label><s:text name="global.gender_specify" /></label>
									<s:select list="#{'':'',
										'Straight':getText('global.gender_straight'),
										'Transgender':getText('global.gender_transgender')}"
									name="frontSearch.gender"></s:select>
								</div>
								<br/>
								<div class="ui inverted accordion">
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
								</div>
								<br/>
								<div class="inline field">
									<div class="ui checkbox">
										<input type="checkbox" name="frontSearch.chkIncallOutcall" id="frontSearch_chkIncallOutcall"
											<s:if test="frontSearch.chkIncallOutcall == 'true'">checked="checked"</s:if>
											value="true" />
										<label for="frontSearch_chkIncallOutcall"><s:text name="global.incall_outcall_specify" /></label>
										<select name="frontSearch.incallOutcall" id="frontSearch_incallOutcall"
											<s:if test="frontSearch.chkIncallOutcall == 'true'"></s:if>
											<s:else>disabled="disabled"</s:else>
											>
											<option value=""></option>
											<option value="1" <s:if test="frontSearch.incallOutcall == 1">selected="selected"</s:if>><s:text name="global.incall" /></option>
											<option value="2" <s:if test="frontSearch.incallOutcall == 2">selected="selected"</s:if>><s:text name="global.outcall" /></option>
										</select>
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
				
				<div class="center aligned column">
					<div class="ui segment header">
						<h4 class="ui top header">
							<i class="list icon"></i>
							<s:text name="global.search_result" />
						</h4>
					</div>
					<div class="ui centered attached segment soft">
						<div class="ui centered four doubling cards ">
						<s:if test="%{girlInfos.size gte 0}">
							<s:iterator value="girlInfos" status="status">
								<div class="ui red card">
									<div class="image ui centered corner labeled" >
										<a class="ui right corner label toggleFavourite link" 
												data-girlInfoId="<s:property value="girlInfoId" />"
												data-content="Please login first" data-variation="tiny">
											<i class="heart 
												<s:if test="girlFavourites.indexOf(girlInfoId) != -1">red</s:if>
												<s:else>outline</s:else>
												icon"></i>
										</a>
										<a href="<s:url value="/girl/%{girlInfoId}"/>" >
											<s:if test="pic1 != null">
												<img class="image ui centered" src="<s:property value="pic1" />">
											</s:if>
											<s:else>
												<img class="image ui centered" src="<s:url value="/assets/images/wireframe/square-image.png" />">
											</s:else>
										</a>
									</div>
									<div class="content left aligned label pink circular ui">
										<span class="right floated">
											<s:property value="girlInfo.class.name" />
											<s:if test="%{type != null}" >
												<s:text name="format.integer"><s:param name="value" value="price"/></s:text>
											</s:if>
											<s:else>
												<s:if test="chk40Mins == 'true'">
													<s:text name="format.integer"><s:param name="value" value="price40Mins"/></s:text>
												</s:if>
												<s:elseif test="chk60Mins == 'true'">
													<s:text name="format.integer"><s:param name="value" value="price60Mins"/></s:text>
												</s:elseif>
												<s:elseif test="chk90Mins == 'true'">
													<s:text name="format.integer"><s:param name="value" value="price90Mins"/></s:text>
												</s:elseif>
												<s:elseif test="chk120Mins == 'true'">
													<s:text name="format.integer"><s:param name="value" value="price120Mins"/></s:text>
												</s:elseif>
												<s:elseif test="chk6Hrs == 'true'">
													<s:text name="format.integer"><s:param name="value" value="price6Hrs"/></s:text>
												</s:elseif>
											</s:else>
										</span>
										ตรงปก
									</div>
									<div class="content left aligned">
										<a class="ui header " href="<s:url value="/girl/%{girlInfoId}"/>"><s:property value="nickName" /></a>
										<div class="description">
											<s:if test="%{type != null}" >
												<s:text name="global.job" /> : 
												<s:if test="type == 1"><s:text name="global.en_girl_type_1" /></s:if>
												<s:if test="type == 2"><s:text name="global.en_girl_type_2" /></s:if>
												<s:if test="type == 3"><s:text name="global.en_girl_type_3" /></s:if>
												<br/>
											</s:if>
											<s:elseif test="%{agentInfo != null}">
												<s:property value="agentInfo.agentName" /><br/>
											</s:elseif>
											<i class="marker icon"></i>
											<s:if test="#request.locale.language=='th'">
												<s:iterator value="girlLocations" >
													<div class="ui medium label">
														<s:property value="primaryKey.zoneInfo.zoneNameEn" />
													</div>
												</s:iterator>
											</s:if>
											<s:else>
												<s:iterator value="girlLocations" >
													<div class="ui medium label">
														<s:property value="primaryKey.zoneInfo.zoneNameJp" />
													</div>
												</s:iterator>
											</s:else>
										</div>
									</div>
								</div>
							</s:iterator>
						</s:if>
						<s:if test="%{girlInfos.size eq 0}">
							<s:text name="global.no_data" />
						</s:if>
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