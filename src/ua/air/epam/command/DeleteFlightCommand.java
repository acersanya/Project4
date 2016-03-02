package ua.air.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.air.epam.dao.FlightDAO;
import ua.air.epam.dao.FlightDAOImpl;
import ua.air.epam.entity.Flight;
import ua.air.epam.exception.DAOException;
import ua.air.epam.resource.ConfigurationManager;

public class DeleteFlightCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(DeleteFlightCommand.class);
	private static final String PARAM_NAME_DELETED_FLIGHT_ID = "delFlightId";
/**
 * Deleting current flight
 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.admin");
		String id = request.getParameter(PARAM_NAME_DELETED_FLIGHT_ID);
		if (id == null) {
			return page;
		}
		int flightId = Integer.valueOf(id);
		boolean flag = false;
		FlightDAO fd = new FlightDAOImpl();
		flag = fd.deleteFlight(flightId);
		List<Flight> flights = null;
		try {
			flights = fd.findAllFlights();
		} catch (DAOException e) {
			flag = false;
			logger.error("TechnicalException", e);
		}
		if (flag) {
			request.getSession().setAttribute("flights", flights);
			request.setAttribute("flightDeleted", "flight.deleted");
			request.removeAttribute("flightNotDeleted");
		} else {
			request.setAttribute("flightNotDeleted", "flight.notdeleted");
		}
		return page;
	}
}
