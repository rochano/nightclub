<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Administrator - Agent</title>

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
              $( "input[name=active]:checked", dataTable.fnGetNodes()).each(function(i, item) {
            	  form.append("<input name='activelist' value='" + item.value + "' type='hidden' />")
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
	                  prompt : 'Please enter valid date from'
	                },
	              ]
	            },
	            userInfo_validDateTo: {
	              identifier  : 'userInfo_validDateTo',
	              rules: [
	                {
	                  type   : 'empty',
	                  prompt : 'Please enter valid date to'
	                },
	              ]
	            }
          ,},
       })
      ;
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
					Agent List
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th>Photo</th>
									<th>Agent name</th>
									<th>Valid date from</th>
									<th>Valid date to</th>
									<th>Active</th>
									<th>Operation</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="userInfos" status="status">
								<tr>
									<td class="center aligned"><s:property value="#status.count" /></td>
									<td>
										<img class="image ui tiny centered" src="<s:property value="agentInfo.logoImg" />">
									</td>
									<td><s:property value="agentInfo.agentName" /></td>
									<td class="center aligned"><s:date name="validDateFrom" format="dd/MM/yyyy" /></td>
									<td class="center aligned"><s:date name="validDateTo" format="dd/MM/yyyy" /></td>
									<td class="center aligned">
										<div class="ui toggle fitted checkbox">
											<input type="checkbox" name="active" 
											<s:if test="active == 'true'">checked="checked"</s:if>
											 value="<s:property value="userInfoId" />">
											<label></label>
										</div>
									</td>
									<td class="center aligned">
										<a href="<s:url value="/admin/agent/edit/%{userInfoId}"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>
									</td>
								</tr>
								</s:iterator>
							</tbody>
							<tfoot class="full-width">
								<tr>
									<th colspan="7">
										<form class="ui form " id="activeForm" method="post" action="<s:url value="/admin/agent/active"/>" >
											<div class="ui right floated small primary submit button">
												Submit
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
    Agent Information
  </div>
  <div class="content">
    <form class="ui form" id="infoForm" method="post" action="<s:url value="/admin/agent/update"/>" >
		<div class="inline fields">
			<label>Agent name</label>
			<div class="field disabled">
				<s:textfield name="userInfo.agentInfo.agentName" disabled="true" />
			</div>
		</div>
		<div class="inline fields">
			<label>Valid Date</label>
			<div class="field">
				<s:textfield name="userInfo.validDateFrom" placeholder="DD/MM/YYYY" />
			</div>
			<label>-</label>
			<div class="field">
				<s:textfield name="userInfo.validDateTo" placeholder="DD/MM/YYYY" />
			</div>
		</div>
		<s:hidden name="userInfoId"></s:hidden>
		<div class="ui error message"></div>
	</form>
  </div>
  <div class="actions">
    <div class="ui approve blue button">Save</div>
    <div class="ui cancel button">Cancel</div>
  </div>
</div>
</body>
</html>