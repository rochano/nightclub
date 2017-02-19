<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Administrator - Shop</title>

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
              form.find("[name=activeList]").remove()
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
	  </s:if>
    })
  ;
  </script>
</head>
<body class="menu pushable">
<%@include file="/common/common_admin_management_header_info.jsp" %>
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
					Shop List
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th>Shop code</th>
									<th>Japanese name</th>
									<th>English name</th>
									<th>Category</th>
									<th>Zone</th>
									<th>Active</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="basicInfos" status="status">
								<tr>
									<td class="center aligned"><s:property value="#status.count" /></td>
									<td><s:property value="shopCode" /></td>
									<td><s:property value="shopNameJp" /></td>
									<td><s:property value="shopNameEn" /></td>
									<td><s:property value="categoryInfo.categoryNameEn" /></td>
									<td><s:property value="zoneInfo.zoneNameEn" /></td>
									<td class="center aligned">
										<div class="ui toggle fitted checkbox">
											<input type="checkbox" name="active" 
											<s:if test="active == 'true'">checked="checked"</s:if>
											 value="<s:property value="shopInfoId" />">
											<label></label>
										</div>
									</td>
								</tr>
								</s:iterator>
							</tbody>
							<tfoot class="full-width">
								<tr>
									<th colspan="7">
										<form class="ui form " method="post" action="<s:url value="/admin/shop/update"/>" >
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