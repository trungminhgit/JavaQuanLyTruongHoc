import React, { useContext } from "react";
import { useEffect, useState } from "react"
import { Alert, Button, Card, Col, Modal, Pagination, Row } from "react-bootstrap";
import { Link, useSearchParams, useNavigate} from "react-router-dom";
import APIs, { authAPI, endpoints } from "../../configs/APIs";
import CustomSpinner from "../../layouts/CustomSpinner";
import { MyCartContext, MyUserContext } from "../../App";
import Cookies from "universal-cookie";
import { formatterPrice } from "../../Formatter";
import { autoHideAlert } from "../../Formatter";

const Home = () => {
    const [, cartDispatch] = useContext(MyCartContext);
    const [rooms, setRooms] = useState(null);
    const [q] = useSearchParams();
    const [user,] = useContext(MyUserContext);
    const nav = useNavigate();
    const [currentPage, setCurrentPage] = useState(1); // Trang hiện tại
    const [totalPages, setTotalPages] = useState(1);
    const pageSize = 8;
    const cookie = new Cookies();
    const [showModalOrder, setShowModalOrder] = useState(false);
    const [startTime, setStartTime] = useState();
    const [finishTime, setFinishTime] = useState();
    const [seatNum, setSeatNum] = useState();
    const [selectedRoom, setSelectedRoom] = useState(null);
    const [showAlert, setShowAlert] = useState(false);
    const [showAlertOrder, setShowAlertOrder] = useState(false);
    const [showAlertDelete, setShowAlertDelete] = useState(false);
    const [showModalDelete, setShowModalDelete] = useState(false);

    const loadRooms = async () => {
        try {

            let endpoint = `${endpoints['rooms']}?page=${currentPage}&pageSize=${pageSize}`;

            let roomTypeID = q.get("roomTypeID");
            if (roomTypeID !== null)
                endpoint += `&roomTypeID=${roomTypeID}`;
            else {
                let kw = q.get("kw");
                if (kw !== null)
                    endpoint += `&kw=${kw}`;
            }

            let res = await APIs.get(endpoint);
            if (res.data.status === "No CONTENT ") {
                setRooms([]);
            } else {
                setRooms(res.data.data);
                setTotalPages(res.data.totalPages);
            }
        } catch (ex) {
            console.error(ex);
            setRooms([]);
        }
    }


    useEffect(() => {
        loadRooms();
    }, [q, currentPage, rooms]);


    const deleteRoom = (roomID) => {
        const process = async () => {

            let { data } = await authAPI().delete(endpoints["deleteRoom"](roomID));
            if (data.status === "OK") {
                console.log(data);
                autoHideAlert(setShowAlertDelete);
                await loadRooms();
            };
        }
        process();
    }

    const openModalDelete = (room) => {
        setSelectedRoom(room);
        setShowModalDelete(true)
    }

    const closeAndDelete = () => {
        setShowModalDelete(false);
        deleteRoom(selectedRoom.roomID);
    }

    



    //Get ngày hiện tại 
    const getCurrentDate = () => {
        const now = new Date();
        const year = now.getFullYear();
        const month = String(now.getMonth() + 1).padStart(2, '0'); // Đảm bảo có 2 chữ số
        const day = String(now.getDate()).padStart(2, '0'); // Đảm bảo có 2 chữ số
        const hour = String(now.getHours()).padStart(2, '0');
        const minute = String(now.getMinutes()).padStart(2, '0');

        return `${year}-${month}-${day}T${hour}:${minute}`; // Định dạng `datetime-local`
    };

    const order = (room, selectedNum, selectedStartTime, selectedFinishTime) => {

        let cart = cookie.get("cart") || null;
        if (cart === null)
            cart = {}

        const startDate = new Date(selectedStartTime);
        const endDate = new Date(selectedFinishTime);

        const startTimeInMs = startDate.getTime();
        const endTimeInMs = endDate.getTime();

        // Chuyển đổi milliseconds thành giờ
        const diffInHours = (endTimeInMs - startTimeInMs) / (1000 * 60 * 60);

        if (room.roomID in cart) {
            autoHideAlert(setShowAlert);
            return;
        } else {

            cart[room.roomID] = {
                "roomID": room.roomID,
                "name": room.roomName,
                "num": selectedNum,
                "startTime": selectedStartTime,
                "finishTime": selectedFinishTime,
                "price": diffInHours * room.price
            }
            cartDispatch({
                "type": "inc",
                "payload": 1
            });
        }

        cookie.set("cart", cart);
        console.info(cart);
    }

    const openModalOrder = (room) => {
        setSelectedRoom(room);
        setShowModalOrder(true);
        setStartTime("");
        setFinishTime("");
        setSeatNum("");
    }

    const closeAndOrder = () => {
        if (!selectedRoom || !seatNum || !startTime || !finishTime || startTime === finishTime) {
            autoHideAlert(setShowAlertOrder);
            return;
        }
        setShowModalOrder(false);
        setShowAlertOrder(false);
        order(selectedRoom, seatNum, startTime, finishTime);
    }


    if (rooms === null)
        return <CustomSpinner />

    if (rooms.length === 0)
        return <Alert variant="info" className="mt-1">Không tìm thấy phòng phù hợp yêu cầu!</Alert>

    const goToUpdateRoom = (r) => {
        nav(`/addRoom/${r.roomID}`);
    };


    return (
        <div>
            {showAlertDelete && <Alert variant="danger">Xóa phòng thành công</Alert>}
            {showAlertOrder && <Alert variant="danger">Vui lòng kiểm tra dữ liệu nhập</Alert>}
            {showAlert && <Alert variant="danger">Bạn đã đặt phòng này rồi</Alert>}
            <h2 className="text-center text-dark">DANH MỤC PHÒNG HỌC</h2>
            <Row>
                {rooms.map(r => {
                    let url = `/rooms/${r.roomID}`;
                    return <Col xs={12} md={3} className="mt-2 mb-2">
                        <Card style={{ width: '18rem' }}>
                            <Card.Img variant="top" src={r.roomImage} fluid rounded />
                            <Card.Body>
                                <Card.Title><h2 className="text-info" style={{ textAlign: 'center' }}>{r.roomName}{user !== null && user.roleID.roleName === "ROLE_ADMIN" ? (
                                    <>
                                        <span onClick={() => goToUpdateRoom(r)} style={{ fontSize: '0.8em', position: 'absolute', right: '40px' }}>✏️</span>

                                        {/* <span onClick={() => openModalDelete(r)} style={{ fontSize: '0.8em', position: 'absolute', right: '3px' }}>❎</span> */}
                                        <Button variant="danger" style={{ position: 'absolute', right: '3px' }} onClick={() => openModalDelete(r)}>&times;</Button>
                                    </>
                                ) : null}
                                </h2></Card.Title>
                                <Card.Text><span style={{ fontWeight: 'bold' }}>Loại phòng:</span> {r.roomTypeID.roomTypeName}</Card.Text>
                                <Card.Text><span style={{ fontWeight: 'bold' }}>Giá phòng:</span> {formatterPrice.format(r.price)}</Card.Text>
                                <Card.Text><span style={{ fontWeight: 'bold' }}>Tiện ích:</span> {r.utilities}</Card.Text>
                                <Link to={url} className="btn btn-info" style={{ marginRight: "15px", marginLeft: "35px" }} variant="primary">Chi tiết</Link>
                                <Button variant="danger" onClick={() => openModalOrder(r)} >Đặt phòng</Button>
                            </Card.Body>
                        </Card>
                    </Col>
                })}
            </Row>

            <Pagination className="justify-content-center">
                {currentPage > 1 && (
                    <Pagination.Prev onClick={() => setCurrentPage(currentPage - 1)} />
                )}

                {Array.from({ length: totalPages }, (_, i) => (
                    <Pagination.Item
                        key={i + 1}
                        active={currentPage === i + 1}
                        onClick={() => setCurrentPage(i + 1)}
                    >
                        {i + 1}
                    </Pagination.Item>
                ))}

                {currentPage < totalPages && (
                    <Pagination.Next onClick={() => setCurrentPage(currentPage + 1)} />
                )}
            </Pagination>
            {/* Modal nhập thông tin */}
            <Modal show={showModalOrder} onHide={() => setShowModalOrder(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>CHỌN THỜI GIAN VÀ SỐ CHỖ NGỒI</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div className="mb-3">
                        <label htmlFor="startTime" className="form-label">Giờ bắt đầu:</label>
                        <input
                            type="datetime-local"
                            dateFormat="yyyy-MM-dd HH:mm"
                            className="form-control"
                            id="startTime"
                            value={startTime}
                            onChange={(e) => setStartTime(e.target.value)}
                            min={getCurrentDate()}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="finishTime" className="form-label">Giờ kết thúc:</label>
                        <input
                            type="datetime-local"
                            dateFormat="yyyy-MM-dd HH:mm"
                            className="form-control"
                            id="finishTime"
                            value={finishTime}
                            onChange={(e) => setFinishTime(e.target.value)}
                            min={startTime}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="seatNum" className="form-label">Số lượng chỗ ngồi:</label>
                        <input
                            type="number"
                            className="form-control"
                            id="seatNum"
                            value={seatNum}
                            onChange={(e) => setSeatNum(e.target.value)}
                            min="1"
                        />
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => setShowModalOrder(false)}>Đóng</Button>
                    <Button variant="danger" onClick={closeAndOrder}>Xác nhận</Button>
                </Modal.Footer>
            </Modal>

            {/* Modal xác nhận xóa phòng */}
            <Modal show={showModalDelete} onHide={() => setShowModalDelete(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Xác nhận xóa phòng</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    Bạn có chắc chắn muốn xóa phòng này không?
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => setShowModalDelete(false)}>Hủy bỏ</Button>
                    <Button variant="danger" onClick={closeAndDelete}>Xác nhận</Button>
                </Modal.Footer>
            </Modal>
        </div>
    )
}


export default Home;