/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service.impl;

import com.doannganh.quanlytruonghoc.models.Comment;
import com.doannganh.quanlytruonghoc.models.Room;
import com.doannganh.quanlytruonghoc.models.User;
import com.doannganh.quanlytruonghoc.repository.CommentRepository;
import com.doannganh.quanlytruonghoc.repository.UserRepository;
import com.doannganh.quanlytruonghoc.service.CommentService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private RoomServiceImpl roomServiceImpl;

    @Autowired
    private UserDetailsServiceImpl userDetailServiceImpl;
    
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Comment> getComments(int roomID) {
        return this.commentRepo.getComments(roomID);
    }

    @Override
    public Comment addComment(Comment comment, int roomID) {
        comment.setCreateDate(LocalDateTime.now());

        Room room = this.roomServiceImpl.findRoomByRoomID(roomID);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = this.userRepo.findByUsername(userDetails.getUsername());
        comment.setUserID(user);
        comment.setRoomID(room);
        comment.setCommentID(null);
        return this.commentRepo.save(comment);
    }

}
