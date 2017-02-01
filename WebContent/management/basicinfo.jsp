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
  <title>Management - Basic Info</title>

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
	  $('.ui.dropdown')
	    .dropdown()
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
      $("#basicInfo_categoryCode").change(function() {
    	  var categoryCode = $(this).val();
    	  $.getJSON("<s:url value="/ajax/categoryZoneJson/" />" + categoryCode, 
			function(jsonResponse) {
				var select = $('#basicInfo_zoneCode');
			     select.find('option').remove();
			     $.each(jsonResponse.categoryZones, function(i, obj) {
			       $('<option>').val(obj.zoneInfo.zoneCode).text(obj.zoneInfo.zoneNameJp).appendTo(select);
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
				var image = "<img src='" + filePath + fileName + "' />";
				$("#logoImg").html(image);
				$('#shopLogoFileName').val(fileName);
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
				var image = "<img src='" + filePath + fileName + "' />";
				$("#shopImg").html(image);
				$('#shopImageFileName').val(fileName);
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
		});
    })
  ;
  </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
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
					Basic Info
				</h4>
				<div class="ui centered attached segment active content">
					<form class="ui form " method="post" action="<s:url value="/management/basicinfo/update"/>" enctype="multipart/form-data" >
						<div class="ui error message"><s:actionerror cssClass="list" /></div>
						<div class="two fields">
							<div class="inline field">
								<s:textfield name="basicInfo.shopCode" label="Shop code"/>
							</div>
							<div class="inline field">
								<s:select list="categoryInfos"
									headerKey="" headerValue="-"
									listKey="categoryCode" listValue="categoryNameJp"
									label="Shop category" 
									cssClass="ui search dropdown" 
									name="basicInfo.categoryCode">
								</s:select>
							</div>
						</div>
						<div class="two fields">
							<div class="inline field">
								<s:textfield name="basicInfo.shopNameJp" label="Japanese shop name"/>
							</div>
							<div class="inline field">
								<s:textfield name="basicInfo.shopNameEn" label="English shop name"/>
							</div>
						</div>
						<div class="two fields">
							<div class="inline field">
								<label>Shop logo</label>
								<div class="image ui small">
									<div id="logoImg">
										<s:if test="%{basicInfo.logoImg != ''}">
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
											  Upload
										</label>
									    <input type="file" id="filelogoImg" style="display:none">
									</div>
								</div>
								<s:hidden name="shopLogoFileName"></s:hidden>
							</div>
							<div class="inline field">
								<label>Shop image</label>
								<div class="image ui small">
									<div id="shopImg">
										<s:if test="%{basicInfo.shopImg != ''}">
											<img class="ui image centered small" src="<s:property value="basicInfo.shopImg" />">
										</s:if>
									</div>
									<div class="ui horizontal divider very basic">
										<!-- <button type="button" class="ui basic button" onclick="BrowseServer('Images:/','shopImg')">
											  <i class="icon upload"></i>
											  Upload
										</button> -->
										<label for="fileshopImg" class="ui basic button">
											<i class="icon upload"></i>
											  Upload
										</label>
									    <input type="file" id="fileshopImg" style="display:none">
									</div>
								</div>
								<s:hidden name="shopImageFileName"></s:hidden>
							</div>
						</div>
						<div class="two fields">
							<div class="inline field">
								<s:textfield name="basicInfo.address" label="Address"/>
							</div>
							<div class="inline field">
								<s:select list="categoryInfo.categoryZones"
									headerKey="" headerValue="-"
									listKey="primaryKey.zoneInfo.zoneCode" listValue="primaryKey.zoneInfo.zoneNameJp"
									label="Zone" 
									cssClass="ui search dropdown" 
									name="basicInfo.zoneCode">
								</s:select>
							</div>
						</div>
						<div class="two fields">
							<div class="inline field">
								<s:select list="#{'taniya':'Bangkok'}"
									headerKey="" headerValue="-"
									label="Province" 
									cssClass="ui search dropdown" 
									name="basicInfo.province">
								</s:select>
							</div>
							<div class="inline field">
								<s:textfield name="basicInfo.postcode" label="Post code"/>
							</div>
						</div>
						<div class="two fields">
							<div class="inline field">
								<s:textfield name="basicInfo.phone" label="Phone"/>
							</div>
							<div class="inline field">
								<s:textfield name="basicInfo.mobile" label="Mobile"/>
							</div>
						</div>
						<div class="inline field">
							<s:textfield name="basicInfo.email" label="Email address"/>
						</div>
						<div class="inline field">
							<s:select list="#{0:'Mon',1:'Tue',2:'Wed',3:'Thu',4:'Fri',5:'Sat',6:'Sun'}"
								headerKey="" headerValue="-" cssStyle="width: 100px;" size="10"
								cssClass="ui search dropdown" 
								label="Work day"
								name="basicInfo.startDayOfWeek">
							</s:select>
							<label>-</label>
							<s:select list="#{0:'Mon',1:'Tue',2:'Wed',3:'Thu',4:'Fri',5:'Sat',6:'Sun'}"
								headerKey="" headerValue="-" cssStyle="width: 100px;" size="10"
								cssClass="ui search dropdown" 
								name="basicInfo.endDayOfWeek">
							</s:select>
						</div>
						<div class="inline field">
							<s:textfield name="basicInfo.startTime" placeholder="HH:mm" label="Work time" size="6" />
							<label>-</label>
							<s:textfield name="basicInfo.endTime" placeholder="HH:mm" size="6" />
						</div>
						<div class="inline field">
							<label>Cusom URL</label>
							<div class="ui checkbox"><s:checkbox name="basicInfo.chkCustomUrl" label="" /></div>
							<s:textfield name="basicInfo.customUrl" size="50" />
						</div>
						<div class="inline field">
							<s:textarea name="basicInfo.description" label="Description"/>
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
								<div class="ui small button submit blue">Submit</div>
							</div>
						</div>
						<s:hidden name="basicInfo.shopInfoId"></s:hidden>
					</form>
				</div>
			</div>
			
		</div>
	</div>
  
</div>
<%@include file="/common/common_shop_management_footer.jsp" %>  
</div>
</body>
</html>