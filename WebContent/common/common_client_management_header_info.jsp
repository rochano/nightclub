<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="ui fixed top menu">
	<div class="left menu">
		<div class="header item">
			<a href="<s:url value="/"/>">THAINIGHTNAVI.COM</a>
		</div>
	</div>
	<div class="right menu">
		<div class="ui dropdown item">
			<s:text name="global.logged_in_as" />&nbsp;&nbsp;
			<label class="ui large">
				<s:if test="#session.clientInfo != null">
					<s:property value="#session.clientInfo.nickName" />
				</s:if>
				<s:else>
					<s:property value="#session.userInfo.username" />
				</s:else>
			</label>
			<i class="dropdown icon"></i>
			<div class="menu">
				<div class="item">
					<a class="ui tiny" href="<s:url value="/management_client/changepassword"/>"><i class="edit icon"></i><s:text name="global.change_password" /></a>
				</div>
				<div class="item">
					<a class="ui tiny" href="<s:url value="/logout"/>"><i class="sign out icon"></i><s:text name="global.log_out" /></a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="ui menu"></div>