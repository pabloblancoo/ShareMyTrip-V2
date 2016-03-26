package com.sdi.persistence;

public interface PersistenceFactory {

	public Transaction newTransaction();

	public RatingDao newRatingDao();

	public UserDao newUserDao();

	public TripDao newTripDao();

	public SeatDao newSeatDao();

	public ApplicationDao newApplicationDao();
}
