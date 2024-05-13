import React from "react";
import { Spinner } from "react-bootstrap";

// const CustomSpinner = () => {
//     return <Spinner animation="grow" variant="info" />;
// }
const CustomSpinner = () => {
    const spinnerStyle = {
        display: "flex",
        justifyContent: "center", // Căn giữa theo chiều ngang
        alignItems: "top", // Căn giữa theo chiều dọc
        height: "50vh", // Chiều cao toàn bộ viewport
    };

    return (
        <div style={spinnerStyle}>
            <Spinner animation="grow" variant="info" />
        </div>
    );
};


export default CustomSpinner;