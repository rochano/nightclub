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
  <title>THAINIGHTNAVI.COM - Home</title>

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
	  .ui.massive.button {
	  	font-size: 1.2rem;
	  }
  }
  .ui.items > .item {
	    display: inline-block;
	    width: auto;
	}
  .ui.green.button {width: 50%;}
  .ui.red.button {width: 45%;}
  .ui.leaderboard.ad {
  	height: 100%;
  }
  .ui.leaderboard.ad image {
  	height: 100px;
  }
  </style>

  <!--- Example Javascript -->
</head>
<body>
<!-- Sidebar Menu -->
<s:set name="base_url" value="%{''}" />
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
						<a href="<s:url value="/shoplist"/>" class="ui massive button green"><i class="checkmark icon"></i>YES</a>
						<a href="#" class="ui massive button red"><i class="remove icon"></i>NO</a>
					</div>
				</div>
				  
				<div class="ui segment">
					<h2 class="ui top header ">
						<i class="heart icon pink"></i>
						<div class="content">在籍一覧</div>
					</h2>
					<div class="ui centered grid attached segment soft">
						<div class="ui horizontal items">
						<s:if test="%{girlInfos.size gte 0}">
							<s:iterator value="girlInfos" status="status">
								<div class="item">
									<a href="<s:url value="/shop/%{basicInfo.shopCode}/girls/%{code}"/>" target="_blank" >
										<img class="image ui small centered" src="<s:property value="pic1" />">
									</a>
								</div>
							</s:iterator>
						</s:if>
						<s:if test="%{girlInfos.size eq 0}">
							データかありません
						</s:if>
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
					<%
						String RESULT_CODE_SUFFIX_MOBILE = "mobile";
						String REQUEST_HEADER_ACCEPT = "Accept";
						String[] MOBILE_BROWSER_UAS = {"iPhone OS","Android","BlackBerry","Windows Phone"};
						//Get User Agent String
						String userAgent = request.getHeader("User-Agent");
						boolean showMobileVersion = false;
						//Run through each entry in the list of browsers
			            for(String ua : MOBILE_BROWSER_UAS){
			                if(userAgent.toLowerCase().matches(".*"+ua.toLowerCase()+".*")){
			                    showMobileVersion = true;
			                }
			            }
					%>
					<div class="ui centered grid attached segment soft">
						<s:if test="%{adsInfos.size gte 0}">
							<s:iterator value="adsInfos" status="status">
								<div class="ui leaderboard ad" data-text="Advertisment">
									<a href="<s:property value="%{'http://' + customUrl}"/>" target="_blank" >
										<% if(showMobileVersion){%>
											<img  class="image ui centered" src="<s:property value="adsImgMobile" />">
										<% } else { %>
											<img  class="image ui centered" src="<s:property value="adsImg" />">
										<% } %>
									</a>
								</div>
							</s:iterator>
						</s:if>
						<s:if test="%{adsInfos.size() eq 0}">
							データかありません
						</s:if>
					</div>
				</div>
			</div>
		</div>
	</div><br />
	 <%@include file="/common/common_footer.jsp" %>
</div>
</body>
</html>