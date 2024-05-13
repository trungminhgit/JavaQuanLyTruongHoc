/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service.impl;

import com.doannganh.quanlytruonghoc.repository.ReceiptDetailRepository;
import com.doannganh.quanlytruonghoc.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private ReceiptDetailRepository receiptDetailRepo;

    @Override
    public Long getTotalRevenueByWeek(int year, int week) {
        return this.receiptDetailRepo.getTotalRevenueByWeek(year, week);
    }

    @Override
    public Long getTotalRevenueByMonth(int year, int month) {
        return this.receiptDetailRepo.getTotalRevenueByMonth(year, month);
    }

    @Override
    public Long getTotalRevenueByQuarter(int year, int quarter) {
        return this.receiptDetailRepo.getTotalRevenueByQuarter(year, quarter);
    }

    @Override
    public Long getTotalRevenueByYear(int year) {
        return this.receiptDetailRepo.getTotalRevenueByYear(year);
    }

}
