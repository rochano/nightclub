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
                  prompt : 'Please enter your e-mail.'
                },
                {
                  type   : 'email',
                  prompt : 'Please enter a valid e-mail.'
                }
              ]
            },
            password: {
              identifier  : 'password',
              rules: [
                {
                  type   : 'empty',
                  prompt : 'Please enter your password.'
                },
                {
                  type   : 'length[6]',
                  prompt : 'Your password must be at least 6 characters.'
                }
              ]
            },
            confirmpassword: {
              identifier  : 'confirmpassword',
              rules: [
                {
                  type   : 'match[password]',
                  prompt : 'Confirm password mismatch.'
                },
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
					<i class="sign in icon"></i>
					Log-in
				</a>
			</div>
		</div>
	</div>
	<div class="ui middle aligned center aligned grid">
		<div class="column">
			<h2 class="ui teal image header">
				<img src="assets/images/logo.png" class="image">
				<div class="content">
					Sign-up for registration
				</div>
			</h2>
			<form class="ui large form inverted" name="form1" method="post" action="<s:url value="/signup"/>">
				<div class="ui segment basic">
					<div class="field">
						<label>Username</label>
						<div class="ui left icon input">
							<i class="user icon"></i>
							<s:textfield name="username" placeholder="E-mail address" />  
						</div>
					</div>
					<div class="field">
						<label>Password</label>
						<div class="ui left icon input">
							<i class="lock icon"></i>
							<s:password name="password" placeholder="Password" />  
						</div>
					</div>
					<div class="field">
						<label>Confirm Password</label>
						<div class="ui left icon input">
							<i class="lock icon"></i>
							<s:password name="confirmpassword" placeholder="Confirm Password" />  
						</div>
					</div>
					<div class="field">
						<label>User Type</label>
						<div class="ui left icon input">
							<s:select list="#{'1':'サービス店', '2':'彼女紹介', '3':'素人援交', '4':'นักท่องเที่ยวมาตามหาน้องๆ'}"
								name="userType">
							</s:select>
						</div>
					</div>
					<div class="ui fluid large teal submit button">Signup</div>
				</div>
				<div class="ui error message"><s:actionerror cssClass="list" /></div>
			</form>
		</div>
	</div>
</body>

</html>
