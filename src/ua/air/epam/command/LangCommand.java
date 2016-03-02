package ua.air.epam.command;

import javax.servlet.http.HttpServletRequest;

import ua.air.epam.resource.ConfigurationManager;

public class LangCommand implements ActionCommand {
	private static final String PARAM_NAME_LANGUAGE = "language";

/**
 * Localization: supports russian and english language, ukrainian is not
 * currently implemented.
 */
	@Override
	public String execute(HttpServletRequest request) {		
		String page = ConfigurationManager.getProperty("path.page.login");
		String language = request.getParameter(PARAM_NAME_LANGUAGE);
		switch (language) {
		case "en":
			request.getSession().setAttribute("lang", "en");
			break;
		case "ru":
			request.getSession().setAttribute("lang", "ru");
			break;
		}
		return page;
	}
}
