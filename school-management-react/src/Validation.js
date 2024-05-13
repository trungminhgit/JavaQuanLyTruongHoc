export const validationAddRoom = (values) => {
    const errors = {};
    if (isNaN(parseFloat(values.price)) || parseFloat(values.price) <= 0) {
        errors.price = "Giá phải là số và lớn hơn 0."
    }

    if (isNaN(parseFloat(values.seats)) || parseFloat(values.seats) <= 0) {
        errors.seats = 'Số lượng phải là số lớn hơn 0';
    }

    if (!values.roomTypeID || values.roomTypeID === 'Chọn loại phòng') {
        errors.roomTypeID = 'Vui lòng chọn loại phòng hợp lệ.';
    }


    return errors;
}