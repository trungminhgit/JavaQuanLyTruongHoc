/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.controllers;

import com.doannganh.quanlytruonghoc.models.Cart;
import com.doannganh.quanlytruonghoc.service.SaleService;
import com.doannganh.quanlytruonghoc.service.impl.SaleServiceImpl;
import com.doannganh.response.ResponseObject;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping(path = "/api/v1")
public class ApiSaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping(path = "/pay/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<ResponseObject> addReceipt(@RequestBody Map<String, Cart> cartItems) {
        if (this.saleService.addReceipt(cartItems)) {
            return ResponseEntity.status(200).body(new ResponseObject("OK", "Payment successfully", null));
        } else {
            return ResponseEntity.status(404).body(new ResponseObject("BAD REQUEST", "Payment failed", null));
        }
    }
}
