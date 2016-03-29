package com.sdi.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.sdi.model.TripStatus;

@ManagedBean(name="viaje")
@RequestScoped
public class BeanViaje implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String departureAddress;
	private String departureCity;
	private String departureState;
	private String departureCountry;
	private String departureZipCode;	
	private String departureWaypointStr;
	private String departureDate;
	
	private String arrivalAddress;
	private String arrivalCity;
	private String arrivalState;
	private String arrivalCountry;
	private String arrivalZipCode;
	private String arrivalWaypointStr;
	private String arrivalDate;
	
	private String closingDate;
	private String availablePax; 
	private String maxPax;
	private String estimatedCost;
	private String comments;
	private TripStatus status;
	
	private Long promoterId;

	private FacesContext context = FacesContext.getCurrentInstance();
	private ResourceBundle msgs = context.getApplication().getResourceBundle(context, "msgs");
	
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}

	

	public String getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(String availablePax) {
		this.availablePax = availablePax;
	}

	public String getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(String maxPax) {
		this.maxPax = maxPax;
	}

	public String getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(String estimatedCost) {
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

	
	
	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	/**
	 * Registra un viaje nuevo en la Base de Datos
	 * @return
	 */
	public String registrarViaje(){
		
		//Sin acabar
		//Sin acabar
		//Sin acabar
		//Sin acabar
		int a = 0;
		
		return null;
	}

	public List<String> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<String> provincias) {
		this.provincias = provincias;
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
	
}
