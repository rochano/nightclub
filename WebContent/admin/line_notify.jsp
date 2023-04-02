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
  <title><s:i18n name="global_th"><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.menu_line_notify" /></s:i18n></title>

  <%@include file="/common/common_admin_management_header.jsp" %>
  
    <style>
  body {
    padding: 1em;
  }
  .ui.form .inline.field>:first-child, .ui.form .inline.fields>label:first-child {
  	width: 150px;
  	margin: 0;
  }
  .ui.form .inline.field>input {
   width: 100%;
  }
  </style>
<script type="text/javascript">
  $(document)
    .ready(function() {
    	$('.ui.form')
        .form({
        	fields: {}
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
    })
  ;
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
					<s:i18n name="global_th"><s:text name="global.menu_line_notify" /></s:i18n>
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<form class="ui form " method="post" action="<s:url value="/admin/lineNotify/update"/>" >
							<div class="ui right aligned one column grid">
								<div class="column">
									<div class="ui small button submit blue"><s:i18n name="global_th"><s:text name="global.submit" /></s:i18n></div>
								</div>
							</div>
							<div class="inline field">
								<s:i18n name="global_th"><s:textfield name="homeInfo.lineClientId" key="global.line_client_id" /></s:i18n>
							</div>
							<div class="inline field">
								<s:i18n name="global_th"><s:textfield name="homeInfo.lineClientSecret" key="global.line_client_secret" /></s:i18n>
							</div>
							<div class="inline field">
								<s:i18n name="global_th"><s:textfield name="homeInfo.lineRedirectUrl" key="global.line_redirect_url" /></s:i18n>
							</div>
							<div class="inline field ui checkbox">
								<s:i18n name="global_th"><s:checkbox name="homeInfo.lineNotifyActive" key="global.line_notify_active" /></s:i18n>
							</div>
							<div class="ui right aligned one column grid">
								<div class="column">
									<div class="ui small button submit blue"><s:i18n name="global_th"><s:text name="global.submit" /></s:i18n></div>
								</div>
							</div>
						</form>
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