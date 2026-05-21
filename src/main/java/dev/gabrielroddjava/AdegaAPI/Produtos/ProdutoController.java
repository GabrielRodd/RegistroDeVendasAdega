package dev.gabrielroddjava.AdegaAPI.Produtos;

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
    public ProdutoModel criarProduto(@RequestBody ProdutoModel novoProduto) {
        return produtoService.cadastrarNovoProduto(novoProduto);
    }

    //GET - Mostrar todos produtos
    @GetMapping("/mostrar")
    public List<ProdutoModel> mostrarTodosProdutos() {
        return produtoService.listarProdutos();
    }

    //GET - Mostrar produtos por ID
    @GetMapping("/mostrar/{id}")
    public ProdutoModel mostrarProdutoPorID(@PathVariable Long id) {
        return produtoService.mostrarProdutoPorId(id);
    }

    //PUT - Editar produto
    @PutMapping("/editar/{id}")
    public void editarProdutoPorID(@PathVariable Long id, @RequestBody ProdutoModel produtoAtualizado) {
        produtoService.atualizarProdutoPorId(id, produtoAtualizado);
    }

    //DELETE - Deletar produto
    @DeleteMapping("/deletar/{id}")
    public void deletarProdutoPorID(@PathVariable Long id) {
        produtoService.deletarProdutoPorId(id);
    }
}
