package com.example.sigmacapi.cucumber.stepdefs;

import com.example.sigmacapi.cucumber.config.SpringIntegrationTest;
import com.example.sigmacapi.cucumber.config.WebDriverConfig;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps extends SpringIntegrationTest {

    private WebDriver driver;

    @After
    public void cerrarNavegador() {
        WebDriverConfig.quitDriver();
    }

    @Given("que estoy en la pagina de login")
    public void que_estoy_en_la_pagina_de_login() {
        driver = WebDriverConfig.getDriver();
        driver.get(getBaseUrl() + "/SIGMAC_con_Login.html");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("loginUser")));
    }

    @When("introduzco usuario {string} y contrasena {string}")
    public void introduzco_usuario_y_contrasena(String user, String pass) {
        driver.findElement(By.id("loginUser")).clear();
        driver.findElement(By.id("loginUser")).sendKeys(user);
        driver.findElement(By.id("loginPass")).clear();
        driver.findElement(By.id("loginPass")).sendKeys(pass);
    }

    @When("hago clic en el boton {string}")
    public void hago_clic_en_el_boton(String buttonText) {
        driver.findElement(By.cssSelector(".btn-login")).click();
    }

    @Then("el login es exitoso")
    public void el_login_es_exitoso() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("loginScreen")));
        assertTrue(true, "Login exitoso - pantalla de login ocultada");
    }

    @Then("veo el error de contrasena {string}")
    public void veo_el_error_de_contrasena(String mensaje) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("loginPassErr")));
        String texto = driver.findElement(By.id("loginPassErr")).getText();
        assertTrue(texto.contains(mensaje),
                "Se esperaba '" + mensaje + "' pero se obtuvo: '" + texto + "'");
    }

    @Then("veo el error de usuario {string}")
    public void veo_el_error_de_usuario(String mensaje) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("loginUserErr")));
        String texto = driver.findElement(By.id("loginUserErr")).getText();
        assertTrue(texto.contains(mensaje),
                "Se esperaba '" + mensaje + "' pero se obtuvo: '" + texto + "'");
    }
}