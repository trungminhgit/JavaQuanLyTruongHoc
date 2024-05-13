import moment from 'moment';

// Hàm tiện ích tính toán các giá trị hiện tại 
export const getCurrentTimeValues = () => {
    const currentWeek = moment().week();
    const currentMonth = moment().month() + 1; // tháng trong moment bắt đầu từ 0
    const currentQuarter = moment().quarter();
    const currentYear = moment().year();

    return {
        currentWeek: currentWeek,
        currentMonth: currentMonth,
        currentQuarter: currentQuarter,
        currentYear: currentYear,
    };
};

export const handlePositiveNumberChange = (setter) => (e) => {
    const value = parseInt(e.target.value, 10);
    if (value >= 1) {
        setter(value);
    }
};