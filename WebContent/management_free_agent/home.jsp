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
  <title><s:i18n name="global_th"><s:text name="global.management" /> - <s:text name="global.menu_home" /></s:i18n></title>

  <%@include file="/common/common_shop_management_header.jsp" %>

</head>
<body class="menu pushable">
<%@include file="/common/common_free_agent_management_header_info.jsp" %>
<!-- Sidebar Menu -->
<div class="ui toc vertical top large inverted sidebar menu">
  	<a class="ui toc item title">
		<i class="cancel icon"></i>
		&nbsp;
	</a>
	<%@include file="/common/common_free_agent_management_menu.jsp" %>
</div>
<div class="pusher">
<div class="full height">
<div class="toc">
	<!-- Sidebar Menu -->
	<div class="ui inverted vertical menu">
		<%@include file="/common/common_free_agent_management_menu.jsp" %>
	</div>
</div>
<div class="article">
<div class="ui segment very basic container">
			<div class="ui menu inverted stackable toc left aligned">
		  		<a class="toc item"><i class="sidebar icon"></i></a>
		  	</div>
			<div class="ui accordion">
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th"><s:text name="global.menu_home" /></s:i18n>
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one center aligned">
						<h2 class="ui header"><s:i18n name="global_th"><s:text name="global.welcome_to" /> <s:text name="global.management" /></s:i18n></h2>
						<div class="welcome_text_small_size_screen">
							<h2 class="ui header "><s:i18n name="global_th"><s:text name="global.welcome_text" /></s:i18n></h2>
							<h3 class="ui header">(<i class="sidebar icon"></i>)</h3>
						</div>
					</div>
				</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_free_agent_management_footer.jsp" %>  
</div>
</div>
</body>
</html>