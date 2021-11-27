<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Shop - System</title>
  
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
  .ui.items > .item > .image {
	margin: auto;
  }
  .ui.items > .item > .image + .content {
	display: block;
    padding: 1.5em 0em 0em;
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
				
				<div class="ui segment">
					<h2 class="ui top header">
						<i class="wait icon"></i>
						<div class="content"><s:text name="global.shop_menu_system" /></div>
					</h2>
					<div class="ui centered grid attached segment">
						<div class="column one center aligned">
							<table class="ui table celled">
								<thead>
									<tr class="center aligned">
										<th><s:text name="global.shop_system_course_name" /></th>
										<th><s:text name="global.shop_system_time" /></th>
										<th><s:text name="global.shop_system_price" /></th>
										<th><s:text name="global.shop_system_detail" /></th>
									</tr>
								</thead>
								<tbody class="top aligned">
									<s:iterator value="systemInfos" status="status">
									<tr>
										<td><s:property value="infoName" /></td>
										<td><s:property value="duration" /></td>
										<td><s:text name="format.integer"><s:param name="value" value="price"/></s:text> baht</td>
										<td><s:text name="description" /></td>
									</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	  
	</div>
	<%@include file="/common/common_shop_footer.jsp" %>
</div>
</body>
</html>