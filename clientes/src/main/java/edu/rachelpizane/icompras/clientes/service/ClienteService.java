package edu.rachelpizane.icompras.clientes.service;

import edu.rachelpizane.icompras.clientes.model.Cliente;
import edu.rachelpizane.icompras.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> obterPorId(Long id) {
        return repository.findById(id);
    }

    public void inativar(Cliente cliente) {
        cliente.setAtivo(false);
        repository.save(cliente);
    }
}
