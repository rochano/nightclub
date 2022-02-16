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
  <title>THAINIGHTNAVI.COM - <s:text name="global.main_menu_service_shop" /></title>
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
  .ui.table, .ui.table tr th, .ui.table tr td {border-width:1px 0px!important;}
  </style>
</head>
<body>
<!-- Sidebar Menu -->
<s:set name="base_url" value="%{'shoplist/'}" />
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
					<div class="active section"><s:text name="global.main_menu_service_shop" /></div>
				</div>
				<div class="center aligned column">
	  				<div class="ui segment header">
						<h2 class="ui top header">
							<i class="bookmark icon yellow"></i>
							<div class="content"><s:text name="category.categoryNameJp" /></div>
						</h2>
					</div>
					<div class="ui grid attached segment">
						<div class="column one left aligned">
							<s:text name="category.description" />
						</div>
					</div>
				</div>
			    
				<div id="shoplist-wrapper" class="ui segment attached">
					<table id="shoplist" class="ui celled center aligned table structured unstackable orange" style="min-width: 733px;">
						<thead>
							<tr>
								<th rowspan="2"><s:text name="global.shop_list_header_pic" /></th>
								<th><s:text name="global.shop_list_header_name" /></th>
								<th><s:text name="global.shop_list_header_price" /></th>
								<th colspan="2"><s:text name="global.shop_list_header_tel_no" /></th>
								<th rowspan="2"><s:text name="global.shop_list_header_all_stuff" /></th>
							</tr>
							<tr>
								<th><s:text name="global.shop_list_header_address" /></th>
								<th><s:text name="global.shop_list_header_service_time" /></th>
								<th><s:text name="global.shop_list_header_time" /></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="basicInfos">
							<s:url value="/shop/" var="shopUrl" />
							<s:set name="system" value="" />
							<s:set name="girl" value="" />
							<s:set name="target" value="%{'target=\"_blank\"'}" />
							<s:if test="active == 'false'">
								<s:set name="url" value="%{'javascript:void()'}" />
							</s:if>
							<s:else>
								<s:set name="url" value="%{#shopUrl + shopInfoId}" />
								<s:set name="system" value="%{'/system'}" />
								<s:set name="girls" value="%{'/girls'}" />
							</s:else>
							<tr>
								<td class="image" rowspan="2" style="width: 7%;">
									<img class="ui tiny image centered" src="<s:property value="shopImg" />">
								</td>
								<td><a href="<s:property value="%{url}"/>" <s:property value="%{target}"/> class="ui header medium"><s:property value="shopNameJp" /></a></td>
								<td>
									<s:if test="systemInfo != null && systemInfo.classType == 'VIP'" >
										<a href="<s:property value="%{#url + #system}"/>" <s:property value="%{target}"/> class="ui"><b>
											<font color="red" size="2">
												<s:text name="format.integer"><s:param name="value" value="systemInfo.priceVIP1"/></s:text>
												&#3647
											</font></b></a>
									</s:if>
									<s:elseif test="systemInfo != null" >
										<a href="<s:property value="%{#url + #system}"/>" <s:property value="%{target}"/> class="ui"><b>
											<font color="red" size="2">
												<s:text name="format.integer"><s:param name="value" value="systemInfo.priceNormal1"/></s:text>
												&#3647
											</font></b></a>
									</s:elseif>
								</td>
								<td colspan="2"><a href="tel:<s:property value="phone" />" class="ui header tiny"><s:property value="phone" /></a></td>
								<td rowspan="2"><a href="<s:property value="%{#url + #girls}"/>" <s:property value="%{target}"/> class="ui">All Stuff</a></td>
							</tr>
							<tr>
								<td><a href="<s:property value="%{url}"/>" <s:property value="%{target}"/> class="ui header tiny"><s:property value="zoneInfo.zoneNameJp" /></a></td>
								<td>60分</td>
								<td><s:property value="startTime" />-<s:property value="endTime" /></td>
							</tr>
							</s:iterator>
							<s:if test="basicInfos.size() == 0" >
								<tr><td colspan="6">データかありません</td></tr>
							</s:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/common/common_footer.jsp" %>
</div>
</body>
</html>