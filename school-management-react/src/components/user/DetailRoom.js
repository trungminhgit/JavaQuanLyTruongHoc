import React from "react";
import { useContext, useEffect, useState } from "react";
import { MyUserContext } from "../../App";
import APIs, { authAPI, endpoints } from "../../configs/APIs";
import { Link, useParams } from "react-router-dom";
import { Alert, Button, Col, Form, Image, ListGroup, Row } from "react-bootstrap";
import CustomSpinner from "../../layouts/CustomSpinner";
import Moment from "react-moment";
import { autoHideAlert } from "../../Formatter";



const DetailRoom = () => {
    const [userID,] = useContext(MyUserContext);
    const [room, setRoom] = useState(null);
    const { roomID } = useParams();
    console.log(roomID);
    const [comments, setComments] = useState(null);
    const [description, setDescription] = useState();
    const [showAlertComment, setShowAlertComment] = useState(false);

    useEffect(() => {
        const loadRoom = async () => {
            let { data } = await APIs.get(endpoints["room-detail"](roomID));
            setRoom(data.data);
        }
        console.log(roomID);

        const loadComments = async () => {
            let res = await APIs.get(endpoints["comments"](roomID));
            if (res.status === 404)
                setComments([]);

            else
                setComments(res.data.data);

            console.log(res.data.data);
        }


        loadRoom();
        loadComments();
    }, [roomID]);



    const addComment = () => {
        if (!description || description.trim() !== "") {
            const process = async () => {
                let { data } = await authAPI().post(endpoints["add-comments"](roomID),
                    { "description": description });

                setComments([...comments, data.data]);
            }
            setDescription('');
            process();
        } else {
            autoHideAlert(setShowAlertComment);
            return;
        }

    }

    if (room === null || comments === null)
        return <CustomSpinner />

    let url = `/login?next=/rooms/${roomID}`;

    return <>
        {showAlertComment && <Alert variant="danger">Vui lòng nhập nội dung</Alert>}
        <h1 className="text-center text-info mt-2">CHI TIẾT PHÒNG ({room.roomName})</h1>
        <Row>
            <Col md={5} xs={6} className="text-center">
                <Image src={room.roomImage} rounded fluid />
            </Col>
            <Col md={5} xs={6}>
                <h2 className="text-danger">{room.roomName}</h2>
                <p><span style={{ fontWeight: 'bold' }}>Loại phòng:</span> {room.roomTypeID.roomTypeName}</p>
                <p><span style={{ fontWeight: 'bold' }}>Số lượng chỗ ngồi:</span> {room.seats}</p>
                <p><span style={{ fontWeight: 'bold' }}>Giá:</span> {room.price} VNĐ</p>
                <p><span style={{ fontWeight: 'bold' }}>Tiện ích:</span> {room.utilities} </p>
                <p><span style={{ fontWeight: 'bold' }}>Mô tả:</span> {room.description} VNĐ</p>
            </Col>
        </Row>
        <hr />


        {userID === null ? <p>Vui lòng <Link to={url}>đăng nhập</Link> để thêm đánh giá! </p> : <>
            <Form.Control as="textarea" aria-label="With textarea" value={description} onChange={e => setDescription(e.target.value)} placeholder="Nội dung đánh giá" />
            <Button onClick={addComment} className="mt-2" variant="info" disabled={!description || description.trim() === ""}>Thêm đánh giá</Button>
            <Link to="/" className="mt-2">
                <Button className="mt-2" style={{ marginLeft: '10px' }} variant="info">Hủy</Button>
            </Link>
        </>}
        <hr />

        <ListGroup>
            {comments.map(c => <ListGroup.Item key={c.commentID} className="d-flex justify-content-between align-items-center">
                <div><strong style={{ fontSize: '1.2rem' }}>{c.userID.username}</strong>: {c.description}</div>
                <div><Moment locale="vi" fromNow>{c.createDate}</Moment></div>
            </ListGroup.Item>)
            }
        </ListGroup>



    </>

}
export default DetailRoom;
