/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service;

import com.doannganh.quanlytruonghoc.models.Room;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
public interface RoomService {

    TypedQuery<Room> getRooms(Map<String, String> prams);

    int countRoom();

    Room findRoomByRoomID(int roomID);

    boolean deleteRoom(int roomID);

    Room addRoom(Map<String, String> param, MultipartFile roomImage);

    Room updateRoom(Map<String, String> param, MultipartFile roomImage, int roomID);

}
