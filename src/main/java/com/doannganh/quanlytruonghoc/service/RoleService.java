/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service;

import com.doannganh.quanlytruonghoc.models.Role;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
public interface RoleService {
    List<Role> getRoles();
}
