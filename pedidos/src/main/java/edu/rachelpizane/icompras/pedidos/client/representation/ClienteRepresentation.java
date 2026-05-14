package edu.rachelpizane.icompras.pedidos.client.representation;

public record ClienteRepresentation(
        Long id,
        String nome,
        String cpf,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone
) {}
