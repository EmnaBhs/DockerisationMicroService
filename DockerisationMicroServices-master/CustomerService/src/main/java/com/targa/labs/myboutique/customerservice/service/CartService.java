package com.targa.labs.myboutique.customerservice.service;


import com.targa.labs.myboutique.commons.dto.CartDto;
import com.targa.labs.myboutique.commons.dto.OrderDto;
import com.targa.labs.myboutique.customerservice.domain.Cart;
import com.targa.labs.myboutique.customerservice.domain.Customer;
import com.targa.labs.myboutique.customerservice.domain.enumeration.CartStatus;
import com.targa.labs.myboutique.customerservice.repository.CartRepository;
import com.targa.labs.myboutique.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderServiceClient orderService;

    public List<CartDto> findAll() {
        return this.cartRepository.findAll()
                .stream()
                .map(CartService::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CartDto> findAllActiveCarts() {
        return this.cartRepository.findByStatus(CartStatus.NEW)
                .stream()
                .map(CartService::mapToDto)
                .collect(Collectors.toList());
    }

    public CartDto create(Long customerId) {
        if (this.getActiveCart(customerId) == null) {
            Customer customer = this.customerRepository.findById(customerId)
                    .orElseThrow(() -> new IllegalStateException("The Customer does not exist!"));

            Cart cart = new Cart(
                    customer,
                    CartStatus.NEW
            );

            OrderDto order = this.orderService.create(mapToDto(cart));
            cart.setOrderId(order.getId());

            return mapToDto(this.cartRepository.save(cart));
        } else {
            throw new IllegalStateException("There is already an active cart");
        }
    }

    @Transactional(readOnly = true)
    public CartDto findById(Long id) {
        return this.cartRepository.findById(id).map(CartService::mapToDto).orElse(null);
    }

    public void delete(Long id) {
        Cart cart = this.cartRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find cart with id " + id));

        cart.setStatus(CartStatus.CANCELED);

        this.cartRepository.save(cart);
    }

    public CartDto getActiveCart(Long customerId) {
        List<Cart> carts = this.cartRepository
                .findByStatusAndCustomerId(CartStatus.NEW, customerId);
        if (carts != null) {

            if (carts.size() == 1) {
                return mapToDto(carts.get(0));
            }
            if (carts.size() > 1) {
                throw new IllegalStateException("Many active carts detected !!!");
            }
        }

        return null;
    }

    public static CartDto mapToDto(Cart cart) {
        if (cart != null) {
            return new CartDto(
                    cart.getId(),
                    cart.getOrderId(),
                    CustomerService.mapToDto(cart.getCustomer()),
                    cart.getStatus().name()
            );
        }
        return null;
    }

}
