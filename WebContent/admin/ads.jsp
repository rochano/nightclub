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
  <title><s:i18n name="global_th"><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.menu_ads" /></s:i18n></title>

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
  .ui.leaderboard.ad img {
  	width: 100%;
  	max-height: 90px;
  	max-width: 660px;
  }
  .ui.skyscraper.ad img {
  	height: 100%;
  	max-height: 600px;
  	max-width: 160px;
  }
  .preview .ui.leaderboard.ad {
  	padding: 0;
  	width: 100%;
  	background: url('<s:url value="/assets/images/black_mamba.png" />');
  }
  .preview .ui.leaderboard.ad img {
  	width: 100%;
  	max-height: 90px;
  	max-width: 660px;
  }
  .preview .ui.leaderboard.ad.active {
  	background: inherit;
  }
  .preview .ui.skyscraper.ad {
  	background: url('<s:url value="/assets/images/black_mamba.png" />')
  }
  .preview .ui.skyscraper.ad img {
  	height: 100%;
  	max-height: 600px;
  	max-width: 160px;
  }
  .preview .ui.skyscraper.ad.active {
  	background: inherit;
  }
  .preview .ui.leaderboard.ad {
	width: auto;
	margin: 1em 0;
  }
  @media only screen and (max-width: 767px) {
	.preview .ui[class*="wide skyscraper"].ad {
		display: none;
	}
	#wrapper > .ui.centered.grid > .row > .three.wide.column,
	#wrapper > .ui.centered.grid > .three.wide.column {
		display: none;
	}
  }
  @media only screen and (max-width: 1120px) {
  	.preview .ui[class*="wide skyscraper"].ad {
		width: 100% !important;
	}
  }
  .preview .centered {
	 text-align: center;
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
		  $('.ui.modal .header:first').text("<s:i18n name="global_th"><s:text name="global.add_information" /><s:text name="global.menu_ads" /></s:i18n>");
		  $('#infoForm').find("input[type=text], textarea").val("");
		  $('#infoForm')[0].action.value = "add";
		  $('#infoForm')[0].action = "<s:url value="/admin/ads/add"/>";
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
	  /* $("#adsSearch_adsDateFrom, #adsSearch_adsDateTo").dateEntry({dateFormat: 'dmy/', spinnerImage: ''}); */
	  addEventDateFormat($("#adsSearch_adsDateFrom, #adsSearch_adsDateTo"), '#searchForm.ui.form');
	  <s:if test="adsSearch.adsDateFrom != null">
	  $("#adsSearch_adsDateFrom").val("<s:date name="adsSearch.adsDateFrom" format="dd/MM/yyyy" />");
	  </s:if>
	  <s:if test="adsSearch.adsDateTo != null">
	  $("#adsSearch_adsDateTo").val("<s:date name="adsSearch.adsDateTo" format="dd/MM/yyyy" />");
	  </s:if>
	  /* $("#adsInfo_adsDateFrom, #adsInfo_adsDateTo").dateEntry({dateFormat: 'dmy/', spinnerImage: ''}); */
	  addEventDateFormat($("#adsInfo_adsDateFrom, #adsInfo_adsDateTo"), '#infoForm.ui.form');
	  <s:if test="adsInfo.adsDateFrom != null">
	  $("#adsInfo_adsDateFrom").val("<s:date name="adsInfo.adsDateFrom" format="dd/MM/yyyy" />");
	  </s:if>
	  <s:if test="adsInfo.adsDateTo != null">
	  $("#adsInfo_adsDateTo").val("<s:date name="adsInfo.adsDateTo" format="dd/MM/yyyy" />");
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
      $('#fileAdsImg2').fileupload({
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
				$("#adsImg2").html("");
				$("#adsImg2").append('<a class="ui right corner label removeImg2 link" data-variation="tiny"><i class="remove icon"></i></a>');
				var image = "<img src='" + filePath + fileName + "' />";
				$("#adsImg2").append(image);
				$('#adsImageFileName2').val(fileName);
	        	console.log('success');
	        },
	        error:function(error){
	        	console.log(error);
	        }
		});
/*       $("#autoSubscribe")
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
      }); */
    })
  ;
  	$('.removeImg1').live('click', function () {
		$("#adsImg1").empty();
		$('#adsImageFileName1').val("");
		$('#adsInfo_AdsImg1').val("");
	});
  	$('.removeImg2').live('click', function () {
		$("#adsImg2").empty();
		$('#adsInfo_AdsImg2').val("");
	});
