/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service.impl;

import com.doannganh.quanlytruonghoc.models.Cart;
import com.doannganh.quanlytruonghoc.models.Receipt;
import com.doannganh.quanlytruonghoc.models.ReceiptDetail;
import com.doannganh.quanlytruonghoc.models.User;
import com.doannganh.quanlytruonghoc.repository.ReceiptDetailRepository;
import com.doannganh.quanlytruonghoc.repository.ReceiptRepository;
import com.doannganh.quanlytruonghoc.service.EmailService;
import com.doannganh.quanlytruonghoc.service.SaleService;
import java.io.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private UserDetailsServiceImpl userDetailServiceImpl;
    @Autowired
    private ReceiptRepository receiptRepo;
    @Autowired
    private ReceiptDetailRepository receiptDetailRepo;
    @Autowired
    private RoomServiceImpl roomServiceImpl;
    
    @Autowired
    private EmailService emailService;

    @Override
    public boolean addReceipt(Map<String, Cart> cartItems) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StringBuilder receiptDetails = new StringBuilder();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Receipt receipt = new Receipt();
        receipt.setCreateDate(LocalDateTime.now());
        receipt.setUserID((User) this.userDetailServiceImpl.findByUserName(authentication.getName()));
        this.receiptRepo.save(receipt);

        receiptDetails.append("RECEIPT DETAILS: \n\n");
        receiptDetails.append(String.format("%-15s\t%-26s\t\t%-26s\t\t%-12s\n", "Room", "Start time", "Finish time", "Price"));
        
        for (Cart c : cartItems.values()) {
            ReceiptDetail receiptDetail = new ReceiptDetail();
            receiptDetail.setNum(c.getNum());
            receiptDetail.setPrice(c.getPrice());
            receiptDetail.setStartTime(c.getStartTime());
            receiptDetail.setFinishTime(c.getFinishTime());
            receiptDetail.setReceiptID(receipt);
            receiptDetail.setRoomID(this.roomServiceImpl.findRoomByRoomID(c.getRoomID()));
            this.receiptDetailRepo.save(receiptDetail);
            //Thêm chi tiết đơn hàng vào nội dung mail
            double price = (double) receiptDetail.getPrice();
            String startTime = receiptDetail.getStartTime().format(dateTimeFormatter);  
            String finishTime = receiptDetail.getFinishTime().format(dateTimeFormatter); 
            receiptDetails.append(String.format(
                "%-15s\t%-25s\t%-25s\t%12.2f\n",
                receiptDetail.getRoomID().getRoomName(),
                startTime,
                finishTime,
                price
            ));
        }
        //Gửi email sau khi lưu đơn hàng
        User user = (User) this.userDetailServiceImpl.findByUserName(authentication.getName());
        emailService.sendEmailToUser(
            "Receipt Confirmation",
            receiptDetails.toString(),
            user.getUserID()
        );
        return true;
    }

}
