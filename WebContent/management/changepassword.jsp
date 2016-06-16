<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Management - Change Password</title>

  <%@include file="/common/common_shop_management_header.jsp" %>

  <style>
  body {
    padding: 1em;
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
  </style>

  <!--- Example Javascript -->
  <script>
  $(document)
    .ready(function() {
      $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
      });
      $('.ui.menu a.item')
        .on('click', function() {
          $(this)
            .addClass('active')
            .siblings()
            .removeClass('active')
          ;
        })
      ;
	  $('.ui.dropdown')
	    .dropdown()
	  ;
    })
  ;
  </script>
</head>
<body>

<div class="ui container">
	<div class="ui grid"> 
		<div class="one wide column"></div>
		<div class="fourteen wide column">
			<%@include file="/common/common_shop_management_header_info.jsp" %>
			<%@include file="/common/common_shop_management_menu.jsp" %>
			<h4 class="ui top attached header inverted">
				Change password
			</h4>
			<div class="ui centered grid attached segment">
				<div class="column one">
					<form class="ui form" action="management/index.html">
						<div class="required inline field">
							<label>Username</label>
							<input type="text">
						</div>
						<div class="required inline field">
							<label>Password</label>
							<input type="password">
						</div>
						<div class="required inline field">
							<label>Confirm Password</label>
							<input type="password">
						</div>
						<div class="ui error message"></div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button blue">Submit</div>
								<div class="ui small button">Clear</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="one wide column"></div>
	</div>
  
</div>
  
</body>
</html>