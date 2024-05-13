/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doannganh.quanlytruonghoc.controllers;

import com.doannganh.quanlytruonghoc.models.Comment;
import com.doannganh.quanlytruonghoc.models.Room;
import com.doannganh.quanlytruonghoc.service.CommentService;
import com.doannganh.quanlytruonghoc.service.RoomService;
import com.doannganh.quanlytruonghoc.service.impl.CommentServiceImpl;
import com.doannganh.quanlytruonghoc.service.impl.RoomServiceImpl;
import com.doannganh.response.PageResponseObject;
import com.doannganh.response.ResponseObject;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping(path = "/api/v1")
public class ApiRoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/rooms/")
    @CrossOrigin
    public ResponseEntity<PageResponseObject> getRooms(@RequestParam Map<String, String> params) {

        try {
            // Lấy truy vấn có phân trang từ repository
            TypedQuery<Room> query = roomService.getRooms(params);

            // Lấy danh sách phòng và tổng số mục
            List<Room> rooms = query.getResultList();
            long totalElements = query.getMaxResults(); // Tổng số kết quả trên mỗi trang
            int pageSize = 8;

            // Tính tổng số trang dựa trên tổng số mục và kích thước trang
            int totalRooms = roomService.countRoom();
            int totalPages = (int) Math.ceil((double) totalRooms / pageSize);

            // Trả về kết quả và thông tin phân trang
            return ResponseEntity.ok().body(new PageResponseObject("OK", "Rooms fetched successfully", rooms, totalPages, totalRooms));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(new PageResponseObject("ERROR", "An error occurred", null));
        }
    }

    @DeleteMapping("/admin/rooms/{id}")
    @CrossOrigin
    public ResponseEntity<ResponseObject> deleteRoom(@PathVariable(value = "id") int roomID) {
        if (this.roomService.deleteRoom(roomID)) {
            return ResponseEntity.status(200).body(new ResponseObject("OK", "Delete room successfully", null));
        } else {
            return ResponseEntity.status(404).body(new ResponseObject("BAD REQUEST", "Delete room failed", null));
        }
    }

    @PostMapping(path = "/admin/rooms", consumes = {
        MediaType.MULTIPART_FORM_DATA_VALUE,
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<ResponseObject> addRoom(@RequestParam Map<String, String> params, @RequestPart MultipartFile roomImage) {
        if (this.roomService.addRoom(params, roomImage) != null) {
            return ResponseEntity.status(204).body(new ResponseObject("OK", "Add or update room successfully", null));
        } else {
            return ResponseEntity.status(404).body(new ResponseObject("BAD REQUEST", "Add or update room failed", null));
        }
    }

    @PostMapping(path = "/admin/rooms/{id}", consumes = {
        MediaType.MULTIPART_FORM_DATA_VALUE,
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<ResponseObject> updateRoom(@RequestParam Map<String, String> params, @RequestPart MultipartFile roomImage,
            @PathVariable(value = "id") int roomID) {
        Room r = this.roomService.updateRoom(params, roomImage, roomID);
        if (r != null) {
            return ResponseEntity.status(200).body(new ResponseObject("OK", "Update room successfully", r));
        } else {
            return ResponseEntity.status(404).body(new ResponseObject("BAD REQUEST", "Update room failed", null));
        }
    }

    @RequestMapping(path = "/rooms/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<ResponseObject> detailRoom(@PathVariable(value = "id") int roomID) {
        Room room = this.roomService.findRoomByRoomID(roomID);
        if (room != null) {
            return ResponseEntity.status(200).body(new ResponseObject("OK", "Get room successfully", room));
        } else {
            return ResponseEntity.status(404).body(new ResponseObject("BAD REQUEST", "Get room failed", null));
        }
    }

    //API lấy comment
    @GetMapping("/rooms/{roomID}/comments/")
    @CrossOrigin
    public ResponseEntity<ResponseObject> getComments(@PathVariable(value = "roomID") int roomID) {
        List<Comment> listComment = this.commentService.getComments(roomID);
        if (listComment == null) {
            return ResponseEntity.status(500).body(new ResponseObject("ERROR", "Get comments failed", null));
        }

        if (listComment.isEmpty()) {
            return ResponseEntity.status(404).body(new ResponseObject("NO CONTENT", "No comment found", null));
        }

        return ResponseEntity.status(200).body(new ResponseObject("OK", "Get rooms successfully", listComment));
    }
    //API thêm comment

    @PostMapping(path = "/rooms/{roomID}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<ResponseObject> addComment(@RequestBody Comment c, @PathVariable(value = "roomID") int roomID) {
        Comment comment = this.commentService.addComment(c, roomID);
        if (comment != null) {
            return ResponseEntity.status(200).body(new ResponseObject("OK", "Add comment successfully", comment));
        } else {
            return ResponseEntity.status(404).body(new ResponseObject("BAD REQUEST", "Add comment failed", null));
        }

    }
}
