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
  .pic .ui.image {
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
  .agentLogoImg {
  	width: 2em;
    height: 2em;
  }
    .girl-tag .ui.circular.label {
  	width: 25px;
  	height: 25px;
  	overflow: hidden;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 40%;
    text-wrap: balance;
    word-break: break-word;
  }
  .ui.list.girl-tag .ui.ui.ui.label {
  	width: 25px;
  	height: 25px;
  	overflow: hidden;
    font-size: 40%;
    text-wrap: balance;
    word-break: break-word;
    margin: auto;
  }
  .ui.list.girl-tag .content {
  	text-wrap: nowrap;
  }
  .ui.corner.label.verified .icon {
	top: 14px;
	right: -1px;
	transform: rotate(45deg);
	font-family: 'Lato', 'Helvetica Neue', Arial, Helvetica, sans-serif;
	font-size: 9px;
  }
  </style>
  <script type="text/javascript">
  	function getCustomDescription(obj) {
		var infoHtml = "";
		if (obj.agentInfo) {
			infoHtml += '		' + obj.agentInfo.agentName;
		}
		infoHtml += '		<br/>';
		return infoHtml;
	}
  </script>
  <!--- Example Javascript -->
</head>
<body>
<!-- Sidebar Menu -->
<s:set name="base_url" value="%{'shoplist/'}" />
<%@include file="/common/common_new_menu_sidebar.jsp" %>
<div class="pusher">
	<div class="ui segment very basic">
		<div class="ui centered grid">
			<div class="eleven wide column container" id="container">
				<%@include file="/common/common_statistic_info.jsp" %>
				<%@include file="/common/common_new_menu.jsp" %>

				<%@include file="/common/common_search.jsp" %>

				<div class="center aligned column">
					<div class="ui centered attached segment soft">
						<div class="ui list inverted girl-tag horizontal">
						<s:iterator value="girlTagInfos" >
							<div class="item">
							<div class="ui <s:property value="color" /> circular label">
							<s:property value="girlTagNameJp" />
							</div>
							<div class="content">
							<s:property value="girlTagNameJp" />
							</div>
							</div>
						</s:iterator>
						</div>
						<div class="ui centered four doubling cards ">
						<s:if test="%{girlInfos.size gte 0}">
							<s:iterator value="girlInfos" status="status">
								<div class="ui red card">
									<div class="ui left red corner label girl-tag"
										data-variation="tiny">
										<s:iterator value="girlTags" >
											<div class="ui <s:property value="primaryKey.girlTagInfo.color" /> circular label">
												<s:property value="primaryKey.girlTagInfo.girlTagNameJp" />
												<br />
											</div>
										</s:iterator>
									</div>
									<div class="image ui centered corner labeled pic" >
										<s:if test="allSame == 'true'">
											<div class="ui right corner label green verified">
												<i class="icon"><s:text name="global.all_same" /></i>
											</div>
										</s:if>
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
											<s:if test="chk40Mins == 'true'">
												<s:if test="priceIncall40Mins != 0">
													<s:text name="format.integer"><s:param name="value" value="priceIncall40Mins"/></s:text>
													<s:property value="crcy40Mins" />
												</s:if>
												<s:elseif test="priceOutcall40Mins != 0">
													<s:text name="format.integer"><s:param name="value" value="priceOutcall40Mins"/></s:text>
													<s:property value="crcy40Mins" />
												</s:elseif>
											</s:if>
											<s:elseif test="chk60Mins == 'true'">
												<s:if test="priceIncall60Mins != 0">
													<s:text name="format.integer"><s:param name="value" value="priceIncall60Mins"/></s:text>
													<s:property value="crcy60Mins" />
												</s:if>
												<s:elseif test="priceOutcall60Mins != 0">
													<s:text name="format.integer"><s:param name="value" value="priceOutcall60Mins"/></s:text>
													<s:property value="crcy60Mins" />
												</s:elseif>
											</s:elseif>
											<s:elseif test="chk90Mins == 'true'">
												<s:if test="priceIncall90Mins != 0">
													<s:text name="format.integer"><s:param name="value" value="priceIncall90Mins"/></s:text>
													<s:property value="crcy90Mins" />
												</s:if>
												<s:elseif test="priceOutcall90Mins != 0">
													<s:text name="format.integer"><s:param name="value" value="priceOutcall90Mins"/></s:text>
													<s:property value="crcy90Mins" />
												</s:elseif>
											</s:elseif>
											<s:elseif test="chk120Mins == 'true'">
												<s:if test="priceIncall120Mins != 0">
													<s:text name="format.integer"><s:param name="value" value="priceIncall120Mins"/></s:text>
													<s:property value="crcy120Mins" />
												</s:if>
												<s:elseif test="priceOutcall120Mins != 0">
													<s:text name="format.integer"><s:param name="value" value="priceOutcall120Mins"/></s:text>
													<s:property value="crcy120Mins" />
												</s:elseif>
											</s:elseif>
											<s:elseif test="chk6Hrs == 'true'">
												<s:if test="priceIncall6Hrs != 0">
													<s:text name="format.integer"><s:param name="value" value="priceIncall6Hrs"/></s:text>
													<s:property value="crcy6Hrs" />
												</s:if>
												<s:elseif test="priceOutcall6Hrs != 0">
													<s:text name="format.integer"><s:param name="value" value="priceOutcall6Hrs"/></s:text>
													<s:property value="crcy6Hrs" />
												</s:elseif>
											</s:elseif>
										</span>
									</div>
									<div class="content left aligned">
										<a class="ui header " href="<s:url value="/girl/%{girlInfoId}"/>">
											<s:property value="nickName" />
										</a>
										<div class="description">
											<s:property value="agentInfo.agentName" />
											<br/>
											<i class="marker icon"></i>
											<s:property value="countryInfo.countryNameJp" />
											<s:iterator value="girlProvinces" >
												<div class="ui medium label">
													<s:property value="primaryKey.provinceInfo.provinceNameJp" />
												</div>
											</s:iterator>
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
	</div><br />
	 <%@include file="/common/common_footer.jsp" %>
</div>
</body>
</html>