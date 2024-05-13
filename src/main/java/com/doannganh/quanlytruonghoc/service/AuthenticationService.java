/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service;

import com.doannganh.quanlytruonghoc.models.User;
import com.doannganh.response.AuthenticationResponse;
import com.doannganh.response.ResponseObject;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
public interface AuthenticationService {

    ResponseObject register(Map<String, String> params, MultipartFile avatar);

    AuthenticationResponse login(String username, String password);

    User updateUser(User user, String newPass);
    
    User updateUser(Map<String, String> params, MultipartFile avatar, int userID);
}
