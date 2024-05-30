<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title><s:i18n name="global_th"><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.menu_girls" /></s:i18n></title>

  <%@include file="/common/common_admin_management_header.jsp" %>

  <style>
  body {
    padding: 1em;
  }
  .ui.menu:last-child {
    margin-bottom: 110px;
  }
  .ui.items > .item {
	display: inline-block;
	width: auto;
  }
  .ui.items > .item > .image {
	margin: auto;
  }
  .ui.items > .item > .image + .content {
	display: block;
    padding: 1.5em 0em 0em;
  }
  #searchForm.ui.form .inline.field>:first-child, #searchForm.ui.form .inline.fields>label:first-child {
  	width: 150px;
  	margin: 0;
  }
  @media only screen and (max-width: 470px) {
  	#searchForm.ui.form .inline.field>label.screen:first-child {
	  	display: none;
	}
  }
  #searchForm .ui.checkbox label, #searchForm.ui.form select {
  	display: inline-block;
  	width: auto;
  }
  #searchFormui.form select {
    margin-top: 0em;
    margin-bottom: 0em;
    vertical-align: middle;
    font-size: 1em;
  }
  searchForm .ui.checkbox label {
  	margin: 0em 0.85714286em 0em 0em;
  }
  #provinceInfos > .column:not(.row) {
  	padding-top: 0;
  }
  #provinceInfos {
  	margin-top:0;
  	margin-left:0;
  }
  [name=provinceInfos] {
  	display: none;
  }
  .ui.ui.ui.red.label {
/*     width: 30px;
    height: 30px;
    overflow: hidden;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 50%;
    text-wrap: balance;
    word-break: break-word; */
    font-size: 10px;
  }
  .ui.multiple.dropdown>.label {
/*   	position: relative;
  	margin: 0;
  	background: #fff;
    box-shadow: none; */
  }
  .ui.label>.delete.icon {
/*   	position: absolute;
  	top: 0;
  	right: 3px; */
  }
  .ui.multiple.dropdown>.label {
    font-size: 10px;
    text-wrap: nowrap;
  }
  .ui.ui.multiple.dropdown {
    padding: 0px;
  }
  </style>

  <!--- Example Javascript -->
  <script>
  $(document)
    .ready(function() {
      $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
      });
      $('.ui.dropdown')
      .dropdown()
    ;
      $('.ui.menu a.item')
        .on('click', function() {
          $(this)
            .addClass('active')
            .siblings()
            .removeClass('active')
          ;
        })
      ;
      $('.message .close')
      .on('click', function() {
        $(this)
          .closest('.message')
          .transition('fade')
        ;
      })
    ;
      $('#allSameForm, #allSameFormTop')
      .form({
          onSuccess: function() { 
              var form = $(this);
              form.find("[name=allsamelist]").remove()
              $( "input[name=allSame]", dataTable.fnGetNodes()).each(function(i, item) {
                  var obj = {id: item.value, checked: item.checked};
            	  form.append("<input name='allsamelist' value='" + JSON.stringify(obj) + "' type='hidden' />")
              })
              form.find("[name=girlTagList]").remove()
              $( "input[name=girlTag]", dataTable.fnGetNodes()).each(function(i, item) {
                  var obj = {id: item.dataset.girl_id, value: item.value};
            	  form.append("<input name='girlTagList' value='" + JSON.stringify(obj) + "' type='hidden' />")
              })
              return true; 
          }
       })
      ;
	  $("table").tablesort();
	  $('#searchForm.ui.form')
      .form({
          fields: {}
      })
      ;
	  $("#girlSearch_chkCategory").click(function() {
			if($(this).prop("checked") == true) {
				$("#girlSearch_categoryInfoId").removeAttr("disabled");
			} else {
				$("#girlSearch_categoryInfoId").attr("disabled", "disabled");
				$("#girlSearch_categoryInfoId").val($("#girlSearch_categoryInfoId option:first").val());
			}
	     });
	  $("#girlSearch_chkAgents").click(function() {
	 		if($(this).prop("checked") == true) {
	 			$("#girlSearch_agentInfoId").removeAttr("disabled");
	 		} else {
	 			$("#girlSearch_agentInfoId").attr("disabled", "disabled");
	 			$("#girlSearch_categoryInfoId").val($("#girlSearch_categoryInfoId option:first").val());
	 		}
	   });
	  $(".clear").click(function() {
	    	 $("#girlSearch_categoryInfoId").attr("disabled", "disabled");
	    	 $("#girlSearch_categoryInfoId").val($("#girlSearch_categoryInfoId option:first").val());
	    	 $("#girlSearch_agentInfoId").attr("disabled", "disabled");
	    	 $("#girlSearch_agentInfoId").val($("#girlSearch_agentInfoId option:first").val());
	     })
       $("#girlSearch_countryInfoId").change(function() {
    	  	var countryInfoId = $(this).val();
    	  	loadProvince(countryInfoId, '#provinceInfos', 'zoneInfos');
 	    });
	   $("[name='girlSearch.zoneInfos']").change(function() {
	   	  	 var parents = $(this).parents(".accordion:first");
	         if(parents.find("[name='girlSearch.zoneInfos']:checked").length == 0) {
	       	  parents.find("[name=provinceInfos]").prop('checked',false );
	         } else {
	       	  parents.find("[name=provinceInfos]").prop('checked',true );
	         }
	     });
    })
  ;
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
							html += '						<input type="checkbox" name="girlSearch.' + girlLocationsId + '" id="' + obj.provinceInfoId + '_' + girlLocationsId + '_' + j + '"';
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
   function postDrawCallback() {
	  console.log("postDrawCallback");
	  $('.ui.dropdown.tag_info')
      .dropdown()
    ;
   }
  </script>
