package dev.gabrielroddjava.AdegaAPI.Item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.gabrielroddjava.AdegaAPI.Pedidos.PedidoModel;
import dev.gabrielroddjava.AdegaAPI.Produtos.ProdutoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_item_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Pedido_id")
    @JsonBackReference
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "Produto_id")
    private ProdutoModel produto;

    @Column(name = "Quantidade")
    private int quantidadeComprada;

    @Column(name = "Valor_Unitario")
    private double valorUnitarioNaVenda;
}