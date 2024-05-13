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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "receipt_detail")
public class ReceiptDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_detail_id")
    private Integer receiptDetailID;
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    @Column(name = "finish_time")
    private LocalDateTime finishTime;
    @Column(name = "price", nullable = false)
    private Long price;
    @Column(name = "num")
    private Integer num;
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    @ManyToOne(optional = false)
    private Room roomID;

    @JoinColumn(name = "receipt_id", referencedColumnName = "receipt_id")
    @OneToOne(optional = false)
    private Receipt receiptID;

    public ReceiptDetail() {
    }

    public ReceiptDetail(Integer receiptDetailID, LocalDateTime startTime, LocalDateTime finishTime, Long price, Integer num) {
        this.receiptDetailID = receiptDetailID;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.price = price;
        this.num = num;
    }

    public Integer getReceiptDetailID() {
        return receiptDetailID;
    }

    public void setReceiptDetailID(Integer receiptDetailID) {
        this.receiptDetailID = receiptDetailID;
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

    public Room getRoomID() {
        return roomID;
    }

    public void setRoomID(Room roomID) {
        this.roomID = roomID;
    }

    public Receipt getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(Receipt receiptID) {
        this.receiptID = receiptID;
    }

    @Override
    public String toString() {
        return "ReceiptDetail{" + "receiptDetailID=" + receiptDetailID + ", startTime=" + startTime + ", finishTime=" + finishTime + ", price=" + price + ", num=" + num + ", roomID=" + roomID + ", receiptID=" + receiptID + '}';
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
        final ReceiptDetail other = (ReceiptDetail) obj;
        if (!Objects.equals(this.receiptDetailID, other.receiptDetailID)) {
            return false;
        }
        if (!Objects.equals(this.startTime, other.startTime)) {
            return false;
        }
        if (!Objects.equals(this.finishTime, other.finishTime)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.num, other.num)) {
            return false;
        }
        if (!Objects.equals(this.roomID, other.roomID)) {
            return false;
        }
        return Objects.equals(this.receiptID, other.receiptID);
    }

}
