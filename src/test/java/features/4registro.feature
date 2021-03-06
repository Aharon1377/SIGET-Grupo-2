Feature: Registro funciona correctamente
	Scenario Outline: El usuario se registra correctamente en la base de datos.
		Given se registra al usuario con "<username>", "<password>" "<roleID>", "<nombre>", "<apellidos>", "<email>" y "<telefono>"
		When se recupera el usuario registrado de la base de datos
		Then los datos introducidos y los recuperados del registro coinciden
	
	Examples: 
      | username | password | roleID | nombre | apellidos | email | telefono |
      | username1 | password1 | 1 | nombre1 | apellido1 | email1 | 625456456 |
      | username2 | password1 | 3 | nombre2 | apellido2 | email2 | 625357357 |
      | username3 | password1 | 3 | nombre3 | apellido3 | email3 | 625159159 |
      | username4 | password1 | 3 | nombre4 | apellido4 | email4 | 625258258 |
