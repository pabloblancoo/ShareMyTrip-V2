package com.sdi.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.model.UserStatus;
import com.sdi.util.Comprobante;
import com.sdi.util.Encriptador;

@ManagedBean(name = "usuario")
@RequestScoped
public class BeanUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String repassword;
	private Long id;
	private UserStatus status;

	private FacesContext context = FacesContext.getCurrentInstance();
	private ResourceBundle msgs = context.getApplication().getResourceBundle(
			context, "msgs");

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if (Comprobante.comprobarDato(login)) {
			this.login = login;
		} else {

		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;

	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repasword) {
		this.repassword = repasword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	/**
	 * Metodo que registra al usuario en la base de datos
	 * 
	 * @return devuelve el string que indica la vista a carga
	 */
	public String registrar() {

		if (context.getMessageList().size() > 0) {
			return null;
		}
		if (!repassword.equals(password)) {

			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msgs
							.getString("sigInRepassworWrong")));
			System.out.println("Las contraseñas no coinciden");

			return null;
		}
		User user = new User();

		user.setLogin(login);
		user.setName(name);
		user.setEmail(email);
		user.setSurname(surname);
		user.setPassword(Encriptador.encriptar(password));
		user.setStatus(UserStatus.ACTIVE);

		try {
			Factories.services.getUserService().registrarse(user);
		} catch (EntityAlreadyExistsException e) {
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msgs
							.getString("sigInLoginYetRegister")));
			System.out.println("Ya existe ese login");
			return null;
		}

		System.out.println("Usuario registrado correctamente");
		return "exito";

	}

	/**
	 * Metodo para iniciar sesion
	 * 
	 * @return Devuelve el string de la vista siguiente
	 */
	public String iniciarSesion() {

		User user = null;
		try{
			user = Factories.services.getUserService().iniciarSesion(login, password);
		} catch(BusinessException e){
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error",
					"Usuario o contraseña incorrecta"));

			return null;
		}

		this.email = user.getEmail();
		this.login = user.getLogin();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.email = user.getEmail();
		this.id = user.getId();
		this.status = user.getStatus();

		BeanSettings settings = (BeanSettings) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get(new String("settings"));
		settings.setUsuario(this);
		System.out.println("Sesion iniciada correctamente");

		return "exito";

	}

	/**
	 * Cierra la sesion activa del usuario
	 * 
	 * @return
	 */
	public String cerrarSesion() {
		BeanSettings settings = (BeanSettings) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get(new String("settings"));
		settings.setUsuario(null);
		System.out.println("Sesion cerrada correctamente");

		return "exito";
	}

	/**
	 * Devuelve el usuario base del bean
	 * @return
	 */
	public User getUsuarioBase(){
		User user = new User();
		user.setEmail(email);
		user.setLogin(login);
		user.setName(name);
		user.setSurname(surname);
		user.setEmail(email);
		user.setId(id);
		user.setStatus(status);
		return user;
	}
}