</head>
<body class="menu pushable">
<%@include file="/common/common_admin_management_header_info.jsp" %>
<!-- Sidebar Menu -->
<div class="ui toc vertical top large inverted sidebar menu">
  	<a class="ui toc item title">
		<i class="cancel icon"></i>
		&nbsp;
	</a>
	<%@include file="/common/common_admin_management_menu.jsp" %>
</div>
<div class="pusher">
<div class="full height">
<div class="toc">
	<!-- Sidebar Menu -->
	<div class="ui inverted vertical menu">
		<%@include file="/common/common_admin_management_menu.jsp" %>
	</div>
</div>
<div class="article">
<div class="ui segment very basic container">
			<div class="ui menu inverted stackable toc left aligned">
		  		<a class="toc item"><i class="sidebar icon"></i></a>
		  	</div>
			<s:if test="hasActionMessages()">
				<div class="ui success message green">
					<i class="close icon"></i>
					<div class="header">
						<s:actionmessage cssClass="list" />
					</div>
				</div>
			</s:if>
			<div class="ui accordion">
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th">
						<s:text name="global.search_condition" />
					</s:i18n>
				</h4>
				<div class="ui left aligned attached segment active content">
					<form class="ui form" id="searchForm" method="post" action="<s:url value="/admin/girls/search"/>">
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="girlSearch.nickName" key="global.nick_name"/></s:i18n>
						</div>
						<s:i18n name="global_th">
							<%-- <div class="inline field">
								<label><s:text name="global.type" />:</label>
								<div class="ui checkbox">
									<input type="checkbox" name="girlSearch.chkCategory" id="girlSearch_chkCategory"
										<s:if test="girlSearch.chkCategory == 'true'">checked="checked"</s:if>
										value="true" />
									<label for="girlSearch_chkCategory"><s:text name="global.main_menu_service_shop" /></label>
									<select name="girlSearch.categoryInfoId" id="girlSearch_categoryInfoId" 
										<s:if test="girlSearch.chkCategory == 'true'"></s:if>
										<s:else>disabled="disabled"</s:else>
										>
										<option value=""></option>
										<s:iterator value="categoryInfos">
											<option value="<s:property value="categoryInfoId" />"
											<s:if test="girlSearch.categoryInfoId == categoryInfoId">selected="selected"</s:if>
											><s:property value="categoryNameEn" /></option>
										</s:iterator>
									</select>
								</div>
							</div> --%>
							<div class="inline field">
								<label class="screen">&nbsp;</label>
								<div class="ui checkbox">
									<input type="checkbox" name="girlSearch.chkAgents" id="girlSearch_chkAgents"
										<s:if test="girlSearch.chkAgents == 'true'">checked="checked"</s:if>
										value="true" />
									<label for="girlSearch_chkAgents"><s:text name="global.main_menu_agents" /></label>
									<select name="girlSearch.agentInfoId" id="girlSearch_agentInfoId"
										<s:if test="girlSearch.chkAgents == 'true'"></s:if>
										<s:else>disabled="disabled"</s:else>
										>
										<option value=""></option>
										<s:iterator value="agentInfos">
											<option value="<s:property value="agentInfoId" />"
											<s:if test="girlSearch.agentInfoId == agentInfoId">selected="selected"</s:if>
											><s:property value="agentName" /></option>
										</s:iterator>
									</select>
								</div>
							</div>
							<div class="inline field">
								<label class="screen">&nbsp;</label>
								<div class="ui checkbox">
									<input type="checkbox" name="girlSearch.chkFreeAgents" id="girlSearch_chkFreeAgents"
										<s:if test="girlSearch.chkFreeAgents == 'true'">checked="checked"</s:if>
										value="true" />
									<label for="girlSearch_chkFreeAgents"><s:text name="global.main_menu_free_agents" /></label>
								</div>
							</div>
							<%-- <div class="inline field">
								<label class="screen">&nbsp;</label>
								<div class="ui checkbox">
									<input type="checkbox" name="girlSearch.chkEnGirls" id="girlSearch_chkEnGirls"
										<s:if test="girlSearch.chkEnGirls == 'true'">checked="checked"</s:if>
										value="true" />
									<label for="girlSearch_chkEnGirls"><s:text name="global.main_menu_en_girls" /></label>
								</div>
							</div> --%>
						</s:i18n>
						<div class="inline field">
							<s:i18n name="global_th">
								<s:select list="genderInfos"
									listKey="genderInfoId" listValue="genderNameEn"
									key="global.gender_specify" 
									headerKey="" headerValue=""
									name="girlSearch.genderInfoId">
								</s:select>
							</s:i18n>
						</div>
						<div class="ui accordion">
							<div class="title">
								<label><s:i18n name="global_th"><s:text name="global.nationality" /></s:i18n></label>
								<i class="dropdown icon"></i>
							</div>
							<div class="content">
								<div class="ui four column grid doubling">
									<s:iterator value="nationalityInfos" status="rowstatus">
										<div class="column">
											<div class="field ui checkbox">
												<input type="checkbox" name="girlSearch.nationalityInfos" id="nationalityInfos_<s:property value="#rowstatus.count" />"
													<s:iterator value="girlSearch.nationalityInfos" >
														<s:property value="top" />
														<s:if test="top == nationalityInfoId">checked="checked"</s:if>
													</s:iterator>
													value="<s:property value="nationalityInfoId" />">
												<label for="nationalityInfoInfos_<s:property value="#rowstatus.count" />"><s:property value="nationalityNameEn" /></label>
											</div>
										</div>
									</s:iterator>
								</div>
							</div>
						</div>
						<br/>
						<div class="inline field">
							<label><s:i18n name="global_th"><s:text name="global.incall_outcall_specify" /></s:i18n></label>
							<select name="girlSearch.incallOutcall" id="girlSearch_incallOutcall">
								<option value=""></option>
								<option value="incall" <s:if test="girlSearch.incallOutcall == 'incall'">selected="selected"</s:if>><s:i18n name="global_th"><s:text name="global.incall" /></s:i18n></option>
								<option value="outcall" <s:if test="girlSearch.incallOutcall == 'outcall'">selected="selected"</s:if>><s:i18n name="global_th"><s:text name="global.outcall" /></s:i18n></option>
							</select>
						</div>
						<%-- <div class="accordion">
							<div class="inline field title">
								<label class="label"><s:text name="global.location" /> :
									<i class="dropdown icon"></i>
								</label>
							</div>
							<div class="content">
								<div class="ui four column grid doubling">
									<s:iterator value="zoneInfos" status="rowstatus">
										<div class="column">
											<div class="field ui checkbox">
												<input type="checkbox" name="girlSearch.zoneInfos" id="zoneInfos_<s:property value="#rowstatus.count" />"
													<s:iterator value="searchGirlLocations" >
														<s:property value="top" />
														<s:if test="top == zoneInfoId">checked="checked"</s:if>
													</s:iterator>
													value="<s:property value="zoneInfoId" />">
												<label for="zoneInfos_<s:property value="#rowstatus.count" />"><s:property value="zoneNameEn" /></label>
											</div>
										</div>
									</s:iterator>
								</div>
							</div>
						</div> --%>
						<div class="inline field">
							<s:i18n name="global_th">
								<s:select list="countryInfos"
									listKey="countryInfoId" listValue="countryNameEn"
									key="global.country" 
									headerKey="" headerValue=""
									name="girlSearch.countryInfoId">
								</s:select>
							</s:i18n>
						</div>
						<div class="ui accordion">
							<h4 class="title">
								<s:i18n name="global_th"><s:text name="global.province" /></s:i18n> :
								<i class="dropdown icon"></i>
							</h4>
							<div class="content">
								<div class="ui one column grid doubling" id="provinceInfos">
									<s:iterator value="provinceInfos" status="rowstatus">
										<div class="column">
											<div class="field ui">
												<div class="ui accordion">
													<div class="title ui">
														<label><s:property value="provinceNameEn" /></label>
														<i class="dropdown icon"></i>
													</div>
													<div class="content">
														<div class="ui four column grid" class="zoneInfos">
															<s:iterator value="top.zoneInfos" status="rowstatus">
																<div class="column">
																	<div class="field ui checkbox">
																		<input type="checkbox" name="girlSearch.zoneInfos" id="<s:property value="provinceInfoId" />_zoneInfos_<s:property value="#rowstatus.count" />"
																		<s:iterator value="girlSearch.zoneInfos" >
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
						<div class="ui error message"></div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button submit blue"><s:i18n name="global_th"><s:text name="global.search" /></s:i18n></div>
								<div class="ui small button clear"><s:i18n name="global_th"><s:text name="global.clear" /></s:i18n></div>
							</div>
						</div>
					</form>
				</div>
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th"><s:text name="global.menu_girls" /></s:i18n>
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<div class="ui right aligned one column grid">
							<div class="column">
								<form class="ui form " id="allSameFormTop" method="post" action="<s:url value="/admin/girls/update"/>" >
									<div class="ui right floated small purple submit button">
										<s:i18n name="global_th"><s:text name="global.submit" /></s:i18n>
									</div>
								</form>
							</div>
						</div>
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th><s:i18n name="global_th"><s:text name="global.girl_photo" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.nick_name" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.type" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.country" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.province" /></s:i18n></th>
									<th>
										<s:i18n name="global_th">
											<s:text name="global.all_same" />
										</s:i18n>
									</th>
									<th><s:i18n name="global_th"><s:text name="global.girl_tag" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.detail" /></s:i18n></th>
								</tr>
							</thead>
							<tfoot class="full-width">
								<tr>
									<th colspan="9">
										<form class="ui form " id="allSameForm" method="post" action="<s:url value="/admin/girls/update"/>" >
											<div class="ui right floated small purple submit button">
												<s:i18n name="global_th"><s:text name="global.submit" /></s:i18n>
											</div>
										</form>
									</th>
								</tr>
							</tfoot>
						</table>
						
					</div>
				</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_admin_management_footer.jsp" %>
