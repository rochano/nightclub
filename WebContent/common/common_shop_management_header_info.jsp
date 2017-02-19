<div class="ui fixed top menu">
	<div class="left menu">
		<div class="header item">
			Shop management
		</div>
	</div>
	<div class="right menu">
		<div class="header item">
			<i class="user icon"></i>Logged in user&nbsp;:&nbsp;<label class="ui large"> ${sessionScope.userInfo.username}</label>
		</div>
		<div class="header item">
			<a class="ui tiny" href="<s:url value="/shop/logout"/>"><i class="sign out icon"></i>Logout</a>
		</div>
	</div>
</div>
<div class="ui menu"></div>