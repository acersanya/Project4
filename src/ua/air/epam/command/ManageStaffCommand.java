package ua.air.epam.command;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.air.epam.dao.CrewDAO;
import ua.air.epam.dao.CrewDAOImpl;
import ua.air.epam.entity.Employee;
import ua.air.epam.entity.Position;
import ua.air.epam.exception.DAOException;
import ua.air.epam.resource.ConfigurationManager;

public class ManageStaffCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(ManageStaffCommand.class);

	/**
	 * returns list of free staff and their current role
	 * 
	 */
	@Override
	public String execute(HttpServletRequest request) {

		CrewDAO cdi = new CrewDAOImpl();
		List<Employee> employees = null;
		try {
			employees = cdi.findAvailableEmployees();
			List<Position> positions = Arrays.asList(Position.values());
			request.getSession().setAttribute("positions", positions);
			request.getSession().setAttribute("employees", employees);
			request.removeAttribute("employeesNull");
		} catch (DAOException e) {
			logger.error("TechnicalException", e);
			request.setAttribute("employeesNull", "employees.null");
			return ConfigurationManager.getProperty("path.page.admin");
		}

		return ConfigurationManager.getProperty("path.page.staff");
	}

}
