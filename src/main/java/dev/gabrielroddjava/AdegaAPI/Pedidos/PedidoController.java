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
    @GetMapping("/mostrarporid")
    public String mostrarPedidoPorId() {
        return "mostrando por id";
    }

    //POST - Criar novo pedido
    @PostMapping("/criar")
    public PedidoModel criarPedido(@RequestBody PedidoModel novoPedido){
        return pedidoService.criarPedido(novoPedido);
    }

    //PUT - Atualizar pedido
    @PutMapping("/editar")
    public String editarPedido() {
        return "editando pedido";
    }

    //DELETE - Deletar pedido
    @DeleteMapping("/deletar")
    public String deletarPedido() {
        return "Deletando pedido";
    }

}
