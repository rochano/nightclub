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
    /*body > .grid {
      height: 70%;
    }*/
    .image {
      margin-top: -100px;
    }
    .column {
      max-width: 900px;
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
	      $('#username').parents(".field:first").addClass("error");
	      $('#password').parents(".field:first").addClass("error");
      </s:if>
    })
  ;
  </script>
</head>
<body>
	<div class="ui very basic segment right aligned grid">
		<div class="right floated column">
			<a class="ui labeled icon button" href="<s:url value="/admin/login"/>">
				<i class="sign in icon"></i>
				Log-in as Administrator
			</a>
		</div>
	</div>
	<s:if test="hasActionMessages()">
		<div class="ui small success message">
			<i class="close icon"></i>
			<div class="header">
				<s:actionmessage cssClass="list" />
			</div>
		</div>
	</s:if>
	<div class="ui middle aligned center aligned grid">
		<div class="column">
			<div class="ui two column middle aligned very relaxed stackable grid">
				<div class="column">
	  				<h2 class="ui teal image header">
						<img src="../assets/images/logo.png" class="image">
						<div class="content">
							Log-in to your account
						</div>
					</h2>
					<form class="ui large form inverted" name="form1" method="post" action="<s:url value="/shop/login"/>">
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
							<div class="ui fluid large teal submit button">Login</div>
						</div>
						<div class="ui error message"><s:actionerror cssClass="list" /></div>
					</form>
				</div>
				<div class="ui vertical divider inverted">
					Or
				</div>
				<div class="center aligned column">
					<a class="ui big green labeled icon button" href="<s:url value="/shop/signup"/>">
						<i class="signup icon"></i>
						Sign Up
					</a>
	    		</div>
			</div>
		</div>
	</div>
</body>

</html>
