package ua.air.epam.command.factory;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.air.epam.command.ActionCommand;
import ua.air.epam.command.EmptyCommand;
import ua.air.epam.command.client.CommandEnum;
/**
 * 
 * @author Alex
 *Action Factory for commands
 */
public class ActionFactory {

	private static Logger logger = Logger.getLogger(ActionFactory.class);

	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = new EmptyCommand();
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			logger.error("Technical Exception", e);
			request.setAttribute("wrongAction", "message.wrongaction");
		}
		return current;
	}
}