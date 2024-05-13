import { useContext, useState } from "react";
import { Alert, Button,Table } from "react-bootstrap";
import React from "react";
import { Link } from "react-router-dom";
import { MyCartContext, MyUserContext } from "../../App";
import { authAPI, endpoints } from "../../configs/APIs";
import Cookies from "universal-cookie";
import { formatterPrice } from "../../Formatter";
import { formatDateTime } from "../../Formatter";

const Cart = () => {
    const cookie = new Cookies();
    const [, cartDispatch] = useContext(MyCartContext);
    const [user,] = useContext(MyUserContext);
    const [carts, setCarts] = useState(cookie.get("cart") || null);


    const deleteItem = (item) => {
        cartDispatch({
            "type": "dec",
            "payload": 1
        });

        if (item.roomID in carts) {
            setCarts(current => {
                const newCart = { ...current }
                delete newCart[item.roomID];
                cookie.set("cart", newCart);
                return newCart;
            });
        }
    }

    const pay = () => {
        const process = async () => {
            let res = await authAPI().post(endpoints['pay'], carts);
            if (res.status === 200) {
                cookie.remove("cart");

                cartDispatch({
                    "type": "update",
                    "payload": 0
                });

                setCarts([]);
            }
        }
        process();
    }

    console.log(carts);

    if (carts === null)
        return <Alert variant="info" className="mt-2">Không có đơn đặt phòng!</Alert>

    if (carts.length === 0)
        return <Alert variant="success" className="mt-2">Thanh toán thành công!</Alert>

    const cartItems = Object.values(carts);

    if (cartItems.length === 0) {
        return <Alert variant="info" className="mt-2">Không có đơn đặt phòng!</Alert>;
    }

    return <>
        <h1 className="text-center text-info mt-2">ĐƠN ĐẶT PHÒNG</h1>

        <Table striped bordered hover>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên phòng</th>
                    <th>Giá</th>
                    <th>Giờ bắt đầu</th>
                    <th>Giờ kết thúc</th>
                    <th>Số chỗ ngồi</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {Object.values(carts).map(c => {
                    return <tr>
                        <td>{c.roomID}</td>
                        <td>{c.name}</td>
                        <td>{formatterPrice.format(c.price)}</td>
                        <td>{formatDateTime(c.startTime)}</td>
                        <td>{formatDateTime(c.finishTime)}</td>
                        <td>{c.num}</td>

                        <td style={{ textAlign: 'center', verticalAlign: 'middle' }}>
                            <Button variant="danger" onClick={() => deleteItem(c)}>&times;</Button>
                        </td>
                    </tr>
                })}

            </tbody>
        </Table>
        {user === null ? <p>Vui lòng <Link to="/login?next=/cart">đăng nhập</Link> để thanh toán! </p> :
            <Button variant="info" onClick={pay} className="mt-2 mb-2 float-left">Thanh toán</Button>}
    </>
}

export default Cart;