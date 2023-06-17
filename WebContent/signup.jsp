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
    #togglePassword {
	    pointer-events: all;
	    cursor: pointer;
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
                  prompt : '<s:text name="global.username_or_email" /><s:text name="global.message_please_input_jp" />'
                }/* ,
                {
                  type   : 'email',
                  prompt : 'Please enter a valid e-mail.'
                } */
              ]
            },
            email: {
                identifier  : 'email',
                rules: [
                  {
                    type   : 'empty',
                    prompt : '<s:text name="global.email" /><s:text name="global.message_please_input_jp" />'
                  },
                  {
                    type   : 'email',
                    prompt : '<s:text name="global.message_please_enter_valid_email" />'
                  }
                ]
              },
            password: {
              identifier  : 'password',
              rules: [
                {
                  type   : 'empty',
                  prompt : '<s:text name="global.password" /><s:text name="global.message_please_input_jp" />'
                },
                {
                  type   : 'length[6]',
                  prompt : '<s:text name="global.password" /><s:text name="global.message_valid_length_jp" ><s:param value="6" /></s:text>'
                }
              ]
            },
            confirmpassword: {
              identifier  : 'confirmpassword',
              rules: [
                {
                  type   : 'match[password]',
                  prompt : '<s:text name="global.signup_confirm_password" /><s:text name="global.message_match_password" />'
                },
              ]
            },
            chk_over18: {
                identifier  : 'chk_over18',
                rules: [
                  {
                    type   : 'checked',
                    prompt : '<s:text name="global.must_over_18" />'
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

      $('#togglePassword')
      .on('click', function() {
    	var password = $("#password")[0];
        if(password.getAttribute('type') == "password") {
        	password.setAttribute('type', "text");
        	$(this).removeClass("fa-eye-slash");
        	$(this).addClass("fa-eye");
        } else {
        	password.setAttribute('type', "password");
        	$(this).addClass("fa-eye-slash");
        	$(this).removeClass("fa-eye");
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
					<s:text name="global.main_menu_login" />
				</a>
			</div>
		</div>
	</div>
	<div class="ui middle aligned center aligned grid">
		<div class="column">
			<h2 class="ui teal image header">
				<img src="assets/images/logo.png" class="image">
				<div class="content">
					<s:text name="global.main_menu_register" />
				</div>
			</h2>
			<form class="ui large form inverted" name="form1" method="post" action="<s:url value="/signup"/>">
				<div class="ui segment basic">
					<div class="field">
						<label><s:text name="global.username" /></label>
						<div class="ui input">
							<input type="text" name="username" value="" id="username" placeholder="<s:text name="global.username" />" />
						</div>
					</div>
					<div class="field">
						<label><s:text name="global.email" /></label>
						<div class="ui input">
							<input type="text" name="email" value="" id="email" placeholder="<s:text name="global.email" />" />
						</div>
					</div>
					<div class="field">
						<label><s:text name="global.password" /></label>
						<div class="ui icon input">
							<i class="fa-eye-slash icon" id="togglePassword"></i>
							<input type="password" name="password" id="password" placeholder="<s:text name="global.password" />" />
						</div>
					</div>
					<div class="field">
						<label><s:text name="global.signup_confirm_password" /></label>
						<div class="ui icon input">
							<i class="lock icon"></i>
							<input type="password" name="confirmpassword" id="confirmpassword" placeholder="<s:text name="global.signup_confirm_password" />" />
						</div>
					</div>
							<div class="field">
								<label><s:text name="global.user_type" /></label>
							</div>
							<div class="grouped fields">
								<div class="field">
									<div class="ui left icon input">
										<s:select list="#{'2':getText('global.main_menu_agents'), 
														'3':getText('global.main_menu_free_agents'), 
														'5':getText('global.main_menu_en_girls')}"
											name="userType">
										</s:select>
										<input type="hidden" name="chkUserType" id="userType_list"
												checked value="list">
									</div>
								</div>
							</div>
							<div class="field inverted">
							 	<div class="ui left icon input">
									<div class="ui checkbox">
										<input type="checkbox" name="chk_over18" id="chk_over18">
										<label for="chk_over18"><s:text name="global.over_18_years_old" /></label>
									</div>
								</div>
							</div>
					<div class="ui fluid large teal submit button"><s:text name="global.main_menu_register" /></div>
				</div>
				<div class="ui error message"><s:actionerror cssClass="list" /></div>
			</form>
		</div>
	</div>
</body>

</html>
