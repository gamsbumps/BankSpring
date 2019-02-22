package com.qualiti.bank.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualiti.bank.dao.ContaDAO;
import com.qualiti.bank.exceptions.BancoException;
import com.qualiti.bank.model.Conta;
import com.qualiti.bank.model.TipoConta;

@Service
public class ContaBOImpl implements ContaBO {

	@Autowired
	private ContaDAO repositorio;

	@Override
	public void inserir(Conta conta) throws BancoException {
		if (conta == null) {
			throw new BancoException("Objeto conta null");

		}
		if (conta.getNumero().length() != 6) {
			throw new BancoException("N�mero de conta invalido");

		}

		if (conta.getNumero() == null || conta.getNumero().isEmpty()) {
			throw new BancoException("Numero da conta deve ser preenchido");

		}
		Optional<Conta> contaBusca = repositorio.findById(conta.getNumero());
		if (!contaBusca.isPresent()) {
			repositorio.save(conta);
		} else
			throw new BancoException("numero de conta ja cadastrado");
	}

	@Override
	public void atualizar(Conta conta) throws BancoException {
		if (conta == null) {
			throw new BancoException("Objeto conta null");

		}
		if (conta.getNumero().length() != 6) {
			throw new BancoException("Numero de conta invalido");

		}

		if (conta.getNumero() == null || conta.getNumero().isEmpty()) {
			throw new BancoException("Numero da conta deve ser preenchido");

		}
		Optional<Conta> contaBusca = repositorio.findById(conta.getNumero());
		if (contaBusca.isPresent()) {
			repositorio.save(conta);

		} else
			throw new BancoException("numero de conta nao existe");

	}

	@Override
	public void remover(String numero) throws BancoException {
		if (numero == null || numero.isEmpty()) {
			throw new BancoException("numero da conta deve ser informado");

		}
		if (numero.length() != 6) {
			throw new BancoException("Numero de conta invalido");

		}
		Optional<Conta> contaBusca = repositorio.findById(numero);
		if (contaBusca.isPresent()) {
			repositorio.delete(contaBusca.get());
		} else {
			throw new BancoException("Numero de conta nao cadastrado");
		}
	}

	@Override
	public Conta procurar(String numero) throws BancoException {
		if (numero == null || numero.isEmpty()) {
			throw new BancoException("numero da conta deve ser informado");

		}
		if (numero.length() != 6) {
			throw new BancoException("Numero de conta invalido");

		}
		Optional<Conta> contaBusca = repositorio.findById(numero);
		if (contaBusca.isPresent()) {
			return (contaBusca.get());
		}
		return null;
	}

	@Override
	public String gerarRelatorioContas() {

		return repositorio.gerarRelatorioContas();
	}

	@Override
	public double somarSaldo(TipoConta tipo) {

		return repositorio.somarSaldo(tipo);
	}

	@Override
	public void creditar(String numero, double valor) throws BancoException {
		if (numero == null || numero.isEmpty()) {
			throw new BancoException("numero da conta deve ser informado");

		}
		if (numero.length() != 6) {
			throw new BancoException("N�mero de conta invalido");

		}
		if (valor <= 0) {
			throw new BancoException("valor para cr�dito deve ser maior do que zero");

		}

		Optional<Conta> contaRetornoOptional = repositorio.findById(numero);
		

		if (contaRetornoOptional.isPresent()) {
			Conta contaRetorno = contaRetornoOptional.get();
			contaRetorno.creditar(valor);
			repositorio.save(contaRetorno);

		} else {
			throw new BancoException("numero da conta nao existe");
		}

	}

	@Override
	public void debitar(String numero, double valor) throws BancoException {
		if (numero == null || numero.isEmpty()) {
			throw new BancoException("numero da conta deve ser informado");

		}
		if (numero.length() != 6) {
			throw new BancoException("N�mero de conta invalido");

		}
		if (valor <= 0) {
			throw new BancoException("valor para cr�dito deve ser maior do que zero");

		}

		Optional<Conta> contaRetornoOptional = repositorio.findById(numero);

		if (contaRetornoOptional.isPresent()) {
			
			Conta contaRetorno = contaRetornoOptional.get();
			contaRetorno.debitar(valor);
			repositorio.save(contaRetorno);

		} else {
			throw new BancoException("numero da conta nao existe");
		}

	}

	@Override
	public void transferir(String numeroFonte, String numeroDestino, double valor) throws BancoException {
		if (numeroFonte == null || numeroFonte.isEmpty()) {
			throw new BancoException("numero da conta origem deve ser informado");

		}

		if (numeroFonte.length() != 6) {
			throw new BancoException("N�mero de conta origem invalido");

		}

		if (numeroDestino == null || numeroDestino.isEmpty()) {
			throw new BancoException("numero da conta destino deve ser informado");

		}

		if (numeroDestino.length() != 6) {
			throw new BancoException("N�mero de conta destino invalido");

		}

		if (valor <= 0) {
			throw new BancoException("valor para cr�dito deve ser maior do que zero");

		}

		Optional<Conta> contaOrigemOptional = repositorio.findById(numeroFonte);

		if (contaOrigemOptional.isPresent()) {
			

			Optional<Conta> contaDestinoOptional = repositorio.findById(numeroDestino);

			if (contaDestinoOptional.isPresent()) {
				
				Conta contaOrigem = contaOrigemOptional.get();
				Conta contaDestino = contaOrigemOptional.get();
				contaOrigem.transferir(contaDestino, valor);
				repositorio.save(contaDestino);
				
				repositorio.save(contaOrigem);
			} else {
				throw new BancoException("Numero de conta destino nao existe");
			}

		} else {
			throw new BancoException("Numero de conta origem nao existe");
		}

	}

}
