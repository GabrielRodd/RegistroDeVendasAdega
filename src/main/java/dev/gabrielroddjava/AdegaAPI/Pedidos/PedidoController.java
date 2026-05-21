package dev.gabrielroddjava.AdegaAPI.Pedidos;

import dev.gabrielroddjava.AdegaAPI.Item.ItemPedidoModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    //Injetando o service
    PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    //GET - Mostrar todos os pedidos
    @GetMapping("/mostrar")
    public List<PedidoModel> mostrarTodosPedidos() {
        return pedidoService.mostrarTodosPedidos();
    }

    //GET - Mostrar pedidos por ID
    @GetMapping("/mostrar/{id}")
    public PedidoModel mostrarPedidoPorId(@PathVariable Long id) {
        return pedidoService.mostrarPedidoPorID(id);
    }

    //POST - Criar novo pedido
    @PostMapping("/criar")
    public PedidoModel criarPedido(@RequestBody PedidoModel novoPedido){
        return pedidoService.criarPedido(novoPedido);
    }

    //PUT - Editar pedido
    @PutMapping("/editar/{id}")
    public void editarPedido(@PathVariable Long id, @RequestBody PedidoModel pedidoAtualizado) {
        pedidoService.editarPedidoPorID(id, pedidoAtualizado);
    }

    //DELETE - Deletar pedido
    @DeleteMapping("/deletar/{id}")
    public void deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedidoPorID(id);
    }

}