</div>
</div>
  <script type="text/javascript">
  var girlProvinces = '';
  var girlTagInfos = '';
	girlTagInfos += '<div class="ui multiple two column dropdown tag_info">';
	girlTagInfos += '<input type="hidden" name="girlTag" data-girl_id="[girlInfoId]" value="[girlTagInfoId]">';
	girlTagInfos += '<i class="tag icon"></i>';
	girlTagInfos += '<span class="text"><s:i18n name="global_th"><s:text name="global.girl_tag" /></s:i18n></span>';
	girlTagInfos += '<div class="menu">';
	girlTagInfos += '<div class="ui icon search input">';
	girlTagInfos += '<i class="search icon"></i>';
	girlTagInfos += '<input type="text" placeholder="Search tags...">';
	girlTagInfos += '</div>';
	girlTagInfos += '<div class="divider"></div>';
	girlTagInfos += '<div class="header">';
	girlTagInfos += '<i class="tags icon"></i>';
	girlTagInfos += 'Tag Label';
	girlTagInfos += '</div>';
	girlTagInfos += '<div class="scrolling menu">';
	<s:iterator value="girlTagInfos" >
		girlTagInfos += '<div class="item" data-value="<s:property value="girlTagInfoId" />">';
		girlTagInfos += '<div class="ui <s:property value="color" /> empty circular label">';
		girlTagInfos += '</div>';
		girlTagInfos += '<div style="display:inline-block"><s:property value="girlTagNameEn" />';
		girlTagInfos += '</div>';
		girlTagInfos += '</div>';
	</s:iterator>
	girlTagInfos += '</div>';
	girlTagInfos += '</div>';
	girlTagInfos += '</div>';

  <s:iterator value="girlInfos" status="status">
  		girlProvinces = '';
	<s:iterator value="girlProvinces" >
	  	girlProvinces += '<s:property value="primaryKey.provinceInfo.provinceNameEn" />';
	  	girlProvinces += '<br />';
	</s:iterator>
		girlTags = '';
	<s:iterator value="girlTags" status="status" >
		<s:if test="#status.index > 0">
		girlTags += ',';
		</s:if>
		girlTags += '<s:property value="primaryKey.girlTagInfo.girlTagInfoId" />';
	</s:iterator>
		dataSet.push(
			['<s:property value="#status.count" />',
			<s:set name="pic1W160" value="%{pic1.replace('upload/', 'upload/w_160,c_scale/')}" />
			'<img class="image ui tiny centered" src="<s:property value="pic1W160" />">',
			<s:if test="%{top instanceof com.nightclub.model.AgentGirlInfo}" >
				"<s:property value="nickName" /><br/><s:property value="phone" />",
			</s:if>
			<s:else>
				"<s:property value="nickName" /><br/><s:property value="userInfo.phone" />",
			</s:else>
			<s:if test="%{top instanceof com.nightclub.model.ShopGirlInfo}" >
				<s:i18n name="global_th">
					"<s:text name="global.main_menu_service_shop" />" +
					"-" +
					"<s:property value="basicInfo.shopNameEn" />",
				</s:i18n>
			</s:if>
			<s:elseif test="%{top instanceof com.nightclub.model.AgentGirlInfo}" >
				<s:i18n name="global_th">
					"<s:text name="global.main_menu_agents" />" +
					"-" +
					"<s:property value="agentInfo.agentName" />",
				</s:i18n>
			</s:elseif>
			<s:elseif test="%{top instanceof com.nightclub.model.FreeAgentGirlInfo}" >
				<s:i18n name="global_th">
					"<s:text name="global.main_menu_free_agents" />",
				</s:i18n>
			</s:elseif>
			<s:elseif test="%{top instanceof com.nightclub.model.EnGirlInfo}" >
				<s:i18n name="global_th">
					"<s:text name="global.main_menu_en_girls" />",
				</s:i18n>
			</s:elseif>
			'<s:property value="countryInfo.countryNameEn" />',
			girlProvinces,
			'<div class="ui toggle fitted checkbox">' +
				'<input type="checkbox" name="allSame" ' +
				<s:if test="allSame == 'true'">'checked="checked"' + </s:if>
				'value="<s:property value="girlInfoId" />">' +
				'<label></label>' +
			'</div>',
			girlTagInfos.replace("[girlInfoId]", "<s:property value="girlInfoId" />")
					.replace("[girlTagInfoId]", girlTags),
			'<a href="<s:url value="/admin/girls/info/%{girlInfoId}"/>" class="ui icon button small green" ><i class="ui icon info"></i></a>'
    	]);
	</s:iterator>
	columnDefs = [
	  {  className: "center aligned", targets: [ 0, 6, 7 ] }
	];
	if (dataSet.length > 100) {
		pageLength = 150;
	} else if (dataSet.length > 75) {
		pageLength = 100;
	} else if (dataSet.length > 25) {
		pageLength = 50;
	} else if (dataSet.length > 10) {
		pageLength = 25;
	} else {
		pageLength = 10;
	}
  </script>
</body>
</html>