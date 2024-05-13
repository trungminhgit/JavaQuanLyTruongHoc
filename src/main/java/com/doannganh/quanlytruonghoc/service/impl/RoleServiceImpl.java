/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service.impl;

import com.doannganh.quanlytruonghoc.models.Role;
import com.doannganh.quanlytruonghoc.repository.RoleRepository;
import com.doannganh.quanlytruonghoc.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class RoleServiceImpl implements RoleService{
    
    @Autowired
    private RoleRepository roleRepo;

    @Override
    public List<Role> getRoles() {
        return this.roleRepo.findAll();
    }
    
}
