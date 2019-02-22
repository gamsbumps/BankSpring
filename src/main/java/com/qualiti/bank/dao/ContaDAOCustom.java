package com.qualiti.bank.dao;

import com.qualiti.bank.model.TipoConta;

public interface ContaDAOCustom {

	String gerarRelatorioContas();
	double somarSaldo(TipoConta tipo);
}
