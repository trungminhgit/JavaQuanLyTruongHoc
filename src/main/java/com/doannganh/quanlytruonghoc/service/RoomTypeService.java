/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service;

import com.doannganh.quanlytruonghoc.models.RoomType;
import java.util.List;
import org.apache.el.stream.Optional;

/**
 *
 * @author ACER
 */
public interface RoomTypeService {

    List<RoomType> getAllRoomTypes();

    RoomType getRoomTypeByID(int roomType);
}
