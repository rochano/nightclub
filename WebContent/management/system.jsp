<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Management - System</title>

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
		  $('#infoForm').find("input[type=text], input[type=hidden], textarea").val("");
		  $('#infoForm')[0].action.value = "add";
		  $('#infoForm')[0].action = "<s:url value="/management/system/add"/>";
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
	  $("body").addClass("scrolling")
	  $("body > .ui.dimmer.modals").addClass("scrolling");
	  $("body > .ui.dimmer.modals > .ui.modal").addClass("scrolling");
	  </s:if>
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
<body class="menu pushable">
<%@include file="/common/common_shop_management_header_info.jsp" %>
<div class="pusher">
<div class="full height">
<div class="toc">
	<!-- Sidebar Menu -->
	<div class="ui inverted vertical menu">
		<%@include file="/common/common_shop_management_menu.jsp" %>
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
					Search Conditions
				</h4>
				<div class="ui left aligned attached segment active content">
					<form class="ui form" id="searchForm" method="post" action="<s:url value="/management/system/search"/>">
						<div class="inline field">
							<s:textfield name="systemSearch.infoName" label="Name"/>
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
					System List
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<div class="ui right aligned one column grid">
							<div class="column">
								<div id="addbtn" class="ui small button blue">Add</div>
							</div>
						</div>
						<table id="searchList" class="ui table celled compact striped unstackable unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th>Name</th>
									<th>Duration</th>
									<th>Price</th>
									<th>Operation</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="systemInfos" status="status">
								<tr>
									<td class="center aligned"><s:property value="#status.count" /></td>
									<td><s:property value="infoName" /></td>
									<td><s:property value="duration" /></td>
									<td class="right aligned"><s:property value="price" /></td>
									<td class="center aligned">
										<div class="ui buttons">
											<a href="<s:url value="/management/system/edit/%{systemInfoId}"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>
											<a href="<s:url value="/management/system/delete/%{systemInfoId}"/>" class="ui icon button small red"><i class="ui icon delete"></i></a>
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
  	<%@include file="/common/common_shop_management_footer.jsp" %>  
</div>
</div>

<div class="ui modal">
  <i class="close icon"></i>
  <div class="header">
    System Information
  </div>
  <div class="content">
    <form class="ui form" id="infoForm" method="post" action="<s:url value="/management/system/update"/>">
		<div class="inline field">
			<s:textfield name="systemInfo.infoName" label="Name" />
		</div>
		<div class="inline field">
			<s:textfield name="systemInfo.duration" label="Duration" />
		</div>
		<div class="inline field">
			<s:textfield name="systemInfo.price" label="Price" />
		</div>
		<h4 class="ui horizontal divider header">
			<i class="comment icon"></i>
			Description
		</h4>		
		<div class="inline field">
			<s:textarea name="systemInfo.description" />
		</div>
		<s:hidden name="action" value="update"></s:hidden>
		<s:hidden name="systemInfo.systemInfoId"></s:hidden>
		<div class="ui error message"></div>
	</form>
  </div>
  <div class="actions">
    <div class="ui approve blue button">Save</div>
    <div class="ui cancel button">Cancel</div>
  </div>
</div>
<script type="text/javascript">
	CKEDITOR.replace("systemInfo.description", {
		/*filebrowserBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html',
		filebrowserImageBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html?type=Images',
		filebrowserFlashBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html?type=Flash',*/
		filebrowserUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
		/*filebrowserImageUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
		filebrowserFlashUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'*/
	});
</script> 
</body>
</html>