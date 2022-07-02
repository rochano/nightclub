<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title><s:text name="global.management" /> - <s:text name="global.change_password" /></title>

  <%@include file="/common/common_shop_management_header.jsp" %>

  <style>
  body {
    /*padding: 1em;*/
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
  .ui.form .inline.field>:first-child {
  	width: 150px;
  }
  </style>

  <!--- Example Javascript -->
  <script>
  $(document)
    .ready(function() {
    	$('.ui.form')
        .form({
          fields: {
            oldPassword: {
              identifier  : 'oldPassword',
              rules: [
                {
                  type   : 'empty',
                  prompt : '<s:text name="global.message_please_input" /><s:text name="global.old_password" />'
                }
              ]
            },
            password: {
              identifier  : 'password',
              rules: [
                {
                  type   : 'empty',
                  prompt : '<s:text name="global.message_please_input" /><s:text name="global.new_password" />'
                },
                {
                  type   : 'length[6]',
                  prompt : '<s:text name="global.new_password" /><s:text name="global.message_valid_length" ><s:param value="6" /></s:text>'
                }
              ]
            },
            confirmPassword: {
                identifier  : 'confirmPassword',
                rules: [
                  {
                    type   : 'empty',
                    prompt : '<s:text name="global.message_please_input" /><s:text name="global.confirm_password" />'
                  },
                  {
                    type   : 'match[password]',
                    prompt : '<s:text name="global.confirm_password" /><s:text name="global.message_match_new_password" />'
                  }
                ]
             }
          }
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
	<s:if test="hasActionErrors()">
  	  $('.ui.form').addClass("error");
  	</s:if>
    })
  ;
	
  </script>
</head>
<body class="menu pushable">
<%@include file="/common/common_client_management_header_info.jsp" %>
<!-- Sidebar Menu -->
<div class="ui toc vertical top large inverted sidebar menu">
  	<a class="ui toc item title">
		<i class="cancel icon"></i>
		&nbsp;
	</a>
	<%@include file="/common/common_client_management_menu.jsp" %>
</div>
<div class="pusher">
<div class="full height">
<div class="toc">
	<!-- Sidebar Menu -->
	<div class="ui inverted vertical menu">
		<%@include file="/common/common_client_management_menu.jsp" %>
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
					<s:text name="global.change_password" />
				</h4>
				<div class="ui centered attached segment active content">
					<form class="ui form" method="post" action="<s:url value="/management_client/changepassword/update"/>">
						<div class="ui error message">
							<s:if test="hasActionErrors()">
								<i class="close icon"></i>
								<div class="header">
									<s:actionerror cssClass="list" />
								</div>
							</s:if>
						</div>
						<div class="required inline field">
							<s:password name="oldPassword" key="global.old_password"/>
						</div>
						<div class="required inline field">
							<s:password name="password" key="global.new_password"/>
						</div>
						<div class="required inline field">
							<s:password name="confirmPassword" key="global.confirm_password"/>
						</div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button submit blue"><s:text name="global.submit" /></div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
  	<%@include file="/common/common_client_management_footer.jsp" %>  
</div>
</div>
</body>
</html>