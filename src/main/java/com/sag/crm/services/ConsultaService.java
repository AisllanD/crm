package com.sag.crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sag.crm.entities.Cliente;
import com.sag.crm.entities.Consulta;
import com.sag.crm.entities.enums.AgendaStatus;
import com.sag.crm.entities.enums.OrcamentoStatus;
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
		
		consulta.setAgendaStatus(AgendaStatus.REALIZADO);
		atualizaCliente = clienteService.atualizaCadastroCliente(atualizaCliente.getId(), cliente);
		atualizaCliente = clienteService.atualizaEstagioAgendaRealizada(atualizaCliente.getId());
		
		return consultaReposutory.save(consulta);
	}
	
	
	public Consulta realizaOrcamento(Long id, Double valor) {
		Consulta consulta = encontraConsultaId(id);
		Cliente atualizaCliente = consulta.getCliente();
		
		consulta.setValor(valor);
		
		List<Consulta> consultas = atualizaCliente.getConsultas();
		Double valorTotal = valorTotalCliente(consultas);
		
		
		
		consulta.setOrcamentoStatus(OrcamentoStatus.REALIZADO);
		atualizaCliente.setValorTotal(valorTotal);
		atualizaCliente = clienteService.atualizaEstagioOrcamentoRealizado(atualizaCliente.getId());
		
		return consultaReposutory.save(consulta);
	}
	
	public Consulta fechaOrcamento(Long id, String dataPagamento) {
		Consulta consulta = encontraConsultaId(id);
		Cliente atualizaCliente = consulta.getCliente();
		
		consulta.setDataDePagamento(dataPagamento);
		consulta.setOrcamentoStatus(OrcamentoStatus.FECHADO);
		
		atualizaCliente = clienteService.atualizaEstagioOrcamentoFechado(atualizaCliente.getId());
		
		return consultaReposutory.save(consulta);
	}
	
	public Double valorTotalCliente (List<Consulta> consultas) {
		Double valorTotal = 0.0;
		
		for (int i = 0; i < consultas.size(); i++) {
			valorTotal = valorTotal + consultas.get(i).getValor();
		}
		
		return valorTotal;
	}

}
