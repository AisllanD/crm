package com.sag.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sag.crm.entities.Cliente;
import com.sag.crm.entities.Consulta;
import com.sag.crm.entities.enums.AgendaStatus;
import com.sag.crm.repositories.ConsultaRepository;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaReposutory; 
	
	@Autowired
	private ClienteService clienteService;

	public Consulta AgendaConsulta(Long id, Consulta consulta) {
		Cliente cliente = clienteService.encontraClienteId(id);
		consulta.setCliente(cliente);
		consulta.setAgendaStatus(AgendaStatus.MARCADO);
		
		cliente = clienteService.atualizaEstagioAgendaMarcada(id);
		
		return consultaReposutory.save(consulta);
	}
	
	
}
