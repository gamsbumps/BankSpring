package com.qualiti.bank.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo",
			discriminatorType=DiscriminatorType.STRING)
public abstract class Pessoa extends BancoEntity<String> {
	

	
	@Id
	//@Column(name = "cpf") opcional quando o nome da coluna for igual ao nome do atributo
	private String cpf;
	
	//@Column(name = "nome")
	private String nome;
	
	@Column(name="datanascimento")//quando houver camel case, usar @column para enviar a coluna desejada 
	private LocalDate dataNascimento;
	
	@OneToOne(cascade=CascadeType.ALL)//cada pessoa tem um endereco
	@PrimaryKeyJoinColumn//a chave primaria de endereco sera a a chave de juncao
	private Endereco endereco;
	
	private String telefone;
	private String login;
	private String senha;
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(insertable=false, updatable=false)//quando for um enum, usar essa coluna
	private TipoPessoa tipo;
	
	
//	@Override 
//	public boolean equals(Object obj){
//		if(obj instanceof Pessoa){
//			
//			Pessoa pes = (Pessoa)obj;
//			
//			
//			return cpf.equals(((Pessoa) pes).getCpf());
//			
//			
//		}
//		return false;
//	}
	
	public Pessoa(String cpf,String nome){
		this.cpf = cpf;
		this.nome = nome;
	}
	
	
	
	public Pessoa() {
		
	}
	
	
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}



	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}



	public TipoPessoa getTipo() {
		return tipo;
	}



	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}
	

}
