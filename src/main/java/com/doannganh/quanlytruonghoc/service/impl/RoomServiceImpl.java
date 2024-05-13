/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.doannganh.quanlytruonghoc.models.Room;
import com.doannganh.quanlytruonghoc.repository.Impl.RoomRepositoryImpl;
import com.doannganh.quanlytruonghoc.repository.RoomRepository;
import com.doannganh.quanlytruonghoc.service.RoomService;
import jakarta.persistence.TypedQuery;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepo;
    
    @Autowired
    private RoomRepositoryImpl roomRepoImpl;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private RoomTypeServiceImpl roomTypeServiceImpl;

    @Override
    public TypedQuery<Room> getRooms(Map<String, String> prams) {
        return this.roomRepoImpl.findRoomByParam(prams);
    }

    @Override
    public int countRoom() {
        return (int) this.roomRepo.count();
    }

    @Override
    public boolean deleteRoom(int roomID) {
        if (this.roomRepo.existsById(roomID)) {
            this.roomRepo.deleteById(roomID);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Room addRoom(Map<String, String> param, MultipartFile roomImage) {

        Room room = new Room();

        room.setRoomName(param.get("roomName"));
        room.setPrice(Long.valueOf(param.get("price")));
        room.setSeats(Integer.valueOf(param.get("seats")));
        room.setDescription(param.get("description"));
        room.setUtilities(param.get("utilities"));
        room.setRoomTypeID(this.roomTypeServiceImpl.getRoomTypeByID(Integer.valueOf(param.get("roomTypeID"))));
        if (!roomImage.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(roomImage.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                room.setRoomImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserDetailsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.roomRepo.save(room);

    }

    @Override
    public Room updateRoom(Map<String, String> param, MultipartFile roomImage, int roomID) {
        Room nRoom = this.roomRepo.findByRoomID(roomID);
        if (nRoom != null) {
            nRoom.setRoomName(param.get("roomName"));
            nRoom.setPrice(Long.valueOf(param.get("price")));
            nRoom.setSeats(Integer.valueOf(param.get("seats")));
            nRoom.setDescription(param.get("description"));
            nRoom.setUtilities(param.get("utilities"));
            nRoom.setRoomTypeID(this.roomTypeServiceImpl.getRoomTypeByID(Integer.valueOf(param.get("roomTypeID"))));
            if (!roomImage.isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(roomImage.getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                    nRoom.setRoomImage(res.get("secure_url").toString());
                } catch (IOException ex) {
                    Logger.getLogger(UserDetailsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return this.roomRepo.save(nRoom);
        } else {
            return null;
        }
    }

    @Override
    public Room findRoomByRoomID(int roomID) {
        return this.roomRepo.findByRoomID(roomID);
    }

}
