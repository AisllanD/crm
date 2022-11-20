package com.sag.crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sag.crm.entities.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
