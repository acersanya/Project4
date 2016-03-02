package ua.air.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import ua.air.epam.dao.FlightDAO;
import ua.air.epam.dao.FlightDAOImpl;
import ua.air.epam.entity.Flight;
import ua.air.epam.exception.DAOException;
import ua.air.epam.resource.ConfigurationManager;


/*
 * AddFlight Command implementation
 * Administrator creates new flight. 
 * He can set time to depart, arrival place, departure place and current plane.
 */
public class AddFlightCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(AddFlightCommand.class);
	private static final String PARAM_NAME_FLIGHT_ID = "addedflight";
	private static final String PARAM_NAME_TO = "to";
	private static final String PARAM_NAME_FROM = "from";
	private static final String PARAM_NAME_DATE = "date";
	private static final String PARAM_NAME_PLANE_ID = "plane";
	private static final String PATTERN_LETTERS_ONLY = "[a-zA-Z]+";

	/**
	 * Executes query to db and place new flight inside the table
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String flightId = request.getParameter(PARAM_NAME_FLIGHT_ID);
		String to = request.getParameter(PARAM_NAME_TO);
		String from = request.getParameter(PARAM_NAME_FROM);
		String date = request.getParameter(PARAM_NAME_DATE);
		String plane = request.getParameter(PARAM_NAME_PLANE_ID);
		String page = ConfigurationManager.getProperty("path.page.admin");
		boolean flag = false;
		try {
			flag = addFlight(flightId, to, from, date, plane);
		} catch (DAOException e) {
			logger.error("TechnicalException", e);
		}
		if (flag) {
			request.setAttribute("flightAdded", "flight.added");
		} else {
			request.setAttribute("flightNotAdded", "flight.notadded");
		}
		request.removeAttribute("flightCompleted");
		request.removeAttribute("flightNotCompleted");
		return page;
	}

	/**
	 * 
	 * @param idInput
	 *            current flight id
	 * @param to
	 *            arrival place
	 * @param from
	 *            department place
	 * @param dateInput
	 *            Depart Date
	 * @param planeInput
	 *            current Plance
	 * @return flag if true - Plane added , else not. addFlight checks if input
	 *         is not empty, according to the input searches flight with same id
	 *         if id is found - flight will not be added , if not found flight
	 *         will be placed inside db.
	 * @throws DAOException
	 */
	// check
	private static boolean addFlight(String idInput, String to, String from, String dateInput, String planeInput)
			throws DAOException {
		boolean flag = false;
		
		if(varification(to) || varification(from)){
			return false;
		}
		
		if (isEmpty(to, from, dateInput)) {
			return flag;
		}
		int flightId;
		int plane;
		try {
			flightId = Integer.valueOf(idInput);
			plane = Integer.valueOf(planeInput);
		} catch (IllegalArgumentException e) {
			return flag;
		}
		FlightDAO fd = new FlightDAOImpl();
		List<Flight> allFlights = fd.findAllFlights();
		for (Flight flight : allFlights) {
			if (flightId == flight.getId()) {
				return flag;
			}
		}
		flag = fd.addFlight(flightId, to, from, dateInput, plane);
		return flag;
	}

	/**
	 * 
	 * @param to
	 *            params
	 * @param from
	 *            params
	 * @param dateInput
	 *            params params to check if not empty , otherwise false
	 * @return
	 */
	private static boolean isEmpty(String to, String from, String dateInput) {
		return to.isEmpty() || from.isEmpty() || dateInput.isEmpty();
	}

	/**
	 * Varification for input
	 * @param input
	 * @return
	 */
	private static boolean varification(String input) {
		if(input.matches(PATTERN_LETTERS_ONLY)){
			return false;
		}
		return true;
	}
}
