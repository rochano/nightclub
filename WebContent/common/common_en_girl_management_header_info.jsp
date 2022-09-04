<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="ui fixed top menu">
	<div class="left menu">
		<div class="header item">
			<s:i18n name="global_th"><s:text name="global.management" /></s:i18n>
		</div>
	</div>
	<div class="right menu">
		<div class="ui dropdown item">
			<s:i18n name="global_th"><s:text name="global.logged_in_as" /></s:i18n>&nbsp;&nbsp;
			<label class="ui large">
				<s:if test="#session.enGirlInfo != null">
					<s:property value="#session.enGirlInfo.nickName" />
				</s:if>
				<s:else>
					<s:property value="#session.userInfo.username" />
				</s:else>
			</label>
			<i class="dropdown icon"></i>
			<div class="menu">
				<div class="item">
					<a class="ui tiny" href="<s:url value="/management_en_girl/howtoinput"/>"><i class="file outline icon"></i><s:i18n name="global_th"><s:text name="global.how_to_input" /></s:i18n></a>
				</div>
				<div class="item">
					<a class="ui tiny" href="<s:url value="/management_en_girl/changepassword"/>"><i class="edit icon"></i><s:i18n name="global_th"><s:text name="global.change_password" /></s:i18n></a>
				</div>
				<div class="item">
					<a class="ui tiny" href="<s:url value="/logout"/>"><i class="sign out icon"></i><s:i18n name="global_th"><s:text name="global.log_out" /></s:i18n></a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="ui menu"></div>