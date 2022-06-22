<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Shop - Girl Info</title>
  
  <%@include file="/common/common_shop_header.jsp" %>

  <!--- Example CSS -->
  <style>
  .ui.menu:last-child {
    margin-bottom: 110px;
  }
  .ui.items > .item {
	display: inline-block;
	width: auto;
  }
  .ui.items > .item > .image > img {
	margin: auto;
  }
  .ui.items > .item > .image + .content {
	display: block;
    padding: 1.5em 0em;
  }
  .ui.celled.table tr td {
  	vertical-align: text-top;
  }
  .ui.list .list > .item .header, .ui.list > .item .header {
    color: #FFFFFF;
  }
  .ui.inverted.segments .segment, .ui.inverted.segment, .ui.primary.inverted.segment {
    background: inherit;
  }
  .ui.segment.clearing, #availableInfos {
    margin:0px;
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
      <s:if test="#session.clientInfo != null">
	  $('#inline_calendar')
	  	.calendar({
	  		initialDate: new Date(),
	  	    type: 'date',
	  	    onChange: function() {
		  	    var lookupDate = arguments[0].toISOString().substring(0, 10);
		  	    getReserveList(lookupDate);
		  	}
	    })
	  ;
	  $("#addbtn")
		.on('click', function() {
		  $('.ui.modal .header:first').text("<s:text name="global.add_information" /><s:text name="global.menu_reserve" />");
		  $('#infoForm').find("input[type=text], textarea").val("");
		  $('#reserveInfo_shopInfoId').val("<s:property value="girlInfo.shopInfoId" />");
		  $('#reserveInfo_girlInfoId').val("<s:property value="girlInfo.girlInfoId" />");
		  $('#action').val("add");
		  $(".ui.error.message").html("");
		  $("#infoForm").removeClass("error");

		  var lookupDate = $('#inline_calendar').calendar("get date").toISOString().substring(0, 10);
		  getAvailableList(lookupDate);
		  var reserveDate = lookupDate.split("-");
		  $('#reserveInfo_reserveDate').val(reserveDate[2] + "/" + reserveDate[1] + "/" + reserveDate[0]);
		  
        $('.ui.modal')
		    .modal('show')
		  ;
      });
	  $('#reserveInfo_startTime, #reserveInfo_endTime').timeEntry({show24Hours: true, spinnerImage: ''});
	  $('#infoForm')
      .form({
    	  fields: {
    		  reserveInfo_startTime: {
	              identifier  : 'reserveInfo_startTime',
	              rules: [
	                {
	                  type   : 'empty',
	                  prompt : '<s:text name="global.message_please_input_param" ><s:param><s:text name="global.start_time" /></s:param></s:text>'
	                },
	              ]
	            },
	            reserveInfo_endTime: {
	              identifier  : 'reserveInfo_endTime',
	              rules: [
	                {
	                  type   : 'empty',
	                  prompt : '<s:text name="global.message_please_input_param" ><s:param><s:text name="global.end_time" /></s:param></s:text>'
	                },
	              ]
	            }
          ,},
          onSuccess: function() { 
              var form = $(this);
              $.post("<s:url value="/ajax/girlReserveExecuteJson" />",
            	form.serialize(),
            	function(jsonResponse) {
              	  if (jsonResponse.actionMessage) {
                  	var html = '<ul class="list"><li>' + jsonResponse.actionMessage + '</li></ul>';
					$(".ui.error.message").html(html);
					$("#infoForm").addClass("error");
                  } else {
	            	  var lookupDate = $('#inline_calendar').calendar("get date").toISOString().substring(0, 10);
	            	  getReserveList(lookupDate);
	            	  $('.ui.modal').modal('toggle');
                  }
       		  });
			  
              return false;
          }
       })
      ;
	  </s:if>
    })
  ;
  $(".edit-reserve")
	.live('click', function() {
	  $('.ui.modal .header:first').text("<s:text name="global.edit_information" /><s:text name="global.menu_reserve" />");
	  $('#infoForm').find("input[type=text], textarea").val("");
	  $('#reserveInfo_shopInfoId').val("<s:property value="girlInfo.shopInfoId" />");
	  $('#reserveInfo_girlInfoId').val("<s:property value="girlInfo.girlInfoId" />");
	  $('#reserveInfo_startTime').val($(this).attr("data-startTime"));
	  $('#reserveInfo_endTime').val($(this).attr("data-endTime"));
	  $('#reserveInfo_reserveInfoId').val($(this).attr("data-reserveInfoId"));
	  $('#action').val("update");
	  $(".ui.error.message").html("");
	  $("#infoForm").removeClass("error");

	  var lookupDate = $('#inline_calendar').calendar("get date").toISOString().substring(0, 10);
	  getAvailableList(lookupDate, $(this).attr("data-reserveInfoId"));
	  var reserveDate = lookupDate.split("-");
	  $('#reserveInfo_reserveDate').val(reserveDate[2] + "/" + reserveDate[1] + "/" + reserveDate[0]);
	  
	$('.ui.modal')
		    .modal('show')
		  ;
	});

  $(".delete-reserve")
	.live('click', function() {
		$.getJSON("<s:url value="/ajax/girlReserveDeleteJson/" />" + $(this).attr("data-reserveInfoId"), 
		function(jsonResponse) {
			var lookupDate = $('#inline_calendar').calendar("get date").toISOString().substring(0, 10);
      	  	getReserveList(lookupDate);
		}
		);
	});
  function getReserveList(lookupDate) {
	  var reserveInfos = $('#reserveInfos');
	    reserveInfos.empty()
   		$.getJSON("<s:url value="/ajax/girlReserveJson/" />" + "<s:property value="girlInfo.girlInfoId" />" + "/" + lookupDate, 
			function(jsonResponse) {
				$("#lookupDateHeader").html(jsonResponse.lookupDateHeader);
				if (jsonResponse.reserveInfos == null) return;
			     $.each(jsonResponse.reserveInfos, function(i, obj) {
	  				var html = '<div class="item">';
		  	  	html += '<div class="right floated content">';
		  	  	html += '<div class="ui buttons">';
		  	    html += '<div class="ui icon button small blue edit-reserve" data-reserveInfoId="' + obj.reserveInfoId + '" data-startTime="' + obj.startTime + '" data-endTime="' + obj.endTime + '" ><i class="ui icon edit"></i></div>';
		  		html += '<div class="ui icon button small red delete-reserve" data-reserveInfoId="' + obj.reserveInfoId + '" ><i class="ui icon delete"></i></div>';
				html += '</div>';
			  	html += '</div>';
			  	html += '<div class="content">';
			  	html += '<div class="header">' + obj.startTime + ' - ' + obj.endTime + '</div>';
			  	html += '</div>';
			  	html += '</div>';
			    	reserveInfos.append(html);
			     });
		});
  }
  
  function getAvailableList(lookupDate, reserveInfoId) {
	  var reserveInfoIdPath = reserveInfoId ? reserveInfoId : "";
	  $.getJSON("<s:url value="/ajax/girlAvailableJson/" />" + "<s:property value="girlInfo.girlInfoId" />" + "/" + lookupDate + "/" + reserveInfoIdPath, 
	  function(jsonResponse) {
		var availableInfos = $('#availableInfos');
		availableInfos.html("");
		if (jsonResponse.availableInfos == null) return;
	     $.each(jsonResponse.availableInfos, function(i, obj) {
			var html = '<div class="item">' + obj.startTime + ' - ' + obj.endTime + '</div>';
			availableInfos.append(html);
	     });
	  });
  }
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
				
				<div class="ui breadcrumb segment attached inverted">
					<a class="section" href="<s:url value="/shop/"/><s:property value="shopInfoId" />/girls" >
						<s:text name="global.shop_menu_girls" />
					</a>
					<i class="right chevron icon divider"></i>
					<div class="active section"><s:text name="global.shop_girl_info" /></div>
				</div>
				
				<div class="center aligned column">
					<div class="ui segment header">
						<h2 class="ui top header">
							<i class="heart icon pink"></i>
							<div class="content"><s:text name="global.shop_girl_info" /></div>
						</h2>
					</div>
					<div class="ui attached segment">
						<div class="ui grid stackable">
							<div class="eight wide column">
								<div class="center aligned sixteen wide column slide-image">
									<div class="single-item">
										<s:if test="girlInfo.pic1 != null && girlInfo.pic1 != ''">
											<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic1" />" /></div>
										</s:if>
										<s:if test="girlInfo.pic2 != null && girlInfo.pic2 != ''">
											<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic2" />" /></div>
										</s:if>
										<s:if test="girlInfo.pic3 != null && girlInfo.pic3 != ''">
											<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic3" />" /></div>
										</s:if>
										<s:if test="girlInfo.pic4 != null && girlInfo.pic4 != ''">
											<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic4" />" /></div>
										</s:if>
										<s:if test="girlInfo.pic5 != null && girlInfo.pic5 != ''">
											<div><img class="ui image fluid centered" src="<s:property value="girlInfo.pic5" />" /></div>
										</s:if>
										<s:if test="(girlInfo.pic1 == null || girlInfo.pic1 == '') 
													&& (girlInfo.pic2 == null || girlInfo.pic2 == '') 
													&& (girlInfo.pic3 == null || girlInfo.pic3 == '') 
													&& (girlInfo.pic4 == null || girlInfo.pic4 == '') 
													&& (girlInfo.pic5 == null || girlInfo.pic5 == '')">
											<img class="image ui centered" src="<s:url value="/assets/images/wireframe/square-image.png" />">
										</s:if>
								  	</div>
								</div>
							</div>
							<div class="eight wide column">
								<h3 class="ui header">
									<div class="content">
										<s:property value="girlInfo.nickName" />
									</div>
								</h3>
								<div class="column one left aligned">
									<div class="ui grid">
										<div class="row">
											<div class="eight wide column">
												<s:text name="global.shop_girl_body_size" />
											</div>
											<div class="eight wide column">
												<span class="property">
													T<s:text name="format.integer"><s:param name="value" value="girlInfo.height"/></s:text>&nbsp;
													B<s:text name="format.integer"><s:param name="value" value="girlInfo.bustSize"/></s:text>&nbsp;
													W<s:text name="format.integer"><s:param name="value" value="girlInfo.waistSize"/></s:text>&nbsp;
													H<s:text name="format.integer"><s:param name="value" value="girlInfo.hipSize"/></s:text>
												</span>
											</div>
										</div>
										<div class="row">
											<div class="eight wide column">
												<s:text name="global.shop_girl_age" />
											</div>
											<div class="eight wide column">
												<s:property value="girlInfo.age" />
											</div>
										</div>
									</div>
								</div>
								
								<h4 class="ui top attached header inverted active title">
									<s:text name="global.shop_girl_description" />
								</h4>
								<div class="ui centered grid attached inverted segment active content">
									<div class="column one left aligned">
										<s:text name="girlInfo.description" />
									</div>
								</div>
							</div>
						</div>
						<s:if test="#session.clientInfo != null">
							<h4 class="ui top attached header inverted active title">
								<s:text name="global.shop_girl_reserve" />
							</h4>
							<div class="ui centered grid attached inverted segment active content">
								<div class="column one left aligned">
									<div class="ui grid stackable">
										<div class="eight wide column">
											<div class="ui calendar" id="inline_calendar">
											</div>
										</div>
										<div class="eight wide column">
											<div class="ui clearing segment">
												<h3 class="ui right floated header">
												  <div id="addbtn" class="ui icon button small blue" ><i class="ui icon add"></i></div>
												</h3>
												<h3 class="ui left floated header">
												   <div class="header" id="lookupDateHeader"><s:text name="lookupDateHeader" /></div>
												</h3>
											</div>
											<div class="ui celled list" id="reserveInfos">
											  <s:iterator value="reserveInfos" status="status">
											  <div class="item">
											  	<div class="right floated content">
											      <div class="ui buttons">
													<div class="ui icon button small blue edit-reserve" data-reserveInfoId="<s:property value="reserveInfoId" />" data-startTime="<s:property value="startTime" />" data-endTime="<s:property value="endTime" />" ><i class="ui icon edit"></i></div>
													<div class="ui icon button small red delete-reserve" data-reserveInfoId="<s:property value="reserveInfoId" />" ><i class="ui icon delete"></i></div>
												  </div>
											    </div>
											    <div class="content">
											      <div class="header"><s:property value="startTime" /> - <s:property value="endTime" /></div>
											    </div>
											  </div>
											  </s:iterator>
											</div>
										</div>
									</div>
								</div>
							</div>
						</s:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/common/common_shop_footer.jsp" %>
