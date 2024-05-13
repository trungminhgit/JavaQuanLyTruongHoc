
export const formatterPrice = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND', // Hoặc 'USD' cho đô la Mỹ
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
});

export const formatDateTime = (dateString) => {
    const date = new Date(dateString);

    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Đảm bảo có hai chữ số
    const day = String(date.getDate()).padStart(2, '0');
    const hour = String(date.getHours()).padStart(2, '0');
    const minute = String(date.getMinutes()).padStart(2, '0');

    return `${year}-${month}-${day} ${hour}:${minute}`; // Định dạng thời gian
};

export const autoHideAlert = (setShowFunction, delay = 2000) => {
    setShowFunction(true); // Hiển thị cảnh báo
    setTimeout(() => {
        setShowFunction(false); // Tự động ẩn sau 'delay' (mặc định là 3 giây)
    }, delay);
};