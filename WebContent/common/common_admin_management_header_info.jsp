<div class="ui fixed top menu">
	<div class="left menu">
		<div class="header item">
			Administrator management
		</div>
	</div>
	<div class="right menu">
		<div class="header item">
			<i class="user icon"></i>Logged in user&nbsp;:&nbsp;<label class="ui large"> ${sessionScope.adminInfo.username}</label>
		</div>
		<div class="header item">
			<a class="ui tiny" href="<s:url value="/admin/logout"/>"><i class="sign out icon"></i>Logout</a>
		</div>
	</div>
</div>
<div class="ui menu"></div>