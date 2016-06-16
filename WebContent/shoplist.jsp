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
  <title>Night Club - Shop List</title>
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
  </style>
</head>
<body>
<!-- Sidebar Menu -->
<%@include file="/common/common_menu_sidebar.jsp" %>
<div class="pusher">
	<div class="ui segment very basic">
		
		<div class="ui centered grid">
			<div class="ten wide column container" id="container">
				<%@include file="/common/common_statistic_info.jsp" %>
				<%@include file="/common/common_menu.jsp" %>
  
				<h4 class="ui top attached header inverted">
					<i class="file outline icon"></i> 中洲をはじめ九州全エリアのソープランドを完全網羅！
				</h4>
				<div class="ui centered grid attached segment">
					<div class="column one left aligned">
						<s:text name="category.description" />
					</div>
				</div>
			    
				<div class="ui centered grid">
					<div class="column one left aligned overflow">
						<table id="shoplist" class="ui celled center aligned table structured unstackable orange" style="min-width: 733px;">
							<thead>
								<tr>
									<th rowspan="2"><s:text name="global.shop_list_header_pic" /></th>
									<th><s:text name="global.shop_list_header_name" /></th>
									<th><s:text name="global.shop_list_header_price" /></th>
									<th colspan="2"><s:text name="global.shop_list_header_tel_no" /></th>
									<th colspan="2"><s:text name="global.shop_list_header_street_view" /></th>
									<th rowspan="2"><s:text name="global.shop_list_header_news" /></th>
								</tr>
								<tr>
									<th><s:text name="global.shop_list_header_address" /></th>
									<th><s:text name="global.shop_list_header_service_time" /></th>
									<th><s:text name="global.shop_list_header_day" /></th>
									<th><s:text name="global.shop_list_header_time" /></th>
									<th><s:text name="global.shop_list_header_ranking" /></th>
									<th><s:text name="global.shop_list_header_all_stuff" /></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="basicInfos">
								<s:url value="/shop/" var="shopUrl" />
								<s:if test="%{chkCustomUrl == 'true'}">
									<s:set name="url" value="%{'http://' + customUrl}" />
								</s:if>
								<s:else>
									<s:set name="url" value="%{#shopUrl + shopCode}" />
								</s:else>
								<tr>
									<td class="image" rowspan="2" style="width: 7%;">
										<img class="ui fluid image centered" src="<s:property value="shopImg" />">
									</td>
									<td><p style="font-size: 12px;"><a href="<s:property value="url"/>" target="_blank" class="ui"><b><s:property value="shopNameEn" /><br>（<s:property value="shopNameJp" />）</b></a></p></td>
									<td>
										<s:if test="systemInfo != null" >
											<a href="<s:property value="url"/>/system" target="_blank" class="ui"><b>
												<font color="red" size="2">
													<s:text name="format.integer">
													<s:param name="value" value="systemInfo.price"/></s:text>
													&#3647
												</font></b></a>
										</s:if>
									</td>
									<td colspan="2"><a href="tel:<s:property value="phone" />"><s:property value="phone" /></a></td>
									<td colspan="2"><a target="_blank" href="http://maps.google.com/maps?q=&layer=c&cbll=<s:property value="mapInfo.latitude" />,<s:property value="mapInfo.longitude" />">ストリートビュー </a></td>
									<td rowspan="2"><a href="<s:property value="url"/>" target="_blank" class="ui blue button icon"><i class="external share icon"></i></a></td>
								</tr>
								<tr>
									<td><a href="<s:property value="url"/>" target="_blank" class="ui"><s:property value="address" /></a></td>
									<td>
										<s:if test="systemInfo != null" >
											<s:property value="systemInfo.duration" />分
										</s:if>
									</td>
									<td>
										<s:if test="startTime != null"><s:property value="dayOfWeek[startDayOfWeek]" /></s:if>
										-
										<s:if test="endTime != null"><s:property value="dayOfWeek[endDayOfWeek]" /></s:if>
									</td>
									<td><s:property value="startTime" />-<s:property value="endTime" /></td>
									<td>
										<s:if test="%{chkCustomUrl != 'true'}">
										<a href="<s:property value="url"/>/ranking" target="_blank" class="ui">Ranking</a>
										</s:if>
									</td>
									<td>
										<s:if test="%{chkCustomUrl != 'true'}">
										<a href="<s:property value="url"/>/girls" target="_blank" class="ui">All Stuff</a>
										</s:if>
									</td>
								</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/common/common_footer.jsp" %>
</div>
</body>
</html>