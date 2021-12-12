<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>THAINIGHTNAVI.COM - Home</title>

 <%@include file="/common/common_header.jsp" %>
  <!--- Example CSS -->
  <style>
  #girls.ui.grid{
  	margin: 0;
  }
  #girls.ui.grid > .column:not(.row) {
  	padding: 0;
  }
  @media only screen and (max-width: 767px) {
  .ui.huge.button {
  	font-size: 1rem;
  }
  }
  </style>

  <!--- Example Javascript -->
</head>
<body>
<!-- Sidebar Menu -->
<%@include file="/common/common_menu_sidebar.jsp" %>
<div class="pusher">
	<div class="ui segment very basic">
		<div class="ui centered grid"> 
			<div class="eleven wide column container" id="container">
				<%@include file="/common/common_statistic_info.jsp" %>
				<%@include file="/common/common_menu.jsp" %>
				<br/>
				<div class="ui breadcrumb segment attached inverted">
					<a class="section" href="<s:url value="/" />" >
						<s:text name="global.shop_menu_home" />
					</a>
					<i class="right chevron icon divider"></i>
					<div class="active section">サイト最新情報</div>
				</div>
				<div class="ui segment attached">
					<h2 class="ui top header">
						<i class="newspaper icon"></i>
						<div class="content">
							<s:property value="newsInfo.title" />
							<div class="sub header">
								<s:date name="newsInfo.newsDate" format="dd MMMM yyyy" />
								at
								<s:property value="newsInfo.newsTime" />
							</div>
						</div>
					</h2>
					<div class="ui centered grid attached segment">
						<div class="column one left aligned">
							<s:text name="newsInfo.description"></s:text>
				 		</div>
					</div>
				</div>
				
			</div>
		</div>
	</div><br />
	 <%@include file="/common/common_footer.jsp" %>
</div>
</body>
</html>