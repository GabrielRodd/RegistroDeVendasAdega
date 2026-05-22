package dev.gabrielroddjava.AdegaAPI.Dtos;

import dev.gabrielroddjava.AdegaAPI.Produtos.ProdutoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String nome;
    private double valor;
    private int qtdEstoque;
}
