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
  <title>THAINIGHTNAVI.COM - <s:text name="global.main_menu_home" /></title>

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
  	padding: 0;
  	width: 100%;
  }
  .ui.leaderboard.ad img {
  	height: auto;
  }
  .ui.grid.segment.banner {
  	padding: 1em 0;
  }
  </style>

  <!--- Example Javascript -->
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
	  
	  			<div class="center aligned column">
		  			<div class="ui segment header">
						<h2 class="ui top header">
							<i class="bookmark icon yellow"></i>
							<div class="content"><s:text name="global.precautions_for_use" /></div>
						</h2>
					</div>
					<div class="ui grid attached segment">
						<div class="column one left aligned">
							<s:if test="#request.locale.language=='jp'">
								<s:text name="homeInfo.description"></s:text>
							</s:if>
							<s:else>
								<s:text name="homeInfo.descriptionEn"></s:text>
							</s:else>
				 		</div>
					</div>
				</div>
				
				<div class="center aligned column">
					<div class="ui segment header">
						<h2 class="ui top header">
							<i class="help circle icon blue"></i>
							<div class="content"><s:text name="global.over_18_years_old" /></div>
						</h2>
					</div>
					<div class="ui centered attached segment">
						<a href="<s:url value="/shoplist"/>" class="ui massive button green"><i class="checkmark icon"></i><s:text name="global.yes" /></a>
						<a href="#" class="ui massive button red"><i class="remove icon"></i><s:text name="global.no" /></a>
					</div>
				</div>
				
				<div class="center aligned column">
					<div class="ui segment header">
						<h2 class="ui top header">
							<i class="idea icon orange"></i>
							<div class="content"><s:text name="global.what_is_thainightnavi" /></div>
						</h2>
					</div>
					<div class="ui grid attached segment">
						<div class="column one left aligned">
							<s:if test="#request.locale.language=='jp'">
								<s:text name="homeInfo.description2"></s:text>
							</s:if>
							<s:else>
								<s:text name="homeInfo.descriptionEn2"></s:text>
							</s:else>
				 		</div>
					</div>
				</div>
				
  				<div class="center aligned column">
  					<div class="ui segment header banner">
						<h2 class="ui top header">
							<i class="announcement icon"></i>
							<div class="content"><s:text name="global.link_information" /></div>
						</h2>
					</div>
  					<div class="ui centered grid attached segment soft banner">
						<s:if test="%{adsInfos.size gte 0}">
							<s:iterator value="adsInfos" status="status">
								<div class="ui leaderboard ad" data-text="Advertisment">
									<a href="<s:property value="%{'http://' + customUrl}"/>" target="_blank" >
										<img  class="image ui centered" src="<s:property value="adsImg" />">
									</a>
								</div>
							</s:iterator>
						</s:if>
						<s:if test="%{adsInfos.size() eq 0}">
							<s:text name="global.no_data" />
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