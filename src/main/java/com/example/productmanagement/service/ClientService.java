package com.example.productmanagement.service;

import com.example.productmanagement.entities.CartModel;
import com.example.productmanagement.entities.ClientModel;
import com.example.productmanagement.entities.OrderStatusModel;
import com.example.productmanagement.entities.ProductModel;
import com.example.productmanagement.entities.repository.CartRepository;
import com.example.productmanagement.entities.repository.ClientRepository;
import com.example.productmanagement.entities.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;




    public void RegisterClient(ClientModel clientModel) {

        clientRepository.save(clientModel);

    }

    public void addToCart(long clientId, long productId) {
        Optional<ProductModel> productModelOptional = productRepository.findById(productId);
        if (!productModelOptional.isPresent()) {
            throw new RuntimeException("Product does not exist!");
        }
        ProductModel productModel = productModelOptional.get();


        Optional<ClientModel> clientModelOptional = clientRepository.findById(clientId);
        if (!clientModelOptional.isPresent()) {
            throw new RuntimeException("Client does not exist!");
        }
        ClientModel clientModel = clientModelOptional.get();

        List<CartModel> cartModelList = clientModel.getCarts();


        CartModel cartFound = null;
        for (CartModel cart : cartModelList) {
            if (cart.getStatus().equals(OrderStatusModel.OPEN)) {
                cartFound = cart;
            }
        }
        if (cartFound == null) {
            cartFound = new CartModel();
            cartFound.setStatus(OrderStatusModel.OPEN);
            cartFound.setClientModel(clientModel);

        }

        cartFound.getProducts().add(productModel);
        //Calculam si totalul

    }

    public CartModel getCart(long clientId) {
        Optional<ClientModel> clientModelOptional = clientRepository.findById(clientId);
        if (!clientModelOptional.isPresent()) {
            throw new RuntimeException("Client does not exist!");
        }
        ClientModel clientModel = clientModelOptional.get();

        List<CartModel> cartModelList = clientModel.getCarts();


        CartModel cartFound = null;
        for (CartModel cart : cartModelList) {
            if (cart.getStatus().equals(OrderStatusModel.OPEN)) {
                cartFound = cart;
            }
        }
        return cartFound;
    }
}
