Feature: Se eliminan usuario correctamente
	Scenario Outline: El usuario se elimina correctamente en la base de datos.
		Given se elimina al usuario con "<username>"
		When se busca el usuario registrado de la base de datos
		Then el usuario ya no existe
	
	Examples: 
      | username |
      | username1 |
      | username2 |
      | username3 |
      | username4 |
