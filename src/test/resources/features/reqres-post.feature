Feature:Registro de usuario
  como nuevo usuario de la pagina
  quiero registrarme
  para hacer transacciones

  Scenario: Registro exitoso
    Given que el cliente entro a la pagina.
    When crea un usuario.
    Then obtendra un token de autenticacion.
