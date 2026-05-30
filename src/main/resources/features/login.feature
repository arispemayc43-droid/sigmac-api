Feature: Autenticacion de usuario
  Como usuario registrado
  Quiero iniciar sesion en el sistema
  Para acceder a mi cuenta

  Background:
    Given que estoy en la pagina de login

  Scenario: Credenciales correctas
    When introduzco usuario "admin" y contrasena "admin123"
    And hago clic en el boton "Iniciar Sesion"
    Then el login es exitoso

  Scenario: Credenciales incorrectas - contrasena erronea
    When introduzco usuario "admin" y contrasena "wrongpass"
    And hago clic en el boton "Iniciar Sesion"
    Then veo el error de contrasena "Contraseña incorrecta"

  Scenario: Credenciales incorrectas - usuario inexistente
    When introduzco usuario "noexiste" y contrasena "1234"
    And hago clic en el boton "Iniciar Sesion"
    Then veo el error de usuario "Usuario no encontrado"