/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doannganh.quanlytruonghoc.repository;

import com.doannganh.quanlytruonghoc.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

    Receipt findByReceiptID(int receiptID);

}