</div>
<div class="ui mini modal">
  <i class="close icon"></i>
  <div class="header">
  	<s:text name="global.edit_information" /><s:text name="global.menu_girls" />
  </div>
  <div class="content">
  	<form class="ui form" id="infoForm" method="post" action="<s:url value="/management/girl/update"/>" enctype="multipart/form-data">
    	<div class="inline fields disabled">
			<label><s:text name="global.reserve_date" /></label>
			<div class="field">
				<s:textfield name="reserveInfo.reserveDate" placeholder="DD/MM/YYYY" size="10" readonly="true" />
			</div>
		</div>
    	<div class="inline fields">
    		<label><s:text name="global.free_time" /></label>
    		<div class="ui list" id="availableInfos"></div>
    	</div>
    	<br />
    	<div class="inline fields">
			<label><s:text name="global.time" /></label>
			<div class="field">
				<s:textfield name="reserveInfo.startTime" placeholder="HH:mm" size="6" />
			</div>
			<label>-</label>
			<div class="field">
				<s:textfield name="reserveInfo.endTime" placeholder="HH:mm" size="6" />
			</div>
		</div>
		<s:hidden name="action" />
		<s:hidden name="reserveInfo.shopInfoId" />
		<s:hidden name="reserveInfo.girlInfoId" />
		<s:hidden name="reserveInfo.reserveInfoId" />
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