/*   function findDateRange(rangeFrom, dateAdsDateFrom) {
	var rangeTo = new Date(rangeFrom.getFullYear(), rangeFrom.getMonth()+2, 0);
	if (rangeTo.getDate() > dateAdsDateFrom.getDate()) {
		rangeTo.setDate(dateAdsDateFrom.getDate());
	}
	rangeTo.setDate(rangeTo.getDate()-1);
	return rangeTo;
  } */
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
				<%-- <h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th">
						<s:text name="global.search_condition" />
					</s:i18n>
				</h4>
				<div class="ui left aligned attached segment active content">
					<form class="ui form" id="searchForm" method="post" action="<s:url value="/admin/ads/search"/>">
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="adsSearch.title" key="global.title"/></s:i18n>
						</div>
						<div class="inline fields">
							<label><s:i18n name="global_th"><s:text name="global.date" /></s:i18n></label>
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
								<div class="ui small button submit blue"><s:i18n name="global_th"><s:text name="global.search" /></s:i18n></div>
								<div class="ui small button clear"><s:i18n name="global_th"><s:text name="global.clear" /></s:i18n></div>
							</div>
						</div>
					</form>
				</div> --%>
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th"><s:text name="global.menu_ads" /></s:i18n>
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<%-- <div class="ui right aligned one column grid">
							<div class="column">
								<div class="addbtn ui small button blue"><s:i18n name="global_th"><s:text name="global.add" /></s:i18n></div>
							</div>
						</div> --%>
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th><s:i18n name="global_th"><s:text name="global.position" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.title" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.date_from" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.date_to" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.active" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.operation" /></s:i18n></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>

				<div class="ui left aligned attached segment active content preview">
					<div class="ui centered grid">
						<div class="row">
							<%@include file="/common/common_ads.jsp" %>
							<div class="ten wide column container">
								<div class="center aligned column">
									<div class="ui centered attached segment soft">
										<div class="ui placeholder">
											<div class="paragraph">
												<div class="line"></div>
												<div class="line"></div>
												<div class="line"></div>
												<div class="line"></div>
												<div class="line"></div>
											</div>
											<div class="paragraph">
												<div class="line"></div>
												<div class="line"></div>
												<div class="line"></div>
											</div>
										</div>
									</div>
								</div>
								<%@include file="/common/common_ads_2.jsp" %>
							</div>
							<%@include file="/common/common_ads_3.jsp" %>
						</div>
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
    <s:i18n name="global_th"><s:text name="global.edit_information" /><s:text name="global.menu_ads" /></s:i18n>
  </div>
  <div class="content">
    <form class="ui form" id="infoForm" method="post" action="<s:url value="/admin/ads/update"/>" >
		<div class="inline field">
			<s:i18n name="global_th"><s:textfield name="adsInfo.title" label="Title"/></s:i18n>
		</div>
		<div class="inline fields">
			<label><s:i18n name="global_th"><s:text name="global.date" /></s:i18n>:</label>
			<div class="field">
				<s:textfield name="adsInfo.adsDateFrom" placeholder="DD/MM/YYYY" />
			</div>
			<label>-</label>
			<div class="field">
				<s:textfield name="adsInfo.adsDateTo" placeholder="DD/MM/YYYY"/>
			</div>
		</div>
		<div class="inline field">
			<label><s:i18n name="global_th"><s:text name="global.active" /></s:i18n>:</label>
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
			<label><s:i18n name="global_th"><s:text name="global.ads_image_1" /></s:i18n>:</label>
			<div class="image ui">
				<div id="adsImg1" class="ui leaderboard ad">
					<s:if test="%{adsInfo.AdsImg1 != ''}">
						<a class="ui right corner label removeImg1 link" data-variation="tiny">
							<i class="remove icon"></i>
						</a>
						<img src="<s:property value="adsInfo.AdsImg1" />">
					</s:if>
				</div>
			</div>
			<div class="ui horizontal divider very basic">
				<label for="fileAdsImg1" class="ui basic button">
					<i class="icon upload"></i>
				  	<s:i18n name="global_th"><s:text name="global.upload" /></s:i18n>
				</label>
			    <input type="file" id="fileAdsImg1" style="display:none">
			</div>
			<s:hidden name="adsImageFileName1"></s:hidden>
			<s:hidden name="adsInfo.AdsImg1"></s:hidden>
		</div>
		<s:if test="%{adsInfo.adsInfoId == 1 || adsInfo.adsInfoId == 2}">
			<div class="inline field">
				<label><s:i18n name="global_th"><s:text name="global.ads_image_2" /></s:i18n>:</label>
				<div class="image ui">
					<div id="adsImg2" class="ui wide skyscraper ad">
						<s:if test="%{adsInfo.AdsImg2 != ''}">
							<a class="ui right corner label removeImg2 link" data-variation="tiny">
								<i class="remove icon"></i>
							</a>
							<img src="<s:property value="adsInfo.AdsImg2" />">
						</s:if>
					</div>
				</div>
				<div class="ui horizontal divider very basic">
					<label for="fileAdsImg2" class="ui basic button">
						<i class="icon upload"></i>
					  	<s:i18n name="global_th"><s:text name="global.upload" /></s:i18n>
					</label>
				    <input type="file" id="fileAdsImg2" style="display:none">
				</div>
				<s:hidden name="adsImageFileName2"></s:hidden>
				<s:hidden name="adsInfo.AdsImg2"></s:hidden>
			</div>
		</s:if>
		<s:else>
			<s:hidden name="adsImageFileName2"></s:hidden>
			<s:hidden name="adsInfo.AdsImg2"></s:hidden>
		</s:else>
		<s:hidden name="action" value="update"></s:hidden>
		<s:hidden name="adsInfo.adsInfoId"></s:hidden>
		<div class="ui error message"></div>
	</form>
  </div>
  <div class="actions">
    <div class="ui approve blue button"><s:i18n name="global_th"><s:text name="global.save" /></s:i18n></div>
    <div class="ui cancel button"><s:i18n name="global_th"><s:text name="global.cancel" /></s:i18n></div>
  </div>
</div>
  <script type="text/javascript">
  <s:iterator value="adsInfos" status="status">
		dataSet.push(
			['<s:property value="#status.count" />', 
			'A<s:property value="#status.count" />', 
			"<s:property value="title" />",
			'<s:date name="adsDateFrom" format="dd/MM/yyyy" />',
			'<s:date name="adsDateTo" format="dd/MM/yyyy" />',
			'<div class="ui toggle fitted checkbox">' +
				'<input type="checkbox" disabled="disabled"' +
				<s:if test="active == 'true'">'checked="checked" ' +</s:if>
				'value="<s:property value="adsInfoId" />">' +
				'<label></label>' +
			'</div>',
			'<a href="<s:url value="/admin/ads/edit/%{adsInfoId}"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>'
    	]);
	</s:iterator>
	columnDefs = [
	  {  className: "center aligned", targets: [ 0, 1, 3, 4, 5, 6 ] }
	];
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