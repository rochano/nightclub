<div class="ui fixed top menu">
	<div class="left menu">
		<div class="header item">
			Administrator management
		</div>
	</div>
	<div class="right menu">
		<div class="ui dropdown item">
			Logged in user&nbsp;&nbsp;<label class="ui large"> ${sessionScope.adminInfo.username}</label>
			<i class="dropdown icon"></i>
			<div class="menu">
				<div class="item">
					<a class="ui tiny" href="<s:url value="/admin/logout"/>"><i class="sign out icon"></i>Logout</a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="ui menu"></div>