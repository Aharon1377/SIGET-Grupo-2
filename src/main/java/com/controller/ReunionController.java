
package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Reunion;
import com.persistence.ReunionRepository;

	@CrossOrigin()
	@RestController
	@RequestMapping("reuniones")
	public class ReunionController { 
		@Autowired
		private ReunionRepository reunionRepository;
		

		@GetMapping("getAll")
	    public List<Reunion> getAll(){
	        
	        return this.reunionRepository.findAll();
	    }
		
		@GetMapping("get")
	    public List<Reunion> get(@RequestParam(name = "asistentes") String asistentes){
	        

	        return this.reunionRepository.findByAsistentesIn(asistentes);
	    }
		
		
		@GetMapping("getHora")
	    public Optional<Reunion> getHora(@RequestParam(name = "horaInicio") String horaInicio){
	        

	        return this.reunionRepository.findOneByHoraInicio(horaInicio);
	    }
		
		
		
		@PostMapping("create")
	    public Reunion create(@RequestParam(name = "temas") String temas,
	    		@RequestParam(name = "descripcion") String descripcion,
	    		@RequestParam(name = "hora_inicio") String hora_inicio,
	    		@RequestParam(name = "hora_fin") String hora_fin,
	    		@RequestParam(name = "asistentes") String[] asistentes,
	    		@RequestParam(name = "convocante") String convocante){

	        return this.reunionRepository.insert(new Reunion(temas,descripcion,hora_inicio,
					hora_fin,asistentes,convocante));
	    }
		
		
		@PostMapping("delete")
	    public boolean delete(@RequestParam(name = "horaInicio") String horaInicio){
			
			Optional<Reunion> reunion = this.reunionRepository.findOneByHoraInicio(horaInicio);
			
	        if(!reunion.toString().equals("Optional.empty")) {
	        	this.reunionRepository.deleteByHoraInicio(horaInicio);
	        	return true;
	        }
	        return false;
	    }
}
