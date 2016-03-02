package ua.air.epam.command;

import javax.servlet.http.HttpServletRequest;

import ua.air.epam.resource.ConfigurationManager;

public class BackToDispatcherCommand implements ActionCommand {
/**
 * redirect to dispatcher page
 */
	
	@Override
	public String execute(HttpServletRequest request) {
		return ConfigurationManager.getProperty("path.page.dispatcher");
	}

}
