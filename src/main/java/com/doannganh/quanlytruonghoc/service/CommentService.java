/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service;

import com.doannganh.quanlytruonghoc.models.Comment;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface CommentService {

    List<Comment> getComments(int roomID);

    Comment addComment(Comment comment, int roomID);
}
