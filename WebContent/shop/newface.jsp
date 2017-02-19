<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Shop - New Face</title>
  
  <%@include file="/common/common_shop_header.jsp" %>
  
  <style>
  .ui.menu:last-child {
    margin-bottom: 110px;
  }
  .ui.items > .item {
	display: inline-block;
  }
  .ui.items > .item > .image {
	margin: auto;
  }
  .ui.items > .item > .image + .content {
	display: block;
    padding: 1.5em 0em 0em;
  }
  p {
  	padding:5px 0;
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
			<div class="eleven wide column container" id="container">
				<%@include file="/common/common_shop_header_info.jsp" %>
				<div class="ui menu inverted stackable">
					<a class="toc item"><i class="sidebar icon"></i></a>
					<%@include file="/common/common_shop_menu.jsp" %>
				</div>
				
				<div class="ui segment">
					<h2 class="ui top header">
						<i class="heart icon pink"></i>
						<div class="content"><s:text name="global.shop_menu_newface" /></div>
					</h2>
					<div class="ui centered grid attached segment">
						<div class="column one center aligned">
							<div class="ui grid stackable ">
								<s:iterator value="girlInfos" status="status">
								<div class="eight wide column">
									<div class="ui grid ">
										<div class="seven wide column">
											<div class="ui items">
												<div class="item">
													<div class="image ui small">
														<img class="image ui centered" src="<s:property value="pic1" />">
													</div>
													<div class="content">
														<div class="header"><a href="<s:url value="/shop/%{shopCode}/girls/%{code}"/>" class="ui labeled"><s:property value="nickName" /></a></div>
														<div class="meta"><span class="age"><s:text name="global.shop_girl_age" />: <s:property value="age" /></span></div>
														<div class="meta">
															<span class="property">
																T<s:text name="format.integer"><s:param name="value" value="height"/></s:text>
																B<s:text name="format.integer"><s:param name="value" value="bustSize"/></s:text>
																W<s:text name="format.integer"><s:param name="value" value="waistSize"/></s:text>
																H<s:text name="format.integer"><s:param name="value" value="hipSize"/></s:text>
															</span>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="nine wide column left aligned">
											<p>
												<span class=""><s:text name="global.shop_girl_home_town" />:&nbsp;</span>
												<s:property value="hometown" />
											</p>
											<span class="ui tiny "><s:text name="global.shop_girl_message" />: </span>
											<s:text name="description" />
										</div>
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
	<%@include file="/common/common_shop_footer.jsp" %>
</div>
</body>
</html>