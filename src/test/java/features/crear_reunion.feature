Feature: Crear reunion funciona correctamente
	Scenario Outline: La reunion se crea correctamente en la base de datos.
		Given se crea la reunion con "<tema>", "<descripcion>", "<horaInicio>", "<horaFin>", "<asistente>", "<convocante>" y "<url>"
		When se recupera la reunion de la base de datos
		Then los datos introducidos y los recuperados de la base de datos coinciden
	
	Examples: 
      | tema | descripcion | horaInicio | horaFin | asistente | convocante | url
      | tema1 | descripcion1 | 24/11/2020 16:00 | 24/11/2020 17:00 | asistente1 | convocante1 | url1
      | tema2 | descripcion2 | 25/11/2020 17:00 | 25/11/2020 18:00 | asistente2 | convocante2 | url2
      | tema3 | descripcion3 | 26/11/2020 18:00 | 26/11/2020 19:00 | asistente3 | convocante3 | url3
      | tema4 | descripcion4 | 27/11/2020 19:00 | 27/11/2020 20:00 | asistente4 | convocante4 | url4
   