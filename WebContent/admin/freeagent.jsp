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
  <title><s:i18n name="global_th"><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.menu_free_agent" /></s:i18n></title>

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
      $('#activeForm')
      .form({
          onSuccess: function() { 
              var form = $(this);
              form.find("[name=activelist]").remove()
              $( "input[name=active]", dataTable.fnGetNodes()).each(function(i, item) {
                  var obj = {id: item.value, checked: item.checked};
            	  form.append("<input name='activelist' value='" + JSON.stringify(obj) + "' type='hidden' />")
              })
              return true; 
          }
       })
      ;
	  $("table").tablesort();
      <s:if test="showInfo">
	  $('.ui.modal')
	    .modal('show')
	  ;
	  $("body").addClass("scrolling");
	  $("body > .ui.dimmer.modals").addClass("scrolling");
	  $("body > .ui.dimmer.modals > .ui.modal").addClass("scrolling");
	  </s:if>
	  $("#userInfo_validDateFrom, #userInfo_validDateTo").dateEntry({dateFormat: 'dmy/', spinnerImage: ''});
	  <s:if test="userInfo.validDateFrom != null">
	  $("#userInfo_validDateFrom").val("<s:date name="userInfo.validDateFrom" format="dd/MM/yyyy" />");
	  </s:if>
	  <s:if test="userInfo.validDateTo != null">
	  $("#userInfo_validDateTo").val("<s:date name="userInfo.validDateTo" format="dd/MM/yyyy" />");
	  </s:if>
	  $('.ui.modal').modal({
          onApprove : function() {
       	  	 var form = $('#infoForm');
             form.find("[name=checklist]").remove()
             $( "input[name=check]", dataTable.fnGetNodes()).each(function(i, item) {
                 var obj = {id: item.value, checked: item.checked};
           	  form.append("<input name='checklist' value='" + JSON.stringify(obj) + "' type='hidden' />")
             })
            //Submits the semantic ui form
            //And pass the handling responsibilities to the form handlers,
            // e.g. on form validation success
            $('#infoForm').submit();
            //Return false as to not close modal dialog
            return false;
          }
      });
	  $('#infoForm.ui.form')
      .form({
          fields: {
        	  userInfo_validDateFrom: {
	              identifier  : 'userInfo_validDateFrom',
	              rules: [
	                {
	                  type   : 'empty',
	                  prompt : '<s:i18n name="global_th"><s:text name="global.message_please_input" /><s:text name="global.valid_date_from" /></s:i18n>'
	                },
	              ]
	            },
	            userInfo_validDateTo: {
	              identifier  : 'userInfo_validDateTo',
	              rules: [
	                {
	                  type   : 'empty',
	                  prompt : '<s:i18n name="global_th"><s:text name="global.message_please_input" /><s:text name="global.valid_date_to" /></s:i18n>'
	                },
	              ]
	            }
          ,},
       })
      ;
	  $('#searchForm.ui.form')
      .form({
          fields: {}
      })
      ;
	  $("#editmultiplebtn")
		.on('click', function() {
		  var selectedItem = $( "input[name=check]:checked", dataTable.fnGetNodes()).length;
		  if(selectedItem == 0) {
			alert("กรุณาเลือกข้อมูล")
			return;
		  }
		  $('.ui.modal .header:first').text("<s:i18n name="global_th"><s:text name="global.edit_information" /><s:text name="global.menu_free_agent" /></s:i18n>(" + selectedItem + " รายการ)");
		  $('#infoForm').find("input[type=text], textarea").val("");
		  $('#infoForm').find("input[type=radio], input[type=checkbox]").prop('checked', false);
		  $('#userInfo_freeAgentGirlInfo_nickName').parents(".fields:first").empty();
		  $('#infoForm')[0].action.value = "editMultiple";
		  $('#infoForm')[0].action = "<s:url value="/admin/freeagent/multipleupdate"/>";
      $('.ui.modal')
		    .modal('show')
		  ;
      });
	  $("#checkAll")
		.on('click', function() {
			$( "input[name=check]", dataTable.fnGetNodes()).prop('checked', this.checked);
		});
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
			<div class="ui accordion">
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th">
						<s:text name="global.search_condition" />
					</s:i18n>
				</h4>
				<div class="ui left aligned attached segment active content">
					<form class="ui form" id="searchForm" method="post" action="<s:url value="/admin/freeagent/search"/>">
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="search.userName" key="global.username"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="search.nickName" key="global.nick_name"/></s:i18n>
						</div>
						<div class="ui error message"></div>
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
					<s:i18n name="global_th"><s:text name="global.menu_free_agent" /></s:i18n>
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<div class="ui right aligned one column grid">
							<div class="column">
								<div id="editmultiplebtn" class="ui small button blue"><s:i18n name="global_th"><s:text name="global.edit" /></s:i18n></div>
							</div>
						</div>
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th>
										<div class="ui fitted checkbox">
											<input type="checkbox" id="checkAll" />
											<label></label>
										</div>
									</th>
									<th><s:i18n name="global_th"><s:text name="global.girl_photo" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.username" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.nick_name" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.valid_date_from" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.valid_date_to" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.line_id" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.active" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.operation" /></s:i18n></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="userInfos" status="status">
								<tr>
									<td class="center aligned"><s:property value="#status.count" /></td>
									<td>
										<div class="ui fitted checkbox">
											<input type="checkbox" name="check" value="<s:property value="userInfoId" />" />
											<label></label>
										</div>
									</td>
									<td>
										<img class="image ui tiny centered" src="<s:property value="freeAgentGirlInfo.pic1" />">
									</td>
									<td><s:property value="username" /></td>
									<td><s:property value="freeAgentGirlInfo.nickName" /></td>
									<td class="center aligned"><s:date name="validDateFrom" format="dd/MM/yyyy" /></td>
									<td class="center aligned"><s:date name="validDateTo" format="dd/MM/yyyy" /></td>
									<td><s:property value="freeAgentGirlInfo.lineId" /></td>
									<td class="center aligned">
										<div class="ui toggle fitted checkbox">
											<input type="checkbox" name="active" 
											<s:if test="active == 'true'">checked="checked"</s:if>
											 value="<s:property value="userInfoId" />">
											<label></label>
										</div>
									</td>
									<td class="center aligned">
										<div class="ui buttons">
											<a href="<s:url value="/admin/freeagent/edit/%{userInfoId}"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>
											<a href="<s:url value="/admin/freeagent/delete/%{userInfoId}"/>" class="ui icon button small red" ><i class="ui icon delete"></i></a>
										</div>
									</td>
								</tr>
								</s:iterator>
							</tbody>
							<tfoot class="full-width">
								<tr>
									<th colspan="9">
										<form class="ui form " id="activeForm" method="post" action="<s:url value="/admin/freeagent/active"/>" >
											<div class="ui right floated small primary submit button">
												<s:i18n name="global_th"><s:text name="global.submit" /></s:i18n>
											</div>
										</form>
									</th>
								</tr>
							</tfoot>
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
    <s:i18n name="global_th"><s:text name="global.edit_information" /><s:text name="global.menu_free_agent" /></s:i18n>
  </div>
  <div class="content">
    <form class="ui form" id="infoForm" method="post" action="<s:url value="/admin/freeagent/update"/>" >
		<div class="inline fields">
			<label><s:i18n name="global_th"><s:text name="global.nick_name" /></s:i18n></label>
			<div class="field disabled">
				<s:textfield name="userInfo.freeAgentGirlInfo.nickName" disabled="true" />
			</div>
		</div>
		<div class="inline fields">
			<label><s:i18n name="global_th"><s:text name="global.valid_date" /></s:i18n></label>
			<div class="field">
				<s:textfield name="userInfo.validDateFrom" placeholder="DD/MM/YYYY" />
			</div>
			<label>-</label>
			<div class="field">
				<s:textfield name="userInfo.validDateTo" placeholder="DD/MM/YYYY" />
			</div>
		</div>
		<div class="inline field">
			<s:i18n name="global_th"><s:textfield name="userInfo.freeAgentGirlInfo.lineId" key="global.line_id" /></s:i18n>
		</div>
		<s:hidden name="userInfoId"></s:hidden>
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