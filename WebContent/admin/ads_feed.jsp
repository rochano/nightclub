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
  <title><s:i18n name="global_th"><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.menu_ads_feed" /></s:i18n></title>

  <%@include file="/common/common_admin_management_header.jsp" %>
  <script src="<s:url value="/assets/library/jquery.form.js"/>"></script>
  <script src="<s:url value="/assets/library/fileUploadScript.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.ui.widget.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload-process.js"/>"></script>
  <script src="<s:url value="/assets/library/jquery.fileupload-ui.js"/>"></script>
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
  	width: 200px;
  	vertical-align: top;
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
	  $(".addbtn")
		.bind('click', function() {
		  $('.ui.modal .header:first').text("<s:i18n name="global_th"><s:text name="global.add_information" /><s:text name="global.menu_ads_feed" /></s:i18n>");
		  $('#infoForm').find("input[type=text], textarea").val("");
		  $('#infoForm')[0].action.value = "add";
		  $('#infoForm')[0].action = "<s:url value="/admin/adsfeed/add"/>";
		  $("#adsImg").html("");
		  $('#infoForm').find("input[type=checkbox]").prop("checked", false);
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
      $('#fileAdsImg1').fileupload({
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
				$("#adsImg1").html("");
				$("#adsImg1").append('<a class="ui right corner label removeImg1 link" data-variation="tiny"><i class="remove icon"></i></a>');
				var image = "<img src='" + filePath + fileName + "' />";
				$("#adsImg1").append(image);
				$('#adsImageFileName1').val(fileName);
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
		});
    })
  ;
  	$('.removeImg1').live('click', function () {
		$("#adsImg1").empty();
		$('#adsImageFileName1').val("");
		$('#adsFeedInfo_AdsImg1').val("");
	});
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
					<s:i18n name="global_th">
						<s:text name="global.search_condition" />
					</s:i18n>
				</h4>
				<div class="ui left aligned attached segment active content">
					<form class="ui form" id="searchForm" method="post" action="<s:url value="/admin/adsfeed/search"/>">
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="adsFeedSearch.title" key="global.title"/></s:i18n>
						</div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button submit blue"><s:i18n name="global_th"><s:text name="global.search" /></s:i18n></div>
								<div class="ui small button clear"><s:i18n name="global_th"><s:text name="global.clear" /></s:i18n></div>
							</div>
						</div>
					</form>
				</div>
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th"><s:text name="global.menu_ads_feed" /></s:i18n>
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
									<th><s:i18n name="global_th"><s:text name="global.title" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.meta" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.description" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.active" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.operation" /></s:i18n></th>
								</tr>
							</thead>
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
    <s:i18n name="global_th"><s:text name="global.edit_information" /><s:text name="global.menu_ads_feed" /></s:i18n>
  </div>
  <div class="content">
    <form class="ui form" id="infoForm" method="post" action="<s:url value="/admin/adsfeed/update"/>" >
		<div class="inline field">
			<s:i18n name="global_th"><s:textfield name="adsFeedInfo.title" key="global.title"/></s:i18n>
		</div>
		<div class="inline field">
			<s:i18n name="global_th"><s:textfield name="adsFeedInfo.meta" key="global.meta"/></s:i18n>
		</div>
		<div class="inline field">
			<s:i18n name="global_th"><s:textfield name="adsFeedInfo.description" size="50" key="global.description"/></s:i18n>
		</div>
		<div class="inline field">
			<label><s:i18n name="global_th"><s:text name="global.active" /></s:i18n>:</label>
			<div class="ui toggle fitted checkbox">
				<input type="checkbox" name="active" 
				<s:if test="adsFeedInfo.active == 'true'">checked="checked"</s:if>
				 value="<s:property value="adsFeedInfoId" />">
				<label></label>
			</div>
		</div>
		<div class="inline field">
			<label>Link:</label>
			<s:textfield name="adsFeedInfo.link" size="50" />
		</div>
		<div class="inline field">
			<s:i18n name="global_th"><s:textfield name="adsFeedInfo.buttonLabel" key="global.button_label"/></s:i18n>
		</div>
		<div class="inline field">
			<label><s:i18n name="global_th"><s:text name="global.ads_feed_image_1" /></s:i18n>:</label>
			<div class="image ui small column">
				<div id="adsImg1" class="ui card ad">
					<s:if test="%{adsFeedInfo.AdsImg1 != ''}">
						<a class="ui right corner label removeImg1 link" data-variation="tiny">
							<i class="remove icon"></i>
						</a>
						<img class="image ui small centered" src="<s:property value="adsFeedInfo.AdsImg1" />">
					</s:if>
				</div>
				<div class="ui horizontal divider very basic">
					<label for="fileAdsImg1" class="ui basic button">
						<i class="icon upload"></i>
					  	<s:i18n name="global_th"><s:text name="global.upload" /></s:i18n>
					</label>
				    <input type="file" id="fileAdsImg1" style="display:none">
				</div>
				<s:hidden name="adsImageFileName1"></s:hidden>
				<s:hidden name="adsFeedInfo.AdsImg1"></s:hidden>
			</div>
		</div>
		<s:hidden name="action" value="update"></s:hidden>
		<s:hidden name="adsFeedInfo.adsFeedInfoId"></s:hidden>
		<div class="ui error message"></div>
	</form>
  </div>
  <div class="actions">
    <div class="ui approve blue button"><s:i18n name="global_th"><s:text name="global.save" /></s:i18n></div>
    <div class="ui cancel button"><s:i18n name="global_th"><s:text name="global.cancel" /></s:i18n></div>
  </div>
</div>
  <script type="text/javascript">
  <s:iterator value="adsFeedInfos" status="status">
		dataSet.push(
			['<s:property value="#status.count" />', 
			"<s:property value="title" />",
			"<s:property value="meta" />",
			"<s:property value="description" />",
			'<div class="ui toggle fitted checkbox">' +
				'<input type="checkbox" disabled="disabled"' +
				<s:if test="active == 'true'">'checked="checked" ' +</s:if>
				'value="<s:property value="adsFeedInfoId" />">' +
				'<label></label>' +
			'</div>',
			'<a href="<s:url value="/admin/adsfeed/edit/%{adsFeedInfoId}"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>'
    	]);
	</s:iterator>
	if (dataSet.length) {
		columnDefs = [
		  {  className: "center aligned", targets: [ 0, 4, 5 ] }
		];
	}
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