/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.controllers;

import com.doannganh.quanlytruonghoc.service.impl.RevenueServiceImpl;
import com.doannganh.response.ResponseObject;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping(path = "/api/v1")
public class ApiRevenueController {

    @Autowired
    private RevenueServiceImpl revenueServiceImpl;

    @GetMapping("/admin/revenue/week")
    @CrossOrigin
    public ResponseEntity<ResponseObject> totalRevenueWeek(@RequestParam(value = "year") int year, @RequestParam(value = "week") int week) {
        try {
            Long totalRevenue = this.revenueServiceImpl.getTotalRevenueByWeek(year, week);
            if (totalRevenue != null) {
                return ResponseEntity.status(200).body(new ResponseObject("OK", "Get total revenue in week successfully", totalRevenue));
            } else {
                return ResponseEntity.status(204).body(new ResponseObject("NO CONTENT", "No revenue data for this week", null));
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseObject("ERROR", "Get total revenue in week failed", null));
        }
    }

    @GetMapping("/admin/revenue/month")
    @CrossOrigin
    public ResponseEntity<ResponseObject> totalRevenueMonth(@RequestParam(value = "year") int year, @RequestParam(value = "month") int month) {
        try {
            Long totalRevenue = this.revenueServiceImpl.getTotalRevenueByMonth(year, month);
            if (totalRevenue != null) {
                return ResponseEntity.status(200).body(new ResponseObject("OK", "Get total revenue in month successfully", totalRevenue));
            } else {
                return ResponseEntity.status(204).body(new ResponseObject("NO CONTENT", "No revenue data for this month", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseObject("ERROR", "Get total revenue in month failed", null));
        }
    }

    @GetMapping("/admin/revenue/quarter")
    @CrossOrigin
    public ResponseEntity<ResponseObject> totalRevenueQuarter(@RequestParam(value = "year") int year, @RequestParam(value = "quarter") int quarter) {
        try {
            Long totalRevenue = this.revenueServiceImpl.getTotalRevenueByQuarter(year, quarter);
            if (totalRevenue != null) {
                return ResponseEntity.status(200).body(new ResponseObject("OK", "Get total revenue in quarter successfully", totalRevenue));
            } else {
                return ResponseEntity.status(204).body(new ResponseObject("NO CONTENT", "No revenue data for this quarter", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseObject("ERROR", "Get total revenue in quarter failed", null));
        }
    }

    @GetMapping("/admin/revenue/year")
    @CrossOrigin
    public ResponseEntity<ResponseObject> totalRevenueYear(@RequestParam(value = "year") int year) {
        try {
            Long totalRevenue = this.revenueServiceImpl.getTotalRevenueByYear(year);
            if (totalRevenue != null) {
                return ResponseEntity.status(200).body(new ResponseObject("OK", "Get total revenue in year successfully", totalRevenue));
            } else {
                return ResponseEntity.status(204).body(new ResponseObject("NO CONTENT", "No revenue data for this year", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseObject("ERROR", "Get total revenue in year failed", null));
        }
    }

}
