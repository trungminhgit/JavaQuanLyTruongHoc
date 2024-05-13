/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.controllers;

import com.doannganh.quanlytruonghoc.models.RoomType;
import com.doannganh.quanlytruonghoc.service.RoomTypeService;
import com.doannganh.quanlytruonghoc.service.impl.RoomTypeServiceImpl;
import com.doannganh.response.ResponseObject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping(path = "/api/v1")
public class ApiRoomTypeController {

    @Autowired
    private RoomTypeServiceImpl roomTypeService;

    @GetMapping("/room-types/")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ResponseObject> getAllRoomTypes() {
        List<RoomType> listRoomType = this.roomTypeService.getAllRoomTypes();
        if (!listRoomType.isEmpty()) {
            return ResponseEntity.status(200).body(new ResponseObject("OK", "Get all room type successfully", listRoomType));
        } else {
            return ResponseEntity.status(400).body(new ResponseObject("BAD REQUEST", "Get all room type failed", null));
        }
    }
}
