<div class="ui small text">
	<div class="ui container grid stackable">
		<div class="left floated eight wide column">
			<h4 class="ui huge header">
				Administrator management
			</h4>
		</div>
    	<div class="right floated right aligned eight wide column">
    		<i class="user icon"></i>Logged in user&nbsp;:&nbsp;<label class="ui large"> ${sessionScope.adminInfo.username}</label>
			|
			<a class="ui tiny" href="<s:url value="/admin/logout"/>"><i class="sign out icon"></i>Logout</a>
		</div>
	</div>
</div>