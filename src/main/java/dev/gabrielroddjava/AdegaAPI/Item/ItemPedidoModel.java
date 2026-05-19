package dev.gabrielroddjava.AdegaAPI.Item;

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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoModel produto;

    private int quantidade;
    private double valorUnitarioNaVenda;
}