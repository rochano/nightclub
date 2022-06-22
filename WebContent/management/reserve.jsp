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
  <title><s:text name="global.management" /> - <s:text name="global.menu_girls" /></title>

  <%@include file="/common/common_shop_management_header.jsp" %>
  <script src="<s:url value="/assets/library/jquery.form.js"/>"></script>
  <script src="<s:url value="/assets/library/fileUploadScript.js"/>"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/ckfinder/ckfinder.js"></script>
  <script src="<s:url value="/assets/library/jquery.ui.widget.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload-process.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload-ui.js"/>"></script>
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
		var relatedId = data["selectActionData"];
		document.getElementById( relateUploadField[relatedId] ).value = fileUrl;
		var image = "<img src='" + (fileUrl) + "' />";
		$("#" + relatedId).html(image);
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

	  $("table").tablesort();
        $('.message .close')
        .on('click', function() {
          $(this)
            .closest('.message')
            .transition('fade')
          ;
        })
      ;
	  $('#searchForm.ui.form')
      .form({
          fields: {}
      })
	  $('#inline_calendar')
	  	.calendar({
	  		initialDate: new Date(),
	  	    type: 'date',
	  	    onChange: function() {
		  	    var lookupDate = arguments[0].toISOString().substring(0, 10);
		  	    getReserveList(lookupDate);
		  	}
	    })
	  ;
  })
  ;
  function getReserveList(lookupDate) {
	  var reserveInfos = $('#reserveInfos');
	    reserveInfos.empty()
   		$.getJSON("<s:url value="/ajax/girlReserveShopJson/" />" + lookupDate, 
			function(jsonResponse) {
				$("#lookupDateHeader").html(jsonResponse.lookupDateHeader);
				if (jsonResponse.reserveInfos == null) return;
				var prevGirlInfoId = "";
			     $.each(jsonResponse.reserveInfos, function(i, obj) {
			    	 var html = "";
			    	 if (prevGirlInfoId != obj.girlInfoId) {
				    	 html += '<div class="item">';
						 html += '<img class="ui avatar image" src="' + obj.girlInfo.pic1 + '">';
						 html += '<div class="content">';
						 html += '<div class="header">' + obj.girlInfo.nickName + '</div>';
						 html += '</div>';
						 html += '</div>';
			    	 }
			    	 prevGirlInfoId = obj.girlInfoId;
					 html += '<div class="item">';
				  	 html += '<div class="right floated content">';
				     html += obj.startTime + ' - ' + obj.endTime;
				     html += '</div>';
				     html += '<div class="content">';
				     html += '<div class="header">' + obj.clientInfo.nickName + '</div>';
				     html += obj.clientInfo.mobile;
				     html += '</div>';
					 html += '</div>';
			    	 reserveInfos.append(html);
			     });
		});
  }
  </script>
</head>
<body class="menu pushable">
<%@include file="/common/common_shop_management_header_info.jsp" %>
<!-- Sidebar Menu -->
<div class="ui toc vertical top large inverted sidebar menu">
  	<a class="ui toc item title">
		<i class="cancel icon"></i>
		&nbsp;
	</a>
	<%@include file="/common/common_shop_management_menu.jsp" %>
</div>
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
					<s:text name="global.menu_reserve" />
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<div class="ui grid stackable">
						<div class="eight wide column">
							<div class="ui calendar" id="inline_calendar">
							</div>
						</div>
						<div class="eight wide column">
							<div class="ui clearing segment">
								<h3 class="ui left floated header">
								   <div class="header" id="lookupDateHeader"><s:text name="lookupDateHeader" /></div>
								</h3>
							</div>
							<div class="ui celled list" id="reserveInfos">
							  <s:set name="prevGirlInfoId" value="" />
							  <s:iterator value="reserveInfos" status="status">
							      <s:if test="%{girlInfoId != #prevGirlInfoId}">
									  <div class="item">
									    <img class="ui avatar image" src="<s:property value="girlInfo.pic1" />">
									    <div class="content">
									      <div class="header"><s:property value="girlInfo.nickName" /></div>
									    </div>
									  </div>
								  </s:if>
								  <div class="item">
								    <div class="right floated content">
								      <s:property value="startTime" /> - <s:property value="endTime" />
								    </div>
								    <div class="content">
								      <div class="header"><s:property value="clientInfo.nickName" /></div>
								      <s:property value="clientInfo.mobile" />
								    </div>
								  </div>
								  <s:set name="prevGirlInfoId" value="%{girlInfoId}" />
							  </s:iterator>
							</div>
						</div>
						</div>
					</div>
				</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_shop_management_footer.jsp" %>  
</div>
</div>
</body>
</html>