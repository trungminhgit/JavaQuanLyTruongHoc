import React from "react";
import { useContext, useState } from "react";
import { Button, Form } from "react-bootstrap";
import Cookies from "universal-cookie";
import { Navigate, useNavigate, useSearchParams } from "react-router-dom";
import { MyUserContext } from "../../App";
import APIs, { authAPI, endpoints } from "../../configs/APIs";

const Login = () => {

    const cookie = new Cookies();
    const [user, dispatch] = useContext(MyUserContext);
    const [username, setUserName] = useState();
    const [password, setPassword] = useState();
    const [showPassword, setShowPassword] = useState(false);
    const [q] = useSearchParams();
    const [loginErrors, setLoginErrors] = useState({ username: "", password: "" });
    const nav = useNavigate();

    const login = (evt) => {
        evt.preventDefault();
        if (validateForm()) {
            const process = async () => {
                try {
                    let res = await APIs.post(endpoints['login'], {
                        "username": username,
                        "password": password
                    });
                    cookie.set("token", res.data.token);
                    console.log(res.data.token);
                    let { data } = await authAPI().get(endpoints['current-user']);
                    cookie.set("user", data.data);
                    dispatch({
                        "type": "login",
                        "payload": data.data
                    });

                    if (data.data.roleID.roleName === "ROLE_ADMIN") {
                        nav("/admin");
                    }
                } catch (err) {
                    console.error(err);
                    if (err.response && err.response.status === 403) {
                        setLoginErrors({ username: "", password: "Vui lòng kiểm tra mật khẩu" });
                    }
                }
            }
            process();
        } else {
            console.log("Dữ liệu không hợp lệ, vui lòng kiểm tra lại.");
        }
    }


    if (user !== null) {
        let next = q.get("next") || "/";
        return <Navigate to={next} />
    }

    const validateForm = () => {
        let errors = { username: "", password: "" };
        let isValid = true;

        if (!username || username.trim() === "") {
            errors.username = "Tên đăng nhập không được để trống";
            isValid = false;
        }

        if (!password || password.trim() === "") {
            errors.password = "Mật khẩu không được để trống";
            isValid = false;
        }

        setLoginErrors(errors);
        return isValid;
    };

    const handleCheckboxChange = () => {
        setShowPassword(!showPassword);
    };

    return <>
        <h1 className="text-center text-info">ĐĂNG NHẬP</h1>


        <Form onSubmit={login}>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                <Form.Label>Tên đăng nhập</Form.Label>
                <Form.Control value={username} onChange={e => setUserName(e.target.value)} type="text" placeholder="Tên đăng nhập" isInvalid={!!loginErrors.username} />
                <Form.Control.Feedback type="invalid">
                    {loginErrors.username}
                </Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                <Form.Label>Mật khẩu</Form.Label>
                <Form.Control type={showPassword ? "text" : "password"} value={password} onChange={e => setPassword(e.target.value)}  placeholder="Mật khẩu" isInvalid={!!loginErrors.password} />
                <Form.Control.Feedback type="invalid">
                    {loginErrors.password}
                </Form.Control.Feedback>
                <Form.Check
                    type="checkbox"
                    label="Hiện mật khẩu"
                    checked={showPassword}
                    onChange={handleCheckboxChange}
                />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                <Button variant="info" type="submit">Đăng nhập</Button>
            </Form.Group>
        </Form>
    </>

}

export default Login;