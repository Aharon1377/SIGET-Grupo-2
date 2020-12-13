Feature: Comprobar nivel de acceso usuario
	Scenario Outline: El cliente hace la llamada GET /getID a la API
		Given el usuario intenta logearse con "<username>" y comprobamos su nivel de acceso
		When el cliente hace la llamada GET /getID con los parámetros username "<username>"
		Then el cliente recibe el "<nivel>" de acceso almacenado del usuario
	
	Examples: 
      | username | nivel |
      | victor | 1 |
      | Alberto | 3 |