package com.sag.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sag.crm.entities.Cliente;
import com.sag.crm.entities.Consulta;
import com.sag.crm.services.ConsultaService;

@RestController
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;
	
	@PostMapping("/consulta/agenda/consulta/{id}")
	public ResponseEntity<Consulta> AgendaConsulta(@PathVariable Long id, @RequestBody Consulta consulta){
		return new ResponseEntity<Consulta>(consultaService.agendaConsulta(id, consulta), HttpStatus.OK);
	}
	
	@PutMapping("/consulta/realiza/consulta/{id}")
	public ResponseEntity<Consulta> AgendaRealizada(@PathVariable Long id, @RequestBody Cliente cliente){
		return new ResponseEntity<Consulta>(consultaService.agendaRealizada(id, cliente), HttpStatus.OK);
	}
	
	@PutMapping("/consulta/realiza/orcamento/{id}")
	public ResponseEntity<Consulta> RealizaOrcamento(@PathVariable Long id, @RequestParam Double valor){
		return new ResponseEntity<Consulta>(consultaService.realizaOrcamento(id, valor), HttpStatus.OK);
	}
	
	@PutMapping("/consulta/fecha/orcamento/{id}")
	public ResponseEntity<Consulta> FechaOrcamento(@PathVariable Long id, @RequestParam String dataPagamento){
		return new ResponseEntity<Consulta>(consultaService.fechaOrcamento(id, dataPagamento), HttpStatus.OK);
	}
}
