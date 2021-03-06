package com.qualiti.bank.model;
//usar 'extends' apos a classe + a classe que herda.

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="POUPANCA")
public class Poupanca extends Conta {
	
	public void renderJuros(double taxa){
		
		double saldoPoupanca = getSaldo();
		saldoPoupanca = saldoPoupanca+saldoPoupanca*taxa;
		setSaldo(saldoPoupanca);
	}
	

}
