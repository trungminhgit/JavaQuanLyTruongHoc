
import Cookies from "universal-cookie";

const cookies = new Cookies();

const MyUserReducer = (currentState, action) => {
    switch (action.type) {
        case "login":
            return action.payload;
        case "logout":
            cookies.remove("token");
            cookies.remove("user");
            return null;
    }
    return currentState;
}

export default MyUserReducer;