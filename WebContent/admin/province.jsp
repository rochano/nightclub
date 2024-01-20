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
  <title><s:i18n name="global_th"><s:text name="global.management" /><s:text name="global.administrator" /> - <s:text name="global.province" /></s:i18n></title>

  <%@include file="/common/common_admin_management_header.jsp" %>

  <style>
  body {
    padding: 1em;
  }
  .ui.menu:last-child {
    margin-bottom: 110px;
  }
  .ui.items > .item {
	display: inline-block;
	width: auto;
  }
  .ui.items > .item > .image {
	margin: auto;
  }
  .ui.items > .item > .image + .content {
	display: block;
    padding: 1.5em 0em 0em;
  }
  .ui.form .inline.field>:first-child, .ui.form .inline.fields>label:first-child {
  	width: 150px;
  	margin: 0;
  }
  </style>

  <!--- Example Javascript -->
  <script>
  $(document)
    .ready(function() {
      $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
      });
      $('.ui.dropdown')
      .dropdown()
    ;
      $('.ui.menu a.item')
        .on('click', function() {
          $(this)
            .addClass('active')
            .siblings()
            .removeClass('active')
          ;
        })
      ;
      $('.ui.modal').modal({
          onApprove : function() {
            //Submits the semantic ui form
            //And pass the handling responsibilities to the form handlers,
            // e.g. on form validation success
            $('#infoForm').submit();
            //Return false as to not close modal dialog
            return false;
          }
      });
      $('.message .close')
      .on('click', function() {
        $(this)
          .closest('.message')
          .transition('fade')
        ;
      })
    ;
      $(".addbtn")
		.bind('click', function() {
		  $('.ui.modal .header:first').text("<s:i18n name="global_th"><s:text name="global.add_information" /><s:text name="global.province" /></s:i18n>");
		  $('#infoForm').find("input[type=text], input[type=hidden], textarea").val("");
		  $('#infoForm')[0].action.value = "add";
		  <s:if test="%{menu.equalsIgnoreCase('domestic')}">
		  	$('#infoForm')[0].action = "<s:url value="/admin/domestic/add"/>";
		  	$('#provinceInfo_countryInfoId').val("<s:property value="countryInfoId" />");
		  </s:if>
		  <s:elseif test="%{menu.equalsIgnoreCase('overseas')}">
		  	$('#infoForm')[0].action = "<s:url value="/admin/overseas/%{countryInfoId}/province/add"/>";
		  	$('#provinceInfo_countryInfoId').val("<s:property value="countryInfoId" />");
		  </s:elseif>
		  <s:else>
		  	$('#infoForm')[0].action = "<s:url value="/admin/province/add"/>";
		  </s:else>
        $('.ui.modal')
		    .modal('show')
		  ;
      });
      $('#infoForm.ui.form')
      .form({
          fields: {},
       })
      ;
      $('#searchForm.ui.form')
      .form({
          fields: {}
      })
      ;
	  $("table").tablesort();
      <s:if test="showInfo">
	  $('.ui.modal')
	    .modal('show')
	  ;
	  </s:if>
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
		  	<s:if test="%{menu.equalsIgnoreCase('overseas')}">
				<div class="ui breadcrumb segment attached inverted">
					<a class="section" href="<s:url value="/admin/overseas" />" >
						<s:i18n name="global_th"><s:text name="global.country" />(<s:text name="global.overseas" />)</s:i18n>
					</a>
					<i class="right chevron icon divider"></i>
					<div class="active section"><s:property value="countryInfo.countryNameEn" /></div>
				</div>
		  	</s:if>
			<s:if test="hasActionMessages()">
				<div class="ui success message green">
					<i class="close icon"></i>
					<div class="header">
						<s:actionmessage cssClass="list" />
					</div>
				</div>
			</s:if>
			<s:if test="hasActionErrors()">
				<div class="ui error message">
					<i class="close icon"></i>
					<div class="header">
						<s:actionerror cssClass="list" />
					</div>
				</div>
			</s:if>
			<div class="ui accordion">
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th">
						<s:text name="global.search_condition" />
					</s:i18n>
				</h4>
				<div class="ui left aligned attached segment active content">
					<form class="ui form" id="searchForm" method="post" 
					<s:if test="%{menu.equalsIgnoreCase('domestic')}">
						action="<s:url value="/admin/domestic/search"/>"
					</s:if>
					<s:elseif test="%{menu.equalsIgnoreCase('overseas')}">
					  	action="<s:url value="/admin/overseas/%{countryInfoId}/province/search"/>"
					</s:elseif>
					<s:else>
						action="<s:url value="/admin/province/search"/>"
					</s:else>
					>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="provinceSearch.provinceNameJp" key="global.japanese_name"/></s:i18n>
						</div>
						<div class="inline field">
							<s:i18n name="global_th"><s:textfield name="provinceSearch.provinceNameEn" key="global.english_name"/></s:i18n>
						</div>
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="ui small button submit blue"><s:i18n name="global_th"><s:text name="global.search" /></s:i18n></div>
								<div class="ui small button clear"><s:i18n name="global_th"><s:text name="global.clear" /></s:i18n></div>
							</div>
						</div>
					</form>
				</div>
				<h4 class="ui top attached header inverted active title">
					<i class="dropdown icon"></i>
					<s:i18n name="global_th"><s:text name="global.province" /></s:i18n>
				</h4>
				<div class="ui centered grid attached segment active content">
					<div class="column one left aligned">
						<div class="ui right aligned one column grid">
							<div class="column">
								<div class="addbtn ui small button blue"><s:i18n name="global_th"><s:text name="global.add" /></s:i18n></div>
							</div>
						</div>
						<table id="searchList" class="ui table celled compact striped unstackable sortable">
							<thead class="center aligned">
								<tr>
									<th>#</th>
									<th><s:i18n name="global_th"><s:text name="global.japanese_name" /></s:i18n></th>
									<th><s:i18n name="global_th"><s:text name="global.english_name" /></s:i18n></th>
									<s:if test="%{menu.equalsIgnoreCase('province')}">
										<th><s:i18n name="global_th"><s:text name="global.country" /></s:i18n></th>
									</s:if>
									<th><s:i18n name="global_th"><s:text name="global.operation" /></s:i18n></th>
								</tr>
							</thead>
							<tfoot class="full-width">
								<tr>
									<s:if test="%{menu.equalsIgnoreCase('province')}">
										<th colspan="5">
									</s:if>
									<s:else>
										<th colspan="4">
									</s:else>
										<div class="ui right aligned one column grid">
											<div class="column">
												<div class="addbtn ui small button blue"><s:i18n name="global_th"><s:text name="global.add" /></s:i18n></div>
											</div>
										</div>
									</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
  	</div>
  	</div>
  	<%@include file="/common/common_admin_management_footer.jsp" %>  
