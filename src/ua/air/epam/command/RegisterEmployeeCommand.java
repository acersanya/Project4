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

public class RegisterEmployeeCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(RegisterEmployeeCommand.class);
	private static final String PARAM_NAME_EMPLOYEE_NAME = "name";
	private static final String PARAM_NAME_EMPLOYEE_SURNAME = "surname";
	private static final String PARAM_NAME_EMPLOYEE_POSITION = "position";
	private static final String PATTERN_LETTERS_ONLY = "[a-zA-Z]+";

	
	/**
	 * registers new employee 
	 */
	@Override
	public String execute(HttpServletRequest request) {

		String name = request.getParameter(PARAM_NAME_EMPLOYEE_NAME);
		String surname = request.getParameter(PARAM_NAME_EMPLOYEE_SURNAME);
		Position position = Position.valueOf(request.getParameter(PARAM_NAME_EMPLOYEE_POSITION));
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
		CrewDAO cd = new CrewDAOImpl();
		if(varification(name) || varification(surname)){
			flag = false;
		}else {
			flag = cd.addEmployee(name, surname, pos);
		}
		if (flag) {
			request.setAttribute("employeeAdded", "employee.registered");
			request.removeAttribute("employeeNotAdded");
			List<Employee> employees = null;
			try {
				employees = cd.findAvailableEmployees();
				request.getSession().setAttribute("employees", employees);
			} catch (DAOException e) {
				logger.error("TechnicalException", e);
				request.setAttribute("employeesNull", "employees.null");
				return ConfigurationManager.getProperty("path.page.admin");
			}
		} else {
			request.setAttribute("employeeNotAdded", "employee.notregistered");
			request.removeAttribute("employeeAdded");
		}
		return ConfigurationManager.getProperty("path.page.staff");
	}
	
	private static boolean varification(String input) {
		if(input.matches(PATTERN_LETTERS_ONLY)){
			return false;
		}
		return true;
	}	
}
