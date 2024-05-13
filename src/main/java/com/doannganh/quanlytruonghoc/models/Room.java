/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Objects;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomID;
    @Column(name = "room_name", nullable = false, unique = true, length = 50)
    private String roomName;
    @Column(name = "price", nullable = false, length = 11)
    private Long price;
    @Column(name = "seats", nullable = false, length = 5)
    private Integer seats;
    @Column(name = "room_image", nullable = true, length = 100)
    private String roomImage;
    @Column(name = "utilities", nullable = false, length = 100)
    private String utilities;
    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @JoinColumn(name = "room_type_id", referencedColumnName = "room_type_id")
    @ManyToOne(optional = false)
    private RoomType roomTypeID;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentID")
    @JsonIgnore
    private Set<Comment> listComment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiptDetailID")
    @JsonIgnore
    private Set<ReceiptDetail> listReceiptDetail;

    @Transient
    private MultipartFile file;

    public Room() {
    }

    public Room(String roomName, Long price, Integer seats, String roomImage, String utilities, String description) {

        this.roomName = roomName;
        this.price = price;
        this.seats = seats;
        this.roomImage = roomImage;
        this.utilities = utilities;
        this.description = description;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public String getUtilities() {
        return utilities;
    }

    public void setUtilities(String utilities) {
        this.utilities = utilities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomType getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(RoomType roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public Set<Comment> getListComment() {
        return listComment;
    }

    public void setListComment(Set<Comment> listComment) {
        this.listComment = listComment;
    }

    public Set<ReceiptDetail> getListReceiptDetail() {
        return listReceiptDetail;
    }

    public void setListReceiptDetail(Set<ReceiptDetail> listReceiptDetail) {
        this.listReceiptDetail = listReceiptDetail;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Room{" + "roomID=" + roomID + ", roomName=" + roomName + ", price=" + price + ", seats=" + seats + ", roomImage=" + roomImage + ", utilities=" + utilities + ", description=" + description + '}';
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
        final Room other = (Room) obj;
        if (!Objects.equals(this.roomName, other.roomName)) {
            return false;
        }
        if (!Objects.equals(this.roomImage, other.roomImage)) {
            return false;
        }
        if (!Objects.equals(this.utilities, other.utilities)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.roomID, other.roomID)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return Objects.equals(this.seats, other.seats);
    }

}
