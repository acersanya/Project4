package ua.air.epam.command;

import javax.servlet.http.HttpServletRequest;

import ua.air.epam.resource.ConfigurationManager;

public class BackToAdminCommand implements ActionCommand {

/**
 * redirect to administrator page
 */
	@Override
	public String execute(HttpServletRequest request) {
		return ConfigurationManager.getProperty("path.page.admin");
	}
}
