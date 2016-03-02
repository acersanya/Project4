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

public class ChangeEmployeeCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(ChangeEmployeeCommand.class);
	private static final String PARAM_NAME_MODIFIED_NAME = "modifiedName";
	private static final String PARAM_NAME_MODIFIED_SURNAME = "modifiedSurname";
	private static final String PARAM_NAME_MODIFIED_POSITION = "modifiedPosition";
	private static final String PARAM_NAME_EMPLOYEE_ID = "employeeIdToModify";

	/**
	 * Change information about current employee in crew
	 */
	@Override
	public String execute(HttpServletRequest request) {
		int employeeId = (Integer) request.getSession().getAttribute(PARAM_NAME_EMPLOYEE_ID);
		String name = request.getParameter(PARAM_NAME_MODIFIED_NAME);
		String surname = request.getParameter(PARAM_NAME_MODIFIED_SURNAME);
		Position position = Position.valueOf(request.getParameter(PARAM_NAME_MODIFIED_POSITION));
		int pos = 0;
		switch (position) {
		case PILOT:
			pos = 1;
			break;
		case NAVIGATOR:
			pos = 2;
			break;
		case RADIOMAN:
			pos = 3;
			break;
		case STEWARD:
			pos = 4;
			break;
		}
		boolean flag = false;
		CrewDAO cdi = new CrewDAOImpl();
		flag = cdi.modifyEmployee(employeeId, name, surname, pos);
		if (flag) {
			List<Employee> employees = null;
			try {
				employees = cdi.findAvailableEmployees();
				request.getSession().setAttribute("employees", employees);
			} catch (DAOException e) {
				logger.error("TechnicalException", e);
				request.setAttribute("employeesNull", "employees.null");
				return ConfigurationManager.getProperty("path.page.admin");
			}
			request.removeAttribute("employeeWasntModified");
			request.setAttribute("employeeWasModified", "employee.modified");
		} else {
			request.setAttribute("employeeWasntModified",
					"employee.notmodified");
			return ConfigurationManager.getProperty("path.page.employee");
		}
		return ConfigurationManager.getProperty("path.page.staff");
	}
}
