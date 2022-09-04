<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="ui fixed top menu">
	<div class="left menu">
		<div class="header item">
			<s:i18n name="global_th"><s:text name="global.management" /><s:text name="global.administrator" /></s:i18n>
		</div>
	</div>
	<div class="right menu">
		<div class="ui dropdown item">
			<s:i18n name="global_th"><s:text name="global.logged_in_as" /></s:i18n>&nbsp;&nbsp;
			<label class="ui large"> ${sessionScope.adminInfo.username}</label>
			<i class="dropdown icon"></i>
			<div class="menu">
				<div class="item">
					<a class="ui tiny" href="<s:url value="/admin/logout"/>"><i class="sign out icon"></i><s:i18n name="global_th"><s:text name="global.log_out" /></s:i18n></a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="ui menu"></div>