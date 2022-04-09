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
								<div class="image ui">
									<img class="image large ui" src="<s:property value="girlInfo.pic1" />">
								</div>
								<div class="images tiny ui">
									<s:if test="%{girlInfo.pic1 != ''}">
										<img class="image ui centered" src="<s:property value="girlInfo.pic1" />">
									</s:if>
									<s:if test="%{girlInfo.pic2 != ''}">
										<img class="image ui centered" src="<s:property value="girlInfo.pic2" />">
									</s:if>
									<s:if test="%{#girlInfo.pic3 != ''}">
										<img class="image ui centered" src="<s:property value="girlInfo.pic3" />">
									</s:if>
									<s:if test="%{girlInfo.pic4 != ''}">
										<img class="image ui centered" src="<s:property value="girlInfo.pic4" />">
									</s:if>
									<s:if test="%{girlInfo.pic5 != ''}">
										<img class="image ui centered" src="<s:property value="girlInfo.pic5" />">
									</s:if>
								</div>
							</div>
							<div class="eight wide column">
								<h3 class="ui header">
									<div class="content">
										<s:property value="girlInfo.firstName" /> 
										<s:property value="girlInfo.lastName" /> 
										(<s:property value="girlInfo.nickName" />)
									</div>
								</h3>
								<table class="ui table definition unstackable">
									<tr>
										<td class="six wide"><s:text name="global.shop_girl_body_size" /></td>
										<td>
											<span class="property">
												T<s:text name="format.integer"><s:param name="value" value="girlInfo.height"/></s:text>
												B<s:text name="format.integer"><s:param name="value" value="girlInfo.bustSize"/></s:text>
												W<s:text name="format.integer"><s:param name="value" value="girlInfo.waistSize"/></s:text>
												H<s:text name="format.integer"><s:param name="value" value="girlInfo.hipSize"/></s:text>
											</span>
										</td>
									</tr>
									<tr>
										<td class="six wide"><s:text name="global.shop_girl_age" /></td>
										<td><s:property value="girlInfo.age" /></td>
									</tr>
								</table>
								<table class="ui table celled unstackable">
									<thead>
										<tr>
											<th><s:text name="global.shop_girl_description" /></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><s:text name="girlInfo.description" /></td>
										</tr>
									</tbody>
									
								</table>
							</div>
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