/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.controllers;

import com.doannganh.quanlytruonghoc.models.Room;
import com.doannganh.response.ResponseObject;
import com.doannganh.quanlytruonghoc.models.User;
import com.doannganh.quanlytruonghoc.service.impl.UserDetailsServiceImpl;
import com.doannganh.response.PageResponseObject;
import jakarta.persistence.TypedQuery;
import java.security.Principal;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping(path = "/api/v1")
public class ApiUserController {

    @Autowired
    private UserDetailsServiceImpl userServiceImpl;

    @RequestMapping("/admin/users/")
    @CrossOrigin
    public ResponseEntity<PageResponseObject> getUsers(@RequestParam Map<String, String> params) {
        try {
            // Lấy truy vấn có phân trang từ repository
            TypedQuery<User> query = userServiceImpl.getUsers(params);

            // Lấy danh sách phòng và tổng số mục
            List<User> users = query.getResultList();
            long totalElements = query.getMaxResults(); // Tổng số kết quả trên mỗi trang
            int pageSize = 8;

            // Tính tổng số trang dựa trên tổng số mục và kích thước trang
            int totalUsers = userServiceImpl.countUser();
            int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

            // Trả về kết quả và thông tin phân trang
            if (!users.isEmpty()) {
                return ResponseEntity.ok().body(new PageResponseObject("OK", "Rooms fetched successfully", users, totalPages, totalUsers));
            } else {
                return ResponseEntity.status(204).body(new PageResponseObject("NO CONTENT", "User not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new PageResponseObject("ERROR", "An error occurred", null));
        }
    }

    @DeleteMapping("/admin/delete-users/{id}")
    @CrossOrigin
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable(value = "id") int userID) {
        if (this.userServiceImpl.deleteUser(userID) == true) {
            return ResponseEntity.status(200).body(new ResponseObject("OK", "Delete user successfully", null));
        } else {
            return ResponseEntity.status(400).body(new ResponseObject("BAD REQUEST", "Delete user failed", null));
        }
    }

    @RequestMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<ResponseObject> currentUser(Principal user) {
        User u = this.userServiceImpl.findByUserName(user.getName());
        if (u != null) {
            return ResponseEntity.status(200).body(new ResponseObject("OK", "Get user successfully", u));
        } else {
            return ResponseEntity.status(400).body(new ResponseObject("BAD REQUEST", "Get user failde", ""));
        }
    }

    @RequestMapping(path = "/admin/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<ResponseObject> detailUser(@PathVariable(value = "id") int userID) {
        User user = this.userServiceImpl.findByUserID(userID);
        try {
            if (user != null) {
                return ResponseEntity.status(200).body(new ResponseObject("OK", "Get detail user succsessfully", user));
            } else {
                return ResponseEntity.status(204).body(new ResponseObject("NO CONTENT", "User not found", null));
            }
        } catch (Exception x) {
            return ResponseEntity.status(500).body(new ResponseObject("ERROR", "Server error, please try again", null));
        }
    }

}
