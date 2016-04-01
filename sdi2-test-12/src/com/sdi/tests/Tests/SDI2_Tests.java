package com.sdi.tests.Tests;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdi.tests.pageobjects.POInicioSesion;
import com.sdi.tests.pageobjects.PORegistro;
import com.sdi.tests.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class SDI2_Tests {

	WebDriver driver; 
	List<WebElement> elementos = null;

	public SDI2_Tests()
	{
	}

	@Before
	public void run()
	{
		//Creamos el driver para Firefox. Recomendable usar Firefox.
		driver = new FirefoxDriver();
		//Vamos a la página principal de mi aplicación
		//driver.get("http://localhost:8280/sdi2-12");			
	}
	@After
	public void end()
	{
		//Cerramos el navegador
		driver.quit();
	}

	//PRUEBAS

	//	1.	[RegVal] Registro de Usuario con datos válidos.
	@Test	
	public void t01_RegVal() throws InterruptedException {
		driver.get("http://localhost:8280/sdi2-12/registrarse.xhtml");
		new PORegistro().rellenaFormulario(driver, "test",  "test",  "test",  "test@test.com",  "test", "test");

		Thread.sleep(1000);

		SeleniumUtils.textoNoPresentePagina(driver, "Error");

	}
	//	2.	[RegInval] Registro de Usuario con datos inválidos (contraseñas diferentes).
	@Test
	public void t02_RegInval() {

		driver.get("http://localhost:8280/sdi2-12/registrarse.xhtml");
		new PORegistro().rellenaFormulario(driver, "test2",  "test2",  "test2",  "test2@test.com",  "test", "test2");

		SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-messages-error", 10);
		SeleniumUtils.textoPresentePagina(driver, "Error");

	}
	//	3.	[IdVal] Identificación de Usuario registrado con datos válidos.
	@Test
	public void t03_IdVal() {

		driver.get("http://localhost:8280/sdi2-12/iniciarSesion.xhtml");
		new POInicioSesion().rellenaFormulario(driver, "test", "test");

		SeleniumUtils.EsperaCargaPagina(driver, "text", "test", 10); 
		SeleniumUtils.textoPresentePagina(driver, "test@test.com"); 

	}
	//	4.	[IdInval] Identificación de usuario registrado con datos inválidos.
	@Test
	public void t04_IdInval() {

		driver.get("http://localhost:8280/sdi2-12/iniciarSesion.xhtml");
		new POInicioSesion().rellenaFormulario(driver, "test2", "test");

		SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-messages-error", 10); 
		SeleniumUtils.textoPresentePagina(driver, "Error");

	}
	//	5.	[AccInval] Intento de acceso con URL desde un usuario no público (no identificado). Intento de acceso a vistas de acceso privado. 
	@Test
	public void t05_AccInval() {
		driver.get("http://localhost:8280/sdi2-12/a/misDatos.xhtml");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "List", 10); 

	}
	//	6.	[RegViajeVal] Registro de un viaje nuevo con datos válidos.
	@Test
	public void t06_RegViajeVal() throws InterruptedException {

		t03_IdVal();

		// Sesion iniciada

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "form-nav-bar:registrarViaje", 2); 
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "cod", 10);

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "form-content:botonRecargaSalida", 2);
		elementos.get(0).click();

		Thread.sleep(1000);

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "form-content:boton", 2);
		elementos.get(2).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Info", 10); 

	}
	//	7.	[RegViajeInVal] Registro de un viaje nuevo con datos inválidos. 
	@Test
	public void t07_RegViajeInVal() throws InterruptedException {

		t03_IdVal();

		// Sesion iniciada

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "form-nav-bar:registrarViaje", 2); 
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "cod", 10);

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "form-content:botonRecargaSalida", 2);
		elementos.get(0).click();

		Thread.sleep(1000);

		WebElement element = driver.findElement(By.id("form-content:availablePax"));
		element.click();
		element.clear();
		element.sendKeys("6");

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "form-content:boton", 2);
		elementos.get(2).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Error", 10); 

	}
	//	8.	[EditViajeVal] Edición de viaje existente con datos válidos.
	@Test
	public void t08_EditViajeVal() {

	}
	//	9.	[EditViajeInVal] Edición de viaje existente con datos inválidos.
	@Test
	public void t09_EditViajeInVal() {

	}
	//	10.	[CancelViajeVal] Cancelación de un viaje existente por un promotor.
	@Test
	public void t10_CancelViajeVal() {

		t03_IdVal();

		WebElement element = driver.findElement(By.id("form-nav-bar:misViajes"));
		element.click();

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "cancelarViaje", 2);
		elementos.get(elementos.size() - 1).click();

		SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-messages-info", 10);
		SeleniumUtils.textoPresentePagina(driver, "Info");

	}
	//	11.	[CancelMulViajeVal] Cancelación de múltiples viajes existentes por un promotor.
	@Test
	public void t11_CancelMulViajeVal() throws InterruptedException {

		t06_RegViajeVal();
		WebElement element = driver.findElement(By.id("form-nav-bar:cerrarSesion"));
		element.click();
		Thread.sleep(1500);
		t06_RegViajeVal();

		element = driver.findElement(By.id("form-nav-bar:misViajes"));
		element.click();

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-chkbox-box", 2);
		Actions builder = new Actions(driver);
		builder.moveToElement(elementos.get(elementos.size() - 1)).perform(); 
		elementos.get(elementos.size() - 1).click();
		builder.moveToElement(elementos.get(elementos.size() - 2)).perform(); 
		elementos.get(elementos.size() - 2).click();

		element = driver.findElement(By.id("form-content:botonCancelar"));
		element.click();

		SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-messages-info", 10);
		SeleniumUtils.textoPresentePagina(driver, "Info");

	}
	//	12.	[Ins1ViajeAceptVal] Inscribir en un viaje un solo usuario y ser admitido por el promotor.
	@Test
	public void t12_Ins1ViajeAceptVal() {

	}
	//	13.	[Ins2ViajeAceptVal] Inscribir en un viaje dos usuarios y ser admitidos los dos por el promotor.
	@Test
	public void t13_Ins2ViajeAceptVal() {

	}
	//	14.	[Ins3ViajeAceptInval] Inscribir en un viaje (2 plazas máximo) dos usuarios y ser admitidos los dos y que un tercero intente inscribirse en ese mismo viaje pero ya no pueda por falta de plazas.
	@Test
	public void t14_Ins3ViajeAceptInval() {

	}
	//	15.	[CancelNoPromotorVal] Un usuario no promotor Cancela plaza.
	@Test
	public void t15_CancelNoPromotorVal() {

	}
	//	16.	[Rech1ViajeVal] Inscribir en un viaje un usuario que será admitido y después rechazarlo por el promotor.
	@Test
	public void t16_Rech1ViajeVal() {

	}
	//	17.	[i18N1] Cambio del idioma por defecto a un segundo idioma. (Probar algunas vistas)
	@Test
	public void t17_i18N1() {

		driver.get("http://localhost:8280/sdi2-12/");

		SeleniumUtils.EsperaCargaPagina(driver, "text", "List trips", 10);

		WebElement element = driver.findElement(By.id("form-footer:languajeES"));
		element.click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", "Listado de viajes", 10);

		element = driver.findElement(By.id("form-nav-bar:iniciarSesion"));
		element.click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", "Contraseña", 10);

		element = driver.findElement(By.id("form-nav-bar:registrarse"));
		element.click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", "Nombre", 10);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Apellidos", 10);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Correo electronico", 10);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Contraseña", 10);

	}
	//	18.	[i18N2] Cambio del idioma por defecto a un segundo idioma y vuelta al idioma por defecto. (Probar algunas vistas)
	@Test
	public void t18_i18N2() {

		t17_i18N1();

		WebElement element = driver.findElement(By.id("form-footer:languajeEN"));
		element.click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", "Name", 10);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Surname", 10);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Email", 10);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Password", 10);

		element = driver.findElement(By.id("form-nav-bar:iniciarSesion"));
		element.click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", "Password", 10);

		element = driver.findElement(By.id("form-nav-bar:listado"));
		element.click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", "List trips", 10);

	}
	//	19.	[OpFiltrado] Prueba para el filtrado opcional.
	@Test
	public void t19_OpFiltrado() throws InterruptedException {
		driver.get("http://localhost:8280/sdi2-12/");
		
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-column-filter", 2); 

		elementos.get(1).click();

		Actions builder = new Actions(driver);	    
		builder.sendKeys("Madrid").perform(); 

		Thread.sleep(1000);
	}
	//	20.	[OpOrden] Prueba para la ordenación opcional.
	@Test
	public void t20_OpOrden() {

	}
	//	21.	[OpPag] Prueba para la paginación opcional.
	@Test
	public void t21_OpPag() throws InterruptedException {
		
		//Creamos 6 viajes para que al menos exita una 2 pagina(los 3 de los otros test y 3 aqui)
		t06_RegViajeVal();
		WebElement element = driver.findElement(By.id("form-nav-bar:cerrarSesion"));
		element.click();
		Thread.sleep(1500);
		t06_RegViajeVal();
		element = driver.findElement(By.id("form-nav-bar:cerrarSesion"));
		element.click();
		Thread.sleep(1500);
		t06_RegViajeVal();
		
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-paginator-next", 2); 
				
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "(1", 2); 

		Actions builder = new Actions(driver);
        builder.moveToElement(elementos.get(0)).perform();   
        elementos.get(0).click();
		
		//Esperamos de nuevo
      	elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-paginator-next", 2); 
      	
        //movemos el raton sobre el botón "siguiente pagina" (el de abajo)		
        builder.moveToElement(elementos.get(1)).perform();   
		//Pinchamos el botón
        elementos.get(1).click();

		//Ahora comprobamos que se ha cargado la pagina 3 de 20.
      	elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "(2", 2); 

	}
	//	22.	[OpMante] Prueba del mantenimiento programado opcional.

	@Test
	public void t22_OpMante() {

	}
}