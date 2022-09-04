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
  <title><s:i18n name="global_th"><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.skin" /></s:i18n></title>

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
      $('.ui.modal').modal({
          onApprove : function() {
            //Submits the semantic ui form
            //And pass the handling responsibilities to the form handlers,
            // e.g. on form validation success
            $('#infoForm').submit();
            //Return false as to not close modal dialog
            return false;
          }
      });
      $('.message .close')
      .on('click', function() {
        $(this)
          .closest('.message')
          .transition('fade')
        ;
      })
    ;
      $("#addbtn")
		.on('click', function() {
		  $('.ui.modal .header:first').text("<s:i18n name="global_th"><s:text name="global.add_information" /><s:text name="global.skin" /></s:i18n>");
		  $('#infoForm').find("input[type=text], input[type=hidden], textarea").val("");
		  $('#infoForm')[0].action.value = "add";
		  $('#infoForm')[0].action = "<s:url value="/admin/skin/add"/>";
        $('.ui.modal')
		    .modal('show')
		  ;
      });
      $('#infoForm.ui.form')
      .form({
          fields: {},
       })
      ;
      $('#searchForm.ui.form')
      .form({
          fields: {}
      })
      ;
	  $("table").tablesort();
      <s:if test="showInfo">
	  $('.ui.modal')
	    .modal('show')
	  ;
	  </s:if>
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
				<div class="ui success message green">
					<i class="close icon"></i>
					<div class="header">
						<s:actionmessage cssClass="list" />
					</div>
				</div>
			</s:if>
			<s:if test="hasActionErrors()">
				<div class="ui error message">
					<i class="close icon"></i>
					<div class="header">
						<s:actionerror cssClass="list" />
					</div>
				</div>
			</s:if>
			<div class="ui accordion">
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th">
						<s:text name="global.search_condition" />
					</s:i18n>
				</h4>
				<div class="ui left aligned attached segment active content">
					<form class="ui form" id="searchForm" method="post" action="<s:url value="/admin/skin/search"/>">
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="skinSearch.skinNameJp" key="global.japanese_name"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="skinSearch.skinNameEn" key="global.english_name"/></s:i18n>
						</div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button submit blue"><s:i18n name="global_th"><s:text name="global.search" /></s:i18n></div>
								<div class="ui small button clear"><s:i18n name="global_th"><s:text name="global.clear" /></s:i18n></div>
							</div>
						</div>
					</form>
				</div>
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th"><s:text name="global.skin" /></s:i18n>
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<div class="ui right aligned one column grid">
							<div class="column">
								<div id="addbtn" class="ui small button blue"><s:i18n name="global_th"><s:text name="global.add" /></s:i18n></div>
							</div>
						</div>
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th><s:i18n name="global_th"><s:text name="global.japanese_name" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.english_name" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.operation" /></s:i18n></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="skinInfos" status="status">
								<tr>
									<td class="center aligned"><s:property value="#status.count" /></td>
									<td><s:property value="skinNameJp" /></td>
									<td><s:property value="skinNameEn" /></td>
									<td class="center aligned">
										<div class="ui buttons">
											<a href="<s:url value="/admin/skin/edit/%{skinInfoId}"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>
											<a href="<s:url value="/admin/skin/delete/%{skinInfoId}"/>" class="ui icon button small red"><i class="ui icon delete"></i></a>
										</div>
									</td>
								</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_admin_management_footer.jsp" %>  
</div>
</div>

<div class="ui modal">
  <i class="close icon"></i>
  <div class="header">
    <s:i18n name="global_th"><s:text name="global.edit_information" /><s:text name="global.skin" /></s:i18n>
  </div>
  <div class="content">
    <form class="ui form" id="infoForm" method="post" action="<s:url value="/admin/skin/update"/>">
		<div class="inline field">
			<s:i18n name="global_th"><s:textfield name="skinInfo.skinNameJp" key="global.japanese_name" /></s:i18n>
		</div>
		<div class="inline field">
			<s:i18n name="global_th"><s:textfield name="skinInfo.skinNameEn" key="global.english_name" /></s:i18n>
		</div>
		<s:hidden name="action" value="update"></s:hidden>
		<s:hidden name="skinInfo.skinInfoId"></s:hidden>
		<div class="ui error message"></div>
	</form>
  </div>
  <div class="actions">
    <div class="ui approve blue button"><s:i18n name="global_th"><s:text name="global.save" /></s:i18n></div>
    <div class="ui cancel button"><s:i18n name="global_th"><s:text name="global.cancel" /></s:i18n></div>
  </div>
</div>
  
</body>
</html>