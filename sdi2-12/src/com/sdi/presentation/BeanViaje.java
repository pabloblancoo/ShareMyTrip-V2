package com.sdi.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.sdi.infrastructure.Factories;
import com.sdi.model.AddressPoint;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.Waypoint;
import com.sdi.persistence.PersistenceException;
import com.sdi.util.Comprobante;

@ManagedBean(name="viaje")
@SessionScoped
public class BeanViaje implements Serializable{

	private static final long serialVersionUID = 1L;
	
	boolean salidaDefecto, llegadaDefecto, informacionDefecto;
	private Long id;

	private String departureAddress;
	private String departureCity;
	private String departureState;
	private String departureCountry;
	private String departureZipCode;	
	private String departureWaypointStr;
	private Date departureDate;

	private String arrivalAddress;
	private String arrivalCity;
	private String arrivalState;
	private String arrivalCountry;
	private String arrivalZipCode;
	private String arrivalWaypointStr;
	private Date arrivalDate;

	private Date closingDate;
	private int availablePax; 
	private int maxPax;
	private Double estimatedCost;
	private String comments;
	private TripStatus status;

	private Long promoterId;

	List<String> provincias = new ArrayList<>();

	@PostConstruct
	private void init(){
		provincias.add("Alava");
		provincias.add("Albacete");
		provincias.add("Alicante");
		provincias.add("Almeria");
		provincias.add("Asturias");
		provincias.add("Avila");
		provincias.add("Badajoz");
		provincias.add("Barcelona");
		provincias.add("Burgos");
		provincias.add("Caceres");
		provincias.add("Cadiz");
		provincias.add("Cantabria");
		provincias.add("Castellon");
		provincias.add("Ciudad Real");
		provincias.add("Cordoba");
		provincias.add("La Coruña");
		provincias.add("Cuenca");
		provincias.add("Gerona");
		provincias.add("Granada");
		provincias.add("Guadalajara");
		provincias.add("Gipuzcoa");
		provincias.add("Huelva");
		provincias.add("Huesca");
		provincias.add("Islas Baleares");
		provincias.add("Jaen");
		provincias.add("Leon");
		provincias.add("Lerida");
		provincias.add("Lugo");
		provincias.add("Madrid");
		provincias.add("Malaga");
		provincias.add("Murcia");
		provincias.add("Navarra");
		provincias.add("Orense");
		provincias.add("Palencia");
		provincias.add("Las palmas");
		provincias.add("Pontevedra");
		provincias.add("La rioja");
		provincias.add("Salamanca");
		provincias.add("Segovia");
		provincias.add("Sevilla");
		provincias.add("Soria");
		provincias.add("Tarragona");
		provincias.add("Santa Cruz de Tenerife");
		provincias.add("Teruel");
		provincias.add("Toledo");
		provincias.add("Valencia");
		provincias.add("Valladolid");
		provincias.add("Vizcaya");
		provincias.add("Zamora");
		provincias.add("Zaragoza");
		provincias.add("Ceuta");
		provincias.add("Melilla");

	}

