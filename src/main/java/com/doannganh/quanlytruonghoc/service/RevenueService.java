/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service;

/**
 *
 * @author ACER
 */
public interface RevenueService {

    Long getTotalRevenueByWeek(int year, int week);

    Long getTotalRevenueByMonth(int year, int month);

    Long getTotalRevenueByQuarter(int year, int quarter);

    Long getTotalRevenueByYear(int year);
}
