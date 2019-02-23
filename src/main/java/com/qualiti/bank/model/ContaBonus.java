package com.qualiti.bank.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/*armazenar o bonus acumulado
 * a cada deposito, 10% deve ficar armazenador na conta
 */

@Entity
@DiscriminatorValue(value="BONUS")
public class ContaBonus extends Conta {

	private double bonus;
	@Override //redefinicao de metodo da classe pai
	public void creditar(double valor){
		bonus = bonus + valor*0.1;
		super.creditar(valor);
		
	}
	public void renderBonus(){
		super.creditar(bonus);//credita o bonus no saldo e depois zera
		bonus=0;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	
}
