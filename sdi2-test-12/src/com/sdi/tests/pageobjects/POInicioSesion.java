package com.sdi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class POInicioSesion {

	public void rellenaFormulario(WebDriver driver, String login,
			String password) {

		WebElement element = driver.findElement(By.id("form-content:login"));
		element.click();
		element.clear();
		element.sendKeys(login);

		element = driver.findElement(By.id("form-content:password"));
		element.click();
		element.clear();
		element.sendKeys(password);

		By boton = By.id("form-content:boton");
		driver.findElement(boton).click();

	}
}
