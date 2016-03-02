package ua.air.epam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.air.epam.command.ActionCommand;
import ua.air.epam.command.factory.ActionFactory;
import ua.air.epam.pool.ConnectionPool;
/**
 * Servlet implementation
 * Performs and executes command which are got from Factory.
 * @author Alex
 *
 */
public class Controller extends HttpServlet {

	private static Logger logger = Logger.getLogger(Controller.class);
	private static final long serialVersionUID = -247767155410348813L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String log4j = config.getInitParameter("log4j-pass");
		String path = getServletContext().getRealPath(log4j);
		PropertyConfigurator.configure(path);
	}

	protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws IOException {processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		processRequest(request, response);
	}

	/**
	 * 
	 * @param request  from client
	 * @param response  to client
	 * @throws IOException
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String page = null;
		ActionFactory actionFactory = new ActionFactory();
		ActionCommand command = actionFactory.defineCommand(request);
		try {
			page = command.execute(request);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("TechnicalException", e);
			response.sendError(500);
		}
	}
	/**
	 * close connection
	 */

	@Override
	public void destroy() {
		super.destroy();
		ConnectionPool.getInstance().cleanUp();
	}

}
