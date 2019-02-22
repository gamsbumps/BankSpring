package com.qualiti.bank.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.qualiti.bank.model.TipoConta;

@Repository
public class ContaDAOImpl implements ContaDAOCustom{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public String gerarRelatorioContas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double somarSaldo(TipoConta tipo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
