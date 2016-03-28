package com.sdi.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.sdi.model.User;
import com.sdi.model.UserStatus;
import com.sdi.util.Comprobante;

@ManagedBean(name="registro")
public class BeanUsuario {
	
	private String login;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String repassword;
	
	private FacesContext context = FacesContext.getCurrentInstance();
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		if(Comprobante.comprobarDato(login)){
			this.login = login;
		}
		else{
			
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(Comprobante.comprobarDato(name)){
			this.name = name;
		}
		else{
			context.addMessage("name", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No puede ser vacia"));
		}
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		if(Comprobante.comprobarDato(surname)){
			this.surname = surname;
		}
		else{
			context.addMessage("surname", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No puede ser vacia"));

		}
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(Comprobante.comprobarDato(email)){
			this.email = email;
		}
		else{
			context.addMessage("email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No puede ser vacia"));
		}
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(Comprobante.comprobarDato(password)){
			this.password = password;
		}
		else{
			context.addMessage("password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No puede ser vacia"));
		}
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repasword) {
		if(Comprobante.comprobarDato(repasword)){
			this.repassword = repasword;
		}
		else{
			context.addMessage("repassword", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No puede ser vacia"));
		}
		
	}
	
	/**
	 * Metodo que registra al usuario en la base de datos
	 * @return
	 */
	public String registrar(){
		
		User user = new User();
		
//		java.util.List<FacesMessage> lista = context.getMessageList();
//		for(FacesMessage msg : lista){
//			System.out.println(msg.getDetail());
//		}
		
		if(repassword != null && repassword.equals(password)){
			
			user.setLogin(login);
			user.setName(name);
			user.setSurname(surname);
			user.setPassword(password);
			user.setStatus(UserStatus.ACTIVE);
			
			System.out.println("exito");
			
			return "exito";
			
		}
		else{
			context.addMessage("repassword", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No inciden las contraseñas"));
			
			System.out.println("fracaso");
			
			return null;
		}
		
		
	}

}
