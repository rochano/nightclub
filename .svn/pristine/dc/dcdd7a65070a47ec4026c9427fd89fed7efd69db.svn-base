package net.viralpatel.contact.view;

import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.controller.ContactManager;
import net.viralpatel.contact.model.Contact;

import com.opensymphony.xwork2.ActionSupport;


public class CommonAction extends ActionSupport {
	
	Logger log_ = Logger.getLogger(this.getClass().getName());
	private String menu;
	
	public CommonAction() {}

	public String execute() {
		log_.info("menu >> " + menu);
		return SUCCESS;
	}
	
	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
}
