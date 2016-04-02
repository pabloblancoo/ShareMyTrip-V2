package com.sdi.presentation.validator;

import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

import com.sdi.infrastructure.Factories;
import com.sdi.persistence.UserDao;

@FacesValidator("validator.loginRepetido")
public class LoginRepetidoValidator implements Validator, ClientValidator{

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

	@Override
	public String getValidatorId() {
		return "validator.loginRepetido";
	}

	@Override
	public void validate(FacesContext context, UIComponent ui, Object value)
			throws ValidatorException {
		ResourceBundle msgs = context.getApplication().getResourceBundle(context, "msgs");
		UserDao ud = Factories.persistence.newUserDao();
		
		if(ud.findByLogin(value.toString()) != null){
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Error", 
					msgs.getString("sigInLoginYetRegister")));
		}
		
	}

}
