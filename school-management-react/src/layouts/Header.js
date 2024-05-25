import React from "react";
import { useContext, useEffect, useState } from "react";
import { Badge, Button, Col, Container, Form, Nav, Navbar, NavDropdown, Row } from "react-bootstrap";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { MyCartContext, MyUserContext } from "../App";
import APIs, { endpoints } from "../configs/APIs";
import CustomSpinner from "./CustomSpinner";


const Header = () => {


    const [user, dispatch] = useContext(MyUserContext);
    const [cartCounter,] = useContext(MyCartContext);
    const [roomTypes, setRoomTypes] = useState([]);
    const [kw, setKw] = useState("");
    const nav = useNavigate();
    const location = useLocation();


    const loadRoomTypes = async () => {
        let res = await APIs.get(endpoints['room-types'])
        setRoomTypes(res.data.data);
    }

    useEffect(() => {
        loadRoomTypes();
    }, [])

    const search = (e) => {
        e.preventDefault();

        if (kw.trim() === "") return; 

        let targetRoute = "/"; 

        if (location.pathname.includes("admin")) {
            targetRoute = "/admin"; 
        }

        nav(`${targetRoute}?kw=${kw.trim()}`); 
        setKw("")
    };



    const logout = () => {
        dispatch({
            "type": "logout"
        })

    }

    if (roomTypes === null)
        return <CustomSpinner />;

    return (
        <Navbar expand="lg" className="container-fluid" style={{ width: '100%' }}>
            <Container>
                <Navbar.Brand href="/" style={{ fontSize: '36px', color: 'Highlight' }}><span style={{ fontSize: '1.5em' }}>🏫</span> OU Management</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Link className="nav-link" to="/" key={"Home"}><span>&#127968;</span>Trang chủ</Link>
                        <NavDropdown title={<><span style={{ fontSize: '1.1em' }}>📋</span> {" "}Danh mục phòng</>} id="basic-nav-dropdown">
                            {roomTypes.map(r => {
                                let h = `/?roomTypeID=${r.roomTypeID}`;
                                return <NavDropdown.Item as={Link} to={h} key={r.roomTypeID}>{r.roomTypeName}</NavDropdown.Item>
                            })}
                        </NavDropdown>
                        {user === null ? <>
                            <Link className="nav-link text-dark" to="/login">&#128589;Đăng nhập</Link>
                            <Link className="nav-link text-dark" to="/register">&#9997;Đăng ký</Link>
                        </> : <>
                            <Link className="nav-link text-dark" to="/" style={{ display: 'flex', alignItems: 'center', marginLeft: '25px' }} >
                                Chào, {user.firstName}
                                <img
                                    src={user.avatar} // Sử dụng đường dẫn hoặc hình ảnh mặc định
                                    alt={`${user.firstName}'s avatar`}
                                    className="avatar"
                                    style={{ width: '30px', height: '25px', borderRadius: '50%', marginRight: '5px', verticalAlign: 'bottom' }}
                                />
                            </Link>
                            <NavDropdown title={<>
                                <span style={{ fontSize: '1.1em' }}>⚙️</span></>} id="basic-nav-dropdown">
                                {user !== null && user.roleID.roleName === "ROLE_ADMIN" ? <>
                                    <NavDropdown.Item as={Link} to="/admin" key="user">Quản lý người dùng</NavDropdown.Item>
                                    <NavDropdown.Item as={Link} to="/register" key="admin-user">Thêm người dùng</NavDropdown.Item>
                                    <NavDropdown.Item as={Link} to="/addRoom" key="admin-room">Thêm phòng</NavDropdown.Item>
                                    <NavDropdown.Item as={Link} to="/revenue" key="admin-revenue">Thống kê</NavDropdown.Item>
                                </> : null}

                                <Link className="dropdown-item" to="/" onClick={logout} key="userDetail">Đăng xuất</Link>
                            </NavDropdown>
                        </>}
                        <Link className="nav-link text-danger" to="/cart">🗃️ <Badge bg="danger">{cartCounter}</Badge></Link>
                    </Nav>
                </Navbar.Collapse>
                <Form onSubmit={search} inline="true">
                    <Row>
                        <Col xs="auto">
                            <Form.Control
                                type="text"
                                value={kw}
                                onChange={e => setKw(e.target.value)}
                                placeholder="Nhập từ khóa..." name="kw"
                                className=" mr-sm-2"
                            />
                        </Col>
                        <Col xs="auto">
                            <Button type="submit" disabled={kw.trim() === ""}>Tìm</Button>
                        </Col>
                    </Row>
                </Form>
            </Container>
        </Navbar>
    )
}

export default Header;