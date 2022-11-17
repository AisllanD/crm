package com.sag.crm.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sag.crm.entities.Cliente;
import com.sag.crm.entities.enums.Status;
import com.sag.crm.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository; 
	
	
	public Cliente cadastraLead(Cliente cliente) {
		cliente.setStatus(Status.LEAD);
		return clienteRepository.save(cliente);
	}
	
	public Collection<Cliente> RecuperaClientes(){
		return clienteRepository.findAll();
	}
 
}
