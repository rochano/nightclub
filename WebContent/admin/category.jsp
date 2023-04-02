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
  <title><s:i18n name="global_th"><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.shop_category" /></s:i18n></title>

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
		
		var buttonPlus = container.find(".event:last").find("button.icon");
		buttonPlus.removeAttr("id");
		buttonPlus.on("click", function() {insertZoneHandler(this)});

		var buttonMinus = container.find(".event:last").find("button.icon").clone();
		buttonMinus.removeAttr("id");
		buttonMinus.find("i").removeClass("plus");
		buttonMinus.find("i").addClass("minus");
		buttonMinus.on("click", removeZoneHandler);
		buttonPlus.after(buttonMinus)
	  });

	  $(".remove-zone-btn").on("click", removeZoneHandler);

      $(".addbtn")
		.bind('click', function() {
		  $('.ui.modal .header:first').text("<s:i18n name="global_th"><s:text name="global.add_information" /><s:text name="global.shop_category" /></s:i18n>");
		  $('#infoForm').find("input[type=text], input[type=hidden], textarea").val("");
		  CKEDITOR.instances.categoryInfo_description.setData('');
		  $('#infoForm').find(".event:gt(0)").remove();
		  var dropdown = $('#infoForm').find("select");
		  dropdown.dropdown("clear");
		  
		  $('#infoForm')[0].action.value = "add";
		  $('#infoForm')[0].action = "<s:url value="/admin/category/add"/>";
	      $('.ui.modal')
			    .modal('show')
			  ;
	    });
    })
  ;
  
  function removeZoneHandler() {
	  $(this).parents(".event:first").remove();
  }

  function insertZoneHandler(obj) {
	var newobj = $(obj).parents(".event:first").clone();
	$(obj).parents(".event:first").before(newobj);

	newobj.find("select").insertBefore(newobj.find(".ui.search.dropdown.selection"));
	newobj.find(".ui.search.dropdown.selection").remove();

	var dropdown = newobj.find("select");
	dropdown.removeAttr("id");
	dropdown.addClass("search");
	dropdown.val(dropdown.find("option:first").val());
	dropdown.dropdown();
	
	var buttonPlus = newobj.find(".icon.plus").parents("button:first");
	buttonPlus.removeAttr("id");
	buttonPlus.on("click", function() {insertZoneHandler(this)});
	
	var buttonMinus = newobj.find(".icon.minus").parents("button:first");
	buttonMinus.removeAttr("id");
	buttonMinus.on("click", removeZoneHandler);
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
					<s:i18n name="global_th"><s:text name="global.shop_category" /></s:i18n>
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="addbtn ui small button blue"><s:i18n name="global_th"><s:text name="global.add" /></s:i18n></div>
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
							<tfoot class="full-width">
								<tr>
									<th colspan="4">
										<div class="ui right aligned one column grid">
											<div class="column">
												<div class="addbtn ui small button blue"><s:i18n name="global_th"><s:text name="global.add" /></s:i18n></div>
											</div>
										</div>
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
    <s:i18n name="global_th"><s:text name="global.edit_information" /><s:text name="global.shop_category" /></s:i18n>
  </div>
  <div class="content">
    <form class="ui form" id="infoForm" method="post" action="<s:url value="/admin/category/update"/>">
		<div class="inline field">
			<s:i18n name="global_th"><s:textfield name="categoryInfo.categoryNameJp" key="global.japanese_name" /></s:i18n>
		</div>
		<div class="inline field">
			<s:i18n name="global_th"><s:textfield name="categoryInfo.categoryNameEn" key="global.english_name" /></s:i18n>
		</div>
		<div class="inline fields">
			<label class="label"><s:i18n name="global_th"><s:text name="global.location" /></s:i18n>:</label>
			<div class="ui left icon input">
				<div class="ui feed">
					<div class="event">
						<div class="ui left icon action input">
							<s:select name="zonelist" list="zoneInfos" cssClass="ui search dropdown" listKey="zoneInfoId" listValue="zoneNameEn"></s:select>
							<button class="icon ui button" id="add-zone-btn" type="button"><i class="plus icon"></i></button>
						</div>
					</div>
					<s:if test="zonelist.size() > 1 ">
					<s:iterator value="zonelist" begin="1" var="zone">
					<div class="event">
						<div class="ui left icon action input">
							<s:select value="zone" name="zonelist" list="zoneInfos" cssClass="ui search dropdown" listKey="zoneInfoId" listValue="zoneNameEn"></s:select>
							<button class="icon ui button remove-zone-btn" type="button"><i class="minus icon"></i></button>
						</div>
					</div>
					</s:iterator>
					</s:if>
				</div>
			</div>
		</div>
		<h4 class="ui horizontal divider header">
			<i class="comment icon"></i>
			<s:i18n name="global_th"><s:text name="global.description" /></s:i18n>
		</h4>
		<div class="inline field">
			<s:textarea name="categoryInfo.description" />
		</div>
		<s:hidden name="action" value="update"></s:hidden>
		<s:hidden name="categoryInfo.categoryInfoId"></s:hidden>
		<div class="ui error message"></div>
	</form>
  </div>
  <div class="actions">
    <div class="ui approve blue button"><s:i18n name="global_th"><s:text name="global.save" /></s:i18n></div>
    <div class="ui cancel button"><s:i18n name="global_th"><s:text name="global.cancel" /></s:i18n></div>
  </div>
</div>
<script type="text/javascript">
	CKEDITOR.replace("categoryInfo.description", {
		/*filebrowserBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html',
		filebrowserImageBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html?type=Images',
		filebrowserFlashBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html?type=Flash',*/
		filebrowserUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files'/*,
		filebrowserImageUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
		filebrowserFlashUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash' */
	});
  <s:iterator value="categoryInfos" status="status">
		dataSet.push(
			['<s:property value="#status.count" />', 
			"<s:property value="categoryNameJp" />",
			"<s:property value="categoryNameEn" />",
			'<a href="<s:url value="/admin/category/edit/%{categoryInfoId}"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>' +
			'<a href="<s:url value="/admin/category/delete/%{categoryInfoId}"/>" class="ui icon button small red" ><i class="ui icon delete"></i></a>'
    	]);
	</s:iterator>
	columnDefs = [
	  {  className: "center aligned", targets: [ 0, 3 ] }
	];
	if (dataSet.length > 100) {
		pageLength = 150;
	} else if (dataSet.length > 75) {
		pageLength = 100;
	} else if (dataSet.length > 25) {
		pageLength = 50;
	} else if (dataSet.length > 10) {
		pageLength = 25;
	} else {
		pageLength = 10;
	}
  </script>
</body>
</html>