package dev.gabrielroddjava.AdegaAPI.Produtos;

import dev.gabrielroddjava.AdegaAPI.Pedidos.PedidoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(unique = true, name = "Produto")
    private String nome;

    @Column(name = "Preco")
    private double valor;

    @Column(name = "Quantidade_Estoque")
    private int qtdEstoque;

}
