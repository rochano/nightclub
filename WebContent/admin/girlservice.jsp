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
          onSuccess: function() { 
              var form = $(this);
              form.find("[name=activelist]").remove()
              $( "input[name=girlServiceName]", dataTable.fnGetNodes()).each(function(i, item) {
            	  form.append("<input name='girlservicenamelist' value='" + item.value + "' type='hidden' />")
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
			                  		'<div class="center aligned">' + (++giCount) + '</div>',
			                		'<div class="ui fluid icon input"><input type="text" name="girlServiceName" /></div>',
			                		'<div class="center aligned">'
										+ '<button type="button" class="insertbtn ui icon button small blue"><i class="ui icon add"></i></button>'
										+ '<button type="button" class="removebtn ui icon button small red"><i class="ui icon delete"></i></button>'
										+ '</div>'] );
      });
    })
  ;
  $('.removebtn').live('click', function () {
	  dataTable.fnDeleteRow($(this).parents("tr:first"));
	  console.log(dataTable.fnGetNodes())
	  $(dataTable.fnGetNodes()).each(function(i, o) {
		  $(this).find("td:first div").html(++i);
	  });
	} );
  $('.insertbtn').live('click', function () {
	  var rowindex = $(this).parents("tr:first").index();
	  var giCount = dataTable.fnGetData().length;
	  dataTable.fnAddData( [
	                  		'<div class="center aligned">' + (++giCount) + '</div>',
	                		'<div class="ui fluid icon input"><input type="text" name="girlServiceName" /></div>',
	                		'<div class="center aligned">'
								+ '<button type="button" class="insertbtn ui icon button small blue"><i class="ui icon add"></i></button>'
								+ '<button type="button" class="removebtn ui icon button small red"><i class="ui icon delete"></i></button>'
								+ '</div>'] );
	  var shiftContent;
	  var currentContent;
	  $(dataTable.fnGetNodes()).map(function(i, o) {
		  var rowno = i+1
		  var tr = $(o)
		  tr.find("td:first div").html(rowno);
		  // clear value inserted row
		  if (i == rowindex) {
			  shiftContent = tr.find("td:eq(1) input").val();
			  tr.find("td:eq(1) input").val("");
		  // shift data to next row
		  } else if (i > rowindex) {
			  currentContent = tr.find("td:eq(1) input").val();
			  tr.find("td:eq(1) input").val(shiftContent);
			  shiftContent = currentContent;
		  }
	  });
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
			<div class="ui accordion">
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					Girl Service List
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<div class="ui right aligned one column grid">
							<div class="column">
								<div id="addbtn" class="ui small button blue">Add</div>
							</div>
						</div>
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th>Service name</th>
									<th width="20%">Operation</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="girlServicInfoList" status="status">
								<tr>
									<td>
										<div class="center aligned">
											<s:property value="#status.count" />
										</div>
									</td>
									<td>
										<div class="ui fluid icon input">
											<input type="text" name="girlServiceName" value="<s:property value="girlServiceName" />" />
										</div>
									</td>
									<td>
										<div class="center aligned">
											<button type="button" class="insertbtn ui icon button small blue"><i class="ui icon add"></i></button>
											<button type="button" class="removebtn ui icon button small red"><i class="ui icon delete"></i></button>
										</div>
									</td>
								</tr>
								</s:iterator>
							</tbody>
							<tfoot class="full-width">
								<tr>
									<th colspan="3">
										<form class="ui form " method="post" action="<s:url value="/admin/girlservice/update"/>" >
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
</body>
</html>