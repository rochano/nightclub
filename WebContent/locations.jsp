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
  <title>THAINIGHTNAVI.COM - <s:text name="global.main_menu_agents" /></title>
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
  </style>
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
					<a class="section" href="<s:url value="/search" />" >
						<s:text name="global.main_menu_search" />
					</a>
					<i class="right chevron icon divider"></i>
					<div class="active section">
						<s:if test="#request.locale.language=='jp'">
							<s:text name="zoneInfo.zoneNameJp" />
						</s:if>
						<s:else>
							<s:text name="zoneInfo.zoneNameEn" />
						</s:else>
					</div>
				</div>

				<div class="center aligned column">
					<div class="ui segment header">
						<h2 class="ui top header">
							<i class="bookmark icon yellow"></i>
							<div class="content">
								<s:if test="#request.locale.language=='jp'">
									<s:text name="zoneInfo.zoneNameJp" />
								</s:if>
								<s:else>
									<s:text name="zoneInfo.zoneNameEn" />
								</s:else>
									</div>
						</h2>
					</div>
					<div class="ui centered attached segment soft">
						<div class="ui centered six doubling cards ">
						<s:if test="%{girlInfos.size gte 0}">
							<s:iterator value="girlInfos" status="status">
								<div class="ui red card">
									<div class="image ui centered corner labeled" >
										<a class="ui right corner label">
											<i class="heart outline icon"></i>
										</a>
										<a href="<s:url value="/girl/%{girlInfoId}"/>" >
											<img class="image ui centered" src="<s:property value="pic1" />">
										</a>
									</div>
									<div class="content left aligned label pink circular ui">
										<span class="right floated">
											<s:text name="format.integer"><s:param name="value" value="price40Mins"/></s:text>
										</span>
										ตรงปก
									</div>
									<div class="content left aligned">
										<a class="ui header " href="<s:url value="/girl/%{girlInfoId}"/>"><s:property value="nickName" /></a>
										<div class="description">
											<s:property value="agentInfo.agentName" /><br/>
											<i class="marker icon"></i><s:property value="zoneInfo.zoneNameEn" />
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