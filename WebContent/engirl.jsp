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
  <title>THAINIGHTNAVI.COM - <s:text name="global.main_menu_en_girls" /></title>
  <s:set var="userType"><%=IConstants.USER_TYPE_EN_GIRL%></s:set>
  <%@include file="/common/common_header.jsp" %>
  <!--- Example CSS -->
  <style>
  .ui.table th, .ui.table tr:nth-child(even) td{
  	font-size: 12px;
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
  .ui.image {
	width: auto;
	height: 160px;
  }
  .ui.image img {
	height: 100%;
  }
  .ui.cards > .card > .content, .ui.card > .content {
  	flex-grow: 0;
  	-webkit-flex-grow: 0;
  	-ms-flex-positive: 0;
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
	});
	
  	function getPrice(obj) {
  		return obj.price.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",");;
	}

  	function getCustomDescription(obj) {
		var infoHtml = "";
		infoHtml += '<s:text name="global.job" /> : ';
		if (obj.type == '1') {
			infoHtml += '<s:text name="global.en_girl_type_1" />';
		}
		if (obj.type == '2') {
			infoHtml += '<s:text name="global.en_girl_type_2" />';
		}
		if (obj.type == '3') {
			infoHtml += '<s:text name="global.en_girl_type_3" />';
		}
		infoHtml += '<br/>';
		return infoHtml;
	}
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
					<div class="active section"><s:text name="global.main_menu_en_girls" /></div>
				</div>
				
				<%@include file="/common/common_search.jsp" %>

				<div class="center aligned column">
					<div class="ui centered attached segment soft">
						<div class="ui centered four doubling cards ">
						<s:if test="%{girlInfos.size gte 0}">
							<s:iterator value="girlInfos" status="status">
								<div class="ui red card">
									<div class="image ui centered corner labeled pic" >
										<a class="ui right corner label toggleFavourite link" 
												data-girlInfoId="<s:property value="girlInfoId" />"
												data-content="Please login first" data-variation="tiny">
											<i class="heart 
												<s:if test="girlFavourites.indexOf(girlInfoId) != -1">red</s:if>
												<s:else>outline</s:else>
												icon"></i>
										</a>
										<a href="<s:url value="/girl/%{girlInfoId}"/>" >
											<s:if test="pic1 != null && pic1 != ''">
												<img class="image ui centered" src="<s:property value="pic1" />">
											</s:if>
											<s:elseif test="pic2 != null && pic2 != ''">
												<img class="image ui centered" src="<s:property value="pic2" />">
											</s:elseif>
											<s:elseif test="pic3 != null && pic3 != ''">
												<img class="image ui centered" src="<s:property value="pic3" />">
											</s:elseif>
											<s:elseif test="pic4 != null && pic4 != ''">
												<img class="image ui centered" src="<s:property value="pic4" />">
											</s:elseif>
											<s:elseif test="pic5 != null && pic5 != ''">
												<img class="image ui centered" src="<s:property value="pic5" />">
											</s:elseif>
											<s:else>
												<img class="image ui centered" src="<s:url value="/assets/images/wireframe/square-image.png" />">
											</s:else>
										</a>
									</div>
									<div class="content left aligned label pink circular ui">
										<span class="right floated">
											<s:text name="format.integer"><s:param name="value" value="price"/></s:text>
										</span>
										<s:if test="allSame == 'true'">
											<s:text name="global.all_same" />
										</s:if>
									</div>
									<div class="content left aligned">
										<a class="ui header " href="<s:url value="/girl/%{girlInfoId}"/>"><s:property value="nickName" /></a>
										<div class="description">
											<s:text name="global.job" /> : 
											<s:if test="type == 1"><s:text name="global.en_girl_type_1" /></s:if>
											<s:if test="type == 2"><s:text name="global.en_girl_type_2" /></s:if>
											<s:if test="type == 3"><s:text name="global.en_girl_type_3" /></s:if>
											<br/>
											<i class="marker icon"></i>
											<%--<s:if test="#request.locale.language=='th'">
												<s:iterator value="girlLocations" >
													<div class="ui medium label">
														<s:property value="primaryKey.zoneInfo.zoneNameEn" />
													</div>
												</s:iterator>
											</s:if>
											<s:else>--%>
												<s:iterator value="girlLocations" >
													<div class="ui medium label">
														<s:property value="primaryKey.zoneInfo.zoneNameJp" />
													</div>
												</s:iterator>
											<%--</s:else>--%>
										</div>
									</div>
								</div>
							</s:iterator>
						</s:if>
						<s:if test="%{girlInfos.size eq 0}">
							<s:text name="global.no_data" />
						</s:if>
						</div>
						<s:if test="%{girlInfos.size gt 0}">
							<br />
							<button class="centered ui basic inverted button loadMore">LOAD MORE</button>
						</s:if>
					</div>
				</div>
			    
			</div>
		</div>
	</div>
	<%@include file="/common/common_footer.jsp" %>
</div>
</body>
</html>