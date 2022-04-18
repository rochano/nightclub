<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="ui fixed top menu">
	<div class="left menu">
		<div class="header item">
			Shop management
		</div>
	</div>
	<div class="right menu">
		<div class="ui dropdown item">
			Logged in as&nbsp;&nbsp;
			<label class="ui large"> 
				<s:if test="#session.basicInfo != null">
					<s:if test="#request.locale.language=='th'">
						<s:property value="#session.basicInfo.shopNameEn" />
					</s:if>
					<s:else>
						<s:property value="#session.basicInfo.shopNameJp" />
					</s:else>
				</s:if>
				<s:else>
					<s:property value="#session.userInfo.username" />
				</s:else>
			</label>
			<i class="dropdown icon"></i>
			<div class="menu">
				<div class="item">
					<a class="ui tiny" href="<s:url value="/management/changepassword"/>"><i class="edit icon"></i>Change password</a>
				</div>
				<div class="item">
					<a class="ui tiny" href="<s:url value="/logout"/>"><i class="sign out icon"></i>Logout</a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="ui menu"></div>