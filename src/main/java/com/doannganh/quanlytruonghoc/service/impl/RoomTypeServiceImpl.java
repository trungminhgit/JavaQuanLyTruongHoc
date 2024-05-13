/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service.impl;

import com.doannganh.quanlytruonghoc.models.RoomType;
import com.doannganh.quanlytruonghoc.repository.RoomTypeRepository;
import com.doannganh.quanlytruonghoc.service.RoomTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepo;

    @Override
    public List<RoomType> getAllRoomTypes() {
        return this.roomTypeRepo.findAll();
    }

    @Override
    public RoomType getRoomTypeByID(int roomTypeID) {
        return this.roomTypeRepo.findByRoomTypeID(roomTypeID);
    }

}
