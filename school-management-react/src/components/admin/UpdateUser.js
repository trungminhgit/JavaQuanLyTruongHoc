import React, { useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import APIs, { authAPI, endpoints } from "../../configs/APIs";
import CustomSpinner from "../../layouts/CustomSpinner";
import { Button, Form } from "react-bootstrap";

const UpdateUser = () => {
    const [user, setUser] = useState({
        "lastName": '',
        "firstName": '',
        "username": '',
        "password": '',
        "email": '',
        "phone": '',
        "roleID": ''
    });
    const [roles, setRoles] = useState([]);
    const { userID } = useParams();
    const [showPassword, setShowPassword] = useState(false);
    const [updateUserLoading, setUpdateUserLoading] = useState(false);
    const [updateUserError, setUpdateUserError] = useState({});
    const nav = useNavigate();
    const avatar = useRef();

    useEffect(() => {
        const loadUser = async () => {
            try {
                let { data } = await authAPI().get(endpoints["user-detail"](userID));
                setUser(data.data);
            } catch (error) {
                console.error("Load user failed", error);
            }
        }
        loadUser();
    }, [userID]);

    const loadRoles = async () => {
        let res = await APIs.get(endpoints["roles"]);
        setRoles(res.data.data || [])
    }
    useEffect(() => {
        loadRoles();
    }, [])

    console.log(user);

    console.log(roles)

    const change = (e, field) => {
        setUser({ ...user, [field]: e.target.value });
    };


    const updateUser = async (evt) => {
        evt.preventDefault();
        console.log("chạy vào đây");

        setUpdateUserLoading(true);
        const formData = new FormData();
        formData.append('lastName', user.lastName);
        formData.append('firstName', user.firstName);
        formData.append('username', user.username);
        formData.append('password', user.password);
        formData.append('email', user.email);
        formData.append('phone', user.phone);
        formData.append('roleID', user.roleID);

        // Thêm file ảnh nếu có
        if (avatar.current && avatar.current.files[0]) {
            formData.append('avatar', avatar.current.files[0]);
        }

        for (let [key, value] of formData.entries()) {
            console.log(key, value);
        }
        try {
            let resUpdate = await authAPI().post(endpoints['update-user'](userID), formData);
            if (resUpdate.status === 200) {
                console.log("vào đây rooif")
                nav(`/users/${userID}`)
            } else {
                setUpdateUserError("Có lỗi xảy ra. Vui lòng thử lại sau.");
            }
        } catch {
            setUpdateUserError("Có lỗi xảy ra. Vui lòng thử lại sau.");
        }
        setUpdateUserLoading(false);
    }


    const handleCheckboxChange = () => {
        setShowPassword(!showPassword);
    };

    if (user === null)
        return <CustomSpinner />

    return <div className="d-flex justify-content-center" style={{ height: '890px' }}>
        <div style={{
            marginTop: '15px',
            width: '600px',
            border: '1px solid #ccc',
            borderRadius: '10px',
            padding: '20px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)'
        }}>
            <h1 className="text-center text-dark mt-2">CẬP NHẬT NGƯỜI DÙNG</h1>
            <Form onSubmit={updateUser}>
                <Form.Group className="mb-3">
                    <Form.Label>Họ và tên đêm:</Form.Label>
                    <Form.Control type="text" value={user.lastName} onChange={(e) => change(e, "lastName")} placeholder="Họ và tên đệm" required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Tên:</Form.Label>
                    <Form.Control type="text" value={user.firstName} onChange={(e) => change(e, "firstName")} placeholder="Tên" required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Tên đăng nhập:</Form.Label>
                    <Form.Control type="text" value={user.username} onChange={(e) => change(e, "username")} placeholder="Tên đăng nhập" required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Mật khẩu:</Form.Label>
                    <Form.Control type={showPassword ? "text" : "password"} onChange={(e) => change(e, "password")} value={user.password} placeholder="Mật khẩu" required />
                    <Form.Check
                        type="checkbox"
                        label="Hiện mật khẩu"
                        checked={showPassword}
                        onChange={handleCheckboxChange}
                        style={{marginTop: '10px'}}
                    />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Email:</Form.Label>
                    <Form.Control onChange={(e) => change(e, "email")} type="email" value={user.email} placeholder="Email" required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Số điện thoại:</Form.Label>
                    <Form.Control onChange={(e) => change(e, "phone")} type="phone" value={user.phone} placeholder="Số điện thoại" required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Quyền </Form.Label>
                    <Form.Select onChange={(e) => change(e, 'roleID')} value={user.roleID} required>
                        <option>Chọn quyền</option>
                        {roles.map((r) => (
                            <option key={r.roleID} value={r.roleID}>{r.roleName}</option>
                        ))}
                    </Form.Select>
                    {/* {addRoomError.roomTypeID && <p style={{ color: "red" }}>{addRoomError.roomTypeID}</p>} */}
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Ảnh phòng học</Form.Label>
                    <Form.Control type="file" ref={avatar} />
                    {/* {addRoomError.roomImage && <p style={{ color: "red" }}>{addRoomError.roomImage}</p>} */}
                </Form.Group>
                <Form.Group className="mb-3 d-flex justify-content-center">
                    {updateUserLoading === true ? (
                        <CustomSpinner />
                    ) : (
                        <Button style={{marginTop: '10px'}} variant="info" type="submit">
                            Cập nhật
                        </Button>
                    )}
                </Form.Group>
            </Form>
        </div>
    </div>
}

export default UpdateUser;