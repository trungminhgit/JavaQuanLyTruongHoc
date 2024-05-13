import React, { useContext, useEffect } from "react";
import { useState } from "react";
import { authAPI, endpoints } from "../../configs/APIs";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import { MyUserContext } from "../../App";
import { Alert, Button, Modal, Pagination, Table } from "react-bootstrap";
import CustomSpinner from "../../layouts/CustomSpinner";
import { autoHideAlert } from "../../Formatter";

const Admin = () => {
    const [users, setUsers] = useState(null);
    const [q] = useSearchParams();
    const [user,] = useContext(MyUserContext);
    const nav = useNavigate();
    const [currentPage, setCurrentPage] = useState(1); // Trang hiện tại
    const [totalPages, setTotalPages] = useState(1);
    const pageSize = 8;
    const [showAlertDelete, setShowAlertDelete] = useState(false);
    const [showModalDelete, setShowModalDelete] = useState(false);
    const [selectedUser, setSelectedUser] = useState(null);

    const loadUsers = async () => {
        try {

            let endpoint = `${endpoints['users']}?page=${currentPage}&pageSize=${pageSize}`;

            let kw = q.get("kw");
            if (kw !== null)
                endpoint += `&kw=${kw}`;

            let res = await authAPI().get(endpoint);
            if (res.data.status === "OK") {
                setUsers(res.data.data);
                setTotalPages(res.data.totalPages);
            } else {
                setUsers([]);
                
            }
        } catch (ex) {
            console.error(ex);
            setUsers([]);
        }
    }
    useEffect(() => {
        loadUsers();
    }, [q, currentPage, users]);


    const deleteUser = (userID) => {
        const process = async () => {

            let { data } = await authAPI().delete(endpoints["delete-user"](userID));
            if (data.status === "OK") {
                console.log(data);
                autoHideAlert(setShowAlertDelete);
                await loadUsers();
            };
        }
        process();
    }
    const openModalDelete = (user) => {
        setSelectedUser(user);
        setShowModalDelete(true)
    }

    const closeAndDelete = () => {
        setShowModalDelete(false);
        deleteUser(selectedUser.userID);
    }


    if (users === null)
        return <CustomSpinner />

    if (users.length === 0)
        return <Alert variant="info" className="mt-1">Không tìm thấy thông tin người dùng!</Alert>

    return (
        <div>
            {showAlertDelete && <Alert variant="danger">Xóa người dùng thành công</Alert>}
            
            <h2 className="text-center text-info">DANH SÁCH NGƯỜI DÙNG</h2>
            <Table striped bordered hover>
            <thead>
                <tr style={{ textAlign: 'center', verticalAlign: 'middle' }}>
                    <th>ID</th>
                    <th>Họ</th>
                    <th>Tên</th>
                    <th>Email</th>
                    <th>Số điện thoại</th>
                    <th>Quyền</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {users.map(u => {
                    let url = `/users/${u.userID}`;
                    return <tr>
                        <td>{u.userID}</td>
                        <td>{u.lastName}</td>
                        <td>{u.firstName}</td>
                        <td>{u.email}</td>
                        <td>{u.phone}</td>
                        <td>{u.roleID.roleName}</td>
                        <td style={{ textAlign: 'center', verticalAlign: 'middle' }}>
                            <Link to={url} className="btn btn-info"  variant="primary">Chi tiết</Link>
                        </td>
                        <td style={{ textAlign: 'center', verticalAlign: 'middle' }}>
                            <Button variant="danger" onClick={() => openModalDelete(u)}>&times;</Button>
                        </td>
                    </tr>
                })}

            </tbody>
        </Table>

            <Modal show={showModalDelete} onHide={() => setShowModalDelete(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Xác nhận xóa người dùng</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    Bạn có chắc chắn muốn xóa người dùng này không?
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => setShowModalDelete(false)}>Hủy bỏ</Button>
                    <Button variant="danger" onClick={closeAndDelete}>Xác nhận</Button>
                </Modal.Footer>
            </Modal>

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
        </div>
    )

}

export default Admin;