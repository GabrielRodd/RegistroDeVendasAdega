package dev.gabrielroddjava.AdegaAPI.Produtos;

import dev.gabrielroddjava.AdegaAPI.Dtos.ProdutoDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    //Injetando service
    ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping("boasvindas")
    public String boasVindasTeste() {
        return "boas vindas a rota";
    }

    //POST - Criar Produto
    @PostMapping("/criar")
    public ProdutoDTO criarProduto(@RequestBody ProdutoDTO novoProduto) {
        return produtoService.cadastrarNovoProduto(novoProduto);
    }

    //GET - Mostrar todos produtos
    @GetMapping("/mostrar")
    public List<ProdutoDTO> mostrarTodosProdutos() {
        return produtoService.listarProdutos();
    }

    //GET - Mostrar produtos por ID
    @GetMapping("/mostrar/{id}")
    public ProdutoDTO mostrarProdutoPorID(@PathVariable Long id) {
        return produtoService.mostrarProdutoPorId(id);
    }

    //PUT - Editar produto
    @PutMapping("/editar/{id}")
    public ProdutoDTO editarProdutoPorID(@PathVariable Long id, @RequestBody ProdutoDTO produtoAtualizado) {
        return produtoService.atualizarProdutoPorId(id, produtoAtualizado);
    }

    //DELETE - Deletar produto
    @DeleteMapping("/deletar/{id}")
    public void deletarProdutoPorID(@PathVariable Long id) {
        produtoService.deletarProdutoPorId(id);
    }
}
