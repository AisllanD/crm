package com.sag.crm.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sag.crm.entities.Cliente;
import com.sag.crm.services.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/cliente")
	public ResponseEntity<Cliente> cadastraLead(@RequestBody Cliente cliente){
		return new  ResponseEntity<Cliente>(clienteService.cadastraLead(cliente), HttpStatus.OK);
	}
	
	@GetMapping("/cliente")
	public ResponseEntity<Collection<Cliente>> RecuperaClientes(){
		return new ResponseEntity<Collection<Cliente>>(clienteService.RecuperaClientes(),HttpStatus.OK);
		
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<Cliente> atualizaCadastroCliente(@PathVariable long id, @RequestBody Cliente cliente ){
		return new  ResponseEntity<Cliente>(clienteService.atualizaCadastroCliente(id, cliente), HttpStatus.OK);
	}
	
}
