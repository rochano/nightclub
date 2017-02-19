<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Management - Home</title>

  <%@include file="/common/common_shop_management_header.jsp" %>

</head>
<body class="menu pushable">
<%@include file="/common/common_shop_management_header_info.jsp" %>
<div class="pusher">
<div class="full height">
<div class="toc">
	<!-- Sidebar Menu -->
	<div class="ui inverted vertical menu">
		<%@include file="/common/common_shop_management_menu.jsp" %>
	</div>
</div>
<div class="article">
<div class="ui segment very basic container">
			<div class="ui accordion">
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					Home
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one center aligned">
						<h2 class="ui header">Welcome to shop management<h2>
					</div>
			</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_shop_management_footer.jsp" %>  
</div>
</div>
</body>
</html>