/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.repository.Impl;

import com.doannganh.quanlytruonghoc.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository
public class UserRepositoryImpl {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public TypedQuery<User> findUserByParam(Map<String, String> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            // Điều kiện tìm kiếm cho firstName
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Expression<String> fullName = cb.concat(cb.lower(userRoot.get("lastName")), " ");
                fullName = cb.concat(fullName, cb.lower(userRoot.get("firstName")));

                Predicate fullNameMatch = cb.like(fullName, "%" + kw.toLowerCase() + "%");

                predicates.add(fullNameMatch);
            }
        }

        // Áp dụng các điều kiện vào truy vấn nếu có
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

        // Sắp xếp kết quả theo thứ tự giảm dần của 'id' (hoặc bất kỳ trường nào bạn muốn)
        cq.orderBy(cb.desc(userRoot.get("userID")));

        // Tạo TypedQuery
        TypedQuery<User> query = entityManager.createQuery(cq);

        if (params != null) {
            // Xử lý phân trang nếu có tham số 'page'
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                query.setMaxResults(8); // Số lượng kết quả mỗi trang
                query.setFirstResult((p - 1) * 8); // Vị trí bắt đầu
            }
        }

        return query;
    }
}
