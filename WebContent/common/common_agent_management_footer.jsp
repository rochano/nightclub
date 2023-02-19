<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="footer" class="ui grid centered segment inverted">
	<div  class="one column center aligned ">
		<div><p>
			<a href="<s:url value="/management_agent"/>"><span><s:i18n name="global_th"><s:text name="global.menu_home" /></s:i18n></span></a> |
			<a href="<s:url value="/management_agent/agentinfo"/>"><span><s:i18n name="global_th"><s:text name="global.menu_agent_info" /></s:i18n></span></a> |
			<a href="<s:url value="/management_agent/girls"/>"><span><s:i18n name="global_th"><s:text name="global.menu_girls" /></s:i18n></span></a>
			<%-- <s:if test="homeInfo.lineNotifyActive == 'true'"> --%>
			 |
			<a href="<s:url value="/management_agent/reserve"/>"><span><s:i18n name="global_th"><s:text name="global.menu_reserve" /></s:i18n></span></a>
			<%-- </s:if> --%>
		</p></div>
		<p class="copy">COPYRIGHT(C) ALL RIGHTS RESERVED.</p>
	</div>
</div>