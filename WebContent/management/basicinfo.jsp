<%@page import="com.nightclub.common.IConstants"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title><s:text name="global.management" /> - <s:text name="global.menu_basic_info" /></title>

  <%@include file="/common/common_shop_management_header.jsp" %>
  <script src="<s:url value="/assets/library/jquery.form.js"/>"></script>
  <script src="<s:url value="/assets/library/fileUploadScript.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.ui.widget.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload-process.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload-ui.js"/>"></script>

  <style type="text/css">
  body {
    /*padding: 1em;*/
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
  .ui.form .inline.field>:first-child {
  	width: 150px;
  }
  </style>

  <!--- Example Javascript -->
  <script type="text/javascript">
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
          fields: {
        	  basicInfo_email: {
	              identifier  : 'basicInfo_email',
	              rules: [
	                {
	                  type   : 'email',
	                  prompt : 'Please enter a valid e-mail.'
	                }
	              ]
	            }
     	   },
     	  onFailure: function() {
     		  window.scrollTo(0,0);
     		  return false;
     	  }
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
	  <s:if test="hasActionErrors()">
      $('.ui.form').addClass("error");
      </s:if>
      $("#basicInfo_categoryInfoId").change(function() {
    	  var categoryInfoId = $(this).val();
    	  $.getJSON("<s:url value="/ajax/categoryZoneJson/" />" + categoryInfoId, 
			function(jsonResponse) {
				var select = $('#basicInfo_zoneInfoId');
			     select.find('option').remove();
			     $.each(jsonResponse.categoryZones, function(i, obj) {
			    	 <%--<s:if test="#request.locale.language=='th'">--%>
			       $('<option>').val(obj.zoneInfo.zoneInfoId).text(obj.zoneInfo.zoneNameEn).appendTo(select);
			       <%--</s:if>
			       <s:else>
			       $('<option>').val(obj.zoneInfo.zoneInfoId).text(obj.zoneInfo.zoneNameJp).appendTo(select);
			       </s:else>--%>
			     });
			     select.parents(".ui.dropdown:first").find(".text").text(select.find('option:first').text());
			});
      });
      $('#basicInfo_startTime, #basicInfo_endTime').timeEntry({show24Hours: true});
      $('#filelogoImg').fileupload({
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
				var iconDelete = '<a class="ui right corner label link filelogoImgDelete">'
					+ '<i class="window close outline icon"></i>'
					+ '</a>';
				var image = "<img class='image ui small centered' src='" + filePath + fileName + "' />";
				$("#logoImg").html(iconDelete + image);
				$('#shopLogoFileName').val(fileName);
				$('#shopLogoFileNameDelete').val("");
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
		});
      $('#fileshopImg').fileupload({
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
				var iconDelete = '<a class="ui right corner label link fileshopImgDelete">'
					+ '<i class="window close outline icon"></i>'
					+ '</a>';
				var image = "<img class='image ui small centered' src='" + filePath + fileName + "' />";
				$("#shopImg").html(iconDelete + image);
				$('#shopImageFileName').val(fileName);
				$('#shopImageFileNameDelete').val("");
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
		});
      $('#fileshopImg2').fileupload({
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
				var iconDelete = '<a class="ui right corner label link fileshopImg2Delete">'
					+ '<i class="window close outline icon"></i>'
					+ '</a>';
				var image = "<img class='image ui small centered' src='" + filePath + fileName + "' />";
				$("#shopImg2").html(iconDelete + image);
				$('#shopImage2FileName').val(fileName);
				$('#shopImage2FileNameDelete').val("");
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
		});
      $('#fileshopImg3').fileupload({
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
				var iconDelete = '<a class="ui right corner label link fileshopImg3Delete">'
					+ '<i class="window close outline icon"></i>'
					+ '</a>';
				var image = "<img class='image ui small centered' src='" + filePath + fileName + "' />";
				$("#shopImg3").html(iconDelete + image);
				$('#shopImage3FileName').val(fileName);
				$('#shopImage3FileNameDelete').val("");
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
		});
      $('#fileshopImg4').fileupload({
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
				var iconDelete = '<a class="ui right corner label link fileshopImg4Delete">'
					+ '<i class="window close outline icon"></i>'
					+ '</a>';
				var image = "<img class='image ui small centered' src='" + filePath + fileName + "' />";
				$("#shopImg4").html(iconDelete + image);
				$('#shopImage4FileName').val(fileName);
				$('#shopImage4FileNameDelete').val("");
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
		});
      $('#fileshopImg5').fileupload({
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
				var iconDelete = '<a class="ui right corner label link fileshopImg5Delete">'
					+ '<i class="window close outline icon"></i>'
					+ '</a>';
				var image = "<img class='image ui small centered' src='" + filePath + fileName + "' />";
				$("#shopImg5").html(iconDelete + image);
				$('#shopImage5FileName').val(fileName);
				$('#shopImage5FileNameDelete').val("");
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
		});
    })
  ;
  	$('.filelogoImgDelete').live('click', function() {
		$("#logoImg").html("");
		$('#shopLogoFileName').val("");
		$('#shopLogoFileNameDelete').val("true");
	});
	$('.fileshopImgDelete').live('click', function() {
		$("#shopImg").html("");
		$('#shopImageFileName').val("");
		$('#shopImageFileNameDelete').val("true");
	});
	$('.fileshopImg2Delete').live('click', function() {
		$("#shopImg2").html("");
		$('#shopImage2FileName').val("");
		$('#shopImage2FileNameDelete').val("true");
	});
	$('.fileshopImg3Delete').live('click', function() {
		$("#shopImg3").html("");
		$('#shopImage3FileName').val("");
		$('#shopImage3FileNameDelete').val("true");
	});
	$('.fileshopImg4Delete').live('click', function() {
		$("#shopImg4").html("");
		$('#shopImage4FileName').val("");
		$('#shopImage4FileNameDelete').val("true");
	});
	$('.fileshopImg5Delete').live('click', function() {
		$("#shopImg5").html("");
		$('#shopImage5FileName').val("");
		$('#shopImage5FileNameDelete').val("true");
	});
  </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
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
					<s:text name="global.menu_basic_info" />
				</h4>
				<div class="ui centered attached segment active content">
					<form class="ui form " method="post" action="<s:url value="/management/basicinfo/update"/>" enctype="multipart/form-data" >
						<div class="ui error message"><s:actionerror cssClass="list" /></div>
						<div class="two fields">
							<div class="inline field">
								<s:textfield name="basicInfo.shopNameJp" key="global.japanese_shop_name" />
							</div>
							<div class="inline field">
								<s:textfield name="basicInfo.shopNameEn" key="global.english_shop_name" />
							</div>
						</div>
						<div class="inline field">
							<s:select list="categoryInfos"
								listKey="categoryInfoId" listValue="categoryNameEn"
								key="global.shop_category" 
								cssClass="ui search dropdown" 
								name="basicInfo.categoryInfoId">
							</s:select>
						</div>
						<div class="fields">
							<div class="inline field">
								<label><s:text name="global.logo" /></label>
								<div class="image ui small">
									<div id="logoImg" class="image ui centered corner labeled">
										<s:if test="%{basicInfo.logoImg != ''}">
											<a class="ui right corner label link filelogoImgDelete">
												<i class="window close outline icon"></i>
											</a>
											<img class="ui image centered small" src="<s:property value="basicInfo.logoImg" />">
										</s:if>
									</div>
									<div class="ui horizontal divider very basic">
										<!-- <button type="button" class="ui basic button" onclick="BrowseServer('Images:/','logoImg')">
											  <i class="icon upload"></i>
											  Upload
										</button> -->
										<label for="filelogoImg" class="ui basic button">
											<i class="icon upload"></i>
										  	<s:text name="global.upload" />
										</label>
									    <input type="file" id="filelogoImg" style="display:none">
									</div>
								</div>
								<s:hidden name="shopLogoFileName"></s:hidden>
								<s:hidden name="shopLogoFileNameDelete"></s:hidden>
							</div>
						</div>
						<div class="fields">
							<div class="inline five field">
								<label><s:text name="global.shop_image" /></label>
								<div class="ui grid five column">
									<div class="image ui small column">
										<div id="shopImg" class="image ui centered corner labeled">
											<s:if test="%{basicInfo.shopImg != ''}">
												<a class="ui right corner label link fileshopImgDelete">
													<i class="window close outline icon"></i>
												</a>
												<img class="image ui small centered" src="<s:property value="basicInfo.shopImg" />">
											</s:if>
										</div>
										<div class="ui horizontal divider very basic">
											<label for="fileshopImg" class="ui basic button">
												<i class="icon upload"></i>
											  	<s:text name="global.upload" />
											</label>
											<input type="file" id="fileshopImg" style="display:none">
											<s:hidden name="shopImageFileName"></s:hidden>
											<s:hidden name="shopImageFileNameDelete"></s:hidden>
										</div>
									</div>
									<div class="image ui small column">
										<div id="shopImg2" class="image ui centered corner labeled">
											<s:if test="%{basicInfo.shopImg2 != ''}">
												<a class="ui right corner label link fileshopImg2Delete">
													<i class="window close outline icon"></i>
												</a>
												<img class="image ui small centered" src="<s:property value="basicInfo.shopImg2" />">
											</s:if>
										</div>
										<div class="ui horizontal divider very basic">
											<label for="fileshopImg2" class="ui basic button">
												<i class="icon upload"></i>
											  	<s:text name="global.upload" />
											</label>
											<input type="file" id="fileshopImg2" style="display:none">
											<s:hidden name="shopImage2FileName"></s:hidden>
											<s:hidden name="shopImage2FileNameDelete"></s:hidden>
										</div>
									</div>
									<div class="image ui small column">
										<div id="shopImg3" class="image ui centered corner labeled">
											<s:if test="%{basicInfo.shopImg3 != ''}">
												<a class="ui right corner label link fileshopImg3Delete">
													<i class="window close outline icon"></i>
												</a>
												<img class="image ui small centered" src="<s:property value="basicInfo.shopImg3" />">
											</s:if>
										</div>
										<div class="ui horizontal divider very basic">
											<label for="fileshopImg3" class="ui basic button">
												<i class="icon upload"></i>
											  	<s:text name="global.upload" />
											</label>
											<input type="file" id="fileshopImg3" style="display:none">
											<s:hidden name="shopImage3FileName"></s:hidden>
											<s:hidden name="shopImage3FileNameDelete"></s:hidden>
										</div>
									</div>
									<div class="image ui small column">
										<div id="shopImg4" class="image ui centered corner labeled">
											<s:if test="%{basicInfo.shopImg4 != ''}">
												<a class="ui right corner label link fileshopImg4Delete">
													<i class="window close outline icon"></i>
												</a>
												<img class="image ui small centered" src="<s:property value="basicInfo.shopImg4" />">
											</s:if>
										</div>
										<div class="ui horizontal divider very basic">
											<label for="fileshopImg4" class="ui basic button">
												<i class="icon upload"></i>
											  	<s:text name="global.upload" />
											</label>
											<input type="file" id="fileshopImg4" style="display:none">
											<s:hidden name="shopImage4FileName"></s:hidden>
											<s:hidden name="shopImage4FileNameDelete"></s:hidden>
										</div>
									</div>
									<div class="image ui small column">
										<div id="shopImg5" class="image ui centered corner labeled">
											<s:if test="%{basicInfo.shopImg5 != ''}">
												<a class="ui right corner label link fileshopImg5Delete">
													<i class="window close outline icon"></i>
												</a>
												<img class="image ui small centered" src="<s:property value="basicInfo.shopImg5" />">
											</s:if>
										</div>
										<div class="ui horizontal divider very basic">
											<label for="fileshopImg5" class="ui basic button">
												<i class="icon upload"></i>
											  	<s:text name="global.upload" />
											</label>
											<input type="file" id="fileshopImg5" style="display:none">
											<s:hidden name="shopImage5FileName"></s:hidden>
											<s:hidden name="shopImage5FileNameDelete"></s:hidden>
										</div>
									</div>
								</div>
							</div>
						</div>
						<h4 class="ui horizontal divider header">
							<i class="home icon"></i>
							<s:text name="global.other_information" />
						</h4>
						<div class="">
							<h4 class="">
								<s:text name="global.location" /> :
							</h4>
							<div class="">
								<div class="ui four column grid doubling">
									<s:iterator value="zoneInfos" status="rowstatus">
										<div class="column">
											<div class="field ui checkbox">
												<input type="checkbox" name="shopLocations" id="shopLocations_<s:property value="#rowstatus.count" />"
													<s:iterator value="shopLocations" >
														<s:property value="top" />
														<s:if test="top == zoneInfoId">checked="checked"</s:if>
													</s:iterator>
													value="<s:property value="zoneInfoId" />">
												<label for="shopLocations_<s:property value="#rowstatus.count" />"><s:property value="zoneNameEn" /></label>
											</div>
										</div>
									</s:iterator>
								</div>
							</div>
						</div>
						<br />
						<div class="two fields">
							<div class="inline field">
								<s:textfield name="basicInfo.phone" key="global.phone"/>
							</div>
							<div class="inline field">
								<s:textfield name="basicInfo.lineId" key="global.line_id"/>
							</div>
						</div>
						<div class="inline field">
							<s:textfield name="basicInfo.startTime" placeholder="HH:mm" key="global.open_time" size="6" />
							<label>-</label>
							<s:textfield name="basicInfo.endTime" placeholder="HH:mm" size="6" />
						</div>
						<s:if test="homeInfo.lineNotifyActive == 'true'">
							<div class="inline field">
								<label for="basicInfo_lineToken" class="label"><s:text name="global.line_token" />:</label>
								<div class="ui action input">
									<s:textfield name="basicInfo.lineToken" />
									<div onclick="window.open('<s:property value="lineOauthUrl" />')" class="ui small green button">
										<i class="linechat icon"></i>
										รับ Token
									</div>
								</div>
							</div>
						</s:if>
						<h4 class="ui horizontal divider header">
							<i class="comment icon"></i>
							<s:text name="global.description" />
						</h4>
						<div class="inline field">
							<s:textarea name="basicInfo.description" />
							<script type="text/javascript">
								CKEDITOR.replace("basicInfo.description", {
									/*filebrowserBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html',
									filebrowserImageBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html?type=Images',
									filebrowserFlashBrowseUrl : '${pageContext.request.contextPath }/ckfinder/ckfinder.html?type=Flash',*/
									filebrowserUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files'/*,
									filebrowserImageUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
									filebrowserFlashUploadUrl : '${pageContext.request.contextPath }/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'*/
								});
							</script>
						</div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button submit blue"><s:text name="global.submit" /></div>
							</div>
						</div>
						<s:hidden name="basicInfo.shopInfoId"></s:hidden>
						<s:hidden name="basicInfo.active"></s:hidden>
					</form>
				</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_shop_management_footer.jsp" %>  
</div>
</div>
</body>
</html>