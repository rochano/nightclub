<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Shop - Schedule</title>

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
    })
  ;
  </script>
</head>
<body>
<!-- Sidebar Menu -->
<div class="ui vertical inverted sidebar menu">
	<%@include file="/common/common_shop_menu.jsp" %>
</div>
<div class="pusher">
	<div class="ui segment very basic">
		<div class="ui centered grid"> 
			<div class="ten wide column container" id="container">
				<%@include file="/common/common_shop_header_info.jsp" %>
				<div class="ui menu inverted stackable">
					<a class="toc item"><i class="sidebar icon"></i></a>
					<%@include file="/common/common_shop_menu.jsp" %>
				</div>
				
				<h4 class="ui top attached header">
					<s:text name="global.today_working_attendance" />
				</h4>
				<div class="ui centered grid attached segment">
					<div class="column one center aligned">
						<h5 class="ui header left aligned">
							<s:date name="currentDate" format="MMMM dd, yyyy" /> attendance scheduled for (<s:date name="currentDate" format="EEEE" />)
						</h5>
						<div class="ui horizontal items">
							<s:iterator value="scheduleToday" status="status">
							<div class="item">
								<div class="image ui small">
									<img class="image ui centered" src="<s:property value="girlInfo.pic1" />">
								</div>
								<div class="content">
									<div class="header"><a href="<s:url value="/shop/%{shopCode}/girls/%{girlInfo.code}"/>" class="ui labeled"><s:property value="girlInfo.nickName" /></a></div>
									<div class="meta"><span class="age"><s:text name="global.shop_girl_age" />: <s:property value="girlInfo.age" /></span></div>
									<div class="meta">
										<span class="property">
											<s:property value="startTime" />
											to 
											<s:property value="endTime" />
										</span>
									</div>
								</div>
							</div>
							</s:iterator>
						</div>
					</div>
				</div>
				<h4 class="ui top attached header">
					<s:text name="global.shop_girls_schedule_list" />
				</h4>
				<div class="ui centered grid attached segment">
					<div class="column one center aligned">
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th><s:text name="global.photo" /></th>
									<th><s:text name="global.name" /></th>
									<th style="width: 5px;"><s:text name="global.mon" /></th>
									<th style="width: 5px;"><s:text name="global.tue" /></th>
									<th style="width: 5px;"><s:text name="global.wed" /></th>
									<th style="width: 5px;"><s:text name="global.thu" /></th>
									<th style="width: 5px;"><s:text name="global.fri" /></th>
									<th style="width: 5px;"><s:text name="global.sat" /></th>
									<th style="width: 5px;"><s:text name="global.sun" /></th>
									<th><s:text name="global.start_time" /></th>
									<th><s:text name="global.end_time" /></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="scheduleInfos" status="status">
								<tr>
									<td class="center aligned"><s:property value="#status.count" /></td>
									<td>
										<img class="image ui tiny centered" src="<s:property value="girlInfo.pic1" />">
									</td>
									<td><s:property value="girlInfo.firstName" /> <s:property value="girlInfo.lastName" /></td>
									<td class="center aligned">
										<div class="ui <s:if test="workMon == 'true'">checked</s:if> fitted checkbox disabled">
										  <s:if test="workMon == 'true'"><i class="circle thin icon"></i></s:if>
										</div>
									</td>
									<td class="center aligned">
										<div class="ui <s:if test="workTue == 'true'">checked</s:if> fitted checkbox disabled">
										  <s:if test="workTue == 'true'"><i class="circle thin icon"></i></s:if>
										</div>
									</td>
									<td class="center aligned">
										<div class="ui <s:if test="workWed == 'true'">checked</s:if> fitted checkbox disabled">
										  <s:if test="workWed == 'true'"><i class="circle thin icon"></i></s:if>
										</div>
									</td>
									<td class="center aligned">
										<div class="ui <s:if test="workThu == 'true'">checked</s:if> fitted checkbox disabled">
										  <s:if test="workThu == 'true'"><i class="circle thin icon"></i></s:if>
										</div>
									</td>
									<td class="center aligned">
										<div class="ui <s:if test="workFri == 'true'">checked</s:if> fitted checkbox disabled">
										  <s:if test="workFri == 'true'"><i class="circle thin icon"></i></s:if>
										</div>
									</td>
									<td class="center aligned">
										<div class="ui <s:if test="workSat == 'true'">checked</s:if> fitted checkbox disabled">
										  <s:if test="workSat == 'true'"><i class="circle thin icon"></i></s:if>
										</div>
									</td>
									<td class="center aligned">
										<div class="ui <s:if test="workSun == 'true'">checked</s:if> fitted checkbox disabled">
										  <s:if test="workSun == 'true'"><i class="circle thin icon"></i></s:if>
										</div>
									</td>
									<td class="center aligned"><s:property value="startTime" /></td>
									<td class="center aligned"><s:property value="endTime" /></td>
								</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	  
	</div>
	<%@include file="/common/common_shop_footer.jsp" %>
</div>
</body>
</html>