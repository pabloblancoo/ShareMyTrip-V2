package com.sdi.persistence;

import java.util.List;

import com.sdi.model.User;
import com.sdi.persistence.util.GenericDao;

public interface UserDao extends GenericDao<User, Long> {

	User findByLogin(String login, String password);

	User findByLogin(String login);

	List<User> findUsersPendingByTrip(long idViaje);
	List<User> findUsersAcceptedPromotorByTrip(long idViaje);

}
