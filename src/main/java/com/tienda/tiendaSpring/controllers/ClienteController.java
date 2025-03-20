package com.tienda.tiendaSpring.controllers;

import com.tienda.tiendaSpring.domain.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private List<Cliente> clientes = new ArrayList(Arrays.asList(
            new Cliente("arm", "123", "Armstrong"),
            new Cliente("ald", "123", "Aldrin"),
            new Cliente("col", "123", "Collins")
    ));


    @GetMapping
    public ResponseEntity<?> getClientes(){
        return ResponseEntity.ok(clientes);
    }

//    @GetMapping("/clientes/{username}")
//    public Cliente getCliente(@PathVariable String username){
//        for (Cliente cliente: clientes){
//            if (cliente.getUsername().equalsIgnoreCase(username)){
//                return cliente;
//            }
//        }
//        return null;
//    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getCliente(@PathVariable String username){
        for (Cliente cliente: clientes){
            if(cliente.getUsername().equalsIgnoreCase(username)){
                return ResponseEntity.ok(cliente);
            }
        }
        return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(clientes.stream().
//                filter(cliente -> cliente.getUsername()
//                        .equalsIgnoreCase(username))
//                .findFirst().orElseThrow());
    }

    @PostMapping
    public ResponseEntity<?> altaCliente(@RequestBody Cliente cliente){
        clientes.add(cliente);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(cliente.getUsername())
                .toUri();
        return ResponseEntity.created(location).body(cliente);
    }

    @PutMapping
    public ResponseEntity<?> modificarCliente(@RequestBody Cliente cliente){
        Cliente clienteEncontrado = clientes.stream()
                .filter(cli -> cli.getUsername().equalsIgnoreCase(cliente.getUsername()))
                .findFirst().orElseThrow();
        clienteEncontrado.setPassword(cliente.getPassword());
        clienteEncontrado.setNombre(cliente.getNombre());
        return ResponseEntity.ok(clienteEncontrado);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity borrarCliente(@PathVariable String username){
        Cliente clienteEncontrado = clientes.stream()
                .filter(cli -> cli.getUsername().equalsIgnoreCase(username))
                .findFirst().orElseThrow();
        clientes.remove(clienteEncontrado);

        return ResponseEntity.noContent().build();
    }
}
