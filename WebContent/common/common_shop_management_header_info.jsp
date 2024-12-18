<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="ui fixed top menu">
	<div class="left menu">
		<div class="header item">
			<s:text name="global.management" />
		</div>
	</div>
	<div class="right menu">
		<div class="ui dropdown item">
			<s:text name="global.logged_in_as" />&nbsp;&nbsp;
			<label class="ui large"> 
				<s:if test="#session.basicInfo != null">
					<s:property value="#session.basicInfo.shopNameEn" />
				</s:if>
				<s:else>
					<s:property value="#session.userInfo.username" />
				</s:else>
			</label>
			<i class="dropdown icon"></i>
			<div class="menu">
				<div class="item">
					<a class="ui tiny" href="<s:url value="/management/howtoinput"/>"><i class="file outline icon"></i><s:text name="global.how_to_input" /></a>
				</div>
				<div class="item">
					<a class="ui tiny" href="<s:url value="/management/changepassword"/>"><i class="edit icon"></i><s:text name="global.change_password" /></a>
				</div>
				<div class="item">
					<a class="ui tiny" href="<s:url value="/logout"/>"><i class="sign out icon"></i><s:text name="global.log_out" /></a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="ui menu"></div>