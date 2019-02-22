package com.qualiti.bank.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualiti.bank.dao.ClienteDAO;
import com.qualiti.bank.exceptions.BancoException;
import com.qualiti.bank.model.Cliente;

@Service
public class ClienteBOImpl implements ClienteBO {

	@Autowired // chama o spring
	private ClienteDAO repositorio;

	@Override
	public void inserir(Cliente cliente) throws BancoException {
		if (cliente == null) {
			throw new BancoException("Cliente null");

		}

		if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
			throw new BancoException("Cpf nao informado");

		}
		if (cliente.getCpf().length() != 14) {
			throw new BancoException("Cpf invalido");

		}

		Optional<Cliente> clienteBusca = repositorio.findById(cliente.getCpf());
		if (!clienteBusca.isPresent()) {
			repositorio.save(cliente);

		} else {
			throw new BancoException("cpf ja cadastrado");
		}

	}

	@Override
	public void atualizar(Cliente cliente) throws BancoException {
		if (cliente == null) {
			throw new BancoException("Cliente null");

		}

		if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
			throw new BancoException("Cpf nao informado");

		}
		if (cliente.getCpf().length() != 14) {
			throw new BancoException("Cpf invalido");

		}

		Optional<Cliente> clienteBusca = repositorio.findById(cliente.getCpf());
		if (clienteBusca.isPresent()) {
			repositorio.save(cliente);

		} else {
			throw new BancoException("cpf nao cadastrado");
		}

	}

	@Override
	public void remover(String cpf) throws BancoException {

		if (cpf == null || cpf.isEmpty()) {
			throw new BancoException("Cpf nao informado");

		}
		if (cpf.length() != 14) {
			throw new BancoException("Cpf invalido");

		}
		Optional<Cliente> clienteBusca = repositorio.findById(cpf);
		if (clienteBusca.isPresent()) {
			repositorio.delete(clienteBusca.get());

		} else {
			throw new BancoException("cpf nao cadastrado");
		}
	}

	@Override
	public Cliente procurar(String cpf) throws BancoException {
		if (cpf == null || cpf.isEmpty()) {
			throw new BancoException("Cpf nao informado");

		}
		if (cpf.length() != 14) {
			throw new BancoException("Cpf invalido");

		}

		Optional<Cliente> clienteBusca = repositorio.findById(cpf);
		if (!clienteBusca.isPresent()) {
			return repositorio.save(clienteBusca.get());
		}
		return null;
	}
}
