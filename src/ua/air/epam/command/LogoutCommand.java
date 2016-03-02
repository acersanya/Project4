package ua.air.epam.command;

import javax.servlet.http.HttpServletRequest;

import ua.air.epam.resource.ConfigurationManager;
/**
 * 
 * @author Alex
 * logout redirects to the main page
 */
public class LogoutCommand implements ActionCommand {
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.index");
		request.getSession().invalidate();
		return page;
	}
}