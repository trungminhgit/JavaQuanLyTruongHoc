import React, { useEffect, useRef, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import APIs, { authAPI, endpoints } from "../../configs/APIs";
import CustomSpinner from "../../layouts/CustomSpinner";
import { Button, Form } from "react-bootstrap";
import { validationAddRoom } from "../../Validation";

const AddRoom = () => {
    const [room, setRoom] = useState({
        "roomName": '',
        "price": '',
        "seats": '',
        "utilities": '',
        "description": '',
        "roomTypeID": ''
    });

    const [roomTypes, setRoomTypes] = useState([]);
    const [addRoomError, setAddRoomError] = useState({});
    const [addRoomLoading, setAddRoomLoading] = useState(false);
    const roomImage = useRef();
    const nav = useNavigate();
    const { roomID } = useParams();
    const [roomUpdate, setRoomUpdate] = useState({
        "roomName": '',
        "price": '',
        "seats": '',
        "utilities": '',
        "description": '',
        "roomTypeID": ''
    });

    const addRoom = async () => {

        const errors = validationAddRoom(room);
        setAddRoomError(errors);
        console.log(addRoomError);
        if (Object.keys(errors).length > 0) {
            return;
        }
        setAddRoomLoading(true);

        try {
            let form = new FormData();
            form.append('roomName', room.roomName);
            form.append('price', room.price);
            form.append('seats', room.seats);
            form.append('utilities', room.utilities);
            form.append('description', room.description);
            form.append('roomTypeID', room.roomTypeID);
            form.append('roomImage', roomImage.current.files[0]);
            let res = await authAPI().post(endpoints['add-room'], form);
            if (res.status === 204) {

                nav("/");
            } else {
                setAddRoomError("Có lỗi xảy ra. Vui lòng thử lại sau.");
            }
        } catch (error) {
            setAddRoomError("Có lỗi trong quá trình xử lý. Vui lòng thử lại.");
        } finally {
            setAddRoomLoading(false); // Đặt lại `loading` thành `false`
        }



    }
    const changeAddRoom = (evt, field) => {
        setRoom(current => ({
            ...current,
            [field]: evt.target.value,
        }));
    }

    const loadRoomTypes = async () => {
        let res = await APIs.get(endpoints['room-types'])
        setRoomTypes(res.data.data || []);
    }

    useEffect(() => {
        loadRoomTypes();
    }, [])

    useEffect(() => {
        if (roomID) {
            const loadRoom = async () => {
                try {
                    const { data } = await APIs.get(endpoints["room-detail"](roomID));
                    setRoomUpdate(data.data); // Gán dữ liệu phòng vào state
                } catch (error) {
                    console.error("Failed to load room:", error);
                }
            };

            loadRoom();
        }
    }, [roomID]);


    const change = (e, field) => {
        setRoomUpdate({ ...roomUpdate, [field]: e.target.value });
    };

    const addOrUpdateRoom = async (evt) => {
        evt.preventDefault();

        // Dựa trên roomID, quyết định là thêm phòng hay cập nhật phòng
        if (roomID) {
            // Cập nhật phòng
            const errors = validationAddRoom(roomUpdate);
            setAddRoomError(errors);
            if (Object.keys(errors).length > 0) {
                return;
            }
            setAddRoomLoading(true);
            const formData = new FormData();
            formData.append('roomName', roomUpdate.roomName);
            formData.append('price', roomUpdate.price);
            formData.append('seats', roomUpdate.seats);
            formData.append('utilities', roomUpdate.utilities);
            formData.append('description', roomUpdate.description);
            formData.append('roomTypeID', roomUpdate.roomTypeID);

            // Thêm file ảnh nếu có
            if (roomImage.current && roomImage.current.files[0]) {
                formData.append('roomImage', roomImage.current.files[0]);
            }
            try {
                let resUpdate = await authAPI().post(endpoints['update-rooms'](roomID), formData);
                if (resUpdate.status === 200) {
                    console.log("đax chạy vào đây");
                    console.log(roomUpdate);
                    console.log(formData);
                    nav("/")
                } else {
                    setAddRoomError("Có lỗi xảy ra. Vui lòng thử lại sau.");
                }
            } catch {
                setAddRoomError("Có lỗi xảy ra. Vui lòng thử lại sau.");
            }
            setAddRoomLoading(false);
        } else {
            addRoom();
        }
    };
    if (roomTypes === null)
        return <CustomSpinner />;

    return <>
        {roomID ? <div className="d-flex justify-content-center" style={{ height: '800px' }}>
            <div style={{
                marginTop: '15px',
                width: '800px',
                border: '1px solid #ccc',
                borderRadius: '10px',
                padding: '20px',
                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)'
            }}>
                <h1 className="text-center text-dark mt-2">CẬP NHẬT PHÒNG HỌC</h1>
                <Form onSubmit={addOrUpdateRoom}>
                    <Form.Group className="mb-3">
                        <Form.Label>Tên phòng học</Form.Label>
                        <Form.Control type="text" value={roomUpdate.roomName} onChange={(e) => change(e, "roomName")} placeholder="Tên phòng" required />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Giá</Form.Label>
                        <Form.Control type="text" value={roomUpdate.price} onChange={(e) => change(e, "price")} placeholder="Giá" required />
                        {addRoomError.price && <p style={{ color: "red" }}>{addRoomError.price}</p>}
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Số chỗ ngồi</Form.Label>
                        <Form.Control type="text" value={roomUpdate.seats} onChange={(e) => change(e, "seats")} placeholder="Số chỗ ngồi" required />
                        {addRoomError.seats && <p style={{ color: "red" }}>{addRoomError.seats}</p>}
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Tiện ích</Form.Label>
                        <Form.Control onChange={(e) => change(e, "utilities")} type="text" value={roomUpdate.utilities} placeholder="Tiện ích" required />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Mô tả</Form.Label>
                        <Form.Control onChange={(e) => change(e, "description")} type="text" value={roomUpdate.description} placeholder="Mô tả" required />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Loại phòng </Form.Label>
                        <Form.Select onChange={(e) => change(e, 'roomTypeID')} value={roomUpdate.roomTypeID} required>
                            <option>Chọn loại phòng</option>
                            {roomTypes.map((r) => (
                                <option key={r.roomTypeID} value={r.roomTypeID}>{r.roomTypeName}</option>
                            ))}
                        </Form.Select>
                        {addRoomError.roomTypeID && <p style={{ color: "red" }}>{addRoomError.roomTypeID}</p>}
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Ảnh phòng học</Form.Label>
                        <Form.Control type="file" ref={roomImage} />
                        {addRoomError.roomImage && <p style={{ color: "red" }}>{addRoomError.roomImage}</p>}
                    </Form.Group>
                    <Form.Group className="mb-3 d-flex justify-content-center" style={{ height: '38px', marginTop: '40px'}}>
                        {addRoomLoading === true ? (
                            <CustomSpinner />
                        ) : (
                            <Button variant="info" type="submit">
                                Cập nhật
                            </Button>
                        )}
                        <Link to="/">
                            <Button variant="info" type="submit" style={{ marginLeft: '10px' }}>
                                Hủy
                            </Button>
                        </Link>
                    </Form.Group>
                </Form> </div> </div> : <div className="d-flex justify-content-center" style={{ height: '800px' }}>
            <div style={{
                marginTop: '15px',
                width: '800px',
                border: '1px solid #ccc',
                borderRadius: '10px',
                padding: '20px',
                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)'
            }}>
                <h1 className="text-center text-dark mt-2">THÊM PHÒNG HỌC</h1>
                <Form onSubmit={addOrUpdateRoom}>
                    <Form.Group className="mb-3">
                        <Form.Label>Tên phòng học</Form.Label>
                        <Form.Control type="text" onChange={(e) => changeAddRoom(e, "roomName")} placeholder="Tên phòng" required />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Giá</Form.Label>
                        <Form.Control type="text" onChange={(e) => changeAddRoom(e, "price")} placeholder="Giá" required />
                        {addRoomError.price && <p style={{ color: "red" }}>{addRoomError.price}</p>}
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Số chỗ ngồi</Form.Label>
                        <Form.Control type="text" onChange={(e) => changeAddRoom(e, "seats")} placeholder="Số chỗ ngồi" required />
                        {addRoomError.seats && <p style={{ color: "red" }}>{addRoomError.seats}</p>}
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Tiện ích</Form.Label>
                        <Form.Control onChange={(e) => changeAddRoom(e, "utilities")} type="text" placeholder="Tiện ích" required />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Mô tả</Form.Label>
                        <Form.Control onChange={(e) => changeAddRoom(e, "description")} type="text" placeholder="Mô tả" required />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Loại phòng </Form.Label>
                        <Form.Select onChange={(e) => changeAddRoom(e, 'roomTypeID')} required>
                            <option>Chọn loại phòng</option>
                            {roomTypes.map((r) => (
                                <option key={r.roomTypeID} value={r.roomTypeID}>{r.roomTypeName}</option>
                            ))}
                        </Form.Select>
                        {addRoomError.roomTypeID && <p style={{ color: "red" }}>{addRoomError.roomTypeID}</p>}
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Ảnh phòng học</Form.Label>
                        <Form.Control type="file" ref={roomImage} />
                        {addRoomError.roomImage && <p style={{ color: "red" }}>{addRoomError.roomImage}</p>}
                    </Form.Group>
                        <Form.Group className="mb-3 d-flex justify-content-center" style={{ height: '38px', marginTop: '40px'}}>
                            {addRoomLoading === true ? (
                                <CustomSpinner />
                            ) : (
                                <Button variant="info" type="submit">
                                    Thêm
                                </Button>
                            )}
                            <Link to="/">
                                <Button variant="info" type="submit" style={{marginLeft: '10px'}}>
                                    Hủy
                                </Button>
                            </Link>
                        </Form.Group>
                </Form>
            </div>
        </div>}
    </>
}

export default AddRoom;