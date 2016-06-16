<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Administrator - Category</title>

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
      $('#infoForm.ui.form')
      .form({
          fields: {},
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

	  $("#add-zone-btn").on("click", function(){
		var container = $(this).parents(".feed:first");
		$(this).parents(".event:first").clone().appendTo(container);
		
		container.find(".event:last select").insertBefore(container.find(".event:last .ui.search.dropdown.selection"));
		container.find(".event:last .ui.search.dropdown.selection").remove();
		
		var dropdown = container.find(".event:last select");
		dropdown.removeAttr("id");
		dropdown.addClass("search");
		dropdown.val(dropdown.find("option:first").val());
		dropdown.dropdown();
		
		var button = container.find(".event:last").find("button.icon");
		button.find("i").removeClass("plus");
		button.find("i").addClass("minus");
		button.on("click", removeZoneHandler);
	  });

	  $(".remove-zone-btn").on("click", removeZoneHandler);
    })
  ;
  
  function removeZoneHandler() {
	  $(this).parents(".event:first").remove();
  }
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
	<%@include file="/common/common_admin_management_menu.jsp" %>
</div>
<div class="pusher">
<div class="ui segment very basic">
		<div class="ui centered grid">
			<div class="eleven wide column container">
			<%@include file="/common/common_admin_management_header_info.jsp" %>
			<div class="ui menu inverted brown stackable">
				<a class="toc item"><i class="sidebar icon"></i></a>
			<%@include file="/common/common_admin_management_menu.jsp" %>
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
					Category List
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th>Category code</th>
									<th>Japanese name</th>
									<th>English name</th>
									<th>Operation</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="categoryInfos" status="status">
								<tr>
									<td class="center aligned"><s:property value="#status.count" /></td>
									<td><s:property value="categoryCode" /></td>
									<td><s:property value="categoryNameJp" /></td>
									<td><s:property value="categoryNameEn" /></td>
									<td class="center aligned">
										<a href="<s:url value="/admin/category/edit/"/><s:property value="categoryInfoId" />" class="ui icon button small blue" ><i class="ui icon edit"></i></a>
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
	<%@include file="/common/common_admin_management_footer.jsp" %> 
</div>

<div class="ui modal">
  <i class="close icon"></i>
  <div class="header">
    Category Information
  </div>
  <div class="content">
    <form class="ui form" id="infoForm" method="post" action="<s:url value="/admin/category/update"/>">
		<div class="inline field">
			<s:textfield name="categoryInfo.categoryCode" label="Category code" />
		</div>
		<div class="inline field">
			<s:textfield name="categoryInfo.categoryNameJp" label="Japanese name" />
		</div>
		<div class="inline field">
			<s:textfield name="categoryInfo.categoryNameEn" label="English name" />
		</div>
		<div class="inline field">
			<label class="label">Zone:</label>
			<div class="ui left icon input">
				<div class="ui feed">
					<div class="event">
						<div class="ui left icon action input">
							<s:if test="zonelist.size() > 0 ">
								<s:set name="zoneValue" value="zonelist.get(0)" />
							</s:if>
							<s:select value="#zoneValue" name="zonelist" list="zoneInfos" cssClass="ui search dropdown" listKey="zoneInfoId" listValue="zoneCode"></s:select>
							<button class="icon ui button" id="add-zone-btn" type="button"><i class="plus icon"></i></button>
						</div>
					</div>
					<s:if test="zonelist.size() > 1 ">
					<s:iterator value="zonelist" begin="1" var="zone">
					<div class="event">
						<div class="ui left icon action input">
							<s:select value="zone" name="zonelist" list="zoneInfos" cssClass="ui search dropdown" listKey="zoneInfoId" listValue="zoneCode"></s:select>
							<button class="icon ui button remove-zone-btn" type="button"><i class="minus icon"></i></button>
						</div>
					</div>
					</s:iterator>
					</s:if>
				</div>
			</div>
		</div>
		<div class="inline field">
			<s:textarea name="categoryInfo.description" label="Description"/>
		</div>
		<s:hidden name="action" value="update"></s:hidden>
		<s:hidden name="categoryInfo.categoryInfoId"></s:hidden>
		<div class="ui error message"></div>
	</form>
  </div>
  <div class="actions">
    <div class="ui approve blue button">Save</div>
    <div class="ui cancel button">Cancel</div>
  </div>
</div>
<script type="text/javascript">
	CKEDITOR.replace("categoryInfo.description", {
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