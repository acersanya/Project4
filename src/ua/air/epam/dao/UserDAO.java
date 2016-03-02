package ua.air.epam.dao;

import ua.air.epam.entity.User;
import ua.air.epam.exception.DAOException;

public abstract class UserDAO extends AbstractDAO {

	public abstract User findUser(String login, String password)throws DAOException;
}
