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
  <title><s:i18n name="global_th"><s:text name="global.management" /> - <s:text name="global.menu_girls" /></s:i18n></title>

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
  .ui.form .inline.field>:first-child, .ui.form .inline.fields>label {
  	width: 200px;
  }
  .ui.form .inline.field>label {
  	vertical-align: top;
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
			
			<div class="ui breadcrumb segment inverted">
				<a class="section" href="javascript:history.back()"> 
					<s:i18n name="global_th"><s:text name="global.menu_reserve" /></s:i18n>
				</a>
				<i class="right chevron icon divider"></i>
				<div class="active section">
					ข้อมูลการจอง
				</div>
			</div>
			
			<div class="ui accordion">
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th"><s:text name="global.menu_reserve" /></s:i18n>
				</h4>
				<div class="ui centered two column centered grid attached segment active content">
					<div class="column">
						<form class="segment content left aligned ui form">
							<div class="inline field">
								<label for="reserveInfo.email">
									<s:i18n name="global_th"><s:text name="global.booking_email" /></s:i18n>:
								</label>
								<div class="ui input">
									<s:property value="reserveInfo.email" />
								</div>
							</div>
							<div class="inline field">
								<label for="reserveInfo_clientName">
									<s:i18n name="global_th"><s:text name="global.booking_name" /></s:i18n>:
								</label>
								<div class="ui input">
									<s:property value="reserveInfo.clientName" />
								</div>
							</div>
							<div class="inline field">
								<label for="reserveInfo_clientName">
									<s:i18n name="global_th"><s:text name="global.contact_method" /></s:i18n>:
								</label>
								<div class="ui input">
									<s:property value="reserveInfo.contactMethod" />
								</div>
							</div>
							<div class="inline field">
								<label for="reserveInfo_contactId">
									<s:i18n name="global_th"><s:text name="global.contact_id" /></s:i18n>:
								</label>
								<div class="ui input">
									<s:property value="reserveInfo.contactId" />
								</div>
							</div>
							<div class="inline field">
								<label for="reserveInfo_nickName">
									<s:i18n name="global_th"><s:text name="global.girl_name" /></s:i18n>:
								</label>
								<div class="ui input">
									<s:property value="reserveInfo.girlInfo.nickName" />
								</div>
							</div>
							<div class="inline field">
								<label for="reserveInfo_reserveDate">
									<s:i18n name="global_th"><s:text name="global.booking_date" /></s:i18n>:
								</label>
								<div class="ui input">
									<s:date name="reserveInfo.reserveDate" format="dd/MM/yyyy" />
								</div>
							</div>
							<div class="inline field">
								<label for="reserveInfo_reserveTime">
									<s:i18n name="global_th"><s:text name="global.booking_time" /></s:i18n>:
								</label>
								<div class="ui input">
									<s:property value="reserveInfo.reserveTime" />
									<s:if test="reserveInfo.chkFlexible == 'true'">
										(<s:text name="global.flexible" />)
									</s:if>
								</div>
							</div>
							<div class="inline field">
								<label for="reserveInfo_hotel">
									<s:i18n name="global_th"><s:text name="global.hotel" /></s:i18n>:
								</label>
								<div class="ui input">
									<s:property value="reserveInfo.hotel" />
								</div>
							</div>
							<div class="inline field">
								<label for="reserveInfo_roomNo">
									<s:i18n name="global_th"><s:text name="global.room_no" /></s:i18n>:
								</label>
								<div class="ui input">
									<s:property value="reserveInfo.roomNo" />
									<s:if test="reserveInfo.chkNotCheckedIn == 'true'">
										(<s:text name="global.not_checked_in" />)
									</s:if>
								</div>
							</div>
							<div class="inline field">
								<label for="reserveInfo_incallOutcall">
									<s:i18n name="global_th"><s:text name="global.booking_incall_outcall" /></s:i18n>:
								</label>
								<div class="ui input">
									<s:property value="reserveInfo.incallOutcall" />
								</div>
							</div>
							<div class="inline field">
								<label for="reserveInfo_duration">
									<s:i18n name="global_th"><s:text name="global.booking_duration" /></s:i18n>:
								</label>
								<div class="ui input">
									<s:property value="reserveInfo.duration" />
								</div>
							</div>
							<div class="inline field">
								<label>Service</label>
								<div class="ui input">
									<div class="ui list">
										<s:iterator value="reserveInfo.reserveGirlServices" status="rowstatus">
											<div class="ui item">
												<s:property value="primaryKey.girlServiceInfo.girlServiceName" />
											</div>
										</s:iterator>
									</div>
								</div>
							</div>
							<br />
							<div class="inline field">
								<label><s:text name="global.inquiry" /></label>
								<div class="ui input">
									<s:text name="reserveInfo.inquiry"></s:text>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_agent_management_footer.jsp" %>  
</div>
</div>
</body>
</html>