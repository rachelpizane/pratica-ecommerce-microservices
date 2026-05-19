package edu.rachelpizane.icompras.faturamento.model;

public record Cliente(
        Long id,
        String nome,
        String cpf,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone
) {
}
