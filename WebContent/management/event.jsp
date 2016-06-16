<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Management - Event</title>

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
  	width: 150px;
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
	  $("#addbtn")
		.on('click', function() {
		  $('#infoForm').find("input[type=text], textarea").val("");
		  $('#infoForm')[0].action.value = "add";
		  $('#infoForm')[0].action = "<s:url value="/management/event/add"/>";
          $('.ui.modal')
		    .modal('show')
		  ;
	  
        })
	  	$("table").tablesort();
        $('.message .close')
        .on('click', function() {
          $(this)
            .closest('.message')
            .transition('fade')
          ;
        })
      ;
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
	  <s:if test="showInfo">
	  $('.ui.modal')
	    .modal('show')
	  ;
	  $("body").addClass("scrolling")
	  $("body > .ui.dimmer.modals").addClass("scrolling");
	  $("body > .ui.dimmer.modals > .ui.modal").addClass("scrolling");
	  </s:if>
	  $("#eventSearch_eventDateFrom, #eventSearch_eventDateTo").dateEntry({dateFormat: 'dmy/'});
	  $("#eventInfo_eventDate").dateEntry({dateFormat: 'dmy/'});
	  $('#eventInfo_eventTime').timeEntry({show24Hours: true});
    })
  ;
  </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
	function BrowseServer(startupPath,functionData){
		var finder = new CKFinder();
		finder.basePath = 'ckfinder/'; //Đường path nơi đặt ckfinder
		finder.startupPath = startupPath; //Đường path hiện sẵn cho user chọn file
		finder.selectActionFunction = SetFileField; // hàm sẽ được gọi khi 1 file được chọn
		finder.selectActionData = functionData; //id của text field cần hiện địa chỉ hình
		//finder.selectThumbnailActionFunction = ShowThumbnails; //hàm sẽ được gọi khi 1 file thumnail được chọn	
		finder.popup(); // Bật cửa sổ CKFinder
	}
	
	function SetFileField(fileUrl,data){
		document.getElementById( data["selectActionData"] ).value = fileUrl;
	}
	
	function ShowThumbnails(fileUrl,data){	
		var sFileName = this.getSelectedFile().name; // this = CKFinderAPI
		document.getElementById( 'thumbnails' ).innerHTML +=
		'<div class="thumb">' +
		'<img src="' + fileUrl + '" />' +
		'<div class="caption">' +
		'<a href="' + data["fileUrl"] + '" target="_blank">' + sFileName + '</a> (' + data["fileSize"] + 'KB)' +
		'</div>' +
		'</div>';
		document.getElementById( 'preview' ).style.display = "";
		return false; // nếu là true thì ckfinder sẽ tự đóng lại khi 1 file thumnail được chọn
	}
</script>
</head>
<body>
<!-- Sidebar Menu -->
<div class="ui vertical inverted sidebar menu">
	<%@include file="/common/common_shop_management_menu.jsp" %>
</div>
<div class="pusher">
	<div class="ui segment very basic">
		<div class="ui centered grid">
			<div class="eleven wide column container">
			<%@include file="/common/common_shop_management_header_info.jsp" %>
			<div class="ui menu inverted brown stackable">
				<a class="toc item"><i class="sidebar icon"></i></a>
			<%@include file="/common/common_shop_management_menu.jsp" %>
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
					Search Conditions
				</h4>
				<div class="ui left aligned attached segment active content">
					<form class="ui form" id="searchForm" method="post" action="<s:url value="/management/event/search"/>">
						<div class="inline field">
							<s:textfield name="eventSearch.title" label="Title"/>
						</div>
						<div class="inline fields">
							<label>Date</label>
							<div class="field">
								<s:textfield name="eventSearch.eventDateFrom" placeholder="DD/MM/YYYY" />
							</div>
							<label>-</label>
							<div class="field">
								<s:textfield name="eventSearch.eventDateTo" placeholder="DD/MM/YYYY" />
							</div>
						</div>
						<div class="ui error message"></div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button submit blue">Search</div>
								<div class="ui small button clear">Clear</div>
							</div>
						</div>
					</form>
				</div>
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					Event List
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
									<th>Title</th>
									<th>Date</th>
									<th>Time</th>
									<th>Operation</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="eventInfos" status="status">
								<tr>
									<td class="center aligned"><s:property value="#status.count" /></td>
									<td width="60%"><s:property value="title" /></td>
									<td class="center aligned"><s:date name="eventDate" format="dd/MM/yyyy" /></td>
									<td class="center aligned"><s:property value="eventTime" /></td>
									<td class="center aligned">
										<div class="ui buttons">
											<a href="<s:url value="/management/event/edit/%{eventInfoId}"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>
											<a href="<s:url value="/management/event/delete/%{eventInfoId}"/>" class="ui icon button small red"><i class="ui icon delete"></i></a>
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
	</div>
<%@include file="/common/common_shop_management_footer.jsp" %>  
</div>

<div class="ui modal">
  <i class="close icon"></i>
  <div class="header">
    Event Information
  </div>
  <div class="content">
    <form class="ui form" id="infoForm" method="post" action="<s:url value="/management/event/update"/>" >
		<div class="inline field">
			<s:textfield name="eventInfo.title" label="Title"/>
		</div>
		<div class="inline field">
			<s:textfield name="eventInfo.eventDate" placeholder="DD/MM/YYYY" label="Date"/>
		</div>
		<div class="inline field">
			<s:textfield name="eventInfo.eventTime" placeholder="HH:mm" label="Time"/>
		</div>
		<div class="inline field">
			<s:textarea name="eventInfo.description" label="Description"/>
		</div>
		<s:hidden name="action" value="update"></s:hidden>
		<s:hidden name="eventInfo.eventInfoId"></s:hidden>
		<div class="ui error message"></div>
	</form>
  </div>
  <div class="actions">
    <div class="ui approve blue button">Save</div>
    <div class="ui cancel button">Cancel</div>
  </div>
</div>
<script type="text/javascript">
	CKEDITOR.replace("eventInfo.description", {
		filebrowserBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html',
		filebrowserImageBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html?type=Images',
		filebrowserFlashBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html?type=Flash',
		filebrowserUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
		filebrowserImageUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
		filebrowserFlashUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
	});
</script>
</body>
</html>