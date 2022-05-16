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
  <title><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.menu_girl_service" /></title>

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
          onSuccess: function() { 
              var form = $(this);
              form.find("[name=girlservicenamelist]").remove()
              $(dataTable.fnGetNodes()).map(function(i, o) {
            	  var tr = $(o);
            	  var obj = {
                      girlServiceInfoId: tr.find("input[name=girlServiceInfoId]").val(),
                      girlServiceName: tr.find("input[name=girlServiceName]").val(),
                      girlServiceNameJp: tr.find("input[name=girlServiceNameJp]").val()
                  };
            	  form.append("<input name='girlservicenamelist' value='" + JSON.stringify(obj) + "' type='hidden' />")
              })
              return true; 
          }
       })
      ;
	  $("table").tablesort();
	  $("#addbtn")
		.on('click', function() {
			var giCount = dataTable.fnGetData().length;
			dataTable.fnAddData( [
			                  		'<div class="center aligned">' + (++giCount) + '<input type="hidden" name="girlServiceInfoId" /></div>',
			                		'<div class="ui fluid icon input"><input type="text" name="girlServiceName" /></div>',
			                		'<div class="ui fluid icon input"><input type="text" name="girlServiceNameJp" /></div>',
			                		'<div class="center aligned">'
										+ '<button type="button" class="insertbtn ui icon button small blue"><i class="ui icon add"></i></button>'
										+ '<button type="button" class="removebtn ui icon button small red"><i class="ui icon delete"></i></button>'
										+ '</div>'] );
			dataTable.fnPageChange('last');
			$("input[name=girlServiceName]").focus();
      });
    })
  ;
  $('.removebtn').live('click', function () {
	  var currentPage = dataTable.api().page();
	  dataTable.fnDeleteRow($(this).parents("tr:first"));
	  $(dataTable.fnGetNodes()).each(function(i, o) {
		  $(this).find("td:first div span").html(++i);
	  });
	  dataTable.fnPageChange(currentPage);
	} );
  $('.insertbtn').live('click', function () {
	  var rowIndexCurrentPage = $(this).parents("tr:first").index();
	  var giCount = dataTable.fnGetData().length;
	  var currentPage = dataTable.api().page();
	  var displayLength = dataTable.fnSettings()._iDisplayLength;
	  var rowindex = rowIndexCurrentPage + (displayLength * currentPage);
	  dataTable.fnAddData( [
	                  		'<div class="center aligned"><span>' + (++giCount) + '</span><input type="hidden" name="girlServiceInfoId" /></div>',
	                		'<div class="ui fluid icon input"><input type="text" name="girlServiceName" /></div>',
	                		'<div class="ui fluid icon input"><input type="text" name="girlServiceNameJp" /></div>',
	                		'<div class="center aligned">'
								+ '<button type="button" class="insertbtn ui icon button small blue"><i class="ui icon add"></i></button>'
								+ '<button type="button" class="removebtn ui icon button small red"><i class="ui icon delete"></i></button>'
								+ '</div>'] );
	  var shiftContent = {};
	  var currentContent = {};
	  $(dataTable.fnGetNodes()).map(function(i, o) {
		  var rowno = i+1
		  var tr = $(o)
		  tr.find("td:first div span").html(rowno);
		  // clear value inserted row
		  if (i == rowindex) {
			  shiftContent = {
				girlServiceInfoId: tr.find("input[name=girlServiceInfoId]").val(),
				girlServiceName: tr.find("input[name=girlServiceName]").val(),
				girlServiceNameJp: tr.find("input[name=girlServiceNameJp]").val(),
				disabled : tr.find("button.removebtn").hasClass("disabled")
			  };
			  tr.find("td input").val("");
			  tr.find("button.removebtn").removeClass("disabled");
		  // shift data to next row
		  } else if (i > rowindex) {
			  currentContent = {
				girlServiceInfoId: tr.find("input[name=girlServiceInfoId]").val(),
				girlServiceName: tr.find("input[name=girlServiceName]").val(),
				girlServiceNameJp: tr.find("input[name=girlServiceNameJp]").val(),
				disabled : tr.find("button.removebtn").hasClass("disabled")
			  };
			  tr.find("input[name=girlServiceInfoId]").val(shiftContent.girlServiceInfoId);
			  tr.find("input[name=girlServiceName]").val(shiftContent.girlServiceName);
			  tr.find("input[name=girlServiceNameJp]").val(shiftContent.girlServiceNameJp);
			  tr.find("button.removebtn").removeClass("disabled");
			  if (shiftContent.disabled) {
				  tr.find("button.removebtn").addClass("disabled");
			  }
			  shiftContent = currentContent;
		  }
	  });
	  dataTable.fnPageChange(currentPage);
	  $("input[name=girlServiceName]:eq(" + rowIndexCurrentPage + ")").focus();
	} );
	
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
					<s:text name="global.menu_girl_service" />
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<div class="ui right aligned one column grid">
							<div class="column">
								<div id="addbtn" class="ui small button blue"><s:text name="global.add" /></div>
							</div>
						</div>
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th><s:text name="global.service_name" /></th>
									<th><s:text name="global.service_name_jp" /></th>
									<th width="20%"><s:text name="global.operation" /></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="girlServicInfoList" status="status">
								<tr>
									<td>
										<div class="center aligned">
											<span><s:property value="#status.count" /></span>
											<input type="hidden" name="girlServiceInfoId" value="<s:property value="girlServiceInfoId" />" />
										</div>
									</td>
									<td>
										<div class="ui fluid icon input">
											<input type="text" name="girlServiceName" value="<s:property value="girlServiceName" />" />
										</div>
									</td>
									<td>
										<div class="ui fluid icon input">
											<input type="text" name="girlServiceNameJp" value="<s:property value="girlServiceNameJp" />" />
										</div>
									</td>
									<td>
										<div class="center aligned">
											<button type="button" class="insertbtn ui icon button small blue"><i class="ui icon add"></i></button>
											<button type="button" class="removebtn ui icon button small red 
												<s:if test="girlServiceList.size() > 0 ">disabled</s:if>">
												<i class="ui icon delete"></i>
											</button>
										</div>
									</td>
								</tr>
								</s:iterator>
							</tbody>
							<tfoot class="full-width">
								<tr>
									<th colspan="4">
										<form class="ui form " method="post" action="<s:url value="/admin/girlservice/update"/>" >
										<div class="ui right floated small primary submit button">
											<s:text name="global.submit" />
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
</body>
</html>