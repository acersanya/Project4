package ua.air.epam.dao;

import java.util.List;

import ua.air.epam.entity.Flight;
import ua.air.epam.entity.Plane;
import ua.air.epam.exception.DAOException;

/**
 * 
 * @author Alex
 * Methods avaible for working with flights and their implementation
 */
public abstract class FlightDAO extends AbstractDAO {

	public abstract List<Flight> findAllFlights() throws DAOException;

	public abstract Plane findPlaneById(int id) throws DAOException;

	public abstract Flight findFlightById(int id) throws DAOException;

	public abstract boolean setFlightOnAir(int id);

	public abstract boolean setFlightCompleted(int id);

	public abstract List<Flight> findUnformedFlights() throws DAOException;

	public abstract boolean addFlight(int flightId, String to, String from,String date, int plane);

	public abstract List<Plane> findAllPlanes() throws DAOException;

	public abstract boolean deleteFlight(int flightId);
}
