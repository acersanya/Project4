package ua.air.epam.dao;

import java.util.List;
import java.util.Set;

import ua.air.epam.entity.Employee;
import ua.air.epam.exception.DAOException;
/**
 * 
 * @author Alex
 *  Methods which will be implemented,
 *  for work with database 
 */

public abstract class CrewDAO extends AbstractDAO {

	public abstract boolean addEmployee(String name, String surname,int position);

	public abstract Employee findEmployeeById(int id) throws DAOException;

	public abstract List<Employee> findAvailableEmployees() throws DAOException;

	public abstract boolean addToFlight(int employeeId);

	public abstract Set<Employee> findCrewByFlightId(int id)
			throws DAOException;

	public abstract boolean releaseEmployee(int employeeId);

	public abstract boolean formCrew(int flightId, List<Employee> crew);

	public abstract boolean modifyEmployee(int id, String name, String surname,int position);

}
