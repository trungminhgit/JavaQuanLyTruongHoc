import React from "react";
import { useRef, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import APIs, { endpoints } from "../../configs/APIs";
import CustomSpinner from "../../layouts/CustomSpinner";


const Register = () => {

    const [user, setUser] = useState({
        "username": "",
        "password": "",
        "firstName": "",
        "lastName": "",
        "email": "",
        "phone": "",
        "confirmPassword": ""
    });
    const [registerErrors, setRegisterErrors] = useState(null);
    const [loading, setLoading] = useState(false);
    const avatar = useRef();
    const nav = useNavigate();

    const register = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let form = new FormData();
            for (let field in user)
                if (field !== "confirmPassword")
                    form.append(field, user[field]);

            form.append("avatar", avatar.current.files[0]);
            setLoading(true)
            let res = await APIs.post(endpoints['register'], form);
            if (res.status === 200) {
                nav("/login");
            } else
                setRegisterErrors("Hệ thống tạm thời gặp lỗi. Mời bạn quay lại sau!");
        }
        if (user.password === user.confirmPassword)
            process();
        else {
            setRegisterErrors("Mật khẩu nhập lại không khớp!");
        }
    }

    const change = (evt, field) => {
        setUser(current => {
            return { ...current, [field]: evt.target.value }
        })
    }

    return <>
        <h1 className="text-center text-info mt-2">ĐĂNG KÝ NGƯỜI DÙNG</h1>

        {registerErrors === null ? "" : <Alert variant="danger">{registerErrors}</Alert>}

        <Form onSubmit={register}>
            <Form.Group className="mb-3">
                <Form.Label>Họ và tên đệm</Form.Label>
                <Form.Control type="text" onChange={(e) => change(e, "lastName")} placeholder="Họ và tên đệm" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Tên</Form.Label>
                <Form.Control type="text" onChange={(e) => change(e, "firstName")} placeholder="Tên" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Tên đăng nhập</Form.Label>
                <Form.Control value={user.username} onChange={(e) => change(e, "username")} type="text" placeholder="Tên đăng nhập" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Mật khẩu</Form.Label>
                <Form.Control value={user.password} onChange={(e) => change(e, "password")} type="password" placeholder="Mật khẩu" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Xác nhận mật khẩu</Form.Label>
                <Form.Control value={user.confirmPassword} onChange={(e) => change(e, "confirmPassword")} type="password" placeholder="Xác nhận mật khẩu" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Email</Form.Label>
                <Form.Control type="email" onChange={(e) => change(e, "email")} placeholder="Email" />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Điện thoại</Form.Label>
                <Form.Control type="tel" onChange={(e) => change(e, "phone")} placeholder="Điện thoại" />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Ảnh đại diện</Form.Label>
                <Form.Control type="file" ref={avatar} />
            </Form.Group>
            <Form.Group className="mb-3">
                {loading === true ? <CustomSpinner /> : <Button variant="info" type="submit">Đăng ký</Button>}

            </Form.Group>
        </Form>
    </>

}
export default Register;