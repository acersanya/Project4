package ua.air.epam.command;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.air.epam.dao.CrewDAO;
import ua.air.epam.dao.CrewDAOImpl;
import ua.air.epam.dao.FlightDAO;
import ua.air.epam.dao.FlightDAOImpl;
import ua.air.epam.entity.Employee;
import ua.air.epam.entity.Flight;
import ua.air.epam.exception.DAOException;
import ua.air.epam.resource.ConfigurationManager;

public class CompleteFlightCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(CompleteFlightCommand.class);
	private static final String PARAM_NAME_FLIGHT_ID = "flightId";

	/**
	 * After clicking button on administrator page
	 * he can set status accomplished, after that
	 * the crew staff will be automatically released
	 */
	
	@Override
	public String execute(HttpServletRequest request) {

		int flightId = Integer.valueOf(request.getParameter(PARAM_NAME_FLIGHT_ID));
		FlightDAO fd = new FlightDAOImpl();
		CrewDAO cd = new CrewDAOImpl();
		FlightDAO flightDAO = new FlightDAOImpl();
		List<Flight> flights = null;
		Set<Employee> crew = null;
		boolean flag = false;
		flag = fd.setFlightCompleted(flightId);
		try {
			crew = cd.findCrewByFlightId(flightId);
			flights = flightDAO.findAllFlights();
		} catch (DAOException e) {
			flag = false;
			logger.error("TechnicalException", e);
		}
		for (Employee employee : crew) {
			int employeeId = employee.getId();
			flag = cd.releaseEmployee(employeeId);
			if (flag == false) {
				break;
			}
		}
		if (flag) {
			request.getSession().setAttribute("flights", flights);
			request.setAttribute("flightCompleted", "flight.completed");
		} else {
			request.setAttribute("flightNotCompleted", "flight.notcompleted");
		}
		request.removeAttribute("flightAdded");
		request.removeAttribute("flightNotAdded");
		request.removeAttribute("flightDeleted");
		request.removeAttribute("flightNotDeleted");
		return ConfigurationManager.getProperty("path.page.admin");
	}
}
