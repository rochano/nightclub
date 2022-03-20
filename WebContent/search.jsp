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
  </style>
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
					<div class="ui centered attached segment soft">
						<div class="ui centered four column doubling grid data ">
						<s:if test="%{zoneInfos.size gte 0}">
							<s:iterator value="zoneInfos" status="status">
								<s:if test="#request.locale.language=='th'">
									<a href="<s:url value="/search/%{zoneInfoId}"/>" class="ui column center aligned inverted">
										<s:property value="zoneNameEn" />
									</a>
								</s:if>
								<s:else>
									<a href="<s:url value="/search/%{zoneInfoId}"/>" class="ui column center aligned inverted">
										<s:property value="zoneNameJp" />
									</a>
								</s:else>
							</s:iterator>
						</s:if>
						<s:if test="%{zoneInfos.size eq 0}">
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