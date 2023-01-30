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
  .ui.table, .ui.table tr th, .ui.table tr td {
  	border-width:1px 0px!important; 
  	vertical-align: text-top;
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
  </style>
  <script type="text/javascript">
  $(document)
    .ready(function() {
    	<s:if test="clientInfo != null">
		$(".toggleFavourite").click(function() {
			var favouriteIcon = $(this).find("i");
			var girlInfoId = $(this).attr("data-girlInfoId");
			var favourite = 0;
			if (favouriteIcon.hasClass("outline")) {
				favouriteIcon.removeClass("outline");
				/* toggleFavourite.addClass("red") */
				favourite = 1;
			} else {
				favouriteIcon.removeClass("red");
				/* toggleFavourite.addClass("outline") */
			}
			favouriteIcon.removeClass("heart");
			favouriteIcon.addClass("spinner");
			$.getJSON("<s:url value="/ajax/toggleFavouriteJson/" />" + girlInfoId + "/" + favourite,
			function(jsonResponse) {
				favouriteIcon.removeClass("spinner");
				favouriteIcon.addClass("heart");
				if (jsonResponse.favourite === '1') {
					favouriteIcon.removeClass("outline");
					favouriteIcon.addClass("red")
				} else {
					favouriteIcon.removeClass("red");
					favouriteIcon.addClass("outline")
				}
  			});
		});
		</s:if>
		<s:else>
		$('.toggleFavourite')
			.popup({
				on: 'click'
			})
		;
		</s:else>
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
  		var lookupDate = $('#inline_calendar').calendar("get date").toISOString().substring(0, 10);
	  	getReserveList(lookupDate);
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
	<div class="ui segment very basic">
		<div class="ui centered grid">
			<div class="eleven wide column container" id="container">
				<%@include file="/common/common_statistic_info.jsp" %>
				<%@include file="/common/common_new_menu.jsp" %>
  				<br/>
				<div class="ui breadcrumb segment attached inverted">
					<a class="section" href="<s:url value="/" />" >
						<s:text name="global.shop_menu_home" />
					</a>
					<i class="right chevron icon divider"></i>
					<s:if test="%{girlInfo.agentInfoId != null}">
					<a class="section" href="<s:url value="/agents" />" >
						<s:text name="global.main_menu_agents" />
					</a>
					<%-- <i class="right chevron icon divider"></i>
					<a class="section" href="<s:url value="/agents/%{girlInfo.agentInfoId}"/>" >
						<s:text name="girlInfo.agentInfo.agentName" />
					</a> --%>
					</s:if>
					<s:elseif test="%{girlInfo instanceof com.nightclub.model.FreeAgentGirlInfo}" >
					<a class="section" href="<s:url value="/freeagents" />" >
						<s:text name="global.main_menu_free_agents" />
					</a>
					</s:elseif>
					<s:elseif test="%{girlInfo instanceof com.nightclub.model.EnGirlInfo}" >
					<a class="section" href="<s:url value="/engirls" />" >
						<s:text name="global.main_menu_en_girls" />
					</a>
					</s:elseif>
					<i class="right chevron icon divider"></i>
					<div class="active section"><s:property value="girlInfo.nickName" /></div>
				</div>

				<div class="center aligned column">
					<div class="ui centered attached segment soft">
						<div class="column one left aligned">
							<div class="ui aligned stackable grid container">
								<div class="row">
									<div class="seven wide column corner labeled">
										<a class="ui right corner label toggleFavourite link huge" 
												data-girlInfoId="<s:property value="girlInfoId" />"
												data-content="Please login first" data-variation="tiny">
											<i class="heart 
												<s:if test="girlFavourites.indexOf(girlInfoId) != -1">red</s:if>
												<s:else>outline</s:else>
												icon"></i>
										</a>
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
										<table class="ui compact table unstackable">
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
												<s:if test="%{girlInfo instanceof com.nightclub.model.EnGirlInfo}" >
													<tr>
														<td class="right aligned"><p>Gender</p></td>
														<td class="center aligned one wide"><p>:</p></td>
														<td>
															<p>
																<s:property value="girlInfo.genderInfo.genderNameJp" />
															</p>
														</td>
													</tr>
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
													<td><p><s:property value="girlInfo.height" /> / <s:property value="girlInfo.weight" /></p></td>
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
														<p>
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
																		<div class="list">
																			<s:iterator value="girlProvinces" >
																				<div class="item">
																					<div class="content">
																						<div class="description">
																							<s:property value="primaryKey.provinceInfo.provinceNameJp" />
																						</div>
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
																					</div>
																				</div>
																			</s:iterator>
																		</div>
																	</div>
																</div>
															</div>
														</p>
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
															<td colspan="3">
																<p class="centered" style="text-align: -webkit-center;">
																	<a href="https://line.me/ti/p/~<s:property value="girlInfo.lineId" />">
																		<img class="ui small image" src="https://scdn.line-apps.com/n/line_add_friends/btn/th.png">
																	</a>
																</p>
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
																<p class="centered" style="text-align: -webkit-center;">
																	<a href="https://line.me/ti/p/~<s:property value="girlInfo.lineId" />">
																		<img class="ui small image" src="https://scdn.line-apps.com/n/line_add_friends/btn/th.png">
																	</a>
																</p>
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
															<td colspan="3">
																<p class="centered" style="text-align: -webkit-center;">
																	<a href="https://line.me/ti/p/~<s:property value="girlInfo.lineId" />">
																		<img class="ui small image" src="https://scdn.line-apps.com/n/line_add_friends/btn/th.png">
																	</a>
																</p>
															</td>
														</tr>
													</s:if>
												</s:else>
												<s:if test="%{clientInfo != null}">
													<s:if test="%{clientInfo.userInfo.active  == 'true'}">
														<s:if test="%{girlInfo.agentInfoId != null}">
															<s:if test="%{girlInfo.agentInfo.userInfo.active  == 'true'}">
																<tr>
																	<td colspan="3">
																		<p class="centered" style="text-align: -webkit-center;">
																			<a href="https://line.me/ti/p/~<s:property value="girlInfo.lineId" />">
																				<img class="ui small image" src="https://scdn.line-apps.com/n/line_add_friends/btn/th.png">
																			</a>
																		</p>
																	</td>
																</tr>
															</s:if>
														</s:if>
														<s:else>
															<s:if test="%{girlInfo.userInfo.active  == 'true'}">
																<tr>
																	<td colspan="3">
																		<p class="centered" style="text-align: -webkit-center;">
																			<a href="https://line.me/ti/p/~<s:property value="girlInfo.lineId" />">
																				<img class="ui small image" src="https://scdn.line-apps.com/n/line_add_friends/btn/th.png">
																			</a>
																		</p>
																	</td>
																</tr>
															</s:if>
														</s:else>
													</s:if>
												</s:if>
											</tbody>
										</table>
										<h5 class="ui horizontal left aligned header">
											Description
										</h5>
										<s:text name="girlInfo.description"></s:text>
									</div>
								</div>
								<s:if test="%{girlInfo instanceof com.nightclub.model.EnGirlInfo}" >
								</s:if>
								<s:else>
								<div class="row">
									<div class="four wide column">
										<h5 class="ui header left aligned inverted">
											Gender
										</h5>
										<div class="ui grid column">
											<div class="column left aligned">
												<i class="ui check square icon green"></i>
												<s:property value="girlInfo.genderInfo.genderNameJp" />
											</div>
										</div>
									</div>
									<div class="eleven wide column">
										<h5 class="ui header left aligned inverted">
											Service
										</h5>
										<div class="ui grid doubling four column">
											<s:iterator value="girlServices" status="rowstatus">
												<div class="column left aligned">
													<i class="ui check square icon green"></i>
													<%--<s:if test="#request.locale.language=='th'">
														<s:property value="primaryKey.girlServiceInfo.girlServiceName" />
													</s:if>
													<s:else>--%>
														<s:property value="primaryKey.girlServiceInfo.girlServiceNameJp" />
													<%--</s:else>--%>
												</div>
											</s:iterator>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="column">
										<div class="ui divider"></div>
										<h5 class="ui header left aligned inverted">
											Price List
										</h5>
										<table class="ui celled center aligned celled table compact unstackable orange">
											<thead class="center aligned">
												<tr>
													<th><s:text name="global.time" /></th>
													<th><s:text name="global.price_rate" /></th>
													<th><s:text name="global.crcy" /></th>
												</tr>
											</thead>
											<tbody>
												<s:if test="girlInfo.chk40Mins == 'true'">
												<tr>
													<td class="center aligned">
														<label for="girlInfo_chk40Mins"><s:text name="global.price40Mins" /></label>
													</td>
													<td class="center aligned">
														<s:text name="format.integer"><s:param name="value" value="girlInfo.price40Mins"/></s:text>
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
														<s:text name="format.integer"><s:param name="value" value="girlInfo.price60Mins"/></s:text>
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
														<s:text name="format.integer"><s:param name="value" value="girlInfo.price90Mins"/></s:text>
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
														<s:text name="format.integer"><s:param name="value" value="girlInfo.price120Mins"/></s:text>
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
														<s:text name="format.integer"><s:param name="value" value="girlInfo.price6Hrs"/></s:text>
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
										<div class="ui divider"></div>
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
								<s:if test="%{girlInfo.agentInfoId != null}">
									<s:if test="homeInfo.lineNotifyActive == 'true'">
										<div class="row">
											<div class="column">
												<div class="ui divider"></div>
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
								</s:if>
							</div>
						</div>
					</div>
				</div>
			    
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