<%@page import="com.nightclub.common.IConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Management - Map</title>

  <%@include file="/common/common_shop_management_header.jsp" %>

  <style>
  body {
    padding: 1em;
  }
  .ui.menu:last-child {
    margin-bottom: 110px;
  }
  .ui.form .inline.field>:first-child {
  	width: 150px;
  }
  .ui.items>.item>.content>.header {
    margin: 0;
  }
    .gm-style-iw, .gm-style-iw .ui.header, .gm-style-iw .ui.header .sub.header {
  	color: rgba(0, 0, 0, 0.87);
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
      $('.ui.form')
      .form({
          fields: {},
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
      <s:if test="hasActionMessages()">
      $('.ui.form').addClass("success");
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
					Map Information
				</h4>
				<div class="ui left aligned attached segment active content">
					<form class="ui form " method="post" action="<s:url value="/management/map/update"/>">
						<div class="ui error message"><s:actionerror cssClass="list" /></div>
						<div class="inline field">
							<s:textfield name="mapInfo.latitude" label="Latitude"/>
						</div>
						<div class="inline field">
							<s:textfield name="mapInfo.longitude" label="Longitude"/>
						</div>
						<h4 class="ui horizontal divider header">
							<i class="comment icon"></i>
							Description
						</h4>	
						<div class="inline field">
							<s:textarea name="mapInfo.description" />
							<script type="text/javascript">
								CKEDITOR.replace("mapInfo.description", {
									/*filebrowserBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html',
									filebrowserImageBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html?type=Images',
									filebrowserFlashBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html?type=Flash',*/
									filebrowserUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
									/*filebrowserImageUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
									filebrowserFlashUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'*/
								});
							</script>
						</div>
						<div class="ui two column grid">
							<div class="column">
								<div class="ui small button orange" onclick="initialize(CKEDITOR.instances.mapInfo_description.getData())">Preview</div>
							</div>
							<div class="right aligned column">
								<div class="ui small button submit blue">Submit</div>
							</div>
						</div>
					</form>
				</div>
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					Map preview
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<div id="map_canvas" style="width: 100%; height: 350px; margin:auto;">
						</div>
					</div>
				</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_shop_management_footer.jsp" %>  
</div>
</div>

<script src="https://maps.googleapis.com/maps/api/js?key=<%=IConstants.GOOGLE_MAP_API_KEY %>"
        type="text/javascript"></script>
<script type="text/javascript">
    function initialize(description) {
       	var lat = 18.490714;
       	var lng = 98.924859;
       	var logoImg = '';
       	<s:if test="%{basicInfo.logoImg != ''}">
       	logoImg = '<img class="ui mini image centered" src="<s:property value="basicInfo.logoImg" />">';
		</s:if>
		if($("#mapInfo_latitude").val()) {
			lat = $("#mapInfo_latitude").val();
		} else {
			$("#mapInfo_latitude").val(lat)
		}
		if($("#mapInfo_longitude").val()) {
			lng = $("#mapInfo_longitude").val();
		} else {
			$("#mapInfo_longitude").val(lng)
		}
		var center = new google.maps.LatLng(lat, lng);
		var map = new google.maps.Map(
			document.getElementById('map_canvas'), {
			center: center,
			zoom: 13,
		});
		var marker = new google.maps.Marker({
			position: center,
			map: map
		});

		var center = new google.maps.LatLng(lat, lng);
		var map = new google.maps.Map(
			document.getElementById('map_canvas'), {
			center: center,
			zoom: 13,
		});
		var marker = new google.maps.Marker({
			position: center,
			map: map
		});

		var html = '<h5 class="ui top header">' +
			logoImg +
			'<div class="content">' +
				'<s:property value="basicInfo.shopNameJp" />' +
				'<div class="sub header">' +
				'<s:property value="basicInfo.shopNameEn" />' +
				'</div>' +
			'</div>' + 
		'</h5>' +
		'<div class="description">' + description + '</div>';
		var infoWindow = new google.maps.InfoWindow({
			content: html
		});
		var openInfoWindowHtml = function() {
			infoWindow.open(map, marker);
		}
		google.maps.event.addListener(marker, 'click', openInfoWindowHtml);
		openInfoWindowHtml();
	}
	google.maps.event.addDomListener(window, 'load', function() {initialize($("#mapInfo_description").val());});
</script>
</body>
</html>