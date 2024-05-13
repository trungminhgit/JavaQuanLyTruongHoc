/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.doannganh.response.ResponseObject;
import com.doannganh.quanlytruonghoc.models.User;
import com.doannganh.quanlytruonghoc.repository.RoleRepository;
import com.doannganh.quanlytruonghoc.repository.UserRepository;
import com.doannganh.quanlytruonghoc.service.AuthenticationService;
import com.doannganh.quanlytruonghoc.service.JwtService;
import com.doannganh.response.AuthenticationResponse;
import io.micrometer.common.util.StringUtils;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserDetailsServiceImpl userDetail;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public ResponseObject register(Map<String, String> params, MultipartFile avatar) {

        User nUser = new User();
        nUser.setFirstName(params.get("firstName"));
        nUser.setLastName(params.get("lastName"));
        nUser.setUsername(params.get("username"));
        nUser.setPassword(passwordEncoder.encode(params.get("password")));
        nUser.setEmail(params.get("email"));
        nUser.setPhone(params.get("phone"));
        nUser.setRoleID(roleRepository.findRoleByRoleID(2));
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                nUser.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserDetailsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (userRepository.findByUsername(nUser.getUsername()) != null) {
            return new ResponseObject("BAD REQUEST", "Username already exists", null);
        }
        nUser = userRepository.save(nUser);
        return new ResponseObject("OK", "User registration was successfully", nUser);
    }

    @Override
    public AuthenticationResponse login(String username, String password) {

        User checkUser = userRepository.findByUsername(username);
        if (checkUser != null) {
            if (this.passwordEncoder.matches(password, checkUser.getPassword())) {
                String jwt = jwtService.generateToken(checkUser);
                return new AuthenticationResponse(jwt, "User login was successfully");
            } else {
                return new AuthenticationResponse(null, "Invalid password");
            }
        } else {
            return new AuthenticationResponse(null, "Username does not exist");
        }
    }

    @Override
    public User updateUser(User user, String newPass) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User checkUser = (User) this.userDetail.loadUserByUsername(authentication.getName());
        if (StringUtils.isNotEmpty(newPass) && checkUser.equals(user)) {
            String enPassword = passwordEncoder.encode(newPass);
            user.setPassword(enPassword);
        }
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(Map<String, String> params, MultipartFile avatar, int userID) {
        User u = this.userRepository.findByUserID(userID);

        if (u != null && !params.isEmpty()) {

            String oldUserName = u.getUsername();
            String oldPass = params.get("password");

            u.setFirstName(params.get("firstName"));
            u.setLastName(params.get("lastName"));
            u.setEmail(params.get("email"));
            u.setPhone(params.get("phone"));
            u.setUsername(oldUserName);
            if (oldPass.matches(u.getPassword())) {
                u.setPassword(oldPass);
            } else {
                u.setPassword(this.passwordEncoder.encode(params.get("password")));
            }
            u.setRoleID(this.roleRepository.findRoleByRoleID(Integer.valueOf(params.get("roleID"))));
            if (!avatar.isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                    u.setAvatar(res.get("secure_url").toString());
                } catch (IOException ex) {
                    Logger.getLogger(UserDetailsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return this.userRepository.save(u);
        } else {
            return null;
        }
    }

}
