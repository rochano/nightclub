<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Shop - Home</title>
  
  <%@include file="/common/common_shop_header.jsp" %>

</head>
<body>
<!-- Sidebar Menu -->
<%@include file="/common/common_shop_menu_sidebar.jsp" %>
<div class="pusher">
	<div class="ui segment very basic">
		<div class="ui centered grid">
			<div class="eleven wide column container" id="container">
				<%@include file="/common/common_statistic_info.jsp" %>
				<%@include file="/common/common_shop_header_info.jsp" %>
				<div class="ui menu inverted stackable">
					<a class="toc item"><i class="sidebar icon"></i></a>
					<%@include file="/common/common_shop_menu.jsp" %>
				</div>
				
				<div class="center aligned column">
		  			<div class="ui segment header">
						<h2 class="ui top header">
							<i class="bookmark icon yellow"></i>
							<div class="content"><s:text name="global.shop_menu_home" /></div>
						</h2>
					</div>
					<div class="ui grid attached segment ">
						<div class="column one left aligned">
							<s:text name="shop.description" />
						</div>
					</div>
				</div>
			</div>
		</div>
	  
	</div>
	<%@include file="/common/common_shop_footer.jsp" %>
</div>
</body>
</html>