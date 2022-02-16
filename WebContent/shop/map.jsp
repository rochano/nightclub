<%@page import="com.nightclub.common.IConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Shop - Map</title>

  <%@include file="/common/common_shop_header.jsp" %>

  <style>
  .ui.menu:last-child {
    margin-bottom: 110px;
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
    })
  ;
  </script>
</head>
<body>
<!-- Sidebar Menu -->
<%@include file="/common/common_shop_menu_sidebar.jsp" %>
<div class="pusher">
	<div class="ui segment very basic">
		<div class="ui centered grid">
			<div class="eleven wide column container" id="container">
				<%@include file="/common/common_statistic_info.jsp" %>
				<%@include file="/common/common_shop_header_info.jsp" %>
				<div class="ui menu inverted stackable">
					<a class="toc item"><i class="sidebar icon"></i></a>
					<%@include file="/common/common_shop_menu.jsp" %>
				</div>
				
				<div class="center aligned column">
					<div class="ui segment header">
						<h2 class="ui top header">
							<i class="marker icon"></i>
							<div class="content"><s:text name="global.shop_menu_map" /></div>
						</h2>
					</div>
					<div class="ui centered attached segment">
						<div class="column one left aligned">
							<div id="map_canvas" style="width: 100%; height: 350px; margin:auto;">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	  
	</div>
	<s:hidden name="shop.shopNameEn" ></s:hidden>
	<s:hidden name="mapInfo.description" ></s:hidden>
	<%@include file="/common/common_shop_footer.jsp" %>
</div>
<script src="https://maps.googleapis.com/maps/api/js?key=<%=IConstants.GOOGLE_MAP_API_KEY %>"
        type="text/javascript"></script>
<script type="text/javascript">
    function initialize() {
       	var lat = <s:property value="mapInfo.latitude" />;
       	var lng = <s:property value="mapInfo.longitude" />;
       	var logoImg = '';
       	<s:if test="%{shop.logoImg != ''}">
       	logoImg = '<img class="ui mini image centered" src="<s:property value="shop.logoImg" />">';
		</s:if>
       	var description = "";
       	if($("#mapInfo_description").val()) {
       		var descriptionNewLine = $("#mapInfo_description").val().split(/\r?\n/g);
       		$.map(descriptionNewLine, function(val, i) {
       			description += "<p>" + val + "</p>"
       		});
		};

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
				'<s:property value="shop.shopNameJp" />' +
				'<div class="sub header">' +
				'<s:property value="shop.shopNameEn" />' +
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
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
  
</body>
</html>