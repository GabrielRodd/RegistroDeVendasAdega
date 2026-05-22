package dev.gabrielroddjava.AdegaAPI.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
    private Long id;
    private Long produtoId;
    private String nomeProduto;
    private int quantidadeComprada;
    private double valorUnitarioNaVenda;
}
