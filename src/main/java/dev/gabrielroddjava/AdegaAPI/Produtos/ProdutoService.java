package dev.gabrielroddjava.AdegaAPI.Produtos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    //Injetando dependencia do Repository
    private ProdutoRepository produtoRepository;

    public ProdutoService (ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoModel cadastrarNovoProduto(ProdutoModel novoProduto) {
        return produtoRepository.save(novoProduto);
    }

    public List<ProdutoModel> listarProdutos() {
        return produtoRepository.findAll();
    }

    public ProdutoModel listarProdutoPorId(Long id) {
        Optional<ProdutoModel> produtoBuscado = produtoRepository.findById(id);
        return produtoBuscado.orElse(null);
    }

    public void deletarProdutoPorId(Long id) {
        ProdutoModel produtoDeletar = listarProdutoPorId(id);
        produtoRepository.delete(produtoDeletar);
    }

    public ProdutoModel atualizarProdutoPorId(Long id, ProdutoModel produtoAtualizado) {
        if (produtoRepository.existsById(id)) {
            produtoAtualizado.setId(id);
        }
        return produtoRepository.save(produtoAtualizado);
    }

}
