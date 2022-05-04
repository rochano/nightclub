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
                  prompt : 'Please enter your username.'
                }/* ,
                {
                  type   : 'email',
                  prompt : 'Please enter a valid e-mail.'
                } */
              ]
            },
            phone: {
                identifier  : 'phone',
                rules: [
                  {
                    type   : 'empty',
                    prompt : 'Please enter your phone number.'
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
            },
            chk_over18: {
                identifier  : 'chk_over18',
                rules: [
                  {
                    type   : 'checked',
                    prompt : 'You must agree to the over 18 years conditions'
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

      $('[name=chkUserType]')
      .on('change', function() {
        if($(this).val() == "list") {
            $("#userType").parents(".field:first").removeClass("disabled");
        } else {
        	$("#userType").parents(".field:first").addClass("disabled");
        }
      })
    ;
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
					Log-in to your account
				</div>
			</h2>
			<form class="ui large form inverted" name="form1" method="post" action="<s:url value="/signup"/>">
				<div class="ui segment basic">
					<div class="field">
						<label>Username</label>
						<div class="ui left icon input">
							<i class="user icon"></i>
							<s:textfield name="username" placeholder="Username" />
						</div>
					</div>
					<div class="field">
						<label>Phone number</label>
						<div class="ui left icon input">
							<i class="phone icon"></i>
							<s:textfield name="phone" placeholder="Phone number" />
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
							</div>
							<div class="grouped fields">
								<div class="field">
									<div class="ui left icon input">
										<div class="ui radio checkbox">
											<input type="radio" name="chkUserType" id="userType_list"
												checked value="list">
											<label for="userType_list">ลูกค้าที่ต้องการโพสต์ข้อมูล</label>
										</div>
									</div>
								</div>
								<div class="field">
									<div class="ui left icon input">
										<s:select list="#{'1':getText('global.main_menu_service_shop'), 
														'2':getText('global.main_menu_agents'), 
														'3':getText('global.main_menu_free_agents'), 
														'5':getText('global.main_menu_en_girls')}"
											name="userType">
										</s:select>
									</div>
								</div>
								<div class="field">
									<div class="ui left icon input">
										<div class="ui radio checkbox">
											<input type="radio" name="chkUserType" id="userType_4" disabled="disabled"
												value="4">
											<label for="userType_4">ลูกค้าที่ต้องการค้นหาข้อมูล</label>
										</div>
									</div>
								</div>
							</div>
							<div class="field inverted">
							 	<div class="ui left icon input">
									<div class="ui checkbox">
										<input type="checkbox" name="chk_over18" id="chk_over18">
										<label for="chk_over18">คุณยอมรับว่ามีอายุมากกว่า 18 ปี</label>
									</div>
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
