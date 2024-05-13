import axios from "axios";
import Cookies from "universal-cookie";

const cookie = new Cookies();
const API_VERSION = "/api/v1"
const SERVER = "http://localhost:8080";

export const endpoints = {
    "login": `${API_VERSION}/login`,
    "register": `${API_VERSION}/register`,
    "room-types": `${API_VERSION}/room-types/`,
    "rooms": `${API_VERSION}/rooms/`,
    "room-detail": (roomID) => `${API_VERSION}/rooms/${roomID}`,
    "comments": (roomID) => `${API_VERSION}/rooms/${roomID}/comments/`,
    "add-comments": (roomID) => `${API_VERSION}/rooms/${roomID}/comments/`,
    "pay": `${API_VERSION}/pay/`,
    "deleteRoom": (roomID) => `${API_VERSION}/admin/rooms/${roomID}`,
    "add-room": `${API_VERSION}/admin/rooms`,
    "update-rooms": (roomID) => `${API_VERSION}/admin/rooms/${roomID}`,
    "users": `${API_VERSION}/admin/users/`,
    "roles": `${API_VERSION}/roles/`,
    "update-user": (userID) => `${API_VERSION}/admin/users/${userID}`,
    "current-user": `${API_VERSION}/current-user/`,
    "user-detail": (userID) => `${API_VERSION}/admin/users/${userID}`,
    "delete-user": (userID) => `${API_VERSION}/admin/delete-users/${userID}`,
    "revenue-week": `${API_VERSION}/admin/revenue/week`,
    "revenue-month": `${API_VERSION}/admin/revenue/month`,
    "revenue-quarter": `${API_VERSION}/admin/revenue/quarter`,
    "revenue-year": `${API_VERSION}/admin/revenue/year`
}



export const authAPI = () => {
    const token = cookie.get("token");
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
}

export default axios.create({
    baseURL: SERVER
});