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
  <title><s:i18n name="global_th"><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.menu_home_slide_image" /></s:i18n></title>

  <%@include file="/common/common_admin_management_header.jsp" %>
  <script src="<s:url value="/assets/library/jquery.form.js"/>"></script>
  <script src="<s:url value="/assets/library/fileUploadScript.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.ui.widget.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload-process.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload-ui.js"/>"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
  <link rel="stylesheet" type="text/css" href="<s:url value="/slick/slick.css"/>"/> 
  <link rel="stylesheet" type="text/css" href="<s:url value="/slick/slick-theme.css"/>"/>
  <script type="text/javascript" src="<s:url value="/slick/slick.min.js"/>"></script>

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
  #slideImgContainer {
  	min-height: 100px;
  	display:flex;
  	align-items: center;
  }
  #container {
  	height: auto !important;
  }
  .slide-image {
  	width:80%;
  	margin: 20px auto;
  }
  .slide-image img.ui.fluid.image {
  	height: 350px;
  	width:auto;
  }
  </style>

  <!--- Example Javascript -->
  <script>
  $(document)
    .ready(function() {
      $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
      });
      $('.single-item').slick({
    	  autoplay: true,
    	  autoplaySpeed: 3000,
    	  arrows: true
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
              form.find("[name=homeSlideImagesFileNameList]").remove()
              $( "input[name=homeSlideImagesFileName]").each(function(i, item) {
            	  form.append("<input name='homeSlideImagesFileNameList' value='" + item.value + "' type='hidden' />")
              })
              return true; 
          }
       })
      ;
	  $("table").tablesort();
	  $("#slideImgContainer").sortable({
		  change: function( event, ui ) {
			  reloadPreview();
		  }
	  });
      $('#slideImage').fileupload({
			url: '<s:url value="/UploadFileServlet"/>',
			dataType: 'json',
			add: function (e, data) {
				data.submit();
			},
	        success:function(response,status) {
		        console.log(arguments)
				console.log(response.fileName);
		        var fileName = response.fileName;
				var filePath = response.path;
				var imageDiv = 	'<div class="ui card">'
								+ '<div class="image ui centered corner labeled">'
								+ '<a class="ui right corner label removeSlideImage link" data-variation="tiny">'
								+ '<i class="remove icon"></i>'
								+ '</a>'
								+ '<img class="ui image centered" src="' + filePath + fileName + '" />'
								+ '<input type="hidden" name="homeSlideImagesFileName" value="' + fileName + '" />'
								+ '</div>'
								+ '</div>';
				if ($("#slideImgContainer img").length == 0) {
					$("#slideImgContainer").html("");
				}
				$("#slideImgContainer").append(imageDiv);
	        	console.log('success');
	        	reloadPreview();
	        },
	        error:function(error){
	        	console.log(error);
	        }
	    });
    })
  ;
  $('.removeSlideImage').live('click', function () {
	  $(this).parents(".ui.card:first").remove();
	  if ($("#slideImgContainer img").length == 0) {
	   	$("#slideImgContainer").html('<s:i18n name="global_th"><s:text name="global.no_data" /></s:i18n>');
	  }
	  reloadPreview();
	} );

  function reloadPreview() {
		$('.single-item').html("");
		if ($("#slideImgContainer img").length > 0) {
	    	$( "#slideImgContainer img").each(function(i, item) {
	   		$('.single-item').append('<div><img class="ui image fluid centered" src="' + item.src + '" /></div>');
	      });
			$('.single-item').slick('destroy');
			$('.single-item').slick({
		    	  autoplay: true,
		    	  autoplaySpeed: 3000,
		    	  arrows: true
	    	});
		}
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
					<s:i18n name="global_th"><s:text name="global.menu_home_slide_image" /></s:i18n>
				</h4>
				<div class="ui centered grid attached segment active content" id="container">
					<div class="column one left aligned">
						<div class="ui centered six doubling cards " id="slideImgContainer">
						<s:if test="%{homeSlideImages.size gte 0}">
							<s:iterator value="homeSlideImages" status="status">
								<div class="ui card">
									<div class="image ui centered corner labeled">
										<a class="ui right corner label removeSlideImage link" data-variation="tiny">
											<i class="remove icon"></i>
										</a>
										<img class="mage ui centered" src="<s:property value="slideImg" />">
										<input type="hidden" name="homeSlideImagesFileName" value="<s:property value="slideImg" />" />
									</div>
								</div>
							</s:iterator>
						</s:if>
						<s:if test="%{homeSlideImages.size eq 0}">
							<s:i18n name="global_th">
								<s:text name="global.no_data" />
							</s:i18n>
						</s:if>
						</div>
						<div class="ui horizontal divider very basic">
							<label for="slideImage" class="ui basic button">
								<i class="icon upload"></i>
							  	<s:i18n name="global_th"><s:text name="global.upload" /></s:i18n>
							</label>
						<input type="file" id="slideImage" style="display:none">
						</div>
						<div class="ui horizontal divider very basic">
							<form class="ui form " method="post" action="<s:url value="/admin/homeslideimage/update"/>" >
								<div class="ui right floated small primary submit button">
									<s:i18n name="global_th"><s:text name="global.submit" /></s:i18n>
								</div>
							</form>
						</div>
						<h4 class="ui horizontal divider header">
							<s:i18n name="global_th"><s:text name="global.preview" /></s:i18n>
						</h4>
						<div class="slide-image">
							<div class="ui centered grid ui">
						  		<div class="center aligned column ">
								  	<div class="single-item">
								  		<s:iterator value="homeSlideImages" status="status">
								  		<s:set name="slideImgH500" value="%{slideImg.replace('upload/', 'upload/h_500,c_scale/')}" />
								  		<div><img class="ui image fluid centered" src="<s:property value="slideImgH500" />" /></div>
								  		</s:iterator>
									</div>
								</div>
							</div>
						</div>
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