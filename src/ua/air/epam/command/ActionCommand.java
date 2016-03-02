package ua.air.epam.command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alex
 */

/**
 interface defines command to execute 
 */
public interface ActionCommand {
	String execute(HttpServletRequest request);
}
