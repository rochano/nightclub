<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="ui fixed top menu">
	<div class="left menu">
		<div class="header item">
			<s:text name="global.management" /><s:text name="global.administrator" />
		</div>
	</div>
	<div class="right menu">
		<div class="ui dropdown item">
			<s:text name="global.logged_in_as" />&nbsp;&nbsp;
			<label class="ui large"> ${sessionScope.adminInfo.username}</label>
			<i class="dropdown icon"></i>
			<div class="menu">
				<div class="item">
					<a class="ui tiny" href="<s:url value="/admin/logout"/>"><i class="sign out icon"></i><s:text name="global.log_out" /></a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="ui menu"></div>