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
  <title>THAINIGHTNAVI.COM - <s:text name="global.main_menu_how_to_use" /></title>

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
  </style>

  <!--- Example Javascript -->
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
						<div class="active section"><s:text name="global.main_menu_how_to_use" /></div>
					</div>
	
					<div class="center aligned column">
			  			<div class="ui segment header">
							<h2 class="ui top header">
								<i class="bookmark icon yellow"></i>
								<div class="content"><s:text name="global.precautions_for_use" /></div>
							</h2>
						</div>
						<div class="ui grid attached segment">
							<div class="column one left aligned">
								<%--<s:if test="#request.locale.language=='th'">
									<s:text name="homeInfo.descriptionEn"></s:text>
								</s:if>
								<s:else>--%>
									<s:text name="homeInfo.descriptionEn"></s:text>
								<%--</s:else>--%>
					 		</div>
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
								<%--<s:if test="#request.locale.language=='th'">
									<s:text name="homeInfo.descriptionEn2"></s:text>
								</s:if>
								<s:else>--%>
									<s:text name="homeInfo.descriptionEn2"></s:text>
								<%--</s:else>--%>
					 		</div>
						</div>
					</div>
					<%@include file="/common/common_ads_2.jsp" %>
				</div>
				<%@include file="/common/common_ads_3.jsp" %>
			</div>
		</div>
	</div><br />
	 <%@include file="/common/common_footer.jsp" %>
</div>
</body>
</html>