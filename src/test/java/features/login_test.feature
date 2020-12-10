Feature: Login funciona correctamente
	Scenario Outline: El cliente hace la llamada POST /login a la API
		Given el usuario intenta logearse
		When el cliente hace la llamada POST /login con los parámetros username "<username>" y password "<password>"
		Then el cliente recibe la respuesta de que el login es "<correcto>"
	
	Examples: 
      | username | password | correcto |
      | dani | Daniel123 | True |
      | name3 | pass3 | False |
      | name4 | pass4 | False |