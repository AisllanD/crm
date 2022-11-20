package com.sag.crm.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sag.crm.entities.Cliente;
import com.sag.crm.entities.Consulta;
import com.sag.crm.entities.enums.Status;
import com.sag.crm.repositories.ClienteRepository;
import com.sag.crm.services.exceptions.ResourceNotFoundException;



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
	
	public Cliente encontraClienteId(Long id) {
		Optional<Cliente> obj =  clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Cliente atualizaEstagioAgendaMarcada(Long id) {
		Cliente cliente = encontraClienteId(id);
		cliente.setStatus(Status.AGENDA_MARCADA);
		return cliente;
	}
	
}
