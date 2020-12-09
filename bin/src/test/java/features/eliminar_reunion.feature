Feature: Eliminar reunion funciona correctamente
	Scenario Outline: La reunion se elimina correctamente de la base de datos.
		Given existe una reunion con "<tema>", "<descripcion>", "<horaInicio>", "<horaFin>", "<asistente>" y "<convocante>"
		When  se elimina de la base de datos.
		Then al intentar recuperarla no existe.
	
	Examples: 
      | tema | descripcion | horaInicio | horaFin | asistente | convocante |
      | tema5 | descripcion5 | 21/11/2020 16:00 | 21/11/2020 17:00 | asistente5 | convocante5 |
      | tema6 | descripcion6 | 22/11/2020 17:00 | 22/11/2020 18:00 | asistente6 | convocante6 |
      | tema7 | descripcion7 | 23/11/2020 18:00 | 23/11/2020 19:00 | asistente7 | convocante7 |
      | tema8 | descripcion8 | 20/11/2020 19:00 | 20/11/2020 20:00 | asistente8 | convocante8 |
   