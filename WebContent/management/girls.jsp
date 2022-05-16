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

	  $("#addbtn")
		.on('click', function() {
		  $('.ui.modal .header:first').text("<s:text name="global.add_information" /><s:text name="global.menu_girls" />");
		  $('#infoForm').find("input[type=text], textarea").val("");
		  CKEDITOR.instances.girlInfo_description.setData('');
		  $('#infoForm')[0].action.value = "add";
		  $('#infoForm')[0].action = "<s:url value="/management/girl/add"/>";
		  $("#pic1").empty();
		  $("#pic2").empty();
		  $("#pic3").empty();
		  $("#pic4").empty();
		  $("#pic5").empty();
		  $("#pic1FileName").val("");
		  $("#pic2FileName").val("");
		  $("#pic3FileName").val("");
		  $("#pic4FileName").val("");
		  $("#pic5FileName").val("");
          $('.ui.modal')
		    .modal('show')
		  ;
        })
	  $("table").tablesort();
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
          fields: {
              girlInfo_nickName: {
                  identifier  : 'girlInfo_nickName',
                  rules: [
                    {
                      type   : 'empty',
                      prompt : '<s:text name="global.message_please_input" /><s:text name="global.nick_name" />'
                    },
                    {
                      type   : 'regExp[/^[a-zA-Z0-9 ]+$/]',
                      prompt : '<s:text name="global.message_please_input" /><s:text name="global.nick_name" /><s:text name="global.message_english_only" />'
                    },
                  ]
                },
          },
       })
      ;
	  $('#searchForm.ui.form')
      .form({
          fields: {}
      })
      ;
	  <s:if test="showInfo">
	  $('.ui.modal')
	    .modal('show')
	  ;
	  $("body").addClass("scrolling")
	  $("body > .ui.dimmer.modals").addClass("scrolling");
	  $("body > .ui.dimmer.modals > .ui.modal").addClass("scrolling");
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
      $('#girlsupdateForm')
      .form({
          onSuccess: function() { 
              var form = $(this);
              form.find("[name=availablelist]").remove()
              $( "input[name=available]", dataTable.fnGetNodes()).each(function(i, item) {
                  var obj = {id: item.value, checked: item.checked};
            	  form.append("<input name='availablelist' value='" + JSON.stringify(obj) + "' type='hidden' />")
              })
              return true;
          }
       })
      ;
  })
  ;
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
					<s:i18n name="global_th">
						<s:text name="global.search_condition" />
					</s:i18n>
				</h4>
				<div class="ui left aligned attached segment active content">
					<form class="ui form" id="searchForm" method="post" action="<s:url value="/management/girl/search"/>">
						<div class="inline field">
							<s:textfield name="girlSearch.nickName" key="global.nick_name"/>
						</div>
						<div class="inline field">
							<s:textfield name="girlSearch.category" key="global.category"/>
						</div>
						<div class="accordion">
							<div class="inline field title">
								<label class="label"><s:text name="global.location" /> :
									<i class="dropdown icon"></i>
								</label>
							</div>
							<div class="content">
								<div class="ui four column grid doubling">
									<s:iterator value="zoneInfos" status="rowstatus">
										<div class="column">
											<div class="field ui checkbox">
												<input type="checkbox" name="searchGirlLocations" id="searchGirlLocations_<s:property value="#rowstatus.count" />"
													<s:iterator value="searchGirlLocations" >
														<s:property value="top" />
														<s:if test="top == zoneInfoId">checked="checked"</s:if>
													</s:iterator>
													value="<s:property value="zoneInfoId" />">
												<label for="searchGirlLocations_<s:property value="#rowstatus.count" />"><s:property value="zoneNameEn" /></label>
											</div>
										</div>
									</s:iterator>
								</div>
							</div>
						</div>
						<div class="ui error message"></div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button submit blue"><s:text name="global.search" /></div>
								<div class="ui small button clear"><s:text name="global.clear" /></div>
							</div>
						</div>
					</form>
				</div>
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:text name="global.search_list" />
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<div class="ui right aligned one column grid">
							<div class="column">
								<div id="addbtn" class="ui small button blue"><s:text name="global.add" /></div>
							</div>
						</div>
						<table id="searchList" class="ui table celled compact striped unstackable sortable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th><s:text name="global.girl_photo" /></th>
									<th><s:text name="global.nick_name" /></th>
									<th><s:text name="global.age" /></th>
									<th><s:text name="global.location" /></th>
									<th>H</th>
									<th>W</th>
									<th>B</th>
									<th>W</th>
									<th>H</th>
									<th><s:text name="global.available" /></th>
									<th><s:text name="global.operation" /></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="girlInfos" status="status">
								<tr>
									<td class="center aligned"><s:property value="#status.count" /></td>
									<td>
										<img class="image ui tiny centered" src="<s:property value="pic1" />">
									</td>
									<td><s:property value="nickName" /></td>
									<td class="center aligned"><s:property value="age" /></td>
									<td>
										<s:iterator value="girlLocations" >
											<s:property value="primaryKey.zoneInfo.zoneNameEn" />
											<br />
										</s:iterator>
									</td>
									<td class="center aligned"><s:text name="format.integer"><s:param name="value" value="height"/></s:text></td>
									<td class="center aligned"><s:text name="format.integer"><s:param name="value" value="weight"/></s:text></td>
									<td class="center aligned"><s:text name="format.integer"><s:param name="value" value="bustSize"/></s:text></td>
									<td class="center aligned"><s:text name="format.integer"><s:param name="value" value="waistSize"/></s:text></td>
									<td class="center aligned"><s:text name="format.integer"><s:param name="value" value="hipSize"/></s:text></td>
									<td class="center aligned">
										<div class="ui toggle fitted checkbox">
											<input type="checkbox" name="available" 
											<s:if test="available == 'true'">checked="checked"</s:if>
											 value="<s:property value="girlInfoId" />">
											<label></label>
										</div>
									</td>
									<td class="center aligned">
										<div class="ui buttons">
											<a href="<s:url value="/management/girl/edit/%{girlInfoId}"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>
											<a href="<s:url value="/management/girl/delete/%{girlInfoId}"/>" class="ui icon button small red"><i class="ui icon delete"></i></a>
										</div>
									</td>
								</tr>
								</s:iterator>
							</tbody>
							<tfoot class="full-width">
								<tr>
									<th colspan="12">
										<form id="girlsupdateForm" class="ui form " method="post" action="<s:url value="/management/girl/girlsupdate"/>" >
											<div class="ui right floated small primary submit button">
												<s:text name="global.submit" />
											</div>
										</form>
									</th>
								</tr>
							</tfoot>
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
  	<s:text name="global.edit_information" /><s:text name="global.menu_girls" />
  </div>
  <div class="content">
  	<form class="ui form" id="infoForm" method="post" action="<s:url value="/management/girl/update"/>" enctype="multipart/form-data">
    	<div class="inline field">
			<s:textfield name="girlInfo.nickName" key="global.nick_name"/>
		</div>
		<div class="inline field">
			<s:textfield name="girlInfo.category" key="global.category"/>
		</div>
		<div class="ui accordion">
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
		</div>
		<br />
		<div class="inline field">
			<s:select list="ageList" name="girlInfo.age" key="global.age"></s:select>
		</div>
		<div class="inline fields">
			<label><s:text name="global.body_size" /> :</label>
			<div class="field">
				<s:select list="bustSizeList" name="girlInfo.bustSize" label="B "></s:select>
			</div>
			<div class="field">
				<s:select list="waistSizeList" name="girlInfo.waistSize" label="W "></s:select>
			</div>
			<div class="field">
				<s:select list="hipSizeList" name="girlInfo.hipSize" label="H "></s:select>
			</div>
		</div>
		<div class="inline fields">
			<label><s:text name="global.height_weight" /> :</label>
			<div class="field">
				<s:select list="heightList" name="girlInfo.height" label="H "></s:select>
			</div>
			<div class="field">
				<s:select list="weightList" name="girlInfo.weight" label="W "></s:select>
			</div>
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
					  	<s:text name="global.upload" />
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
					  	<s:text name="global.upload" />
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
					  	<s:text name="global.upload" />
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
					  	<s:text name="global.upload" />
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
					  	<s:text name="global.upload" />
					</label>
					<input type="file" id="filePic5" style="display:none">
					<s:hidden name="pic5FileName"></s:hidden>
				</div>
			</div>
		</div>
		<h4 class="ui horizontal divider header">
			<i class="comment icon"></i>
			<s:text name="global.description" />
		</h4>
		<div class="inline field">
			<s:textarea name="girlInfo.description" />
		</div>
		<s:hidden name="action" value="update"></s:hidden>
		<s:hidden name="girlInfo.girlInfoId"></s:hidden>
		<s:hidden name="girlInfo.available"></s:hidden>
		<s:hidden name="girlInfo.allSame"></s:hidden>
		<div class="ui error message"></div>
	</form>
	<%@include file="/common/common_upload_file.jsp" %>
  </div>
  <div class="actions">
    <div class="ui approve blue button"><s:text name="global.save" /></div>
    <div class="ui cancel button"><s:text name="global.cancel" /></div>
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