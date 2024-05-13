/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.doannganh.quanlytruonghoc.models.Room;
import com.doannganh.quanlytruonghoc.models.User;
import com.doannganh.quanlytruonghoc.repository.Impl.UserRepositoryImpl;
import com.doannganh.quanlytruonghoc.repository.RoleRepository;
import com.doannganh.quanlytruonghoc.repository.UserRepository;
import jakarta.persistence.TypedQuery;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserRepositoryImpl userRepoImpl;


    public TypedQuery<User> getUsers(Map<String, String> prams) {
        return userRepoImpl.findUserByParam(prams);
    }

    public boolean updateUser(User user) {
        try {
            if (user.getUserID() != null) {
                userRepo.save(user);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int countUser() {
        return (int) userRepo.count();
    }

    public boolean deleteUser(int userID) {
        if (this.userRepo.existsById(userID)) {
            this.userRepo.deleteById(userID);
            return true;
        } else {
            return false;
        }
    }

    public User findByUserName(String userName) {
        return this.userRepo.findByUsername(userName);
    }

    public User findByUserID(int userID) {
        return this.userRepo.findByUserID(userID);
    }

    public List<User> findUserByParam(String kw) {
        return this.userRepo.findUserByParam(kw);
    }

    //Update user for admin
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String roleName = user.getRoleID().getRoleName();

        // Tạo GrantedAuthority với vai trò
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(roleName));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);

    }

}
