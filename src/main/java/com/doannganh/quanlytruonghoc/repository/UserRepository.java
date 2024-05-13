/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doannganh.quanlytruonghoc.repository;

import com.doannganh.quanlytruonghoc.models.User;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByUserID(int userID);

    @Query("SELECT u FROM User u WHERE LOWER(u.lastName) LIKE %:keyword% OR LOWER(u.firstName) LIKE %:keyword%")
    List<User> findUserByParam(@Param("keyword") String keyword);

}
