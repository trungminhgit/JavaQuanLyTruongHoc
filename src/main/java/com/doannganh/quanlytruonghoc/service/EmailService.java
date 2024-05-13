/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service;

import com.doannganh.quanlytruonghoc.models.User;
import com.doannganh.quanlytruonghoc.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private UserRepository userRepo;
    
    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    
    
    public void sendEmailToUser(String subject, String text, int userID) {
        User user = userRepo.findByUserID(userID);
            sendEmail(user.getEmail(), subject, text);
    }
    
}
