package com.sag.crm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sag.crm.entities.Cliente;
import com.sag.crm.entities.Consulta;
import com.sag.crm.entities.enums.AgendaStatus;
import com.sag.crm.repositories.ConsultaRepository;
import com.sag.crm.services.exceptions.ResourceNotFoundException;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaReposutory; 
	
	@Autowired
	private ClienteService clienteService;

	public Consulta agendaConsulta(Long id, Consulta consulta) {
		Cliente cliente = clienteService.encontraClienteId(id);
		consulta.setCliente(cliente);
		consulta.setAgendaStatus(AgendaStatus.MARCADO);
		
		cliente = clienteService.atualizaEstagioAgendaMarcada(id);
		
		return consultaReposutory.save(consulta);
	}
	
	public Consulta encontraConsultaId(Long id) {
		Optional<Consulta> obj =  consultaReposutory.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Consulta agendaRealizada(Long id, Cliente cliente) {
		Consulta consulta = encontraConsultaId(id);
		Cliente atualizaCliente = consulta.getCliente();
		
		
		atualizaCliente = clienteService.atualizaCadastroCliente(atualizaCliente.getId(), cliente);
		
		consulta.setAgendaStatus(AgendaStatus.REALIZADO);
		
		atualizaCliente = clienteService.atualizaEstagioAgendaRealizada(atualizaCliente.getId());
		
		return consultaReposutory.save(consulta);
	}
	
	
}
