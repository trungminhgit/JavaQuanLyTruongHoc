/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doannganh.quanlytruonghoc.repository;

import com.doannganh.quanlytruonghoc.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByRoleID(int id);
}
