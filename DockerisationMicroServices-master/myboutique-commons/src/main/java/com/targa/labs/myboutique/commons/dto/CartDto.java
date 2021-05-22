package com.targa.labs.myboutique.commons.dto;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
public class CartDto {
    private Long id;
    private Long orderId;
    private CustomerDto customerDto;
    private String status;

}
