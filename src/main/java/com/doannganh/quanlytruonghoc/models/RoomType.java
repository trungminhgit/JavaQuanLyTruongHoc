/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "room_type")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    private Integer roomTypeID;
    @Column(name = "room_type_name", nullable = false, unique = true, length = 50)
    private String roomTypeName;

    public RoomType() {
    }

    public RoomType(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Integer getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(Integer roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    @Override
    public String toString() {
        return "RoomType{" + "roomTypeID=" + roomTypeID + ", roomTypeName=" + roomTypeName + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RoomType other = (RoomType) obj;
        if (this.roomTypeID != other.roomTypeID) {
            return false;
        }
        return Objects.equals(this.roomTypeName, other.roomTypeName);
    }

}
