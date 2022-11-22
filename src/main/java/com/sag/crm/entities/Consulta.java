package com.sag.crm.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sag.crm.entities.enums.AgendaStatus;
import com.sag.crm.entities.enums.OrcamentoStatus;

@Entity
public class Consulta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String procedimento;
	private String medico;
	private String data;
	private Double valor;
	
	private AgendaStatus agendaStatus;
	private OrcamentoStatus orcamentoStatus;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	public Consulta() {
		
	}

	public Consulta(String procedimento, String medico, String data, Double valor ,AgendaStatus agendaStatus,
			OrcamentoStatus orcamentoStatus, Cliente cliente) {
		super();
		this.procedimento = procedimento;
		this.medico = medico;
		this.data = data;
		this.valor = valor;
		this.agendaStatus = agendaStatus;
		this.orcamentoStatus = orcamentoStatus;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public AgendaStatus getAgendaStatus() {
		return agendaStatus;
	}

	public void setAgendaStatus(AgendaStatus agendaStatus) {
		this.agendaStatus = agendaStatus;
	}

	public OrcamentoStatus getOrcamentoStatus() {
		return orcamentoStatus;
	}

	public void setOrcamentoStatus(OrcamentoStatus orcamentoStatus) {
		this.orcamentoStatus = orcamentoStatus;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
