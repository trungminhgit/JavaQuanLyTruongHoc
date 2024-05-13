/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Data;

/**
 *
 * @author ACER
 */

@Data
public class Cart {

    private Integer roomID;
    private String name;
    private Long price;
    private Integer num;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;

    public Cart() {
    }

    public Cart(Integer roomID, String name, Long price, LocalDateTime startTime, LocalDateTime finishTime, Integer num) {
        this.roomID = roomID;
        this.name = name;
        this.price = price;
        this.num = num;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "CartItem{" + "roomID=" + roomID + ", name=" + name + ", price=" + price + ", num=" + num + ", startTime=" + startTime + ", finishTime=" + finishTime + '}';
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
        final Cart other = (Cart) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.roomID, other.roomID)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.num, other.num)) {
            return false;
        }
        if (!Objects.equals(this.startTime, other.startTime)) {
            return false;
        }
        return Objects.equals(this.finishTime, other.finishTime);
    }

}
