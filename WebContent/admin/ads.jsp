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
  <title><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.menu_ads" /></title>

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
  .ui.form .inline.field>:first-child, .ui.form .inline.fields>label {
  	width: 150px;
  }
  .ui.leaderboard.ad {
  	height: 100%;
  }
  .ui.leaderboard.ad image {
  	height: 100px;
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
		  $('#infoForm').find("input[type=text], textarea").val("");
		  $('#infoForm')[0].action.value = "add";
		  $('#infoForm')[0].action = "<s:url value="/admin/ads/add"/>";
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
	  $("#adsSearch_adsDateFrom, #adsSearch_adsDateTo").dateEntry({dateFormat: 'dmy/', spinnerImage: ''});
	  <s:if test="adsSearch.adsDateFrom != null">
	  $("#adsSearch_adsDateFrom").val("<s:date name="adsSearch.adsDateFrom" format="dd/MM/yyyy" />");
	  </s:if>
	  <s:if test="adsSearch.adsDateTo != null">
	  $("#adsSearch_adsDateTo").val("<s:date name="adsSearch.adsDateTo" format="dd/MM/yyyy" />");
	  </s:if>
	  $("#adsInfo_adsDateFrom, #adsInfo_adsDateTo").dateEntry({dateFormat: 'dmy/', spinnerImage: ''});
	  <s:if test="adsInfo.adsDateFrom != null">
	  $("#adsInfo_adsDateFrom").val("<s:date name="adsInfo.adsDateFrom" format="dd/MM/yyyy" />");
	  </s:if>
	  <s:if test="adsInfo.adsDateTo != null">
	  $("#adsInfo_adsDateTo").val("<s:date name="adsInfo.adsDateTo" format="dd/MM/yyyy" />");
	  </s:if>
      $('#fileAdsImg').fileupload({
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
				$("#adsImg").html(image);
				$('#adsImageFileName').val(fileName);
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
		});
      $("#autoSubscribe")
		.on('click', function() {console.log($(this).is(":checked"))
			if ($(this).is(":checked")) {
				$('#adsInfo_adsDateTo').attr("readonly", "readonly");
				$('#adsInfo_adsDateTo').parents(".field:first").addClass("disabled");
			} else {
				$('#adsInfo_adsDateTo').removeAttr("readonly");
				$('#adsInfo_adsDateTo').parents(".field:first").removeClass("disabled");
			}
      });
      $("#adsInfo_adsDateFrom").on('change', function() {
        var adsDateFrom = $(this).val();
        if (adsDateFrom == '') {
        	$("#current_range_from").text("");
        	$("#current_range_to").text("");
        	$("#next_range_from").text("");
        	$("#next_range_to").text("");
        } else {
			var dateAdsDateFrom = new Date(adsDateFrom.substring(6), (adsDateFrom.substr(3, 2))-1, adsDateFrom.substr(0, 2));
			var currentRangeFrom = new Date();
			currentRangeFrom.setMonth(currentRangeFrom.getMonth()-1);
			currentRangeFrom = findDateRange(currentRangeFrom, dateAdsDateFrom);
			currentRangeFrom.setDate(currentRangeFrom.getDate()+1);
			var currentRangeTo = findDateRange(currentRangeFrom, dateAdsDateFrom);
			var nextRangeFrom = new Date(currentRangeTo);
			nextRangeFrom.setDate(nextRangeFrom.getDate()+1);
			var nextRangeTo = findDateRange(nextRangeFrom, dateAdsDateFrom);
			$("#current_range_from").text(currentRangeFrom.getDate() + "/" + (currentRangeFrom.getMonth()+1) + "/" + currentRangeFrom.getFullYear());
			$("#current_range_to").text(currentRangeTo.getDate() + "/" + (currentRangeTo.getMonth()+1) + "/" + currentRangeTo.getFullYear());
			$("#next_range_from").text(nextRangeFrom.getDate() + "/" + (nextRangeFrom.getMonth()+1) + "/" + nextRangeFrom.getFullYear());
			$("#next_range_to").text(nextRangeTo.getDate() + "/" + (nextRangeTo.getMonth()+1) + "/" + nextRangeTo.getFullYear());
		}
      });
    })
  ;
  function findDateRange(rangeFrom, dateAdsDateFrom) {
	var rangeTo = new Date(rangeFrom.getFullYear(), rangeFrom.getMonth()+2, 0);
	if (rangeTo.getDate() > dateAdsDateFrom.getDate()) {
		rangeTo.setDate(dateAdsDateFrom.getDate());
	}
	rangeTo.setDate(rangeTo.getDate()-1);
	return rangeTo;
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
					<s:i18n name="global_th">
						<s:text name="global.search_condition" />
					</s:i18n>
				</h4>
				<div class="ui left aligned attached segment active content">
					<form class="ui form" id="searchForm" method="post" action="<s:url value="/admin/ads/search"/>">
						<div class="inline field">
							<s:textfield name="adsSearch.title" key="global.title"/>
						</div>
						<div class="inline fields">
							<label><s:text name="global.date" /></label>
							<div class="field">
								<s:textfield name="adsSearch.adsDateFrom" placeholder="DD/MM/YYYY" />
							</div>
							<label>-</label>
							<div class="field">
								<s:textfield name="adsSearch.adsDateTo" placeholder="DD/MM/YYYY" />
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
					<s:text name="global.menu_ads" />
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<div class="ui right aligned one column grid">
							<div class="column">
								<div id="addbtn" class="ui small button blue"><s:text name="global.add" /></div>
							</div>
						</div>
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th><s:text name="global.title" /></th>
									<th><s:text name="global.date_from" /></th>
									<th><s:text name="global.date_to" /></th>
									<th><s:text name="global.active" /></th>
									<th><s:text name="global.operation" /></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="adsInfos" status="status">
								<tr>
									<td class="center aligned"><s:property value="#status.count" /></td>
									<td><s:property value="title" /></td>
									<td class="center aligned">
										<s:if test="autoSubscribe != 'true'"><s:date name="adsDateFrom" format="dd/MM/yyyy" /></s:if>
										<s:if test="autoSubscribe == 'true'"><s:date name="currentRangeFrom" format="dd/MM/yyyy" /></s:if>
									</td>
									<td class="center aligned">
										<s:if test="autoSubscribe != 'true'"><s:date name="adsDateTo" format="dd/MM/yyyy" /></s:if>
										<s:if test="autoSubscribe == 'true'"><s:date name="currentRangeTo" format="dd/MM/yyyy" /></s:if>
									</td>
									<td class="center aligned">
										<div class="ui toggle fitted checkbox">
											<input type="checkbox" disabled="disabled"
											<s:if test="active == 'true'">checked="checked"</s:if>
											 value="<s:property value="adsInfoId" />">
											<label></label>
										</div>
									</td>
									<td class="center aligned">
										<a href="<s:url value="/admin/ads/edit/"/><s:property value="adsInfoId" />" class="ui icon button small blue" ><i class="ui icon edit"></i></a>
										<a href="<s:url value="/admin/ads/delete/"/><s:property value="adsInfoId" />" class="ui icon button small red"><i class="ui icon delete"></i></a>
									</td>
								</tr>
								</s:iterator>
							</tbody>
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
    <s:text name="global.edit_information" /><s:text name="global.menu_ads" />
  </div>
  <div class="content">
    <form class="ui form" id="infoForm" method="post" action="<s:url value="/admin/ads/update"/>" >
		<div class="inline field">
			<s:textfield name="adsInfo.title" label="Title"/>
		</div>
		<div class="inline fields">
			<label><s:text name="global.date" />:</label>
			<div class="field">
				<s:textfield name="adsInfo.adsDateFrom" placeholder="DD/MM/YYYY" />
			</div>
			<label>-</label>
			<div class="field <s:if test="adsInfo.autoSubscribe == 'true'">disabled</s:if>">
				<s:set var="readonly" value="%{adsInfo.autoSubscribe == 'true'}"/>
				<s:textfield name="adsInfo.adsDateTo" placeholder="DD/MM/YYYY" readonly="%{readonly}"/>
			</div>
		</div>
		<div class="inline field">
			<label><s:text name="global.ads_auto_subscribe" />:</label>
			<div class="ui toggle fitted checkbox">
				<input type="checkbox" name="autoSubscribe" id="autoSubscribe" 
				<s:if test="adsInfo.autoSubscribe == 'true'">checked="checked"</s:if>
				 value="<s:property value="adsInfoId" />">
				<label></label>
			</div>
		</div>
		<div class="inline field">
			<label></label>
			<label><s:text name="global.ads_current_range" />:</label>
			<label id="current_range_from"><s:text name="currentRangeFrom" /></label>
			<label>-</label>
			<label id="current_range_to"><s:text name="currentRangeTo" /></label>
		</div>
		<div class="inline field">
			<label></label>
			<label><s:text name="global.ads_next_range" />:</label>
			<label id="next_range_from"><s:text name="nextRangeFrom" /></label>
			<label>-</label>
			<label id="next_range_to"><s:text name="nextRangeTo" /></label>
		</div>
		<div class="inline field">
			<label><s:text name="global.active" />:</label>
			<div class="ui toggle fitted checkbox">
				<input type="checkbox" name="active" 
				<s:if test="adsInfo.active == 'true'">checked="checked"</s:if>
				 value="<s:property value="adsInfoId" />">
				<label></label>
			</div>
		</div>
		<div class="inline field">
			<label>URL:</label>
			<s:textfield name="adsInfo.customUrl" size="50" />
		</div>
		<div class="inline field">
			<label><s:text name="global.ads_image" />:</label>
			<div class="image ui small">
				<div id="adsImg" class="ui leaderboard ad">
					<s:if test="%{adsInfo.AdsImg != ''}">
						<img src="<s:property value="adsInfo.AdsImg" />">
					</s:if>
				</div>
				<div class="ui horizontal divider very basic">
					<label for="fileAdsImg" class="ui basic button">
						<i class="icon upload"></i>
					  	<s:text name="global.upload" />
					</label>
				    <input type="file" id="fileAdsImg" style="display:none">
				</div>
			</div>
			<s:hidden name="adsImageFileName"></s:hidden>
		</div>
		<s:hidden name="action" value="update"></s:hidden>
		<s:hidden name="adsInfo.adsInfoId"></s:hidden>
		<div class="ui error message"></div>
	</form>
  </div>
  <div class="actions">
    <div class="ui approve blue button"><s:text name="global.save" /></div>
    <div class="ui cancel button"><s:text name="global.cancel" /></div>
  </div>
</div>
</body>
</html>