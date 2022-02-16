<div class="ui fixed top menu">
	<div class="left menu">
		<div class="header item">
			<a href="<s:url value="/"/>">THAINIGHTNAVI.COM</a>
		</div>
	</div>
	<div class="right menu">
		<div class="ui dropdown item">
			Logged in user&nbsp;&nbsp;<label class="ui large"> ${sessionScope.userInfo.username}</label>
			<i class="dropdown icon"></i>
			<div class="menu">
				<div class="item">
					<a class="ui tiny" href="<s:url value="/management_client/changepassword"/>"><i class="edit icon"></i>Change password</a>
				</div>
				<div class="item">
					<a class="ui tiny" href="<s:url value="/logout"/>"><i class="sign out icon"></i>Logout</a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="ui menu"></div>