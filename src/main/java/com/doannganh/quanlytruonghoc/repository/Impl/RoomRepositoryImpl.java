/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.repository.Impl;

import com.doannganh.quanlytruonghoc.models.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository
public class RoomRepositoryImpl {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public TypedQuery<Room> findRoomByParam(Map<String, String> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> cq = cb.createQuery(Room.class);
        Root<Room> roomRoot = cq.from(Room.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            // Điều kiện tìm kiếm cho roomName
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(cb.like(cb.lower(roomRoot.get("roomName")), "%" + kw.toLowerCase() + "%"));
            }

            // Điều kiện tìm kiếm cho roomTypeID
            String roomTypeID = params.get("roomTypeID");
            if (roomTypeID != null && !roomTypeID.isEmpty()) {
                predicates.add(cb.equal(roomRoot.get("roomTypeID").get("roomTypeID"), Integer.valueOf(roomTypeID)));
            }

            // Điều kiện tìm kiếm cho roomTypeName (thông qua tham chiếu roomTypeID)
            String roomTypeName = params.get("roomTypeName");
            if (roomTypeName != null && !roomTypeName.isEmpty()) {
                predicates.add(cb.like(cb.lower(roomRoot.join("roomTypeID").get("roomTypeName")), "%" + roomTypeName.toLowerCase() + "%"));
            }
        }

        // Áp dụng các điều kiện vào truy vấn nếu có điều kiện nào
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

        // Sắp xếp kết quả theo thứ tự giảm dần của 'id'
        cq.orderBy(cb.desc(roomRoot.get("roomID")));
        
        TypedQuery<Room> query = entityManager.createQuery(cq);
        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                query.setMaxResults(8);
                query.setFirstResult((p - 1) * 8);
            }
        }

        // Thực thi truy vấn và trả về kết quả
        return query;
    }
}
