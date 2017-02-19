<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Night Club - Home</title>

 <%@include file="/common/common_header.jsp" %>
  <!--- Example CSS -->
  <style>
  #girls.ui.grid{
  	margin: 0;
  }
  #girls.ui.grid > .column:not(.row) {
  	padding: 0;
  }
  @media only screen and (max-width: 767px) {
  .ui.huge.button {
  	font-size: 1rem;
  }
  }
  </style>

  <!--- Example Javascript -->
</head>
<body>
<!-- Sidebar Menu -->
<%@include file="/common/common_menu_sidebar.jsp" %>
<div class="pusher">
	<div class="ui segment very basic">
		<div class="ui centered grid"> 
			<div class="eleven wide column container" id="container">
				<%@include file="/common/common_statistic_info.jsp" %>
				<%@include file="/common/common_menu.jsp" %>
	  
	  			<div class="ui segment">
					<h2 class="ui top header">
						<i class="bookmark icon yellow"></i>
						<div class="content">ご利用上のご注意</div>
					</h2>
					<div class="ui centered grid attached segment">
						<div class="column one left aligned">
							<s:text name="homeInfo.description"></s:text>
				 		</div>
					</div>
				</div>
				
				<div class="ui segment">
					<h2 class="ui top header">
						<i class="help circle icon blue"></i>
						<div class="content">貴方は18才以上ですか？</div>
					</h2>
					<div class="ui centered grid attached segment">
						<div class="column center aligned">
							<a href="<s:url value="/shoplist"/>" class="ui massive button green"><i class="thumbs up icon"></i>YES</a>
							<a href="#" class="ui massive button red"><i class="thumbs down icon"></i>NO</a>
						</div>
					</div>
				</div>
				  
				<div class="ui segment">
					<h2 class="ui top header ">
						<i class="heart icon pink"></i>
						<div class="content">在籍一覧</div>
					</h2>
					<div class="ui center grid attached segment soft">
						<div id="girls" class="ui six column grid centered">
							<s:iterator value="girlInfos" status="status">
								<div class="column">
									<a href="<s:url value="/shop/%{basicInfo.shopCode}/girls/%{code}"/>" target="_blank" >
										<img class="image ui small centered" src="<s:property value="pic1" />">
									</a>
								</div>
							</s:iterator>
						</div>
					</div>
				</div>
				
				<div class="ui segment">
					<h2 class="ui top header">
						<i class="idea icon orange"></i>
						<div class="content">東京ソープとは</div>
					</h2>
					<div class="ui centered grid attached segment">
						<div class="column one left aligned">
							<s:text name="homeInfo.description2"></s:text>
				 		</div>
					</div>
				</div>
				
				<div class="ui segment">
					<h2 class="ui top header">
						<i class="newspaper icon"></i>
						<div class="content">サイト最新情報</div>
					</h2>
					<div class="ui attached segment">
						<s:iterator value="newsInfos" status="status">
						<span>[<s:date name="newsDate" format="dd MMMM yyyy" /> at <s:property value="newsTime" />]</span>
						<span><a href="<s:url value="/news/%{newsInfoId}" />"><s:property value="title" /></a></span><br />
						</s:iterator>
					</div>
				</div>
				
				<div class="ui segment">
					<h2 class="ui top header">
						<i class="announcement icon"></i>
						<div class="content">リンク情報</div>
					</h2>
					<div class="ui attached segment">
						<div class="ui leaderboard test ad centered" data-text="Advertisment"></div>
					</div>
				</div>
			</div>
		</div>
	</div><br />
	 <%@include file="/common/common_footer.jsp" %>
</div>
</body>
</html>