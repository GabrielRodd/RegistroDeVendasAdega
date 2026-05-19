package dev.gabrielroddjava.AdegaAPI.Produtos;

import dev.gabrielroddjava.AdegaAPI.Pedidos.PedidoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "tb_produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double valor;
    private int qtdEstoque;

}
