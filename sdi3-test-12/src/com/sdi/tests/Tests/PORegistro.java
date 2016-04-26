package com.sdi.tests.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PORegistro {

	public void rellenaFormulario(WebDriver driver, String login, String name,
			String surname, String email, String password, String repassword) {

		WebElement element = driver.findElement(By.id("form-content:login"));
		element.click();
		element.clear();
		element.sendKeys(login);

		element = driver.findElement(By.id("form-content:name"));
		element.click();
		element.clear();
		element.sendKeys(name);

		element = driver.findElement(By.id("form-content:surname"));
		element.click();
		element.clear();
		element.sendKeys(surname);

		element = driver.findElement(By.id("form-content:email"));
		element.click();
		element.clear();
		element.sendKeys(email);

		element = driver.findElement(By.id("form-content:password"));
		element.click();
		element.clear();
		element.sendKeys(password);

		element = driver.findElement(By.id("form-content:repassword"));
		element.click();
		element.clear();
		element.sendKeys(repassword);

		By boton = By.id("form-content:boton");
		driver.findElement(boton).click();

	}

}
