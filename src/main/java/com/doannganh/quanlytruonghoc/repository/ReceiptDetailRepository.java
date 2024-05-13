/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doannganh.quanlytruonghoc.repository;

import com.doannganh.quanlytruonghoc.models.ReceiptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository
public interface ReceiptDetailRepository extends JpaRepository<ReceiptDetail, Integer> {

    // Doanh thu theo tuần
    @Query("SELECT SUM(rd.price) "
            + "FROM ReceiptDetail rd "
            + "JOIN rd.receiptID r "
            + "WHERE YEAR(r.createDate) = :year "
            + "AND WEEK(r.createDate) = :week")
    Long getTotalRevenueByWeek(int year, int week);

    // Doanh thu theo tháng
    @Query("SELECT SUM(rd.price) "
            + "FROM ReceiptDetail rd "
            + "JOIN rd.receiptID r "
            + "WHERE YEAR(r.createDate) = :year "
            + "AND MONTH(r.createDate) = :month")
    Long getTotalRevenueByMonth(int year, int month);

    // Doanh thu theo quý
    @Query("SELECT SUM(rd.price) "
            + "FROM ReceiptDetail rd "
            + "JOIN rd.receiptID r "
            + "WHERE YEAR(r.createDate) = :year "
            + "AND QUARTER(r.createDate) = :quarter")
    Long getTotalRevenueByQuarter(int year, int quarter);

    // Doanh thu theo năm
    @Query("SELECT SUM(rd.price) "
            + "FROM ReceiptDetail rd "
            + "JOIN rd.receiptID r "
            + "WHERE YEAR(r.createDate) = :year")
    Long getTotalRevenueByYear(int year);
}
