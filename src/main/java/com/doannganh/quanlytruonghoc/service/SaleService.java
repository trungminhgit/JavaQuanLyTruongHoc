/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service;

import com.doannganh.quanlytruonghoc.models.Cart;
import java.util.Map;

/**
 *
 * @author ACER
 */
public interface SaleService {
    boolean addReceipt(Map<String, Cart> cartItems);
}
