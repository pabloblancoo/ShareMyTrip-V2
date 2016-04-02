package com.sdi.tests.Tests;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
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

	public SDI2_Tests() {
	}

	@Before
	public void run() {
		// Creamos el driver para Firefox. Recomendable usar Firefox.
		driver = new FirefoxDriver();
	}

	@After
	public void end() {
		// Cerramos el navegador
		driver.quit();
	}

	// PRUEBAS

	/**
	 * Registro de Usuario con datos válidos.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t01_RegVal() throws InterruptedException {
		driver.get("http://localhost:8280/sdi2-12/registrarse.xhtml");
		new PORegistro().rellenaFormulario(driver, "test", "test", "test",
				"test@test.com", "test", "test");

		Thread.sleep(1000);

		SeleniumUtils.textoNoPresentePagina(driver, "login");

	}

	/**
	 * Registro de Usuario con datos inválidos (contraseñas diferentes).
	 */
	@Test
	public void t02_RegInval() {

		driver.get("http://localhost:8280/sdi2-12/registrarse.xhtml");
		new PORegistro().rellenaFormulario(driver, "test2", "test2", "test2",
				"test2@test.com", "test2", "test");

		SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-messages-error",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Error");

	}

	/**
	 * Identificación de Usuario registrado con datos válidos.
	 */
	@Test
	public void t03_IdVal() {

		driver.get("http://localhost:8280/sdi2-12/iniciarSesion.xhtml");
		new POInicioSesion().rellenaFormulario(driver, "test", "test");

		SeleniumUtils.EsperaCargaPagina(driver, "text", "test", 10);
		SeleniumUtils.textoPresentePagina(driver, "test@test.com");

	}

	/**
	 * Identificación de usuario registrado con datos inválidos.
	 */
	@Test
	public void t04_IdInval() {

		driver.get("http://localhost:8280/sdi2-12/iniciarSesion.xhtml");
		new POInicioSesion().rellenaFormulario(driver, "test2", "test2");

		SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-messages-error",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Error");

	}

	/**
	 * Intento de acceso con URL desde un usuario no público (no identificado).
	 * Intento de acceso a vistas de acceso privado.
	 */
	@Test
	public void t05_AccInval() {
		driver.get("http://localhost:8280/sdi2-12/a/misDatos.xhtml");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "List", 10);

	}

	/**
	 * Registro de un viaje nuevo con datos válidos.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t06_RegViajeVal() throws InterruptedException {

		t03_IdVal();

		// Sesion iniciada

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "form-nav-bar:registrarViaje", 10);
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "cod", 10);
		Thread.sleep(500);
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-content:botonRecargaSalida", 2);
		elementos.get(0).click();

		Thread.sleep(1000);

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-content:boton", 2);
		elementos.get(2).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Info", 10);

	}

	/**
	 * Registro de un viaje nuevo con datos inválidos.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t07_RegViajeInVal() throws InterruptedException {

		t03_IdVal();

		// Sesion iniciada

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "form-nav-bar:registrarViaje", 2);
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "cod", 10);

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-content:botonRecargaSalida", 2);
		elementos.get(0).click();

		Thread.sleep(1000);

		WebElement element = driver.findElement(By
				.id("form-content:availablePax"));
		element.click();
		element.clear();
		element.sendKeys("6");

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-content:boton", 2);
		elementos.get(2).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Error", 10);

	}

	/**
	 * Edición de viaje existente con datos válidos.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t08_EditViajeVal() throws InterruptedException {

		t03_IdVal();

		// Sesion iniciada

		WebElement element = driver
				.findElement(By.id("form-nav-bar:misViajes"));
		element.click();

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "editarViaje", 2);
		elementos.get(elementos.size() - 1).click();

		SeleniumUtils.EsperaCargaPagina(driver, "id", "botonActualizar", 10);

		element = driver.findElement(By.id("form-content:maxPax"));
		element.click();
		element.clear();
		element.sendKeys("3");

		element = driver.findElement(By.id("form-content:availablePax"));
		element.click();
		element.clear();
		element.sendKeys("2");

		SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-content:botonActualizar", 10);
		element = driver.findElement(By.id("form-content:botonActualizar"));
		element.click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", "Info", 10);

	}

	/**
	 * Edición de viaje existente con datos inválidos.
	 */
	@Test
	public void t09_EditViajeInVal() {

		t03_IdVal();

		// Sesion iniciada

		WebElement element = driver
				.findElement(By.id("form-nav-bar:misViajes"));
		element.click();

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "editarViaje", 2);
		elementos.get(elementos.size() - 1).click();

		SeleniumUtils.EsperaCargaPagina(driver, "id", "botonActualizar", 10);

		element = driver.findElement(By.id("form-content:maxPax"));
		element.click();
		element.clear();
		element.sendKeys("1");

		element = driver.findElement(By.id("form-content:availablePax"));
		element.click();
		element.clear();
		element.sendKeys("1234");

		SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-content:botonActualizar", 10);
		element = driver.findElement(By.id("form-content:botonActualizar"));
		element.click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", "Error", 10);

	}

	/**
	 * Cancelación de un viaje existente por un promotor.
	 */
	@Test
	public void t10_CancelViajeVal() {

		t03_IdVal();

		WebElement element = driver
				.findElement(By.id("form-nav-bar:misViajes"));
		element.click();

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "cancelarViaje", 2);
		elementos.get(elementos.size() - 1).click();

		SeleniumUtils
				.EsperaCargaPagina(driver, "class", "ui-messages-info", 10);
		SeleniumUtils.textoPresentePagina(driver, "Info");

	}

	/**
	 * Cancelación de múltiples viajes existentes por un promotor.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t11_CancelMulViajeVal() throws InterruptedException {

		t06_RegViajeVal();
		WebElement element = driver.findElement(By
				.id("form-nav-bar:cerrarSesion"));
		element.click();
		Thread.sleep(1500);
		t06_RegViajeVal();

		Thread.sleep(1000);
		element = driver.findElement(By.id("form-nav-bar:misViajes"));
		element.click();

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "ui-chkbox-box", 2);
		Actions builder = new Actions(driver);
		builder.moveToElement(elementos.get(elementos.size() - 1)).perform();
		elementos.get(elementos.size() - 1).click();
		builder.moveToElement(elementos.get(elementos.size() - 2)).perform();
		elementos.get(elementos.size() - 2).click();

		element = driver.findElement(By.id("form-content:botonCancelar"));
		element.click();

		SeleniumUtils
				.EsperaCargaPagina(driver, "class", "ui-messages-info", 20);
		SeleniumUtils.textoPresentePagina(driver, "Info");

	}

	/**
	 * Inscribir en un viaje un solo usuario y ser admitido por el promotor.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t12_Ins1ViajeAceptVal() throws InterruptedException {

		t06_RegViajeVal();
		cerrarSesion();

		Thread.sleep(1000);
		t06_RegViajeVal();
		cerrarSesion();
		Thread.sleep(1000);

		WebElement element = driver.findElement(By
				.id("form-nav-bar:iniciarSesion"));
		element.click();
		Thread.sleep(1000);
		new POInicioSesion().rellenaFormulario(driver, "test3", "test"); // Inicia
																			// sesion
																			// el
																			// Test3
		Thread.sleep(1000);
		element = driver.findElement(By.id("form-nav-bar:listado"));
		element.click();

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "moreInfo", 2);
		elementos.get(0).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"solicitarPlaza", 2); // Solicita plaza en el viaje
		elementos.get(0).click();

		cerrarSesion(); // Cierra sesion
		t03_IdVal(); // Inicia sesion Test

		element = driver.findElement(By.id("form-nav-bar:listado"));
		element.click();

		elementos = SeleniumUtils
				.EsperaCargaPagina(driver, "id", "moreInfo", 2); // Entrar en la
																	// informacion
																	// del viaje
		elementos.get(0).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "aceptar", 2); // Aceptar
																					// al
																					// usuario
																					// Test2
		elementos.get(0).click();

	}

	/**
	 * Inscribir en un viaje dos usuarios y ser admitidos los dos por el
	 * promotor.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t13_Ins2ViajeAceptVal() throws InterruptedException {

		driver.get("http://localhost:8280/sdi2-12/iniciarSesion.xhtml");
		Thread.sleep(1000);
		new POInicioSesion().rellenaFormulario(driver, "test4", "test"); // Inicia
																			// sesion
																			// el
																			// Test2

		WebElement element = driver.findElement(By.id("form-nav-bar:listado"));
		element.click();

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "moreInfo", 2);
		elementos.get(1).click(); // Solicitar plaza en el segundo viaje, ya que
									// el primero ya esta solicitada y
									// confirmada por otro viaje

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"solicitarPlaza", 2); // Solicita plaza en el viaje
		elementos.get(0).click();

		cerrarSesion(); // Cierra sesion

		driver.get("http://localhost:8280/sdi2-12/iniciarSesion.xhtml");
		Thread.sleep(1000);
		new POInicioSesion().rellenaFormulario(driver, "test5", "test"); // Inicia
																			// sesion
																			// el
																			// Test3

		element = driver.findElement(By.id("form-nav-bar:listado"));
		element.click();

		elementos = SeleniumUtils
				.EsperaCargaPagina(driver, "id", "moreInfo", 2);
		elementos.get(1).click(); // Solicitar plaza en el segundo viaje

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"solicitarPlaza", 2); // Solicita plaza en el viaje
		elementos.get(0).click();

		cerrarSesion(); // Cierra sesion

		t03_IdVal(); // Inicia sesion Test

		element = driver.findElement(By.id("form-nav-bar:listado"));
		element.click();

		elementos = SeleniumUtils
				.EsperaCargaPagina(driver, "id", "moreInfo", 2); // Entrar en la
																	// informacion
																	// del viaje
		elementos.get(1).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "aceptar", 2); // Aceptar
																					// al
																					// usuario
																					// Test2
		elementos.get(0).click();

		Thread.sleep(1000);

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "aceptar", 2); // Aceptar
																					// al
																					// usuario
																					// Test3
		elementos.get(0).click();

	}

	/**
	 * Inscribir en un viaje (2 plazas máximo) dos usuarios y ser admitidos los
	 * dos y que un tercero intente inscribirse en ese mismo viaje pero ya no
	 * pueda por falta de plazas.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t14_Ins3ViajeAceptInval() throws InterruptedException {

		t03_IdVal();
		registrarViaje("CiudadTest", 2);
		cerrarSesion();

		driver.get("http://localhost:8280/sdi2-12/iniciarSesion.xhtml");
		Thread.sleep(1000);
		new POInicioSesion().rellenaFormulario(driver, "test6", "test"); // Inicia
																			// sesion
																			// el
																			// Test6

		WebElement element = driver.findElement(By.id("form-nav-bar:listado"));
		element.click();

		// Esperamos que aparezcan los botones de ordenacion
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "sortable-column-icon", 2);

		// Pinchamos el primer criterio de ordenacion
		Thread.sleep(500); // Esta espera es para poder el efecto de ordenación
		elementos.get(0).click();
		Thread.sleep(2000);

		elementos = SeleniumUtils
				.EsperaCargaPagina(driver, "id", "moreInfo", 2);
		elementos.get(0).click(); // Solicitar plaza en el viaje

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"solicitarPlaza", 2); // Solicita plaza en el viaje
		elementos.get(0).click();

		cerrarSesion(); // Cierra sesion

		driver.get("http://localhost:8280/sdi2-12/iniciarSesion.xhtml");
		Thread.sleep(1000);
		new POInicioSesion().rellenaFormulario(driver, "test7", "test"); // Inicia
																			// sesion
																			// el
																			// Test7

		element = driver.findElement(By.id("form-nav-bar:listado"));
		element.click();
		// Esperamos que aparezcan los botones de ordenacion
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "class",
				"sortable-column-icon", 2);

		// Pinchamos el primer criterio de ordenacion
		Thread.sleep(500); // Esta espera es para poder el efecto de ordenación
		elementos.get(0).click();
		Thread.sleep(2000);

		elementos = SeleniumUtils
				.EsperaCargaPagina(driver, "id", "moreInfo", 2);
		elementos.get(0).click(); // Solicitar plaza en el viaje

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"solicitarPlaza", 2); // Solicita plaza en el viaje
		elementos.get(0).click();

		cerrarSesion(); // Cierra sesion

		t03_IdVal(); // Inicia sesion Test

		element = driver.findElement(By.id("form-nav-bar:listado"));
		element.click();

		// Esperamos que aparezcan los botones de ordenacion
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "class",
				"sortable-column-icon", 2);

		// Pinchamos el segundo criterio de ordenacion
		Thread.sleep(500); // Esta espera es para poder el efecto de ordenación
		elementos.get(0).click();
		Thread.sleep(2000);

		elementos = SeleniumUtils
				.EsperaCargaPagina(driver, "id", "moreInfo", 2); // Entrar en la
																	// informacion
																	// del viaje
		elementos.get(0).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "aceptar", 2); // Aceptar
																					// al
																					// usuario
																					// Test2
		elementos.get(0).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "aceptar", 2); // Aceptar
																					// al
																					// usuario
																					// Test3
		elementos.get(0).click();

		// Ya esta el viaje completo, y nadie mas puede incluirse

		cerrarSesion();

		driver.get("http://localhost:8280/sdi2-12/iniciarSesion.xhtml");
		Thread.sleep(1000);
		new POInicioSesion().rellenaFormulario(driver, "test8", "test"); // Inicia
																			// sesion
																			// el
																			// Test8

		element = driver.findElement(By.id("form-nav-bar:listado"));
		element.click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "class",
				"sortable-column-icon", 2);

		// Pinchamos el segundo criterio de ordenacion
		Thread.sleep(500); // Esta espera es para poder el efecto de ordenación
		elementos.get(0).click();
		Thread.sleep(2000);

		elementos = SeleniumUtils
				.EsperaCargaPagina(driver, "id", "moreInfo", 2);
		Assert.assertEquals(2, elementos.size()); // Ya que el otro no aparece

	}

	/**
	 * Un usuario no promotor Cancela plaza.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t15_CancelNoPromotorVal() throws InterruptedException {

		t06_RegViajeVal();
		WebElement element = driver.findElement(By
				.id("form-nav-bar:cerrarSesion"));
		element.click();

		Thread.sleep(1000);

		element = driver.findElement(By.id("form-nav-bar:iniciarSesion"));
		element.click();
		Thread.sleep(1000);
		new POInicioSesion().rellenaFormulario(driver, "test8", "test");
		element = driver.findElement(By.id("form-nav-bar:listado"));
		element.click();

		List<WebElement> elements = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "moreInfo", 10);
		elements.get(elements.size() - 1).click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", ":", 10);
		element = driver.findElement(By.id("form-content:solicitarPlaza"));
		element.click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", "Info", 10);
		element = driver.findElement(By.id("form-nav-bar:misViajes"));
		element.click();

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "cancelarViaje", 10);
		elementos.get(elementos.size() - 1).click();

		SeleniumUtils
				.EsperaCargaPagina(driver, "class", "ui-messages-info", 10);
		SeleniumUtils.textoPresentePagina(driver, "Info");

	}

	/**
	 * Inscribir en un viaje un usuario que será admitido y después rechazarlo
	 * por el promotor.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t16_Rech1ViajeVal() throws InterruptedException {

		t03_IdVal(); // Inicia sesion Test

		WebElement element = driver.findElement(By.id("form-nav-bar:listado"));
		element.click();

		// Esperamos que aparezcan los botones de ordenacion
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "class",
				"sortable-column-icon", 2);

		// Pinchamos el segundo criterio de ordenacion
		Thread.sleep(500); // Esta espera es para poder el efecto de ordenación
		elementos.get(0).click();
		Thread.sleep(2000);

		elementos = SeleniumUtils
				.EsperaCargaPagina(driver, "id", "moreInfo", 2); // En este
																	// viaje ya
																	// hay
																	// usuarios
																	// incluidos
		elementos.get(0).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "excluir", 2); // Excluir
		elementos.get(0).click();

	}

	/**
	 * Cambio del idioma por defecto a un segundo idioma. (Probar algunas
	 * vistas)
	 */
	@Test
	public void t17_i18N1() {

		driver.get("http://localhost:8280/sdi2-12/");

		SeleniumUtils.EsperaCargaPagina(driver, "text", "List trips", 10);

		WebElement element = driver
				.findElement(By.id("form-footer:languajeES"));
		element.click();

		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de viajes", 10);

		element = driver.findElement(By.id("form-nav-bar:iniciarSesion"));
		element.click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", "Contraseña", 10);

		element = driver.findElement(By.id("form-nav-bar:registrarse"));
		element.click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", "Nombre", 10);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Apellidos", 10);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Correo electronico",
				10);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Contraseña", 10);

	}

	/**
	 * Cambio del idioma por defecto a un segundo idioma y vuelta al idioma por
	 * defecto. (Probar algunas vistas)
	 */
	@Test
	public void t18_i18N2() {

		t17_i18N1();

		WebElement element = driver
				.findElement(By.id("form-footer:languajeEN"));
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

	/**
	 * Prueba para el filtrado opcional.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t19_OpFiltrado() throws InterruptedException {
		driver.get("http://localhost:8280/sdi2-12/");

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "ui-column-filter", 2);

		// Obtengo los datos que hay antes de filtrado
		List<WebElement> datos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "ui-widget-content", 2);

		elementos.get(1).click();

		Actions builder = new Actions(driver);
		// Filtro por algo que hara desaparecer todas las opciones
		builder.sendKeys("+").perform();
		Thread.sleep(5000);

		// Una vez filtrado tiene que haber menos datos
		List<WebElement> datosFiltrados = SeleniumUtils.EsperaCargaPagina(
				driver, "class", "ui-widget-content", 2);
		org.junit.Assert.assertTrue(datos.size() > datosFiltrados.size());
	}

	/**
	 * Prueba para la ordenación opcional.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t20_OpOrden() throws InterruptedException {
		driver.get("http://localhost:8280/sdi2-12/");

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "sortable-column-icon", 2);
		elementos.get(0).click();
		Thread.sleep(1500);

		// Obtengo las filas
		List<WebElement> filas = driver.findElements(By
				.cssSelector("tbody > tr"));
		String primero = filas.get(0).findElements(By.tagName("td")).get(0)
				.getText();

		// Compruebo que estan correctamente ordenadas
		for (WebElement fila : filas) {
			WebElement actual = fila.findElements(By.tagName("td")).get(0);
			org.junit.Assert
					.assertTrue(actual.getText().compareTo(primero) >= 0);
		}

	}

	/**
	 * Prueba para la paginación opcional.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void t21_OpPag() throws InterruptedException {

		// Creamos 6 viajes para que al menos exita una 2 pagina(los 3 de los
		// otros test y 3 aqui)
		t06_RegViajeVal();
		WebElement element = driver.findElement(By
				.id("form-nav-bar:cerrarSesion"));
		element.click();
		Thread.sleep(1500);
		t06_RegViajeVal();
		element = driver.findElement(By.id("form-nav-bar:cerrarSesion"));
		element.click();
		Thread.sleep(1500);
		t06_RegViajeVal();

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "ui-paginator-next", 2);

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "(1", 2);

		Actions builder = new Actions(driver);
		builder.moveToElement(elementos.get(0)).perform();
		elementos.get(0).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "class",
				"ui-paginator-next", 2);
		builder.moveToElement(elementos.get(1)).perform();
		elementos.get(1).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "(2", 2);

	}

	// METODOS AUXILIARES PARA TEST COMPLEJOS
	/**
	 * Cierra sesion
	 * 
	 * @throws InterruptedException
	 */
	private void cerrarSesion() throws InterruptedException {
		Thread.sleep(1000);
		WebElement element = driver.findElement(By
				.id("form-nav-bar:cerrarSesion"));
		element.click();
	}

	/**
	 * Registra un viaje desde una calle de salida, con determinado numero de
	 * plazas.
	 * 
	 * @param calleSalida
	 * @param plazas
	 * @throws InterruptedException
	 */
	private void registrarViaje(String ciudadSalida, int plazas)
			throws InterruptedException {
		// Sesion iniciada

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "form-nav-bar:registrarViaje", 2);
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "cod", 10);

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-content:botonRecargaSalida", 2);
		elementos.get(0).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-content:departureCity_input", 2);
		elementos.get(0).click();
		elementos.get(0).clear();
		elementos.get(0).sendKeys(ciudadSalida);

		WebElement element = driver.findElement(By.id("form-content:maxPax"));
		element.click();
		element.clear();
		element.sendKeys(String.valueOf(plazas));

		element = driver.findElement(By.id("form-content:availablePax"));
		element.click();
		element.clear();
		element.sendKeys(String.valueOf(plazas));

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-content:boton", 2);
		elementos.get(2).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Info", 10);

	}
}