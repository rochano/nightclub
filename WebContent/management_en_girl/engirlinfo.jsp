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
  <title><s:i18n name="global_th"><s:text name="global.management" /> - <s:text name="global.menu_basic_info" /></s:i18n></title>

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
  .ui.form .inline.field>:first-child, .ui.form .inline.fields>label {
  	width: 150px;
  }
  #provinceInfos > .column:not(.row) {
  	padding-top: 0;
  }
  #provinceInfos {
  	margin-left:0;
  }
  [name=girlProvinces] {
  	display: none;
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
              girlInfo_nickName: {
                  identifier  : 'girlInfo_nickName',
                  rules: [
                    {
                      type   : 'empty',
                      prompt : '<s:i18n name="global_th"><s:text name="global.message_please_input" /><s:text name="global.nick_name" /></s:i18n>'
                    },
                    {
                      type   : 'regExp[/^[a-zA-Z0-9 ]+$/]',
                      prompt : '<s:i18n name="global_th"><s:text name="global.message_please_input" /><s:text name="global.nick_name" /><s:text name="global.message_english_only" /></s:i18n>'
                    },
                  ]
                },
              girlInfo_price: {
                  identifier  : 'girlInfo_price',
                  rules: [
                     {
                      type   : 'regExp[/(^[0-9]{1,3}(,[0-9]{3})*$)|^$/]',
                      prompt : '<s:i18n name="global_th"><s:text name="global.message_please_input" /><s:text name="global.price" /><s:text name="global.message_correctly" /></s:i18n>'
                     },
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
	  $('#filePic1').fileupload({
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
				var image = "<img src='" + filePath + fileName + "' />";
				$("#pic1").html(image);
				$('#pic1FileName').val(fileName);
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
	  });
	  $('#filePic2').fileupload({
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
				var image = "<img src='" + filePath + fileName + "' />";
				$("#pic2").html(image);
				$('#pic2FileName').val(fileName);
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
	  });
	  $('#filePic3').fileupload({
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
				var image = "<img src='" + filePath + fileName + "' />";
				$("#pic3").html(image);
				$('#pic3FileName').val(fileName);
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
	  });
	  $('#filePic4').fileupload({
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
				var image = "<img src='" + filePath + fileName + "' />";
				$("#pic4").html(image);
				$('#pic4FileName').val(fileName);
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
	  });
	  $('#filePic5').fileupload({
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
				var image = "<img src='" + filePath + fileName + "' />";
				$("#pic5").html(image);
				$('#pic5FileName').val(fileName);
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
	  });
	  $('#fileMov1').fileupload({
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
				var image = "<video src='" + filePath + fileName + "' controls />";
				$("#mov1").html(image);
				$('#mov1FileName').val(fileName);
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
	  });
      $("input.number").on("focus", function() {
		  $(this).val($(this).val().replace(",",""));
	  }).on("blur", function() {
		  var val = $(this).val().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		  $(this).val(val);
	  });
      $("#girlInfo_countryInfoId").change(function() {
   	  	var countryInfoId = $(this).val();
   	  	loadProvince(countryInfoId, '#provinceInfos', 'girlLocations');
       });
      $("[name=girlLocations]").change(function() {
    	  var parents = $(this).parents(".accordion:first")
          if(parents.find("[name=girlLocations]:checked").length == 0) {
        	  parents.find("[name=girlProvinces]").prop('checked',false );
          } else {
        	  parents.find("[name=girlProvinces]").prop('checked',true );
          }
      });
    })
  ;
  function loadProvince(countryInfoId, provinceInfosElmId, girlLocationsId) {
	  $.getJSON("<s:url value="/ajax/loadProvinceByCountry/" />" + countryInfoId, 
			function(jsonResponse) {
				var provinceInfos = $(provinceInfosElmId);
				provinceInfos.empty();
			     $.each(jsonResponse.provinceInfos, function(i, obj) {
					var html = '';
					html += '<div class="column">';
					html += '	<div class="field ui">';
					html += '		<div class="ui accordion">';
					html += '			<div class="title ui">';
					html += '				<input type="checkbox" name="girlProvinces" id="girlProvinces_' + i + '"';
					html += '				value="' + obj.provinceInfoId + '">';
					html += '				<label>' + obj.provinceNameEn + '</label>';
					html += '				<i class="dropdown icon"></i>';
					html += '			</div>';
					html += '			<div class="content">';
					html += '				<div class="ui four column grid" class="zoneInfos">';
					if(obj.zoneInfos) {
						$.each(obj.zoneInfos, function(j, objZoneInfo) {
							html += '				<div class="column">';
							html += '					<div class="field ui checkbox">';
							html += '						<input type="checkbox" name="' + girlLocationsId + '" id="' + obj.provinceInfoId + '_' + girlLocationsId + '_' + j + '"';
							html += '						value="' + objZoneInfo.zoneInfoId + '">';
							html += '						<label for="' + obj.provinceInfoId + '_' + girlLocationsId + '_' + j + '">' + objZoneInfo.zoneNameEn + '</label>';
							html += '					</div>';
							html += '				</div>';
						});
					}
					html += '				</div>';
					html += '			</div>';
					html += '		</div>';
					html += '	</div>';
					html += '</div>';
			       $(html).appendTo(provinceInfos);
			     });
			});
   }
  </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
</head>
<body class="menu pushable">
<%@include file="/common/common_en_girl_management_header_info.jsp" %>
<!-- Sidebar Menu -->
<div class="ui toc vertical top large inverted sidebar menu">
  	<a class="ui toc item title">
		<i class="cancel icon"></i>
		&nbsp;
	</a>
	<%@include file="/common/common_en_girl_management_menu.jsp" %>
</div>
<div class="pusher">
<div class="full height">
<div class="toc">
	<!-- Sidebar Menu -->
	<div class="ui inverted vertical menu">
		<%@include file="/common/common_en_girl_management_menu.jsp" %>
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
					<s:i18n name="global_th"><s:text name="global.menu_basic_info" /></s:i18n>
				</h4>
				<div class="ui centered attached segment active content">
					<form class="ui form " method="post" action="<s:url value="/management_en_girl/information/update"/>" enctype="multipart/form-data" >
						<div class="ui error message"><s:actionerror cssClass="list" /></div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="girlInfo.nickName" key="global.nick_name"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:select list="ageList" name="girlInfo.age" key="global.age" ></s:select></s:i18n>
						</div>
						<div class="inline fields">
							<label class="label">
								<s:i18n name="global_th">
									<s:text name="global.gender" /> :
								</s:i18n>
							</label>
							<s:iterator value="genderInfos" status="rowstatus">
								<div class="inline field">
									<div class="ui radio checkbox">
										<input type="radio" name="girlInfo.genderInfoId" id="genderInfos_<s:property value="#rowstatus.count" />"
											<s:if test="girlInfo.genderInfoId == genderInfoId">checked="checked"</s:if>
											 value="<s:property value="genderInfoId" />">
										<label for="genderInfos_<s:property value="#rowstatus.count" />">
											<s:property value="genderNameEn" />
										</label>
									</div>
								</div>
							</s:iterator>
						</div>
						<div class="inline field">
							<s:i18n name="global_th">
								<s:select list="nationalityInfos"
									listKey="nationalityInfoId" listValue="nationalityNameEn"
									key="global.nationality" 
									cssClass="ui search dropdown" 
									name="girlInfo.nationalityInfoId">
								</s:select>
							</s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th">
								<s:select list="skinInfos"
									listKey="skinInfoId" listValue="skinNameEn"
									key="global.skin" 
									cssClass="ui search dropdown" 
									name="girlInfo.skinInfoId">
								</s:select>
							</s:i18n>
						</div>
						<div class="inline fields">
							<label><s:i18n name="global_th"><s:text name="global.body_size" /></s:i18n> :</label>
							<div class="field">
								<s:select list="bustSizeList" name="girlInfo.bustSize" label="B " headerKey="-1" headerValue="-"></s:select>
							</div>
							<div class="field">
								<s:select list="waistSizeList" name="girlInfo.waistSize" label="W " headerKey="-1" headerValue="-"></s:select>
							</div>
							<div class="field">
								<s:select list="hipSizeList" name="girlInfo.hipSize" label="H " headerKey="-1" headerValue="-"></s:select>
							</div>
						</div>
						<div class="inline fields">
							<label><s:i18n name="global_th"><s:text name="global.height_weight" /></s:i18n> :</label>
							<div class="field">
								<s:select list="heightList" name="girlInfo.height" label="H " headerKey="-1" headerValue="-"></s:select>
							</div>
							<div class="field">
								<s:select list="weightList" name="girlInfo.weight" label="W " headerKey="-1" headerValue="-"></s:select>
							</div>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="phone" key="global.mobile"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="email" key="global.email"/></s:i18n>
						</div>
						<%-- <div class="accordion">
							<h4 class="title">
								<s:text name="global.location" /> :
								<i class="dropdown icon"></i>
							</h4>
							<div class="content">
								<div class="ui four column grid doubling">
									<s:iterator value="zoneInfos" status="rowstatus">
										<div class="column">
											<div class="field ui checkbox">
												<input type="checkbox" name="girlLocations" id="girlLocations_<s:property value="#rowstatus.count" />"
													<s:iterator value="girlLocations" >
														<s:property value="top" />
														<s:if test="top == zoneInfoId">checked="checked"</s:if>
													</s:iterator>
													value="<s:property value="zoneInfoId" />">
												<label for="girlLocations_<s:property value="#rowstatus.count" />"><s:property value="zoneNameEn" /></label>
											</div>
										</div>
									</s:iterator>
								</div>
							</div>
						</div> --%>
						<div class="inline field">
							<s:i18n name="global_th">
								<s:select list="countryInfos"
									listKey="countryInfoId" listValue="countryNameEn"
									key="global.country" 
									cssClass="ui search dropdown" 
									name="girlInfo.countryInfoId">
								</s:select>
							</s:i18n>
						</div>
						<div class="ui accordion">
							<h4 class="title">
								<s:i18n name="global_th"><s:text name="global.province" /></s:i18n> :
								<i class="dropdown icon"></i>
							</h4>
							<div class="content">
								<div class="ui one column grid doubling" id="provinceInfos">
									<s:iterator value="provinceInfos" status="rowstatus">
										<div class="column">
											<div class="field ui">
												<div class="ui accordion">
													<div class="title ui">
														<input type="checkbox" name="girlProvinces" id="girlProvinces_<s:property value="#rowstatus.count" />"
														<s:iterator value="girlProvinces" >
															<s:property value="top" />
															<s:if test="top == provinceInfoId">checked="checked"</s:if>
														</s:iterator>
														value="<s:property value="provinceInfoId" />">
														<label><s:property value="provinceNameEn" /></label>
														<i class="dropdown icon"></i>
													</div>
													<div class="content">
														<div class="ui four column grid" class="zoneInfos">
															<s:iterator value="top.zoneInfos" status="rowstatus">
																<div class="column">
																	<div class="field ui checkbox">
																		<input type="checkbox" name="girlLocations" id="<s:property value="provinceInfoId" />_girlLocations_<s:property value="#rowstatus.count" />"
																		<s:iterator value="girlLocations" >
																			<s:property value="top" />
																			<s:if test="top == zoneInfoId">checked="checked"</s:if>
																		</s:iterator>
																		value="<s:property value="zoneInfoId" />">
																		<label for="<s:property value="provinceInfoId" />_girlLocations_<s:property value="#rowstatus.count" />"><s:property value="zoneNameEn" /></label>
																	</div>
																</div>
															</s:iterator>
														</div>
													</div>
												</div>
											</div>
										</div>
									</s:iterator>
								</div>
							</div>
						</div>
						<br />
						<div class="inline field">
							<s:i18n name="global_th">
								<s:select list="#{'1':getText('global.en_girl_type_1'), 
												'2':getText('global.en_girl_type_2'), 
												'3':getText('global.en_girl_type_3')}"
									key="global.type" 
									cssClass="ui search dropdown" 
									name="girlInfo.type">
								</s:select>
							</s:i18n>
						</div>
						<div class="inline field">
							<label for="girlInfo_price"><s:i18n name="global_th"><s:text name="global.price" /></s:i18n>: </label>
							<input type="text" name="girlInfo.price" size="7" class="number" id="girlInfo_price"
												value="<s:text name="format.integer"><s:param name="value" value="girlInfo.price"/></s:text>" />
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="girlInfo.crcy" key="global.crcy"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="girlInfo.lineId" key="global.line_id"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="girlInfo.telegramId" key="global.telegram_id"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="girlInfo.skypeId" key="global.skype_id"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="girlInfo.twitterId" key="global.twitter_id"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="girlInfo.wechatId" key="global.wechat_id"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="girlInfo.whatsAppId" key="global.whats_app_id"/></s:i18n>
						</div>
						<div class="ui grid five column">
							<div class="image ui small column">
								<div id="pic1">
									<s:if test="%{girlInfo.pic1 != ''}">
										<img class="image ui small centered" src="<s:property value="girlInfo.pic1" />">
									</s:if>
								</div>
								<div class="ui horizontal divider very basic">
									<!-- <button type="button" class="ui basic button" onclick="BrowseServer('Images:/','pic1')">
										  <i class="icon upload"></i>
										  Upload
									</button> -->
									<label for="filePic1" class="ui basic button">
										<i class="icon upload"></i>
									  	<s:i18n name="global_th"><s:text name="global.upload" /></s:i18n>
									</label>
									<input type="file" id="filePic1" style="display:none">
									<s:hidden name="pic1FileName"></s:hidden>
								</div>
							</div>
							<div class="image ui small column">
								<div id="pic2">
									<s:if test="%{girlInfo.pic2 != ''}">
										<img class="image ui small centered" src="<s:property value="girlInfo.pic2" />">
									</s:if>
								</div>
								<div class="ui horizontal divider very basic">
									<!-- <button type="button" class="ui basic button" onclick="BrowseServer('Images:/','pic2')">
										  <i class="icon upload"></i>
										  Upload
									</button> -->
									<label for="filePic2" class="ui basic button">
										<i class="icon upload"></i>
									  	<s:i18n name="global_th"><s:text name="global.upload" /></s:i18n>
									</label>
									<input type="file" id="filePic2" style="display:none">
									<s:hidden name="pic2FileName"></s:hidden>
								</div>
							</div>
							<div class="image ui small column">
								<div id="pic3">
									<s:if test="%{girlInfo.pic3 != ''}">
										<img class="image ui small centered" src="<s:property value="girlInfo.pic3" />">
									</s:if>
								</div>
								<div class="ui horizontal divider very basic">
									<!-- <button type="button" class="ui basic button" onclick="BrowseServer('Images:/','pic3')">
										  <i class="icon upload"></i>
										  Upload
									</button> -->
									<label for="filePic3" class="ui basic button">
										<i class="icon upload"></i>
									  	<s:i18n name="global_th"><s:text name="global.upload" /></s:i18n>
									</label>
									<input type="file" id="filePic3" style="display:none">
									<s:hidden name="pic3FileName"></s:hidden>
								</div>
							</div>
							<div class="image ui small column">
								<div id="pic4">
									<s:if test="%{girlInfo.pic4 != ''}">
										<img class="image ui small centered" src="<s:property value="girlInfo.pic4" />">
									</s:if>
								</div>
								<div class="ui horizontal divider very basic">
									<!-- <button type="button" class="ui basic button" onclick="BrowseServer('Images:/','pic4')">
										  <i class="icon upload"></i>
										  Upload
									</button> -->
									<label for="filePic4" class="ui basic button">
										<i class="icon upload"></i>
									  	<s:i18n name="global_th"><s:text name="global.upload" /></s:i18n>
									</label>
									<input type="file" id="filePic4" style="display:none">
									<s:hidden name="pic4FileName"></s:hidden>
								</div>
							</div>
							<div class="image ui small column">
								<div id="pic5">
									<s:if test="%{girlInfo.pic5 != ''}">
										<img class="image ui small centered" src="<s:property value="girlInfo.pic5" />">
									</s:if>
								</div>
								<div class="ui horizontal divider very basic">
									<!-- <button type="button" class="ui basic button" onclick="BrowseServer('Images:/','pic5')">
										  <i class="icon upload"></i>
										  Upload
									</button> -->
									<label for="filePic5" class="ui basic button">
										<i class="icon upload"></i>
									  	<s:i18n name="global_th"><s:text name="global.upload" /></s:i18n>
									</label>
									<input type="file" id="filePic5" style="display:none">
									<s:hidden name="pic5FileName"></s:hidden>
								</div>
							</div>
						</div>
						<br />
						<h4 class="ui horizontal divider header">
							<i class="video icon"></i>
							<s:i18n name="global_th"><s:text name="global.video" /></s:i18n>
						</h4>
						<div class="ui grid five column">
							<div class="image ui small column">
								<div id="mov1">
									<s:if test="%{girlInfo.mov1 != ''}">
										<video class="ui small centered" src="<s:property value="girlInfo.mov1" />" controls />
									</s:if>
								</div>
								<div class="ui horizontal divider very basic">
									<label for="fileMov1" class="ui basic button">
										<i class="icon upload"></i>
									  	<s:i18n name="global_th"><s:text name="global.upload" /></s:i18n>
									</label>
									<input type="file" id="fileMov1" style="display:none">
									<s:hidden name="mov1FileName"></s:hidden>
								</div>
							</div>
						</div>
						<h4 class="ui horizontal divider header">
							<i class="comment icon"></i>
							<s:i18n name="global_th"><s:text name="global.description" /></s:i18n>
						</h4>
						<div class="inline field">
							<s:textarea name="girlInfo.description" />
						</div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button submit blue"><s:i18n name="global_th"><s:text name="global.submit" /></s:i18n></div>
							</div>
						</div>
						<s:hidden name="action" value="update"></s:hidden>
						<s:hidden name="girlInfo.girlInfoId"></s:hidden>
						<s:hidden name="girlInfo.available"></s:hidden>
						<s:hidden name="girlInfo.allSame"></s:hidden>
						<div class="ui error message"></div>
					</form>
				</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_en_girl_management_footer.jsp" %>  
</div>
</div>
<script type="text/javascript">
	CKEDITOR.replace("girlInfo.description", {
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