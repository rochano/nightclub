<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>THAINIGHTNAVI.COM - <s:text name="global.main_menu_free_agents" /></title>
  <%@include file="/common/common_header.jsp" %>
  <!--- Example CSS -->
  <style>
  .ui.table th, .ui.table tr:nth-child(even) td{
  	vertical-align: baseline;
    word-break: break-word;
  }
  .ui.table td {
  	padding: 0;
  }
  .ui.table td input[type=button], .ui.table td button {
  	margin: auto;
  }
  @media only screen and (max-width: 767px) {
  	.ui.table:not(.unstackable) tr:nth-child(odd) {
	  box-shadow: none !important;
    }
    .ui.stackable.grid > .column:not(.row) {
      padding-top: 10px!important;
      padding-bottom: 0px!important;
    }
    .ui.stackable.grid > .row > .wide.column,
    .ui.stackable.grid > .row > .column {
      padding:1em 0!important;
    }
  }
  .segment {
  	padding: 0;
  }
  .ui.table tbody tr:nth-child(4n), .ui.table tbody tr:nth-child(4n-1) {
	background-color: rgba(0,0,50,.02);
  }
  @media only screen and (max-width: 767px) {
  	#shoplist-wrapper {overflow: scroll;}
  }
  .ui.grid > .column:not(.row) {
    padding-top: 10px;
    padding-bottom: 0;
  }
  .ui.table , .ui.table  tr th, .ui.table  tr td {
  	border-width:1px 0px!important; 
  	vertical-align: text-top;
  }
  .ui.table.girl-info tr td  {
 	border-width:0px 0px!important; 
  }
  .ui.segment.clearing, #availableInfos {
    margin:0px;
  }
  #availableInfos {
    display: inline-grid;
  }
  #wrapper_calendar {
    margin-bottom:30px;
  }
  #mov1 {
    display: none;
  }
  #mov1.display {
    display: block;
  }
  .ui.rating .icon {
    color: #555;
  }
  .ui.left.corner.label.girl-tag {
  	border-color: transparent;
  }
  .girl-tag .ui.circular.label {
  	width: 50px;
  	height: 50px;
  	overflow: hidden;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 70%;
    text-wrap: balance;
    word-break: break-word;
  }
	.ui.corner.label.verified {
  	width: 6em;
  	height: 6em;
  }
  .ui.corner.label.verified:after {
    border-right-width: 6em;
    border-bottom-width: 6em;
    background-image: linear-gradient(red, yellow);
  }
  .ui.corner.label.verified .icon {
  	top: 20px;
    left: 20px;
    transform: rotate(45deg);
    font-family: 'Lato', 'Helvetica Neue', Arial, Helvetica, sans-serif;
    font-size: 12px;
}
  .icon.linechat {
	color: #00b300;
  }
  .icon.telegram {
    color: #2ea0d5;
    
  }
  .icon.skype {
    color: #00aae8;
  }
  .fa-x-twitter {
    color: #fff;
    display: inline-block;
    opacity: 1;
    margin: 0 0.25rem 0 0;
    width: 1.18em;
    height: 1em;
    font-style: normal;
    font-weight: normal;
    text-decoration: inherit;
    text-align: center;
    speak: none;
    -moz-osx-font-smoothing: grayscale;
    -webkit-font-smoothing: antialiased;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
    font-size: 4em;
    vertical-align: middle;
  }
  .icon.wechat {
    color: #2dbb01;
  }
  .icon.whatsapp {
    color: #fff;
  }
  .icon.linechat::before,
  .icon.telegram::before,
  .icon.skype::before,
  .fa-x-twitter::before,
  .icon.wechat::before,
  .icon.whatsapp::before {
    position: relative;
    z-index: 2;
  }
  .icon.linechat::after {
	content: "";
    background: #fff;
    position: absolute;
    width: 39px;
    height: 36px;
    display: block;
    top: 10px;
    left: 14px;
    z-index: 1;
  }
  .icon.telegram::after {
    content: "";
    background: #fff;
    position: absolute;
    width: 35px;
    height: 30px;
    display: block;
    top: 15px;
    left: 15px;
    z-index: 1;
  }
  .icon.skype::after {
    content: "";
    background: #fff;
    position: absolute;
    width: 30px;
    height: 30px;
    display: block;
    top: 13px;
    left: 19px;
    z-index: 1;
  }
  .icon.wechat::after {
    content: "";
    position: absolute;
    width: 66px;
    height: 54px;
    display: block;
    top: 1px;
    left: 14px;
    z-index: 1;
    border-radius: 5px;
  }
  .icon.whatsapp::after {
    content: "";
    background: #2dbb01;
    position: absolute;
    width: 48px;
    height: 45px;
    display: block;
    top: 6px;
    left: 10px;
    z-index: 1;
    border-radius: 50px;
  }
  .ui.grid.social-icon > .row > .column {
  	padding: 0;
  }
  
  </style>
  <script type="text/javascript">
  $(document)
    .ready(function() {
<s:if test="%{girlInfo.agentInfoId != null}">
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
  		  $('#infoForm #action').val("add");
  		  $(".ui.error.message").html("");
  		  $("#infoForm").removeClass("error");

  		  if ($('#inline_calendar').length > 0) {
	  		  var lookupDate = $('#inline_calendar').calendar("get date").toISOString().substring(0, 10);
	  		  getAvailableList(lookupDate);
	  		  var reserveDate = lookupDate.split("-");
	  		  $('#reserveInfo_reserveDate').val(reserveDate[2] + "/" + reserveDate[1] + "/" + reserveDate[0]);
	  		  
	          $('.ui.modal')
	  		    .modal('show')
	  		  ;
  		  }
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
  	  $("#playMov1")
		.on('click', function() {
		  var elem = $('#mov1').get(0);
		  elem.currentTime = 0;
		  if (elem.requestFullscreen) {
		    elem.requestFullscreen();
		  } else if (elem.webkitRequestFullscreen) { /* Safari */
		    elem.webkitRequestFullscreen();
		  } else if (elem.msRequestFullscreen) { /* IE11 */
		    elem.msRequestFullscreen();
		  }
		  elem.play();
		  $(elem).addClass('display');
		});
  		$("#mov1").on('ended', function() {
  			var mov1 = $('#mov1');
  			mov1.removeClass('display');
		  	var elem = mov1.get(0);
		  	elem.pause();
		  	if (elem.exitFullscreen) {
		  		elem.exitFullscreen();
			} else if (elem.webkitExitFullscreen) {
				elem.webkitExitFullscreen();
			} else if (elem.mozCancelFullScreen) {
				elem.mozCancelFullScreen();
			} else if (elem.msExitFullscreen) {
				elem.msExitFullscreen();
			}
	  	});
  		$(document).on('fullscreenchange', fullscreenChangeHandler);
  	  	$(document).on('mozfullscreenchange', fullscreenChangeHandler);
  	  	$(document).on('MSFullscreenChange', fullscreenChangeHandler);
  		$(document).on('webkitfullscreenchange', fullscreenChangeHandler);
  		function fullscreenChangeHandler() {
	  	  var mov1 = $('#mov1');
	  	  if (!this.fullscreenElement) {
		  	if (mov1.hasClass('display')) {
		  	  mov1.removeClass('display');
		  	  mov1.get(0).pause();
		  	}
	  	  }
	  	}
  		$('#girlCommentForm ')
        .form({
        	fields: {}
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
  		$('#rating')
	  	  .rating({
	  		initialRating: function(value) {
		  		$("#girlComment_rating").val(value);
		  	},
	  		onRate: function(value) {
		  		$("#girlComment_rating").val(value);
		  	}
		  })
	  	;
	  	if ($('#inline_calendar').length > 0) {
	  		var lookupDate = $('#inline_calendar').calendar("get date").toISOString().substring(0, 10);
		  	getReserveList(lookupDate);
	  	}
	});
<s:if test="%{girlInfo.agentInfoId != null}">
  $(".edit-reserve")
	.live('click', function() {
	  $('.ui.modal .header:first').text("<s:text name="global.edit_information" /><s:text name="global.menu_reserve" />");
	  $('#infoForm').find("input[type=text], textarea").val("");
	  $('#reserveInfo_shopInfoId').val("<s:property value="girlInfo.shopInfoId" />");
	  $('#reserveInfo_girlInfoId').val("<s:property value="girlInfo.girlInfoId" />");
	  $('#reserveInfo_startTime').val($(this).attr("data-startTime"));
	  $('#reserveInfo_endTime').val($(this).attr("data-endTime"));
	  $('#reserveInfo_reserveInfoId').val($(this).attr("data-reserveInfoId"));
	  $('#infoForm #action').val("update");
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
		  	    //html += '<div class="ui icon button small blue edit-reserve" data-reserveInfoId="' + obj.reserveInfoId + '" data-startTime="' + obj.startTime + '" data-endTime="' + obj.endTime + '" ><i class="ui icon edit"></i></div>';
		  		//html += '<div class="ui icon button small red delete-reserve" data-reserveInfoId="' + obj.reserveInfoId + '" ><i class="ui icon delete"></i></div>';
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
</s:if>
  </script>
</head>
<body>
<!-- Sidebar Menu -->
<s:set name="base_url" value="%{''}" />
<%@include file="/common/common_new_menu_sidebar.jsp" %>
<div class="pusher">
	<div class="ui segment very basic" id="wrapper">
		<div class="ui centered grid">
			<div class="row">
				<%@include file="/common/common_ads.jsp" %>
				<div class="ten wide column container" id="container">
					<%@include file="/common/common_statistic_info.jsp" %>
	  				<br/>
					<div class="ui breadcrumb segment attached inverted">
						<a class="section" href="<s:url value="/" />" >
							<s:text name="global.shop_menu_home" />
						</a>
						<i class="right chevron icon divider"></i>
						<div class="active section"><s:property value="girlInfo.nickName" /></div>
					</div>
	
					<div class="center aligned column">
						<div class="ui centered attached segment soft">
							<div class="column one left aligned">
								<div class="ui aligned stackable grid container">
									<div class="row">
										<div class="seven wide column corner labeled">
											<div class="ui left corner label girl-tag">
												<s:iterator value="girlInfo.girlTags" >
													<div class="ui <s:property value="primaryKey.girlTagInfo.color" /> circular label">
														<s:property value="primaryKey.girlTagInfo.girlTagNameJp" />
														<br />
													</div>
												</s:iterator>
											</div>
											<div class="center aligned sixteen wide column slide-image">
												<s:if test="girlInfo.allSame == 'true'">
													<div class="ui right corner label green verified">
														<i class="icon"><s:text name="global.all_same" /></i>
													</div>
												</s:if>
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
											<s:if test="girlInfo.mov1 != null && girlInfo.mov1 != ''">
												<br />
												<div class="center aligned sixteen wide column">
													<div class="image ui small column">
														<button class="ui icon button green basic large" id="playMov1">
															<i class="play icon"></i>
														</button>
														<video id="mov1" class="ui image fluid centered" src="<s:property value="girlInfo.mov1" />" />
													</div>
												</div>
											</s:if>
										</div>
										<div class="seven wide right floated centered column">
											<table class="ui compact basic table unstackable girl-info">
												<tbody>
													<s:if test="girlInfo.agentInfo != null">
													<tr>
														<td class="right aligned"><p>Agent</p></td>
														<td class="center aligned one wide"><p>:</p></td>
														<td><p><s:property value="girlInfo.agentInfo.agentName" /></p></td>
													</tr>
													</s:if>
													<tr>
														<td class="six wide right aligned"><p>Nick Name</p></td>
														<td class="center aligned one wide"><p>:</p></td>
														<td><p><s:property value="girlInfo.nickName" /></p></td>
													</tr>
													<tr>
														<td class="right aligned"><p>Gender</p></td>
														<td class="center aligned one wide"><p>:</p></td>
														<td>
															<p>
																<s:property value="girlInfo.genderInfo.genderNameJp" />
															</p>
														</td>
													</tr>
													<s:if test="%{girlInfo instanceof com.nightclub.model.EnGirlInfo}" >
														<tr>
															<td class="right aligned"><p>Skin</p></td>
															<td class="center aligned one wide"><p>:</p></td>
															<td>
																<p>
																	<%--<s:if test="#request.locale.language=='th'">
																		<s:property value="girlInfo.skinInfo.skinNameEn" />
																	</s:if>
																	<s:else>--%>
																		<s:property value="girlInfo.skinInfo.skinNameJp" />
																	<%--</s:else>--%>
																</p>
															</td>
														</tr>
													</s:if>
													<tr>
														<td class="right aligned"><p>Age</p></td>
														<td class="center aligned one wide"><p>:</p></td>
														<td><p><s:property value="girlInfo.age" /></p></td>
													</tr>
													<tr>
														<td class="right aligned"><p>H / W</p></td>
														<td class="center aligned one wide"><p>:</p></td>
														<td>
															<p>
																<s:if test="girlInfo.height!=-1">
																	<s:property value="girlInfo.height" />
																</s:if>
																<s:else>
																	-
																</s:else>
																/
																<s:if test="girlInfo.weight!=-1">
																	<s:property value="girlInfo.weight" />
																</s:if>
																<s:else>
																	-
																</s:else>
															</p>
														</td>
													</tr>
													<tr>
														<td class="right aligned"><p>Body size</p></td>
														<td class="center aligned one wide"><p>:</p></td>
														<td>
															<p>
																<s:if test="girlInfo.bustSize!=-1">
																	B:<s:property value="girlInfo.bustSize" />
																</s:if>
																<s:if test="girlInfo.waistSize!=-1">
																	W:<s:property value="girlInfo.waistSize" />
																</s:if>
																<s:if test="girlInfo.hipSize!=-1">
																	H:<s:property value="girlInfo.hipSize" />
																</s:if>
																<s:if test="girlInfo.bustSize==-1 && girlInfo.waistSize==-1 && girlInfo.hipSize==-1">
																	-
																</s:if>
															</p>
														</td>
													</tr>
													<tr>
														<td class="right aligned"><p>Location</p></td>
														<td class="center aligned one wide"><p>:</p></td>
														<td>
															<%--<s:if test="#request.locale.language=='th'">
																<s:iterator value="girlInfo.girlLocations" >
																	<s:property value="primaryKey.zoneInfo.zoneNameEn" />
																	<br />
																</s:iterator>
															</s:if>
															<s:else>--%>
																<%-- <s:iterator value="girlInfo.girlLocations" >
																	<s:property value="primaryKey.zoneInfo.zoneNameJp" />
																	<br />
																</s:iterator> --%>
															<%--</s:else>--%>
															<div class="ui inverted list">
																<div class="item">
																	<div class="content">
																		<div class="header">
																			<h5 class="ui header left aligned inverted">
																				<s:property value="girlInfo.countryInfo.countryNameJp" />
																			</h5>
																		</div>
																		<s:if test="girlProvinces.size() > 0" >
																			<div class="list">
																				<s:iterator value="girlProvinces" >
																					<div class="item">
																						<div class="content">
																							<div class="description">
																								<s:property value="primaryKey.provinceInfo.provinceNameJp" />
																							</div>
																							<s:if test="primaryKey.provinceInfo.zoneInfos.size() > 0" >
																								<div class="list">
																									<s:iterator value="primaryKey.provinceInfo.zoneInfos" >
																										<div class="item">
																											<div class="content">
																												<div class="ui medium label">
																													<s:property value="primaryKey.zoneInfo.zoneNameJp" />
																												</div>
																											</div>
																										</div>
																									</s:iterator>
																								</div>
																							</s:if>
																						</div>
																					</div>
																				</s:iterator>
																			</div>
																		</s:if>
																	</div>
																</div>
															</div>
														</td>
													</tr>
													<s:if test="%{girlInfo instanceof com.nightclub.model.EnGirlInfo}" >
														<tr>
															<td class="right aligned"><p><s:text name="global.job" /></p></td>
															<td class="center aligned one wide"><p>:</p></td>
															<td>
																<p>
																	<s:if test="girlInfo.type == 1"><s:text name="global.en_girl_type_1" /></s:if>
																	<s:if test="girlInfo.type == 2"><s:text name="global.en_girl_type_2" /></s:if>
																	<s:if test="girlInfo.type == 3"><s:text name="global.en_girl_type_3" /></s:if>
																</p>
															</td>
														</tr>
														<tr>
															<td class="right aligned"><p><s:text name="global.service_charge" /></p></td>
															<td class="center aligned one wide"><p>:</p></td>
															<td>
																<p>
																	<s:text name="format.integer"><s:param name="value" value="girlInfo.price"/></s:text>
																</p>
															</td>
														</tr>
													</s:if>
													<s:if test="%{girlInfo.agentInfoId != null}">
														<s:if test="%{girlInfo.agentInfo.userInfo.active  == 'true'}">
															<%-- <tr>
																<td class="right aligned"><p>Line</p></td>
																<td class="center aligned one wide"><p>:</p></td>
																<td>
																	<s:if test="%{clientInfo != null}">
																		<s:if test="%{clientInfo.userInfo.active  == 'true'}">
																			<p><s:property value="girlInfo.lineId" /></p>
																		</s:if>
																		<s:else>
																			<p>[รอตรวจสอบข้อมูลลูกค้า]</p>
																		</s:else>
																	</s:if>
																	<s:else>
																		<p>[<s:text name="global.for_more_info" />]</p>
																		<p>[<a href="<s:url value="/signup"/>">กรุณาสมัครสมาชิก</a> หรือ <a href="<s:url value="/login"/>">เข้าสู่ระบบ</a>]</p>
																	</s:else>
																	<p><s:property value="girlInfo.lineId" /></p>
																</td>
															</tr> --%>
															<tr>
																<td class="right aligned"><p>Phone</p></td>
																<td class="center aligned one wide"><p>:</p></td>
																<td>
																	<p>
																		<s:if test="girlInfo.agentInfo.userInfo.phone!=''">
																			<s:property value="girlInfo.agentInfo.userInfo.phone" />
																		</s:if>
																	</p>
																</td>
															</tr>
															<tr>
																<td colspan="3">
																	<div class="ui centered grid social-icon">
																		<div class="six column row">
																			<s:if test="girlInfo.agentInfo.lineId != ''">
																				<div class="center aligned column">
																					<a href="https://line.me/ti/p/~<s:property value="girlInfo.agentInfo.lineId" />">
																						<i class="icon huge linechat"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.agentInfo.telegramId != ''">
																				<div class="center aligned column">
																					<a href="https://t.me/s/<s:property value="girlInfo.agentInfo.telegramId" />">
																						<i class="icon huge telegram"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.agentInfo.skypeId != ''">
																				<div class="center aligned column">
																					<a href="skype:<s:property value="girlInfo.agentInfo.skypeId" />">
																						<i class="icon huge skype"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.agentInfo.twitterId != ''">
																				<div class="center aligned column">
																					<a href="https://twitter.com/<s:property value="girlInfo.agentInfo.twitterId" />">
																						<i class="fa-brands fa-x-twitter"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.agentInfo.wechatId != ''">
																				<div class="center aligned column">
																					<a href="weixin://dl/chat?<s:property value="girlInfo.agentInfo.wechatId" />">
																						<i class="icon huge wechat"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.agentInfo.whatsAppId != ''">
																				<div class="center aligned column">
																					<a href="https://wa.me/<s:property value="girlInfo.agentInfo.whatsAppId" />">
																						<i class="icon huge whatsapp"></i>
																					</a>
																				</div>
																			</s:if>
																		</div>
																	</div>
																</td>
															</tr>
														</s:if>
													</s:if>
													<s:elseif test="%{girlInfo.shopInfoId != null}">
														<s:if test="%{girlInfo.basicInfo.userInfo.active  == 'true'}">
															<%-- <tr>
																<td class="right aligned"><p>Line</p></td>
																<td class="center aligned one wide"><p>:</p></td>
																<td>
																	<s:if test="%{clientInfo != null}">
																		<s:if test="%{clientInfo.userInfo.active  == 'true'}">
																			<p><s:property value="girlInfo.lineId" /></p>
																		</s:if>
																		<s:else>
																			<p>[รอตรวจสอบข้อมูลลูกค้า]</p>
																		</s:else>
																	</s:if>
																	<s:else>
																		<p>[<s:text name="global.for_more_info" />]</p>
																		<p>[<a href="<s:url value="/signup"/>">กรุณาสมัครสมาชิก</a> หรือ <a href="<s:url value="/login"/>">เข้าสู่ระบบ</a>]</p>
																	</s:else>
																	<p><s:property value="girlInfo.lineId" /></p>
																</td>
															</tr> --%>
															<tr>
																<td colspan="3">
																	<div class="ui centered grid social-icon">
																		<div class="six column row">
																			<s:if test="girlInfo.lineId != ''">
																				<div class="center aligned column">
																					<a href="https://line.me/ti/p/~<s:property value="girlInfo.lineId" />">
																						<i class="icon huge linechat"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.telegramId != ''">
																				<div class="center aligned column">
																					<a href="https://t.me/s/<s:property value="girlInfo.telegramId" />">
																						<i class="icon huge telegram"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.skypeId != ''">
																				<div class="center aligned column">
																					<a href="skype:<s:property value="girlInfo.skypeId" />">
																						<i class="icon huge skype"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.twitterId != ''">
																				<div class="center aligned column">
																					<a href="https://twitter.com/<s:property value="girlInfo.twitterId" />">
																						<i class="fa-brands fa-x-twitter"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.wechatId != ''">
																				<div class="center aligned column">
																					<a href="weixin://dl/chat?<s:property value="girlInfo.wechatId" />">
																						<i class="icon huge wechat"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.whatsAppId != ''">
																				<div class="center aligned column">
																					<a href="https://wa.me/<s:property value="girlInfo.whatsAppId" />">
																						<i class="icon huge whatsapp"></i>
																					</a>
																				</div>
																			</s:if>
																		</div>
																	</div>
																</td>
															</tr>
														</s:if>
													</s:elseif>
													<s:else>
														<s:if test="%{girlInfo.userInfo.active  == 'true'}">
															<%-- <tr>
																<td class="right aligned"><p>Line</p></td>
																<td class="center aligned one wide"><p>:</p></td>
																<td>
																	<s:if test="%{clientInfo != null}">
																		<s:if test="%{clientInfo.userInfo.active  == 'true'}">
																			<p><s:property value="girlInfo.lineId" /></p>
																		</s:if>
																		<s:else>
																			<p>[รอตรวจสอบข้อมูลลูกค้า]</p>
																		</s:else>
																	</s:if>
																	<s:else>
																		<p>[<s:text name="global.for_more_info" />]</p>
																		<p>[<a href="<s:url value="/signup"/>">กรุณาสมัครสมาชิก</a> หรือ <a href="<s:url value="/login"/>">เข้าสู่ระบบ</a>]</p>
																	</s:else>
																	<p><s:property value="girlInfo.lineId" /></p>
																</td>
															</tr> --%>
															<tr>
																<td class="right aligned"><p>Phone</p></td>
																<td class="center aligned one wide"><p>:</p></td>
																<td>
																	<p>
																		<s:if test="girlInfo.userInfo.phone!=''">
																			<s:property value="girlInfo.userInfo.phone" />
																		</s:if>
																	</p>
																</td>
															</tr>
															<tr>
																<td colspan="3">
																	<div class="ui centered grid social-icon">
																		<div class="six column row">
																			<s:if test="girlInfo.lineId != ''">
																				<div class="center aligned column">
																					<a href="https://line.me/ti/p/~<s:property value="girlInfo.lineId" />">
																						<i class="icon huge linechat"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.telegramId != ''">
																				<div class="center aligned column">
																					<a href="https://line.me/ti/p/~<s:property value="girlInfo.telegramId" />">
																						<i class="icon huge telegram"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.skypeId != ''">
																				<div class="center aligned column">
																					<a href="https://line.me/ti/p/~<s:property value="girlInfo.skypeId" />">
																						<i class="icon huge skype"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.twitterId != ''">
																				<div class="center aligned column">
																					<a href="https://line.me/ti/p/~<s:property value="girlInfo.twitterId" />">
																						<i class="fa-brands fa-x-twitter"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.wechatId != ''">
																				<div class="center aligned column">
																					<a href="weixin://dl/chat?<s:property value="girlInfo.wechatId" />">
																						<i class="icon huge wechat"></i>
																					</a>
																				</div>
																			</s:if>
																			<s:if test="girlInfo.whatsAppId != ''">
																				<div class="center aligned column">
																					<a href="https://wa.me/<s:property value="girlInfo.whatsAppId" />">
																						<i class="icon huge whatsapp"></i>
																					</a>
																				</div>
																			</s:if>
																		</div>
																	</div>
																</td>
															</tr>
														</s:if>
													</s:else>
												</tbody>
											</table>
											<h5 class="ui horizontal left aligned header">
												Description
											</h5>
											<s:text name="girlInfo.description"></s:text>
										</div>
									</div>
									<s:if test="%{girlInfo.agentInfoId != null}">
										<%-- <s:if test="homeInfo.lineNotifyActive == 'true'"> --%>
											<div class="row">
												<div class="column">
													<a class="fluid ui attached button blue huge" href="<s:url value="/girl/%{girlInfoId}/booking"/>" >
														BOOK NOW
													</a>
												</div>
											</div>
											<div class="row">
												<div class="column">
													<table class="ui celled selectable center aligned celled table compact unstackable inverted ">
														<thead class="center aligned">
															<tr>
																<s:iterator value="workingDateList" status="status">
																	<th>
																		<s:date name="date" format="MMM.dd (E)" />
																	</th>
																</s:iterator>
															</tr>
														</thead>
														<tbody>
															<tr>
																<s:iterator value="workingDateList" status="status">
																	<td>
																		<s:if test="sunday == true" >
																			<s:if test="girlInfo.chkWorkSun == 'true'" >
																				<s:property value="girlInfo.workSunTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:if>
																		<s:elseif test="monday == true" >
																			<s:if test="girlInfo.chkWorkMon == 'true'" >
																				<s:property value="girlInfo.workMonTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:elseif>
																		<s:elseif test="tuesday == true" >
																			<s:if test="girlInfo.chkWorkTue == 'true'" >
																				<s:property value="girlInfo.workTueTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:elseif>
																		<s:elseif test="wednesday == true" >
																			<s:if test="girlInfo.chkWorkWed == 'true'" >
																				<s:property value="girlInfo.workWedTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:elseif>
																		<s:elseif test="thursday == true" >
																			<s:if test="girlInfo.chkWorkThu == 'true'" >
																				<s:property value="girlInfo.workTueTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:elseif>
																		<s:elseif test="friday == true" >
																			<s:if test="girlInfo.chkWorkFri == 'true'" >
																				<s:property value="girlInfo.workFriTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:elseif>
																		<s:elseif test="saturday == true" >
																			<s:if test="girlInfo.chkWorkSat == 'true'" >
																				<s:property value="girlInfo.workSatTime" />
																			</s:if>
																			<s:else>-</s:else>
																		</s:elseif>
																	</td>
																</s:iterator>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										<%-- </s:if> --%>
									</s:if>
									<s:if test="%{girlInfo instanceof com.nightclub.model.EnGirlInfo}" >
									</s:if>
									<s:else>
									<div class="row">
										<div class="column">
											<h5 class="ui header left aligned inverted">
												Service
											</h5>
											<table class="ui celled selectable center aligned celled table compact unstackable inverted ">
												<thead class="center aligned">
													<tr>
														<th width="25%">Service</th>
														<th width="25%">Included</th>
														<th width="25%">Extra</th>
														<th width="25%">Price</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="girlServices" status="rowstatus">
														<tr>
															<td>
																<s:property value="primaryKey.girlServiceInfo.girlServiceNameJp" />
															</td>
															<td class="center aligned">
																<s:if test="chkInclude == 'true'">
																	<i class="ui check teal icon"></i>
																</s:if>
															</td>
															<td class="center aligned">
																<s:if test="chkExtra == 'true'">
																	<i class="ui check teal icon"></i>
																</s:if>
															</td>
															<td class="center aligned">
																<s:if test="priceExtra != 0">
																	<s:text name="format.integer"><s:param name="value" value="priceExtra"/></s:text>
																	<s:property value="crcy"/>
																</s:if>
															</td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</div>
									</div>
									<div class="row">
										<div class="column">
											<h5 class="ui header left aligned inverted">
												Rate Information
											</h5>
											<table class="ui celled selectable center aligned celled table compact unstackable inverted">
												<thead class="center aligned">
													<tr>
														<th width="25%"><s:text name="global.time" /></th>
														<th width="25%"><s:text name="global.price_incall" /></th>
														<th width="25%"><s:text name="global.price_outcall" /></th>
														<th width="25%"><s:text name="global.crcy" /></th>
													</tr>
												</thead>
												<tbody>
													<s:if test="girlInfo.chk40Mins == 'true'">
													<tr>
														<td class="center aligned">
															<label for="girlInfo_chk40Mins"><s:text name="global.price40Mins" /></label>
														</td>
														<td class="center aligned">
															<s:if test="girlInfo.priceIncall40Mins != 0">
																<s:text name="format.integer"><s:param name="value" value="girlInfo.priceIncall40Mins"/></s:text>
															</s:if>
														</td>
														<td class="center aligned">
															<s:if test="girlInfo.priceOutcall40Mins != 0">
																<s:text name="format.integer"><s:param name="value" value="girlInfo.priceOutcall40Mins"/></s:text>
															</s:if>
														</td>
														<td class="center aligned">
															<s:property value="girlInfo.crcy40Mins" />
														</td>
													</tr>
													</s:if>
													<s:if test="girlInfo.chk60Mins == 'true'">
													<tr>
														<td class="center aligned">
															<label for="girlInfo_chk60Mins"><s:text name="global.price60Mins" /></label>
														</td>
														<td class="center aligned">
															<s:if test="girlInfo.priceIncall60Mins != 0">
																<s:text name="format.integer"><s:param name="value" value="girlInfo.priceIncall60Mins"/></s:text>
															</s:if>
														</td>
														<td class="center aligned">
															<s:if test="girlInfo.priceOutcall60Mins != 0">
																<s:text name="format.integer"><s:param name="value" value="girlInfo.priceOutcall60Mins"/></s:text>
															</s:if>
														</td>
														<td class="center aligned">
															<s:property value="girlInfo.crcy60Mins" />
														</td>
													</tr>
													</s:if>
													<s:if test="girlInfo.chk90Mins == 'true'">
													<tr>
														<td class="center aligned">
															<label for="girlInfo_chk90Mins"><s:text name="global.price90Mins" /></label>
														</td>
														<td class="center aligned">
															<s:if test="girlInfo.priceIncall90Mins != 0">
																<s:text name="format.integer"><s:param name="value" value="girlInfo.priceIncall90Mins"/></s:text>
															</s:if>
														</td>
														<td class="center aligned">
															<s:if test="girlInfo.priceOutcall90Mins != 0">
																<s:text name="format.integer"><s:param name="value" value="girlInfo.priceOutcall90Mins"/></s:text>
															</s:if>
														</td>
														<td class="center aligned">
															<s:property value="girlInfo.crcy90Mins" />
														</td>
													</tr>
													</s:if>
													<s:if test="girlInfo.chk120Mins == 'true'">
													<tr>
														<td class="center aligned">
															<label for="girlInfo_chk120Mins"><s:text name="global.price120Mins" /></label>
														</td>
														<td class="center aligned">
															<s:if test="girlInfo.priceIncall120Mins != 0">
																<s:text name="format.integer"><s:param name="value" value="girlInfo.priceIncall120Mins"/></s:text>
															</s:if>
														</td>
														<td class="center aligned">
															<s:if test="girlInfo.priceOutcall120Mins != 0">
																<s:text name="format.integer"><s:param name="value" value="girlInfo.priceOutcall120Mins"/></s:text>
															</s:if>
														</td>
														<td class="center aligned">
															<s:property value="girlInfo.crcy120Mins" />
														</td>
													</tr>
													</s:if>
													<s:if test="girlInfo.chk6Hrs == 'true'">
													<tr>
														<td class="center aligned">
															<label for="girlInfo_chk6Hrs"><s:text name="global.price6Hrs" /></label>
														</td>
														<td class="center aligned">
															<s:if test="girlInfo.priceIncall6Hrs != 0">
																<s:text name="format.integer"><s:param name="value" value="girlInfo.priceIncall6Hrs"/></s:text>
															</s:if>
														</td>
														<td class="center aligned">
															<s:if test="girlInfo.priceOutcall6Hrs != 0">
																<s:text name="format.integer"><s:param name="value" value="girlInfo.priceOutcall6Hrs"/></s:text>
															</s:if>
														</td>
														<td class="center aligned">
															<s:property value="girlInfo.crcy6Hrs" />
														</td>
													</tr>
													</s:if>
												</tbody>
											</table>
										</div>
									</div>
									</s:else>
									<div class="row">
										<div class="column left aligned ">
											<h5 class="ui header left aligned inverted">
												Comment
											</h5>
											<form class="ui form " id="girlCommentForm" method="post" action="" >
												<div class="inline field ">
													<div class="ui left small icon input">
														<s:textfield name="girlComment.createdBy" value="Anonymous" placeholder="User" />
														<i class="user icon"></i>
													</div>
												</div>
												<div class="inline field">
													<s:textarea name="girlComment.comment" placeholder="Type here..." />
												</div>
												<div class="ui row grid">
													<div class="eight wide column left aligned">
														<div id="rating" class="ui yellow rating" data-icon="star" data-max-rating="5"></div>
													</div>
													<div class="eight wide column right aligned">
														<div class="ui small button submit blue"><s:text name="global.submit" /></div>
													</div>
												</div>
												<input type="hidden" name="girlComment.girlInfoId" value="<s:property value="girlInfo.girlInfoId" />" />
												<s:hidden name="girlComment.rating" />
												<s:hidden name="action" value="addGirlComment" />
											</form>
											<br />
										</div>
									</div>
	<%-- 								<s:if test="%{girlInfo.agentInfoId != null}">
										<s:if test="homeInfo.lineNotifyActive == 'true'">
											<div class="row">
												<div class="column">
													<h5 class="ui header left aligned inverted">
														<s:text name="global.shop_girl_reserve" />
													</h5>
													<div class="ui centered grid attached content">
														<div class="column one left aligned" id="wrapper_calendar">
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
																		   <div class="header" id="lookupDateHeader"><s:property value="lookupDateHeader" /></div>
																		</h3>
																	</div>
																	<div class="ui celled list inverted" id="reserveInfos">
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
												</div>
											</div>
										</s:if>
									</s:if> --%>
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
	<%@include file="/common/common_footer.jsp" %>
</div>
<s:if test="%{girlInfo.agentInfoId != null}">
<s:if test="homeInfo.lineNotifyActive == 'true'">
<div class="ui mini modal">
  <i class="close icon"></i>
  <div class="header">
  	<s:text name="global.edit_information" /><s:text name="global.menu_girls" />
  </div>
  <div class="content">
  	<form class="ui form" id="infoForm" method="post" action="<s:url value="/management/girl/update"/>" enctype="multipart/form-data">
    	<div class="inline field disabled">
			<label><s:text name="global.reserve_date" /></label>
			<s:textfield name="reserveInfo.reserveDate" placeholder="DD/MM/YYYY" size="10" readonly="true" />
		</div>
    	<div class="inline field">
    		<label><s:text name="global.free_time" /></label>
    		<div class="ui list" id="availableInfos"></div>
    	</div>
    	<br />
    	<div class="inline field">
			<label><s:text name="global.time" /></label>
			<s:textfield name="reserveInfo.startTime" placeholder="HH:mm" size="6" />
			<label>-</label>
			<s:textfield name="reserveInfo.endTime" placeholder="HH:mm" size="6" />
		</div>
		<div class="inline field">
			<label><s:text name="global.client_name" /></label>
				<s:textfield name="reserveInfo.clientName" />
		</div>
		<div class="inline field">
			<label><s:text name="global.mobile" /></label>
			<s:textfield name="reserveInfo.mobile" />
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
</s:if>
</s:if>
</body>
</html>