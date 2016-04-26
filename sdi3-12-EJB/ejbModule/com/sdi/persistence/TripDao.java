package com.sdi.persistence;

import java.util.Date;
import java.util.List;

import com.sdi.model.Trip;
import com.sdi.persistence.util.GenericDao;

public interface TripDao extends GenericDao<Trip, Long> {

	Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate);

	List<Trip> findAllOpenAndPaxAvailables();

	List<Trip> findByPromoterId(Long id);

	List<Trip> find(String seach);

}
