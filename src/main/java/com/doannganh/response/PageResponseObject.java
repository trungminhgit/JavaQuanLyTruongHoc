/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.response;

/**
 *
 * @author ACER
 */
public class PageResponseObject extends ResponseObject {

    private int totalPages;
    private long totalElements;

    public PageResponseObject() {
    }

    public PageResponseObject(String status, String message, Object data) {
        super(status, message, data);       
    }

    
    
    public PageResponseObject(String status, String message, Object data,int totalPages, long totalElements) {
        super(status, message, data);
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

}
