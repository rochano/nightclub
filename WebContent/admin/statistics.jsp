<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title><s:i18n name="global_th"><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.menu_statistics" /></s:i18n></title>

  <%@include file="/common/common_admin_management_header.jsp" %>
  <script type="text/javascript" src="<s:url value="/assets/library/Chart.js"/>"></script>
<script type="text/javascript">
  $(document)
    .ready(function() {
    	$('.ui.form')
        .form({
        	fields: {}
        })
        ;
        $('.message .close')
        .on('click', function() {
          $(this)
            .closest('.message')
            .transition('fade')
          ;
        })
      ;
    })
  ;
</script>
</head>
<body class="menu pushable">
<%@include file="/common/common_admin_management_header_info.jsp" %>
<!-- Sidebar Menu -->
<div class="ui toc vertical top large inverted sidebar menu">
  	<a class="ui toc item title">
		<i class="cancel icon"></i>
		&nbsp;
	</a>
	<%@include file="/common/common_admin_management_menu.jsp" %>
</div>
<div class="pusher">
<div class="full height">
<div class="toc">
	<!-- Sidebar Menu -->
	<div class="ui inverted vertical menu">
		<%@include file="/common/common_admin_management_menu.jsp" %>
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
			<div class="ui accordion">
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th"><s:text name="global.statistics_view" /></s:i18n>
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<canvas id="totalViewChart" style="width:100%"></canvas>
					</div>
				</div>
			</div>
			<div class="ui accordion">
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th"><s:text name="global.statistics_created_agent_girl" /></s:i18n>
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<canvas id="createdAgentGirlChart" style="width:100%"></canvas>
					</div>
				</div>
			</div>
			<div class="ui accordion">
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th"><s:text name="global.statistics_created_independent_girl" /></s:i18n>
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<canvas id="createdIndependentGirlChart" style="width:100%"></canvas>
					</div>
				</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_admin_management_footer.jsp" %>  
</div>
</div>
<script>
var xValues = [<s:text name="statisticsAccessDateList" />];
var yViewValues = [<s:text name="statisticsViewList" />];
var yCreatedCountAgentGirlValues = [<s:text name="statisticsCreatedCountAgentGirlList" />];
var yCreatedCountIndependentGirlValues = [<s:text name="statisticsCreatedCountIndependentGirlList" />];

new Chart("totalViewChart", {
  type: "line",
  data: {
    labels: xValues,
    datasets: [{
      fill: false,
      lineTension: 0,
      borderColor: "rgb(101 181 255)",
      data: yViewValues
    }]
  },
  options: {
	responsive: true,
    legend: {display: false},
  }
});

new Chart("createdAgentGirlChart", {
  type: "line",
  data: {
    labels: xValues,
    datasets: [{
      fill: false,
      lineTension: 0,
      borderColor: "rgb(101 181 255)",
      data: yCreatedCountAgentGirlValues
    }]
  },
  options: {
	responsive: true,
    legend: {display: false},
  }
});

new Chart("createdIndependentGirlChart", {
  type: "line",
  data: {
    labels: xValues,
    datasets: [{
      fill: false,
      lineTension: 0,
      borderColor: "rgb(101 181 255)",
      data: yCreatedCountIndependentGirlValues
    }]
  },
  options: {
	responsive: true,
    legend: {display: false},
  }
});
</script>
</body>
</html>