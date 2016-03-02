package ua.air.epam.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.air.epam.dao.CrewDAO;
import ua.air.epam.dao.CrewDAOImpl;
import ua.air.epam.entity.Employee;
import ua.air.epam.exception.DAOException;
import ua.air.epam.resource.ConfigurationManager;

public class ModifyEmployeeCommand implements ActionCommand {

	

	private static Logger logger = Logger.getLogger(ModifyEmployeeCommand.class);
	private static final String PARAM_NAME_EMPLOYEE_ID = "employeeId";
/**
 * admin has a previlague to change employee occupation, role.
 */
	@Override
	public String execute(HttpServletRequest request) {

		int employeeId = Integer.valueOf(request.getParameter(PARAM_NAME_EMPLOYEE_ID));
		CrewDAO cdi = new CrewDAOImpl();
		try {
			Employee employee = cdi.findEmployeeById(employeeId);
			request.setAttribute("employeeToModify", employee);
			request.getSession().setAttribute("employeeIdToModify", employeeId);
		} catch (DAOException e) {
			request.setAttribute("employeeWasntModified", "emloyee.notmodified");
			logger.error("TechnicalException", e);
			return ConfigurationManager.getProperty("path.page.staff");
		}
		return ConfigurationManager.getProperty("path.page.employee");
	}

}