</div>
</div>

<div class="ui modal">
  <i class="close icon"></i>
  <div class="header">
    <s:i18n name="global_th"><s:text name="global.edit_information" /><s:text name="global.province" /></s:i18n>
  </div>
  <div class="content">
    <form class="ui form" id="infoForm" method="post" 
    	<s:if test="%{menu.equalsIgnoreCase('domestic')}">
			action="<s:url value="/admin/domestic/update"/>"
		</s:if>
		<s:elseif test="%{menu.equalsIgnoreCase('overseas')}">
			action="<s:url value="/admin/overseas/%{countryInfoId}/province/update"/>"
		</s:elseif>
		<s:else>
			action="<s:url value="/admin/province/update"/>"
		</s:else>
    >
		<div class="inline field">
			<s:i18n name="global_th"><s:textfield name="provinceInfo.provinceNameJp" key="global.japanese_name" /></s:i18n>
		</div>
		<div class="inline field">
			<s:i18n name="global_th"><s:textfield name="provinceInfo.provinceNameEn" key="global.english_name" /></s:i18n>
		</div>
		<s:if test="%{!''.equals(countryInfoId)}">
			<s:hidden name="provinceInfo.countryInfoId" value="%{countryInfoId}"></s:hidden>
		</s:if>
		<s:else>
			<div class="inline field">
				<s:i18n name="global_th">
					<s:select list="countryInfos"
						listKey="countryInfoId" listValue="countryNameEn"
						key="global.country" 
						cssClass="ui search dropdown" 
						name="provinceInfo.countryInfoId">
					</s:select>
				</s:i18n>
			</div>
		</s:else>
		<s:hidden name="action" value="update"></s:hidden>
		<s:hidden name="provinceInfo.provinceInfoId"></s:hidden>
		<div class="ui error message"></div>
	</form>
  </div>
  <div class="actions">
    <div class="ui approve blue button"><s:i18n name="global_th"><s:text name="global.save" /></s:i18n></div>
    <div class="ui cancel button"><s:i18n name="global_th"><s:text name="global.cancel" /></s:i18n></div>
  </div>
</div>
  <script type="text/javascript">
  <s:iterator value="provinceInfos" status="status">
	dataSet.push(
		['<s:property value="#status.count" />', 
		"<s:property value="provinceNameJp" />",
		"<s:property value="provinceNameEn" />",
		<s:if test="%{menu.equalsIgnoreCase('province')}">
			"<s:property value="countryInfo.countryNameEn" />",
		</s:if>
		'<div class="ui buttons">' +
			<s:if test="%{menu.equalsIgnoreCase('domestic')}">
				'<a href="<s:url value="/admin/domestic/edit/%{provinceInfoId}"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>' +
				'<a href="<s:url value="/admin/domestic/%{provinceInfoId}/zone"/>" class="ui icon button small green" ><i class="ui icon compass"></i></a>' +
				'<a href="<s:url value="/admin/domestic/delete/%{provinceInfoId}"/>" class="ui icon button small red" ><i class="ui icon delete"></i></a>' +
			</s:if>
			<s:elseif test="%{menu.equalsIgnoreCase('overseas')}">
				'<a href="<s:url value="/admin/overseas/%{countryInfoId}/province/edit/%{provinceInfoId}"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>' +
				'<a href="<s:url value="/admin/overseas/%{countryInfoId}/province/%{provinceInfoId}/zone"/>" class="ui icon button small green" ><i class="ui icon compass"></i></a>' +
				'<a href="<s:url value="/admin/overseas/%{countryInfoId}/province/delete/%{provinceInfoId}"/>" class="ui icon button small red" ><i class="ui icon delete"></i></a>' +
			</s:elseif>
			<s:else>
				'<a href="<s:url value="/admin/province/edit/%{provinceInfoId}"/>" class="ui icon button small blue" ><i class="ui icon edit"></i></a>' +
				'<a href="<s:url value="/admin/province/delete/%{provinceInfoId}"/>" class="ui icon button small red" ><i class="ui icon delete"></i></a>' +
			</s:else>
		'</div>'
	]);
  </s:iterator>
	columnDefs = [
	  {  className: "center aligned", 
		  targets: [ 0
<s:if test="%{menu.equalsIgnoreCase('province')}">, 4</s:if>
<s:else>, 3</s:else>
		   ] }
	];
  </script>
</body>
</html>