package ua.air.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.air.epam.dao.CrewDAO;
import ua.air.epam.dao.CrewDAOImpl;
import ua.air.epam.entity.Employee;
import ua.air.epam.entity.Position;
import ua.air.epam.exception.DAOException;
import ua.air.epam.resource.ConfigurationManager;

/**
 * @author Alex
 */

/**
 * class which implements ActionCommands 
 */
public class AddEmployeeCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(AddEmployeeCommand.class);
	private static final String PARAM_NAME_EMPLOYEE_ID = "employeeId";

	/**
	 * Adds new employee to the crew, new employee
	 * will be set on already created flight by dispatcher
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request) {

		String page = ConfigurationManager.getProperty("path.page.team");
		int employeeId = Integer.valueOf(request.getParameter(PARAM_NAME_EMPLOYEE_ID));
		CrewDAO cd = new CrewDAOImpl();
		Employee employee = null;
		try {
			employee = cd.findEmployeeById(employeeId);
		} catch (DAOException e) {
			request.setAttribute("employeeNotAdded", "employee.notadded");
			request.removeAttribute("employeeAdded");
			logger.error("TechnicalException", e);
			return page;
		}

		//returns the list of employees according to current session
		List<Employee> employees = (List<Employee>) request.getSession().getAttribute("employees");

		// returns list of crew members according to current session
		List<Employee> crew = (List<Employee>) request.getSession().getAttribute("crew");
		Position pos = employee.getPosition();
		int i = 0;
		while (i < crew.size()) {
			Employee empl = crew.get(i);
			if (empl.getPosition().equals(pos) && (empl.getId() == 0)) {
				crew.set(i, employee);
				employees.remove(employee);
				request.setAttribute("employeeAdded", "employee.added");
				request.removeAttribute("employeeNotAdded");
				break;
			} else {
				request.setAttribute("employeeNotAdded", "employee.notadded");
				request.removeAttribute("employeeAdded");
			}
			i++;
		}
		request.getSession().setAttribute("crew", crew);
		request.getSession().setAttribute("employees", employees);
		return page;
	}
}
