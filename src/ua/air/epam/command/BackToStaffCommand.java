package ua.air.epam.command;

import javax.servlet.http.HttpServletRequest;

import ua.air.epam.resource.ConfigurationManager;

public class BackToStaffCommand implements ActionCommand {

/**
 * redirect to staff	
 */
	
	@Override
	public String execute(HttpServletRequest request) {

		return ConfigurationManager.getProperty("path.page.staff");
	}

}
