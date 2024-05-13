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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private Integer receiptID;
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userID;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiptDetailID")
    @JsonIgnore
    private Set<ReceiptDetail> listReceiptDetail;

    public Receipt() {
    }

    public Receipt(Integer receiptID, LocalDateTime createDate) {
        this.receiptID = receiptID;
        this.createDate = createDate;
    }

    public Integer getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(Integer receiptID) {
        this.receiptID = receiptID;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Set<ReceiptDetail> getListReceiptDetail() {
        return listReceiptDetail;
    }

    public void setListReceiptDetail(Set<ReceiptDetail> listReceiptDetail) {
        this.listReceiptDetail = listReceiptDetail;
    }

    @Override
    public String toString() {
        return "Receipt{" + "receiptID=" + receiptID + ", createDate=" + createDate + '}';
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
        final Receipt other = (Receipt) obj;
        if (!Objects.equals(this.receiptID, other.receiptID)) {
            return false;
        }
        return Objects.equals(this.createDate, other.createDate);
    }

}
