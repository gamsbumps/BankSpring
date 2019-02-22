package com.qualiti.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qualiti.bank.model.Conta;

public interface ContaDAO extends JpaRepository<Conta, String>, ContaDAOCustom {
	
	
	
}
