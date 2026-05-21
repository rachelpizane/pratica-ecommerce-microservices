package edu.rachelpizane.icompras.produtos.service;

import edu.rachelpizane.icompras.produtos.model.Produto;
import edu.rachelpizane.icompras.produtos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Optional<Produto> obterPorId(Long id) {
        return repository.findById(id);
    }

    public void inativar(Produto produto) {
        produto.setAtivo(false);
        repository.save(produto);
    }
}
