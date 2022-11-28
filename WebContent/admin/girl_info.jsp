<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title><s:i18n name="global_th">
		<s:text name="global.management" />
		<s:text name="global.administrator" /> - <s:text
			name="global.menu_girls" />
	</s:i18n></title>

<%@include file="/common/common_admin_management_header.jsp"%>

<style>
body {
	padding: 1em;
}
</style>

<!--- Example Javascript -->
<script>
	$(document).ready(function() {
		$('.ui.menu .ui.dropdown').dropdown({
			on : 'hover'
		});
		$('.ui.dropdown').dropdown();
		$('.ui.menu a.item').on('click', function() {
			$(this).addClass('active').siblings().removeClass('active');
		});
		$('.message .close').on('click', function() {
			$(this).closest('.message').transition('fade');
		});
		$("table").tablesort();
		$('.ui.rating').rating({});
	});
</script>
</head>
<body class="menu pushable">
	<%@include file="/common/common_admin_management_header_info.jsp"%>
	<!-- Sidebar Menu -->
	<div class="ui toc vertical top large inverted sidebar menu">
		<a class="ui toc item title"> <i class="cancel icon"></i> &nbsp;
		</a>
		<%@include file="/common/common_admin_management_menu.jsp"%>
	</div>
	<div class="pusher">
		<div class="full height">
			<div class="toc">
				<!-- Sidebar Menu -->
				<div class="ui inverted vertical menu">
					<%@include file="/common/common_admin_management_menu.jsp"%>
				</div>
			</div>
			<div class="article">
				<div class="ui segment very basic container">
					<div class="ui menu inverted stackable toc left aligned">
						<a class="toc item"><i class="sidebar icon"></i></a>
					</div>
					<s:if test="hasActionMessages()">
						<div class="ui success message green">
							<i class="close icon"></i>
							<div class="header">
								<s:actionmessage cssClass="list" />
							</div>
						</div>
					</s:if>

					<div class="ui breadcrumb segment inverted">
						<a class="section" href="javascript:history.back()"> <s:text
								name="global.menu_girls" />
						</a> <i class="right chevron icon divider"></i>
						<div class="active section">
							<s:property value="girlInfo.nickName" />
						</div>
					</div>

					<div class="ui accordion">
						<h4 class="ui top attached header inverted active title">
							<i class="dropdown icon"></i>
							<s:i18n name="global_th">
								<s:text name="global.comment" />
							</s:i18n>
						</h4>
						<div class="ui centered grid attached segment active content">
							<div class="column one left aligned">
								<table id="searchList"
									class="ui table celled compact striped unstackable sortable">
									<thead class="center aligned">
										<tr>
											<th width="5%">#</th>
											<th width="10%"><s:i18n name="global_th">
													<s:text name="global.comment_by" />
												</s:i18n></th>
											<th width="20%"><s:i18n name="global_th">
													<s:text name="global.date" />
												</s:i18n></th>
											<th width="10%"><s:i18n name="global_th">
													<s:text name="global.rating" />
												</s:i18n></th>
											<th><s:i18n name="global_th">
													<s:text name="global.detail" />
												</s:i18n></th>
										</tr>
									</thead>
								</table>

							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="/common/common_admin_management_footer.jsp"%>
		</div>
	</div>
	<script type="text/javascript">
		<s:iterator value="girlComments" status="status">
		dataSet
				.push([
						'<s:property value="#status.count" />',
						'<s:property value="createdBy" />',
						'<s:text name="text.datetime.format"><s:param value="createdDate"/></s:text>',
						'<div class="ui yellow rating disabled" data-icon="star" data-rating="<s:property value="rating" />" data-max-rating="<s:property value="rating" />" ></div>',
						'<s:property value="comment" />', ]);
		</s:iterator>
		columnDefs = [ {
			className : "center aligned",
			targets : [ 0, 1, 2, 3 ]
		} ];
	</script>
</body>
</html>