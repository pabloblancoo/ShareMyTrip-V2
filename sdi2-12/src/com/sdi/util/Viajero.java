package com.sdi.util;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import com.sdi.model.Application;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.User;

public class Viajero {

	private static ResourceBundle msgs = FacesContext.getCurrentInstance()
			.getApplication()
			.getResourceBundle(FacesContext.getCurrentInstance(), "msgs");

	private User user;
	private Trip trip;
	private Application application;
	private Seat seat;

	public Viajero(User user, Trip trip, Application application, Seat seat) {
		this.user = user;
		this.trip = trip;
		this.application = application;
		this.seat = seat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public String getStatus() {

		if (seat != null) {
			if (seat.getStatus().equals(SeatStatus.ACCEPTED)) {
				return msgs.getString("ownTripAccepted");
			} else {
				return msgs.getString("ownTripExcluded");
			}
		} else if (seat == null && trip.getAvailablePax() > 0) {
			return msgs.getString("ownTripPending");
		} else {
			return msgs.getString("ownTripNoSeat");
		}

	}

	public boolean isAcceptable() {
		return (getStatus().equals(msgs.getString("ownTripExcluded")) || trip
				.getAvailablePax() > 0)
				&& !getStatus().equals(msgs.getString("ownTripAccepted"));
	}

	public boolean isExcludable() {
		return (trip.getAvailablePax() > 0 && !getStatus().equals(
				msgs.getString("ownTripExcluded")))
				|| getStatus().equals(msgs.getString("ownTripAccepted"));
	}

}
