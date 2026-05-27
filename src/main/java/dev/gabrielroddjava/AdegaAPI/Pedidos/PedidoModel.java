package dev.gabrielroddjava.AdegaAPI.Pedidos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.gabrielroddjava.AdegaAPI.Item.ItemPedidoModel;
import dev.gabrielroddjava.AdegaAPI.Produtos.ProdutoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pedido_id")
    private Long id;

    @CreationTimestamp
    @Column(name = "Data_Venda")
    private LocalDateTime dataDaVenda;

    @Column(name = "Valor_Total")
    private double valorTotalPedido;

    //Um pedido pode conter muitos produtos
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemPedidoModel> itensPedido;
}
