<%@ taglib prefix="s" uri="/struts-tags"%>
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
  <title><s:i18n name="global_th"><s:text name="global.management" /> - <s:text name="global.menu_agent_info" /></s:i18n></title>

  <%@include file="/common/common_shop_management_header.jsp" %>
  <script src="<s:url value="/assets/library/jquery.form.js"/>"></script>
  <script src="<s:url value="/assets/library/fileUploadScript.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.ui.widget.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload-process.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload-ui.js"/>"></script>

  <style type="text/css">
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
  <script type="text/javascript">
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
	  $('.ui.form')
      .form({
          fields: {},
     	  onFailure: function() {
     		  window.scrollTo(0,0);
     		  return false;
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
      $('#filelogoImg').fileupload({
			url: '<s:url value="/UploadFileServlet"/>',
			dataType: 'json',
			add: function (e, data) {
				data.submit();
			},
	        success:function(response,status) {
		        console.log(arguments)
				console.log(response.fileName);
		        var fileName = response.fileName;
				var filePath = response.path;
				var image = "<img src='" + filePath + fileName + "' />";
				$("#logoImg").html(image);
				$('#agentLogoFileName').val(fileName);
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
		});
    })
  ;
  </script>
</head>
<body class="menu pushable">
<%@include file="/common/common_agent_management_header_info.jsp" %>
<!-- Sidebar Menu -->
<div class="ui toc vertical top large inverted sidebar menu">
  	<a class="ui toc item title">
		<i class="cancel icon"></i>
		&nbsp;
	</a>
	<%@include file="/common/common_agent_management_menu.jsp" %>
</div>
<div class="pusher">
<div class="full height">
<div class="toc">
	<!-- Sidebar Menu -->
	<div class="ui inverted vertical menu">
		<%@include file="/common/common_agent_management_menu.jsp" %>
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
					<s:i18n name="global_th"><s:text name="global.menu_agent_info" /></s:i18n>
				</h4>
				<div class="ui centered attached segment active content">
					<form class="ui form " method="post" action="<s:url value="/management_agent/agentinfo/update"/>" enctype="multipart/form-data" >
						<div class="ui error message"><s:actionerror cssClass="list" /></div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="agentInfo.agentName" key="global.agent_name"/></s:i18n>
						</div>
						<div class="two fields">
							<div class="inline field">
								<label><s:i18n name="global_th"><s:text name="global.logo" /></s:i18n></label>
								<div class="image ui small">
									<div id="logoImg">
										<s:if test="%{agentInfo.logoImg != ''}">
											<img class="ui image centered small" src="<s:property value="agentInfo.logoImg" />">
										</s:if>
									</div>
									<div class="ui horizontal divider very basic">
										<!-- <button type="button" class="ui basic button" onclick="BrowseServer('Images:/','logoImg')">
											  <i class="icon upload"></i>
											  Upload
										</button> -->
										<label for="filelogoImg" class="ui basic button">
											<i class="icon upload"></i>
										  	<s:i18n name="global_th"><s:text name="global.upload" /></s:i18n>
										</label>
									    <input type="file" id="filelogoImg" style="display:none">
									</div>
								</div>
								<s:hidden name="agentLogoFileName"></s:hidden>
							</div>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="agentInfo.lineId" key="global.line_id"/></s:i18n>
						</div>
						<s:if test="homeInfo.lineNotifyActive == 'true'">
							<div class="inline field">
								<label for="agentInfo_lineToken" class="label"><s:i18n name="global_th"><s:text name="global.line_token" /></s:i18n>:</label>
								<div class="ui action input">
									<s:textfield name="agentInfo.lineToken" />
									<div onclick="window.open('<s:property value="lineOauthUrl" />')" class="ui small green button">
										<i class="linechat icon"></i>
										รับ Token
									</div>
								</div>
							</div>
						</s:if>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="phone" key="global.mobile"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="email" key="global.email"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="agentInfo.telegramId" key="global.telegram_id"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="agentInfo.skypeId" key="global.skype_id"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="agentInfo.twitterId" key="global.twitter_id"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="agentInfo.wechatId" key="global.wechat_id"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="agentInfo.whatsAppId" key="global.whats_app_id"/></s:i18n>
						</div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button submit blue"><s:i18n name="global_th"><s:text name="global.submit" /></s:i18n></div>
							</div>
						</div>
						<s:hidden name="agentInfo.agentInfoId"></s:hidden>
						<s:hidden name="agentInfo.active"></s:hidden>
					</form>
				</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_agent_management_footer.jsp" %>  
</div>
</div>
</body>
</html>