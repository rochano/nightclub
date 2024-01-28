<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="footer" class="ui grid centered segment inverted">
	<div  class="one column center aligned ">
		<div><p>
			<%-- <a href="<s:url value="/"/>" class="ui item"><s:text name="global.main_menu_home" /></a> |
			<a href="#" class="ui item"><s:text name="global.main_menu_service_shop" /></a> | --%>
			<%-- <a href="<s:url value="/agents"/>" class="ui item"><s:text name="global.main_menu_agents" /></a> |
			<a href="<s:url value="/freeagents"/>" class="ui item"><s:text name="global.main_menu_free_agents" /></a> |
			<a href="<s:url value="/engirls"/>" class="ui item"><s:text name="global.main_menu_en_girls" /></a> | --%>
			<%-- <a href="<s:url value="/search"/>" class="ui item"><s:text name="global.main_menu_search" /></a> |
			<a href="<s:url value="/contact"/>" class="ui item"><s:text name="global.main_menu_contact" /></a> | --%>
			<a href="<s:url value="/howtouse"/>" class="ui item"><s:text name="global.main_menu_how_to_use" /></a> |
			<a href="<s:url value="/signup"/>" class="ui item"><s:text name="global.main_menu_register" /></a> |
			<a href="<s:url value="/login"/>" class="ui item"><s:text name="global.main_menu_login" /></a>
		</p></div>
		<p class="copy">COPYRIGHT(C) ALL RIGHTS RESERVED.</p>
	</div>
</div>
<div class="ui bottom cookie sidebar">
	<div class="ui vertical inverted center aligned segment">
		This site uses cookies. By continuing to use this site you agree with our cookie policy.
		<div class="ui borderless pipe"></div>
		<button class="ui small inverted compact marginless accept button">Got it</button>
	</div>
</div>
<%-- <div class="ui basic modal">
  <div class="ui icon header">
    <i class="language icon"></i>
    Default Language
  </div>
  <div class="content">
    <p>Please select default language below</p>
  </div>
  <div class="actions">
    <div class="ui large buttons">
		<a class="ui blue button" href="<s:url value="?request_cookie_locale=jp"/>">
			<i class="jp flag"></i>
			JP
		</a>
		<a class="ui orange button" href="<s:url value="?request_cookie_locale=th"/>">
			<i class="th flag"></i>
			TH
		</a>
	</div>
  </div>
</div> --%>