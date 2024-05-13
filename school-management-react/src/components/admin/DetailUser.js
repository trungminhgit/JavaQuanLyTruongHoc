import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { authAPI, endpoints } from "../../configs/APIs";
import CustomSpinner from "../../layouts/CustomSpinner";
import { Button, Col, Image, Row } from "react-bootstrap";

const DetailUser = () => {

    const { userID } = useParams();
    const [user, setUser] = useState(null);
    const nav = useNavigate();

    useEffect(() => {
        const loadUser = async () => {
            let { data } = await authAPI().get(endpoints["user-detail"](userID));
            setUser(data.data);
        }
        loadUser();
    }, [userID]);

    let url = `/user-admin/${userID}`

    if (user === null)
        return <CustomSpinner />

    return <>
        <h1 className="text-center text-info mt-2">CHI TIẾT NGƯỜI DÙNG</h1>
        <Row>
            <Col md={5} xs={6} className="text-center">
                <Image src={user.avatar} rounded fluid />
            </Col>
            <Col md={5} xs={6}>
                <p><span style={{ fontWeight: 'bold' }}>Họ và tên đệm:</span> {user.lastName}</p>
                <p><span style={{ fontWeight: 'bold' }}>Tên:</span> {user.firstName}</p>
                <p><span style={{ fontWeight: 'bold' }}>Tên đăng nhập:</span> {user.username}</p>
                <p><span style={{ fontWeight: 'bold' }}>Mật khẩu:</span> {user.password} </p>
                <p><span style={{ fontWeight: 'bold' }}>Số điện thoại:</span> {user.phone}</p>
                <p><span style={{ fontWeight: 'bold' }}>Email:</span> {user.email}</p>
                <hr />
                <div>
                <Link to={url} className="mt-2">
                    <Button className="mt-2" style={{ marginRight: '10px' }} variant="info">Cập nhật</Button>
                </Link>
                <Link to="/admin" className="mt-2"> 
                    <Button className="mt-2" style={{ marginRight: '10px' }} variant="info">Hủy</Button>
                </Link>
                
                </div>
            </Col>
        </Row>

    </>
}

export default DetailUser;