/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.controllers;

import com.doannganh.quanlytruonghoc.models.User;
import com.doannganh.quanlytruonghoc.service.AuthenticationService;

import com.doannganh.quanlytruonghoc.service.impl.AuthenticationServiceImpl;
import com.doannganh.quanlytruonghoc.service.impl.UserDetailsServiceImpl;
import com.doannganh.response.AuthenticationResponse;
import com.doannganh.response.ResponseObject;
import java.util.Map;
import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.status;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authService;

    @Autowired
    private UserDetailsServiceImpl userDetailServiceImpl;

    @PostMapping(path = "/register",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<ResponseObject> register(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
        return ResponseEntity.ok(authService.register(params, avatar));
    }

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) {
        return ResponseEntity.ok(authService.login(user.getUsername(), user.getPassword()));
    }

    @PutMapping("/users/{id}")
    @CrossOrigin
    public ResponseEntity<ResponseObject> updateUser(@PathVariable(value = "id") int userID,
            @RequestParam(value = "newPass") String newPass) {

        if (authService.updateUser(userDetailServiceImpl.findByUserID(userID), newPass) != null) {
            return ResponseEntity.status(200).body(new ResponseObject("OK", "Update password successfully", null));
        } else {
            return ResponseEntity.status(400).body(new ResponseObject("BAD REQUEST", "Update password failed", null));
        }
    }

    @PostMapping(path = "/admin/users/{id}", consumes = {
        MediaType.MULTIPART_FORM_DATA_VALUE,
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<ResponseObject> updateUser(@RequestParam Map<String, String> param, @RequestPart MultipartFile avatar,
            @PathVariable(value = "id") int userID) {
        User u = this.authService.updateUser(param, avatar, userID);
        if (u != null) {
            return ResponseEntity.status(200).body(new ResponseObject("OK", "Update user successfully", u));
        } else {
            return ResponseEntity.status(400).body(new ResponseObject("BAD REQUEST", "Update user failed", null));
        }
    }
}
