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
  <title><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.menu_girls" /></title>

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
      $('#allSameForm')
      .form({
          onSuccess: function() { 
              var form = $(this);
              form.find("[name=allsamelist]").remove()
              $( "input[name=allSame]", dataTable.fnGetNodes()).each(function(i, item) {
                  var obj = {id: item.value, checked: item.checked};
            	  form.append("<input name='allsamelist' value='" + JSON.stringify(obj) + "' type='hidden' />")
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
					html += '		<input type="checkbox" name="girlSearch.provinceInfos" id="provinceInfos_' + i + '"';
					html += '			value="' + obj.provinceInfoId + '">';
					html += '		<label for="provinceInfos_' + i + '">' + obj.provinceNameEn + '</label>';
					html += '	</div>';
					html += '</div>';
			       $(html).appendTo(provinceInfos);
			     });
			});
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
							<s:textfield name="girlSearch.nickName" key="global.nick_name"/>
						</div>
						<s:i18n name="global_th">
							<div class="inline field">
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
							</div>
							<div class="inline field">
								<label>&nbsp;</label>
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
								<label>&nbsp;</label>
								<div class="ui checkbox">
									<input type="checkbox" name="girlSearch.chkFreeAgents" id="girlSearch_chkFreeAgents"
										<s:if test="girlSearch.chkFreeAgents == 'true'">checked="checked"</s:if>
										value="true" />
									<label for="girlSearch_chkFreeAgents"><s:text name="global.main_menu_free_agents" /></label>
								</div>
							</div>
							<div class="inline field">
								<label>&nbsp;</label>
								<div class="ui checkbox">
									<input type="checkbox" name="girlSearch.chkEnGirls" id="girlSearch_chkEnGirls"
										<s:if test="girlSearch.chkEnGirls == 'true'">checked="checked"</s:if>
										value="true" />
									<label for="girlSearch_chkEnGirls"><s:text name="global.main_menu_en_girls" /></label>
								</div>
							</div>
						</s:i18n>
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
						<div class="ui inverted accordion">
							<div class="inline field title">
								<label class="label"><s:i18n name="global_th"><s:text name="global.province" /></s:i18n> :
									<i class="dropdown icon"></i>
								</label>
							</div>
							<div class="content">
								<div class="ui four column grid doubling" id="provinceInfos">
									<s:iterator value="provinceInfos" status="rowstatus">
										<div class="column">
											<div class="field ui checkbox">
												<input type="checkbox" name="girlSearch.provinceInfos" id="provinceInfos_<s:property value="#rowstatus.count" />"
													<s:iterator value="girlSearch.provinceInfos" >
														<s:property value="top" />
														<s:if test="top == provinceInfoId">checked="checked"</s:if>
													</s:iterator>
													value="<s:property value="provinceInfoId" />">
												<label for="provinceInfos_<s:property value="#rowstatus.count" />"><s:property value="provinceNameEn" /></label>
											</div>
										</div>
									</s:iterator>
								</div>
							</div>
						</div>
						<div class="ui error message"></div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button submit blue"><s:text name="global.search" /></div>
								<div class="ui small button clear"><s:text name="global.clear" /></div>
							</div>
						</div>
					</form>
				</div>
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:text name="global.menu_girls" />
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th><s:text name="global.girl_photo" /></th>
									<th><s:text name="global.nick_name" /></th>
									<th><s:text name="global.type" /></th>
									<th><s:i18n name="global_th"><s:text name="global.country" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.province" /></s:i18n></th>
									<th>
										<s:i18n name="global_th">
											<s:text name="global.all_same" />
										</s:i18n>
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="girlInfos" status="status">
								<tr>
									<td class="center aligned"><s:property value="#status.count" /></td>
									<td>
										<img class="image ui tiny centered" src="<s:property value="pic1" />">
									</td>
									<td><s:property value="nickName" /></td>
									<td>
										<s:if test="%{top instanceof com.nightclub.model.ShopGirlInfo}" >
											<s:i18n name="global_th">
												<s:text name="global.main_menu_service_shop" />
												-
												<s:property value="basicInfo.shopNameEn" />
											</s:i18n>
										</s:if>
										<s:elseif test="%{top instanceof com.nightclub.model.AgentGirlInfo}" >
											<s:i18n name="global_th">
												<s:text name="global.main_menu_agents" />
												-
												<s:property value="agentInfo.agentName" />
											</s:i18n>
										</s:elseif>
										<s:elseif test="%{top instanceof com.nightclub.model.FreeAgentGirlInfo}" >
											<s:i18n name="global_th">
												<s:text name="global.main_menu_free_agents" />
											</s:i18n>
										</s:elseif>
										<s:elseif test="%{top instanceof com.nightclub.model.EnGirlInfo}" >
											<s:i18n name="global_th">
												<s:text name="global.main_menu_en_girls" />
											</s:i18n>
										</s:elseif>
									</td>
									<td><s:property value="countryInfo.countryNameEn" /></td>
									<td>
										<s:iterator value="girlProvinces" >
											<s:property value="primaryKey.provinceInfo.provinceNameEn" />
											<br />
										</s:iterator>
									</td>
									<td class="center aligned">
										<div class="ui toggle fitted checkbox">
											<input type="checkbox" name="allSame" 
											<s:if test="allSame == 'true'">checked="checked"</s:if>
											 value="<s:property value="girlInfoId" />">
											<label></label>
										</div>
									</td>
								</tr>
								</s:iterator>
							</tbody>
							<tfoot class="full-width">
								<tr>
									<th colspan="7">
										<form class="ui form " id="allSameForm" method="post" action="<s:url value="/admin/girls/update"/>" >
											<div class="ui right floated small primary submit button">
												<s:text name="global.submit" />
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
</body>
</html>