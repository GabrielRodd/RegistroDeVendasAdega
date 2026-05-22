package dev.gabrielroddjava.AdegaAPI.Mappers;

import dev.gabrielroddjava.AdegaAPI.Dtos.ProdutoDTO;
import dev.gabrielroddjava.AdegaAPI.Produtos.ProdutoModel;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoModel toModel(ProdutoDTO produtoDTO) {
        ProdutoModel produtoModel = new ProdutoModel();

        produtoModel.setId(produtoDTO.getId());
        produtoModel.setQtdEstoque(produtoDTO.getQtdEstoque());
        produtoModel.setNome(produtoDTO.getNome());
        produtoModel.setValor(produtoDTO.getValor());

        return produtoModel;
    }

    public ProdutoDTO toDTO(ProdutoModel produtoModel) {
        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setId(produtoModel.getId());
        produtoDTO.setQtdEstoque(produtoModel.getQtdEstoque());
        produtoDTO.setNome(produtoModel.getNome());
        produtoDTO.setValor(produtoModel.getValor());

        return produtoDTO;
    }
}
