import React, { useEffect } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/user/Home";
import Footer from "./layouts/Footer";
import Header from "./layouts/Header";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container } from "react-bootstrap";
import Login from "./components/user/Login";
import { createContext, useReducer } from "react";
import MyUserReducer from "./reducers/MyUserReducer";
import Cookies from "universal-cookie";
import Register from "./components/user/Register";
import 'moment/locale/vi';
import Admin from "./components/admin/Admin";
import DetailRoom from "./components/user/DetailRoom";
import MyCartCounterReducer from "./reducers/MyCartCounterReducer";
import Cart from "./components/user/Cart";
import AddRoom from "./components/admin/AddRoom";
import DetailUser from "./components/admin/DetailUser";
import UpdateUser from "./components/admin/UpdateUser";
import Revenue from "./components/admin/Revenue";
import backgroundImage from './image/th.jpg';



export const cookie = new Cookies();
export const MyUserContext = createContext();
export const MyCartContext = createContext();

const countCart = () => {
  let cart = cookie.get("cart") || null;
  if (cart !== null)
    return Object.values(cart).length;
  return 0;
}

const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.get("user") || null);
  const [cartCounter, cartDispatch] = useReducer(MyCartCounterReducer, countCart());
  useEffect(() => {
    const newCartCount = countCart();
    cartDispatch({ type: "update", payload: newCartCount });
  }, []);

  return (
    <MyUserContext.Provider value={[user, dispatch]}>
      <MyCartContext.Provider value={[cartCounter, cartDispatch]}>
      <div style={{
          backgroundImage: `url(${backgroundImage})`,
          backgroundSize: 'cover',
          backgroundPosition: 'center',
          minHeight: '100vh',
          display: 'flex',
          flexDirection: 'column'
        }}>
        <BrowserRouter>
          <Header />
          <Container>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="/rooms/:roomID" element={<DetailRoom />} />
              <Route path="/admin" element={<Admin />} />
              <Route path="/cart" element={<Cart />} />
              <Route path="/addRoom/:roomID" element={<AddRoom />} />
              <Route path="/addRoom" element={<AddRoom />} />
              <Route path="/users/:userID" element={<DetailUser />} />
              <Route path="/user-admin/:userID" element={<UpdateUser />} />
              <Route path="/revenue" element={<Revenue />} />
            </Routes>
          </Container>
          <Footer />
        </BrowserRouter>
        </div>
      </MyCartContext.Provider>
    </MyUserContext.Provider>
  )
}

export default App;
