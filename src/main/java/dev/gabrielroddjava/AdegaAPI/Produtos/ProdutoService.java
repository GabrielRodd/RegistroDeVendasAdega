package dev.gabrielroddjava.AdegaAPI.Produtos;

import dev.gabrielroddjava.AdegaAPI.Dtos.ProdutoDTO;
import dev.gabrielroddjava.AdegaAPI.Mappers.ProdutoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    //Injetando dependencia do Repository
    private ProdutoRepository produtoRepository;

    //Injetando dependencia do Repository
    private ProdutoMapper produtoMapper;

    public ProdutoService (ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    public ProdutoDTO cadastrarNovoProduto(ProdutoDTO novoProduto) {
        ProdutoModel novoProdutoModel = produtoMapper.toModel(novoProduto);
        produtoRepository.save(novoProdutoModel);
        return produtoMapper.toDTO(novoProdutoModel);
    }

    public List<ProdutoModel> listarProdutos() {
        return produtoRepository.findAll();
    }

    public ProdutoModel mostrarProdutoPorId(Long id) {
        Optional<ProdutoModel> produtoBuscado = produtoRepository.findById(id);
        return produtoBuscado.orElse(null);
    }

    public void deletarProdutoPorId(Long id) {
        ProdutoModel produtoDeletar = mostrarProdutoPorId(id);
        produtoRepository.delete(produtoDeletar);
    }

    public ProdutoModel atualizarProdutoPorId(Long id, ProdutoModel produtoAtualizado) {
        if (produtoRepository.existsById(id)) {
            produtoAtualizado.setId(id);
        }
        return produtoRepository.save(produtoAtualizado);
    }

}
