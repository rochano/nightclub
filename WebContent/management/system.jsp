<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Management - System</title>

  <%@include file="/common/common_shop_management_header.jsp" %>

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
  .ui.celled.table tr td.border-left {
    border-left: 1px solid rgba(34,36,38,.1);
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
      $('.message .close')
      .on('click', function() {
        $(this)
          .closest('.message')
          .transition('fade')
        ;
      })
    ;
      $("#addbtn")
		.on('click', function() {
		  $('#infoForm').find("input[type=text], input[type=hidden], textarea").val("");
		  $('#infoForm')[0].action.value = "add";
		  $('#infoForm')[0].action = "<s:url value="/management/system/add"/>";
        $('.ui.modal')
		    .modal('show')
		  ;
      });
      $('#infoForm.ui.form')
      .form({
          fields: {},
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
	  $("input.number").on("focus", function() {
		  $(this).val($(this).val().replace(",",""));
	  }).on("blur", function() {
		  var val = $(this).val().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		  $(this).val(val);
	  });
      $('#systemInfoupdateForm')
      .form({
          onSuccess: function() { 
              var form = $(this);
              form.find("[name=classType]").remove()
              $( "input[name=classType]:checked", dataTable.fnGetNodes()).each(function(i, item) {
            	  form.append("<input name='classType' value='" + item.value + "' type='hidden' />")
              })
              return true; 
          }
       })
      ;
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
					System List
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<table id="searchList" class="ui table celled compact striped unstackable unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th>Class</th>
									<th>Service</th>
									<th>Duration</th>
									<th>Price</th>
									<th>Active</th>
									<th>Operation</th>
								</tr>
							</thead>
							<tbody>
								<%-- Normal --%>
								<tr>
									<td class="center aligned" rowspan="3">1</td>
									<td class="center aligned" rowspan="3">Normal</td>
									<td class="center aligned">1</td>
									<td class="center aligned">60</td>
									<td class="center aligned"><s:text name="format.integer"><s:param name="value" value="systemInfo.priceNormal1"/></s:text></td>
									<td class="center aligned" rowspan="3">
										<div class="ui toggle fitted checkbox">
											<input type="radio" name="classType" 
											<s:if test="systemInfo.classType == 'Normal'">checked="checked"</s:if>
											 value="Normal">
											<label></label>
										</div>
									</td>
									<td class="center aligned" rowspan="3">
										<a href="<s:url value="/management/system/edit/Normal"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>
									</td>
								</tr>
								<tr>
									<td style="display: none"></td>
									<td style="display: none"></td>
									<td class="center aligned border-left">2</td>
									<td class="center aligned">90</td>
									<td class="center aligned"><s:text name="format.integer"><s:param name="value" value="systemInfo.priceNormal2"/></s:text></td>
									<td style="display: none"></td>
									<td style="display: none"></td>
								</tr>
								<tr>
									<td style="display: none"></td>
									<td style="display: none"></td>
									<td class="center aligned border-left">3</td>
									<td class="center aligned">120</td>
									<td class="center aligned"><s:text name="format.integer"><s:param name="value" value="systemInfo.priceNormal3"/></s:text></td>
									<td style="display: none"></td>
									<td style="display: none"></td>
								</tr>
								<%-- VIP --%>
								<tr>
									<td class="center aligned" rowspan="3">2</td>
									<td class="center aligned" rowspan="3">VIP</td>
									<td class="center aligned">1</td>
									<td class="center aligned">60</td>
									<td class="center aligned"><s:text name="format.integer"><s:param name="value" value="systemInfo.priceVIP1"/></s:text></td>
									<td class="center aligned" rowspan="3">
										<div class="ui toggle fitted checkbox">
											<input type="radio" name="classType" 
											<s:if test="systemInfo.classType == 'VIP'">checked="checked"</s:if>
											 value="VIP">
											<label></label>
										</div>
									</td>
									<td class="center aligned" rowspan="3">
										<a href="<s:url value="/management/system/edit/VIP"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>
									</td>
								</tr>
								<tr>
									<td style="display: none"></td>
									<td style="display: none"></td>
									<td class="center aligned border-left">2</td>
									<td class="center aligned">90</td>
									<td class="center aligned"><s:text name="format.integer"><s:param name="value" value="systemInfo.priceVIP2"/></s:text></td>
									<td style="display: none"></td>
									<td style="display: none"></td>
								</tr>
								<tr>
									<td style="display: none"></td>
									<td style="display: none"></td>
									<td class="center aligned border-left">3</td>
									<td class="center aligned">120</td>
									<td class="center aligned"><s:text name="format.integer"><s:param name="value" value="systemInfo.priceVIP3"/></s:text></td>
									<td style="display: none"></td>
									<td style="display: none"></td>
								</tr>
							</tbody>
							<tfoot class="full-width">
								<tr>
									<th colspan="7">
										<form id="systemInfoupdateForm" class="ui form " method="post" action="<s:url value="/management/system/systemsupdate"/>" >
											<div class="ui right floated small primary submit button">
												Submit
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
    System Information
  </div>
  <div class="content">
    <form class="ui form" id="infoForm" method="post" action="<s:url value="/management/system/update"/>">
		<div class="inline fields">
			<label class="label">Class:</label>
			<div class="disabled inline field">
				<s:textfield name="classType" readonly="true" disabled="true" />
			</div>
		</div>
		<div class="inline fields">
			<label class="label">Service:</label>
			<div class="disabled inline field">
				<s:textfield name="service1" size="7" value="1" readonly="true" />
			</div>
			<div class="disabled inline field">
				<s:textfield name="service2" size="7" value="2" readonly="true" />
			</div>
			<div class="disabled inline field">
				<s:textfield name="service2" size="7" value="3" readonly="true" />
			</div>
		</div>
		<div class="inline fields">
			<label class="label">Duration:</label>
			<div class="disabled inline field">
				<s:textfield name="duration1" size="7" value="60" readonly="true" />
			</div>
			<div class="disabled inline field">
				<s:textfield name="duration2" size="7" value="90" readonly="true" />
			</div>
			<div class="disabled inline field">
				<s:textfield name="duration2" size="7" value="120" readonly="true" />
			</div>
		</div>
		<div class="inline fields">
			<label class="label">Price:</label>
			<div class="inline field">
				<s:textfield name="price1" size="7" class="number" value="%{getText('format.integer',{price1})}"/>
			</div>
			<div class="inline field">
				<s:textfield name="price2" size="7" class="number" value="%{getText('format.integer',{price2})}"/>
			</div>
			<div class="inline field">
				<s:textfield name="price3" size="7" class="number" value="%{getText('format.integer',{price3})}"/>
			</div>
		</div>
		<s:hidden name="action" value="update"></s:hidden>
		<s:hidden name="systemInfo.classType"></s:hidden>
		<s:hidden name="classType"></s:hidden>
		<div class="ui error message"></div>
	</form>
  </div>
  <div class="actions">
    <div class="ui approve blue button">Save</div>
    <div class="ui cancel button">Cancel</div>
  </div>
</div>
</body>
</html>