	@PreDestroy
	public void avisame(){
		System.out.println("Se meure el bean");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public int getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(int availablePax) {
		this.availablePax = availablePax;
	}

	public int getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(int maxPax) {
		this.maxPax = maxPax;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	public Long getPromoterId() {
		return promoterId;
	}

	public void setPromoterId(Long promoterId) {
		this.promoterId = promoterId;
	}

	public String getDepartureAddress() {
		return departureAddress;
	}

	public void setDepartureAddress(String departureAddress) {
		this.departureAddress = departureAddress;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getDepartureState() {
		return departureState;
	}

	public void setDepartureState(String departureState) {
		this.departureState = departureState;
	}

	public String getDepartureCountry() {
		return departureCountry;
	}

	public void setDepartureCountry(String departureCountry) {
		this.departureCountry = departureCountry;
	}

	public String getDepartureZipCode() {
		return departureZipCode;
	}

	public void setDepartureZipCode(String departureZipCode) {
		this.departureZipCode = departureZipCode;
	}


	public String getArrivalAddress() {
		return arrivalAddress;
	}

	public void setArrivalAddress(String arrivalAddress) {
		this.arrivalAddress = arrivalAddress;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public String getArrivalState() {
		return arrivalState;
	}

	public void setArrivalState(String arrivalState) {
		this.arrivalState = arrivalState;
	}

	public String getArrivalCountry() {
		return arrivalCountry;
	}

	public void setArrivalCountry(String arrivalCountry) {
		this.arrivalCountry = arrivalCountry;
	}

	public String getArrivalZipCode() {
		return arrivalZipCode;
	}

	public void setArrivalZipCode(String arrivalZipCode) {
		this.arrivalZipCode = arrivalZipCode;
	}


	public String getDepartureWaypointStr() {
		return departureWaypointStr;
	}

	public void setDepartureWaypointStr(String departureWaypointStr) {
		this.departureWaypointStr = departureWaypointStr;
	}


	public String getArrivalWaypointStr() {
		return arrivalWaypointStr;
	}

	public void setArrivalWaypointStr(String arrivalWaypointStr) {
		this.arrivalWaypointStr = arrivalWaypointStr;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public List<String> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<String> provincias) {
		this.provincias = provincias;
	}


	public boolean isSalidaDefecto() {
		return salidaDefecto;
	}

	public void setSalidaDefecto(boolean salidaDefecto) {
		this.salidaDefecto = salidaDefecto;
	}

	public boolean isLlegadaDefecto() {
		return llegadaDefecto;
	}

	public void setLlegadaDefecto(boolean llegadaDefecto) {
		this.llegadaDefecto = llegadaDefecto;
	}

	public boolean isInformacionDefecto() {
		return informacionDefecto;
	}

	public void setInformacionDefecto(boolean informacionDefecto) {
		this.informacionDefecto = informacionDefecto;
	}

	/**
	 * Metodo para completar el input de las provincias
	 * 
	 * Filtra el array de provincias si contienen el String como parametro
	 * @param str Texto para filtrar provincias
	 * @return Lista de provincias que contienen el valor
	 */
	public List<String> completarProvincias(String str){
		List<String> retorno = new ArrayList<>();
		for (String string : provincias) {
			if(string.toLowerCase().contains(str.toLowerCase()))
				retorno.add(string);
		}
		return retorno;
	}

	/**
	 * Registra un viaje nuevo en la Base de Datos
	 * @return
	 */
	public String registrarViaje() {
		
		ResourceBundle msgs = FacesContext.getCurrentInstance()
				.getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		
		Trip trip = new Trip();
		AddressPoint departure;
		AddressPoint destination;
		Waypoint salida = Comprobante.comprobarPunto(departureWaypointStr);
		Waypoint llegada = Comprobante.comprobarPunto(arrivalWaypointStr);
		BeanUsuario usuario = ((BeanSettings) FacesContext.getCurrentInstance()
				.getExternalContext()
				.getSessionMap().get(new String("settings"))).getUsuario();

		if (usuario != null) {

			if (salida == null)
				salida = new Waypoint(0.0, 0.0);
			if (llegada == null)
				llegada = new Waypoint(0.0, 0.0);
			departure = new AddressPoint(departureAddress, departureCity,
					departureState, departureCountry, departureZipCode, salida);
			destination = new AddressPoint(arrivalAddress, arrivalCity,
					arrivalState, arrivalCountry, arrivalZipCode, llegada);

			if (closingDate.compareTo(departureDate) <= 0) {		//Fecha de cierre antes de la salida
				if (departureDate.compareTo(arrivalDate) <= 0) {	//Fecha de llegada, despues de salida
					if(maxPax >= availablePax){
						trip.setDeparture(departure);
						trip.setDestination(destination);
						trip.setArrivalDate(arrivalDate);
						trip.setDepartureDate(departureDate);
						trip.setClosingDate(closingDate);
						trip.setAvailablePax(availablePax);
						trip.setMaxPax(maxPax);
						trip.setEstimatedCost(estimatedCost);
						trip.setComments(comments);
						trip.setStatus(TripStatus.OPEN);

						trip.setPromoterId(usuario.getId());

						try{
							Factories.persistence.newTripDao().save(trip);
						}catch(PersistenceException e){
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
											msgs.getString("errorSaveTrip")));
							return null;
						}
						
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
										msgs.getString("infoCreateTrip")));
						
						borrarDatos();
						System.out.println("Viaje creado con exito");
						return "exito";
					}
					else{
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
										msgs.getString("errorMaxPax")));
						return null;
					}
				}
				else{
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
									msgs.getString("errorArrivalDate")));
					return null;
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								msgs.getString("errorClosingDate")));
				return null;
			}
		} 

		return null;
	}

	/**
	 * Pone unos datos validos precargados en los campos de registrar viaje
	 */
	@SuppressWarnings("deprecation")
	public void cargarDatos(){
		
		Date fecha = new Date();
		fecha.setDate(fecha.getDate() + 2);
		
		departureAddress = "Uria";
		departureCity = "Oviedo";
		departureState = "Asturias";
		departureCountry = "España";
		departureZipCode = "33070";	
		departureWaypointStr = "0.0 ; 0,0";
		departureDate = fecha;

		arrivalAddress = "Gran via";
		arrivalCity = "Madrid";
		arrivalState = "Madrid";
		arrivalCountry = "España";
		arrivalZipCode = "28080";
		arrivalWaypointStr = "0.0 ; 0.0";
		arrivalDate = fecha;

		closingDate = fecha;
		availablePax = 5; 
		maxPax = 5;
		estimatedCost = 10.0;
		comments = "Viaje barato a madrid";
	}

	/**
	 * 
	 */
	public void borrarDatos(){
		departureAddress = "";
		departureCity = "";
		departureState = "";
		departureCountry = "";
		departureZipCode = "";	
		departureWaypointStr = "";
		departureDate = null;

		arrivalAddress = "";
		arrivalCity = "";
		arrivalState = "";
		arrivalCountry = "";
		arrivalZipCode = "";
		arrivalWaypointStr = "";
		arrivalDate = null;

		closingDate = null;
		availablePax = 0; 
		maxPax = 0;
		estimatedCost = 0.0;
		comments = "";
	}

	/**
	 * Recibe un viaje e inicializa todos los datos con los parametros del viaje recibido
	 * @param viaje
	 */
	public void cargarViaje(Trip viaje) {
		id = viaje.getId();
		
		departureAddress = viaje.getDeparture().getAddress();
		departureCity = viaje.getDeparture().getCity();
		departureState = viaje.getDeparture().getState();
		departureCountry = viaje.getDeparture().getCountry();
		departureZipCode = viaje.getDeparture().getZipCode();	
		departureWaypointStr = viaje.getDeparture().getWaypoint().toString();
		departureDate = viaje.getDepartureDate();

		arrivalAddress = viaje.getDestination().getAddress();
		arrivalCity = viaje.getDestination().getCity();
		arrivalState = viaje.getDestination().getState();
		arrivalCountry = viaje.getDestination().getCountry();
		arrivalZipCode = viaje.getDestination().getZipCode();
		arrivalWaypointStr = viaje.getDestination().getWaypoint().toString();
		arrivalDate = viaje.getArrivalDate();

		closingDate = viaje.getClosingDate();
		availablePax = viaje.getAvailablePax(); 
		maxPax = viaje.getMaxPax();
		estimatedCost = viaje.getEstimatedCost();
		comments = viaje.getComments();
		
		status = viaje.getStatus();
		
		promoterId = viaje.getPromoterId();
		
	}

	public String actualizarViaje(){
		ResourceBundle msgs = FacesContext.getCurrentInstance()
				.getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		
		Trip trip = new Trip();
		AddressPoint departure;
		AddressPoint destination;
		Waypoint salida = Comprobante.comprobarPunto(departureWaypointStr);
		Waypoint llegada = Comprobante.comprobarPunto(arrivalWaypointStr);
		BeanUsuario usuario = ((BeanSettings) FacesContext.getCurrentInstance()
				.getExternalContext()
				.getSessionMap().get(new String("settings"))).getUsuario();

		if (usuario != null) {

			if (salida == null)
				salida = new Waypoint(0.0, 0.0);
			if (llegada == null)
				llegada = new Waypoint(0.0, 0.0);
			departure = new AddressPoint(departureAddress, departureCity,
					departureState, departureCountry, departureZipCode, salida);
			destination = new AddressPoint(arrivalAddress, arrivalCity,
					arrivalState, arrivalCountry, arrivalZipCode, llegada);

			if (closingDate.compareTo(departureDate) <= 0) {		//Fecha de cierre antes de la salida
				if (departureDate.compareTo(arrivalDate) <= 0) {	//Fecha de llegada, despues de salida
					if(maxPax >= availablePax){
						trip.setDeparture(departure);
						trip.setDestination(destination);
						trip.setArrivalDate(arrivalDate);
						trip.setDepartureDate(departureDate);
						trip.setClosingDate(closingDate);
						trip.setAvailablePax(availablePax);
						trip.setMaxPax(maxPax);
						trip.setEstimatedCost(estimatedCost);
						trip.setComments(comments);
						trip.setStatus(TripStatus.OPEN);

						trip.setPromoterId(usuario.getId());

						try{
							Factories.persistence.newTripDao().update(trip);
						}catch(PersistenceException e){
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
											msgs.getString("errorSaveTrip")));
							return null;
						}
						
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
										msgs.getString("infoCreateTrip")));
						
						borrarDatos();
						System.out.println("Viaje creado con exito");
						return "exito";
					}
					else{
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
										msgs.getString("errorMaxPax")));
						return null;
					}
				}
				else{
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
									msgs.getString("errorArrivalDate")));
					return null;
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								msgs.getString("errorClosingDate")));
				return null;
			}
		} 

		return null;		
	}
}
