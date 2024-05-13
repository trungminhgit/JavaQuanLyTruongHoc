/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.controllers;

import com.doannganh.quanlytruonghoc.models.Role;
import com.doannganh.quanlytruonghoc.service.impl.RoleServiceImpl;
import com.doannganh.response.ResponseObject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping(path = "/api/v1")
public class ApiRoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/roles/")
    @CrossOrigin
    public ResponseEntity<ResponseObject> getRoles() {
        List<Role> listRole = this.roleService.getRoles();
        if (!listRole.isEmpty()) {
            return ResponseEntity.status(200).body(new ResponseObject("OK", "Get all role successfully", listRole));
        } else {
            return ResponseEntity.status(400).body(new ResponseObject("BAD REQUEST", "Get all role failed", null));
        }
    }

}
