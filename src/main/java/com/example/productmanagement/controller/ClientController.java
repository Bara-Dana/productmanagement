package com.example.productmanagement.controller;

import com.example.productmanagement.entities.CartModel;
import com.example.productmanagement.entities.ClientModel;
import com.example.productmanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("register")
    public void registerClient(@RequestBody ClientModel clientModel) {

        clientService.RegisterClient(clientModel);
    }

    @PutMapping("addToCart/{clientId}/{productId}")
    public void addToCart(@PathVariable long clientId, @PathVariable long productId) {
        clientService.addToCart(clientId, productId);
    }


    @GetMapping("getCart/{clientId}")
    public CartModel getCart(@PathVariable long clientId) {
        CartModel cartModel = clientService.getCart(clientId);
        return cartModel;

    }
}
