package dev.gabrielroddjava.AdegaAPI.Dtos;

import dev.gabrielroddjava.AdegaAPI.Item.ItemPedidoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private LocalDateTime dataDaVenda;
    private double valorTotalPedido;
    private List<ItemPedidoModel> itensPedido;
}
