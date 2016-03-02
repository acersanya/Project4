package ua.air.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.air.epam.dao.FlightDAO;
import ua.air.epam.dao.FlightDAOImpl;
import ua.air.epam.dao.UserDAO;
import ua.air.epam.dao.UserDAOImpl;
import ua.air.epam.entity.Flight;
import ua.air.epam.entity.Plane;
import ua.air.epam.entity.Role;
import ua.air.epam.entity.User;
import ua.air.epam.exception.DAOException;
import ua.air.epam.resource.ConfigurationManager;
/**
 * 
 * @author Alex
 * 
 * LoginComannd only for users(admin and dispatcher)
 * administator can create flight and create new employees. 
 * dispatcher can set crew on flight
 */
public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static Logger logger = Logger.getLogger(LoginCommand.class);

	
	/**
	 * checks in db(user table) if users are created (dispatcher and admin)
	 * and checks their role: admin or dispatcher
	 * if no such users throws DaoException(),
	 * after login set's session attributes;
	 */
	@Override
	public String execute(HttpServletRequest request) {

		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		String page = null;
		try {
			UserDAO userDAO = new UserDAOImpl();
			User body = userDAO.findUser(login, pass);
			String user = body.getName() + " " + body.getSurname();
			request.getSession().setAttribute("user", user);
			FlightDAO flightDAO = new FlightDAOImpl();
			List<Flight> flights = flightDAO.findAllFlights();
			request.getSession().setAttribute("flights", flights);
			Role role = body.getRole();
			switch (role) {
			case ADMIN:
				FlightDAO fd = new FlightDAOImpl();
				List<Plane> planes = fd.findAllPlanes();
				request.getSession().setAttribute("planes", planes);
				page = ConfigurationManager.getProperty("path.page.admin");
				break;
			case DISPATCHER:
				page = ConfigurationManager.getProperty("path.page.dispatcher");
				break;
			default:
				page = ConfigurationManager.getProperty("path.page.index");
			}
		} catch (DAOException e) {
			request.setAttribute("errorLoginPassMessage", "login.error");
			page = ConfigurationManager.getProperty("path.page.login");
			logger.error("TechnicalException", e);
		}
		return page;
	}
}