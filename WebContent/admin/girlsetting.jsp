<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Administrator - Girl Service</title>

  <%@include file="/common/common_admin_management_header.jsp" %>

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
  .ui.form .inline.field>:first-child, .ui.form .inline.fields>label:first-child {
  	width: 150px;
  	margin: 0;
  }
  </style>

  <!--- Example Javascript -->
  <script>
  $(document)
    .ready(function() {
      $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
      });
      $('.ui.dropdown')
      .dropdown()
    ;
      $('.ui.menu a.item')
        .on('click', function() {
          $(this)
            .addClass('active')
            .siblings()
            .removeClass('active')
          ;
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
	  $('.ui.form')
      .form({
          fields: {
        	  girlSetting_ageFrom: {
                    identifier  : 'girlSetting_ageFrom',
                    rules: [
                      {
                        type   : 'integer',
                        prompt : 'Please enter a valid age'
                      },
                    ]
                  },
           	  girlSetting_ageTo: {
                     identifier  : 'girlSetting_ageTo',
                     rules: [
                       {
                         type   : 'integer',
                         prompt : 'Please enter a valid age'
                       },
                     ]
                   },
              girlSetting_bustSizeFrom: {
                  identifier  : 'girlSetting_bustSizeFrom',
                  rules: [
                    {
                      type   : 'integer',
                      prompt : 'Please enter a valid bust size'
                    },
                  ]
                },
              girlSetting_bustSizeTo: {
                  identifier  : 'girlSetting_bustSizeTo',
                  rules: [
                    {
                      type   : 'integer',
                      prompt : 'Please enter a valid bust size'
                    },
                  ]
                },
              girlSetting_waistSizeFrom: {
                  identifier  : 'girlSetting_waistSizeFrom',
                  rules: [
                    {
                      type   : 'integer',
                      prompt : 'Please enter a valid waist size'
                    },
                  ]
                },
               girlSetting_waistSizeTo: {
                   identifier  : 'girlSetting_waistSizeTo',
                   rules: [
                     {
                       type   : 'integer',
                       prompt : 'Please enter a valid waist size'
                     },
                   ]
                 },
               girlSetting_hipSizeFrom: {
                identifier  : 'girlSetting_hipSizeFrom',
                rules: [
                  {
                    type   : 'integer',
                    prompt : 'Please enter a valid hip size'
                  },
                ]
              },
              girlSetting_hipSizeTo: {
                  identifier  : 'girlSetting_hipSizeTo',
                  rules: [
                    {
                      type   : 'integer',
                      prompt : 'Please enter a valid hip size'
                    },
                  ]
                },
              girlSetting_heightFrom: {
                  identifier  : 'girlSetting_heightFrom',
                  rules: [
                    {
                      type   : 'integer',
                      prompt : 'Please enter a valid height'
                    },
                  ]
                },
              girlSetting_heightTo: {
                  identifier  : 'girlSetting_heightTo',
                  rules: [
                    {
                      type   : 'integer',
                      prompt : 'Please enter a valid height'
                    },
                  ]
                },
              girlSetting_weightFrom: {
                  identifier  : 'girlSetting_weightFrom',
                  rules: [
                    {
                      type   : 'integer',
                      prompt : 'Please enter a valid weight'
                    },
                  ]
                },
              girlSetting_weightTo: {
                  identifier  : 'girlSetting_weightTo',
                  rules: [
                    {
                      type   : 'integer',
                      prompt : 'Please enter a valid weight'
                    },
                  ]
                },
          },
    	  onFailure: function() {
     		  window.scrollTo(0,0);
     		  return false;
     	  }
       })
      ;
	  $("table").tablesort();
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
				<div class="ui success message green inverted">
					<i class="close icon"></i>
					<div class="header">
						<s:actionmessage cssClass="list" />
					</div>
				</div>
			</s:if>
			<div class="ui accordion">
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					Girl Setting
				</h4>
				<div class="ui centered attached segment active content">
					<form class="ui form " method="post" action="<s:url value="/admin/girlsetting/update"/>">
						<div class="ui error message"><s:actionerror cssClass="list" /></div>
						<div class="inline fields">
							<label>Age</label>
							<div class="field">
								<s:textfield name="girlSetting.ageFrom" size="5" />
							</div>
							<label>-</label>
							<div class="field">
								<s:textfield name="girlSetting.ageTo" size="5" />
							</div>
						</div>
						<div class="inline fields">
							<label>Bust Size</label>
							<div class="field">
								<s:textfield name="girlSetting.bustSizeFrom" size="5" />
							</div>
							<label>-</label>
							<div class="field">
								<s:textfield name="girlSetting.bustSizeTo" size="5" />
							</div>
						</div>
						<div class="inline fields">
							<label>Waist Size</label>
							<div class="field">
								<s:textfield name="girlSetting.waistSizeFrom" size="5" />
							</div>
							<label>-</label>
							<div class="field">
								<s:textfield name="girlSetting.waistSizeTo" size="5" />
							</div>
						</div>
						<div class="inline fields">
							<label>Hip Size</label>
							<div class="field">
								<s:textfield name="girlSetting.hipSizeFrom" size="5" />
							</div>
							<label>-</label>
							<div class="field">
								<s:textfield name="girlSetting.hipSizeTo" size="5" />
							</div>
						</div>
						<div class="inline fields">
							<label>Height</label>
							<div class="field">
								<s:textfield name="girlSetting.heightFrom" size="5" />
							</div>
							<label>-</label>
							<div class="field">
								<s:textfield name="girlSetting.heightTo" size="5" />
							</div>
						</div>
						<div class="inline fields">
							<label>Weight</label>
							<div class="field">
								<s:textfield name="girlSetting.weightFrom" size="5" />
							</div>
							<label>-</label>
							<div class="field">
								<s:textfield name="girlSetting.weightTo" size="5" />
							</div>
						</div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button submit blue">Submit</div>
							</div>
						</div>
					</form>
				</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_admin_management_footer.jsp" %>  
</div>
</div>
</body>
</html>