<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

  <%@include file="/common/common_shop_header.jsp" %>

  <style type="text/css">
    body {
      background-color: #333;
    }
    body > .grid {
      height: 100%;
    }
    .image {
      margin-top: -100px;
    }
    .column {
      max-width: 450px;
    }
    .ui.form .field > label {
    	text-align: left;
    }
    .ui.stackable.grid .ui.vertical.divider {
    	margin: auto;
    }
  </style>
  <script>
  $(document)
    .ready(function() {
      $('.ui.form')
        .form({
          fields: {
            username: {
              identifier  : 'username',
              rules: [
                {
                  type   : 'empty',
                  prompt : '<s:text name="global.username_or_phone" /><s:text name="global.message_please_input_jp" />'
                }/* ,
                {
                  type   : 'email',
                  prompt : 'Please enter a valid e-mail.'
                } */
              ]
            },
            password: {
              identifier  : 'password',
              rules: [
                {
                  type   : 'empty',
                  prompt : '<s:text name="global.password" /><s:text name="global.message_please_input_jp" />'
                }/* ,
                {
                  type   : 'length[6]',
                  prompt : '<s:text name="global.password" /><s:text name="global.message_valid_length_jp" ><s:param value="6" /></s:text>'
                } */
              ]
            }
          }
        })
      ;
      
      <s:if test="hasActionErrors()">
	      $('.ui.form').addClass("error");
	      $('#username').parents(".field:first").addClass("error");
	      $('#password').parents(".field:first").addClass("error");
      </s:if>
    })
  ;
  </script>
</head>
<body>
	<div class="ui secondary pointing menu stackable fixed">
		<div class="left menu">
			<div class="header item">
				<a class="ui labeled icon button" href="<s:url value="/login"/>">
					<i class="long arrow left icon"></i>
					<s:text name="global.main_menu_login" />
				</a>
			</div>
		</div>
	</div>
	<div class="ui middle aligned center aligned grid">
		<div class="column">
			<h2 class="ui teal image header">
				<img src="../assets/images/logo.png" class="image">
				<div class="content">
					<s:text name="global.login_as_admin" />
				</div>
			</h2>
			<form class="ui large form inverted" name="form1" method="post" action="<s:url value="/admin/login"/>">
				<div class="ui segment basic">
					<div class="field">
						<label><s:text name="global.username" /></label>
						<div class="ui left icon input">
							<i class="user icon"></i>
							<input type="text" name="username" value="" id="username" placeholder="<s:text name="global.username" />" />
						</div>
					</div>
					<div class="field">
						<label><s:text name="global.password" /></label>
						<div class="ui left icon input">
							<i class="lock icon"></i>
							<input type="password" name="password" id="password" placeholder="<s:text name="global.password" />" />
						</div>
					</div>
					<div class="ui fluid large teal submit button"><s:text name="global.main_menu_login" /></div>
				</div>
				<div class="ui error message"><s:actionerror cssClass="list" /></div>
			</form>
		</div>
	</div>
</body>

</html>
