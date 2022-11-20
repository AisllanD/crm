package com.sag.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sag.crm.entities.Cliente;
import com.sag.crm.entities.Consulta;
import com.sag.crm.services.ConsultaService;

@RestController
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;
	
	@PostMapping("/consulta/{id}")
	public ResponseEntity<Consulta> AgendaConsulta(@PathVariable long id, @RequestBody Consulta consulta){
		return new ResponseEntity<Consulta>(consultaService.AgendaConsulta(id, consulta), HttpStatus.OK);
	}
}
