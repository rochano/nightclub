<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Shop - Ranking</title>
  
  <%@include file="/common/common_shop_header.jsp" %>
  
  <!--- Example CSS -->
  <!-- <link rel="stylesheet" type="text/css" href="../Semantic-UI-master/dist/semantic.min.css"> -->
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
  .ui.table thead tr:first-child > th:only-child {
  	font-size: 12px;
  }
  .ui.table span {
  	padding: 0 5px;
  }
  .ui.three.column.table td .ui.table td.collapsing {
  	width: 10%;
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
					<s:text name="global.shop_menu_ranking" />
				</h4>
				<div class="ui centered grid attached segment">
					<div class="column one center aligned">
						<div class="ui horizontal items">
							<s:iterator value="hmRanking.ranking" status="status">
							<div class="item">
								<div class="image ui small">
									<img class="image ui centered" src="<s:property value="pic1" />">
								</div>
								<div class="content">
									<div class="header">
										<a href="<s:url value="/shop/"/><s:property value="shopCode" />/girls/<s:property value="code" />" class="ui labeled"><s:property value="nickName" /></a>
									</div>
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
							</s:iterator>
						</div>
					</div>
				</div>
				<div class="ui centered grid stackable three column">
					<div class="column center aligned">
							<table class="ui striped celled table unstackable">
								<thead>
									<tr>
										<th colspan="3">指名ランキング</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="hmRanking.bodySize" status="status">
									<tr>
										<td class="center aligned collapsing"><span><s:property value="#status.count" /></span></td>
										<td>
											<a href="<s:url value="/shop/"/><s:property value="shopCode" />/girls/<s:property value="code" />" class="ui labeled"><s:property value="nickName" /></a>
										</td>
										<td class="center aligned collapsing">
											<s:text name="format.integer"><s:param name="value" value="bustSize"/></s:text>
											/
											<s:text name="format.integer"><s:param name="value" value="hipSize"/></s:text>
										</td>
									</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<div class="column center aligned">
							<table class="ui striped celled table unstackable">
								<thead>
									<tr>
										<th colspan="3">小柄ランキング（cm）</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="hmRanking.miniSize" status="status">
									<tr>
										<td class="center aligned collapsing"><span><s:property value="#status.count" /></span> </td>
										<td>
											<a href="<s:url value="/shop/"/><s:property value="shopCode" />/girls/<s:property value="code" />" class="ui labeled"><s:property value="nickName" /></a>
										</td>
										<td class="center aligned collapsing"><s:text name="format.integer"><s:param name="value" value="height"/></s:text></td>
									</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<div class="column center aligned">
							<table class="ui striped celled table unstackable">
								<thead>
									<tr>
										<th colspan="3">長身ランキング（cm）</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="hmRanking.heightSize" status="status">
									<tr>
										<td class="center aligned collapsing"><span><s:property value="#status.count" /></span> </td>
										<td>
											<a href="<s:url value="/shop/"/><s:property value="shopCode" />/girls/<s:property value="code" />" class="ui labeled"><s:property value="nickName" /></a>
										</td>
										<td class="center aligned collapsing"><s:text name="format.integer"><s:param name="value" value="height"/></s:text></td>
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