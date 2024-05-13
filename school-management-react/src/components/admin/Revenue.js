import React, { useState } from "react";
import moment from 'moment';
import { getCurrentTimeValues } from "../../GetDateAndChange";
import { handlePositiveNumberChange } from "../../GetDateAndChange";
import { authAPI, endpoints } from "../../configs/APIs";
import { formatterPrice } from "../../Formatter";
const Revenue = () => {
    const [activeTab, setActiveTab] = useState('week');

    const handleTabClick = (tab) => {
        setActiveTab(tab);
    };

    return (
        <div style={{ display: 'flex' }}>
            {/* Thanh bên */}
            <div style={{ width: '200px', background: '#f5f5f5', padding: '10px' }}>
                <div
                    onClick={() => handleTabClick('week')}
                    style={{ cursor: 'pointer', padding: '10px', background: activeTab === 'week' ? '#e0e0e0' : 'transparent' }}
                >
                    Thống kê theo tuần
                </div>
                <div
                    onClick={() => handleTabClick('month')}
                    style={{ cursor: 'pointer', padding: '10px', background: activeTab === 'month' ? '#e0e0e0' : 'transparent' }}
                >
                    Thống kê theo tháng
                </div>
                <div
                    onClick={() => handleTabClick('quarter')}
                    style={{ cursor: 'pointer', padding: '10px', background: activeTab === 'quarter' ? '#e0e0e0' : 'transparent' }}
                >
                    Thống kê theo quý
                </div>
                <div
                    onClick={() => handleTabClick('year')}
                    style={{ cursor: 'pointer', padding: '10px', background: activeTab === 'year' ? '#e0e0e0' : 'transparent' }}
                >
                    Thống kê theo năm
                </div>
            </div>

            {/* Nội dung chính */}
            <div style={{ flex: 1, padding: '20px' }}>
                {activeTab === 'week' && <WeekTab />}
                {activeTab === 'month' && <MonthTab />}
                {activeTab === 'quarter' && <QuarterTab />}
                {activeTab === 'year' && <YearTab />}
            </div>
        </div>
    );
};

// Các thành phần cho mỗi tab
const WeekTab = () => {
    const { currentWeek, currentYear } = getCurrentTimeValues();
    const [week, setWeek] = useState(currentWeek);
    const [year, setYear] = useState(currentYear);
    const [revenueWeek, setRevenueWeek] = useState(null);

    const loadRevenueWeek = async (week, year) => {
        const url = `${endpoints["revenue-week"]}?week=${week}&year=${year}`;
        let res = await authAPI().get(url);
        if (res.data.data !== null) {
            setRevenueWeek(res.data.data);
        }
        if (res.status === 204) {
            setRevenueWeek(0);
        }
    }

    const handleWeekChange = handlePositiveNumberChange(setWeek);
    const handleYearChange = handlePositiveNumberChange(setYear)
    return (
        <div style={{ textAlign: 'center' }}>
            <h2 style={{ marginBottom: '20px', color: 'Highlight' }}>THỐNG KÊ THEO TUẦN</h2>
            {/* Giao diện để chọn tuần và năm */}
            <div style={{ display: 'flex', justifyContent: 'center', marginBottom: '10px', marginTop: '50px' }}>
                <div style={{ marginRight: '10px' }}>
                    <label style={{ marginRight: '5px', marginLeft: '15px', fontSize: '18px', fontWeight: 'bold' }}>Tuần:</label>
                    <input type="number"
                        style={{
                            width: '90px',
                            textAlign: 'center'
                        }}
                        value={week}  // Đặt giá trị mặc định
                        onChange={handleWeekChange}
                    />
                </div>
                <div>
                    <label style={{ marginRight: '2px', marginLeft: '39px', fontSize: '18px', fontWeight: 'bold' }}>Năm:</label>
                    <input type="number"
                        style={{
                            width: '90px',
                            textAlign: 'center'
                        }}
                        value={year}  // Đặt giá trị mặc định
                        onChange={handleYearChange}
                    />
                </div>
                <div>
                    <button onClick={() => loadRevenueWeek(week, year)} style={{ marginLeft: '25px', backgroundColor: 'cyan' }}>Thống kê</button>
                </div>
            </div>
            <div style={{ marginTop: '30px', marginLeft: '47px' }}>
                <label style={{ marginLeft: '102px', fontSize: '20px', fontWeight: 'bold' }}>
                    Doanh thu trong tuần {week} của năm {year}:
                </label>
                <input
                    type="text"
                    style={{ marginLeft: '10px', fontSize: '20px', padding: '5px', width: '180px', textAlign: 'right' }}
                    value={formatterPrice.format(revenueWeek)}
                    readOnly
                />
            </div>
        </div>
    );
};

const MonthTab = () => {
    const { currentMonth, currentYear } = getCurrentTimeValues();
    const [month, setMonth] = useState(currentMonth);
    const [year, setYear] = useState(currentYear);
    const [revenueMonth, setRevenueMonth] = useState(null);

    const loadRevenueMonth = async (month, year) => {
        const url = `${endpoints["revenue-month"]}?month=${month}&year=${year}`;
        let res = await authAPI().get(url);
        if (res.data.data !== null) {
            setRevenueMonth(res.data.data);
        }
        if (res.status === 204) {
            setRevenueMonth(0);
        }
    }

    const handleMonthChange = handlePositiveNumberChange(setMonth);
    const handleYearChange = handlePositiveNumberChange(setYear)

    return (
        <div style={{ textAlign: 'center' }}>
            <h2 style={{ marginBottom: '20px', color: 'Highlight' }}>THỐNG KÊ THEO THÁNG</h2>
            {/* Giao diện để chọn tuần và năm */}
            <div style={{ display: 'flex', justifyContent: 'center', marginBottom: '10px', marginTop: '50px' }}>
                <div style={{ marginRight: '10px' }}>
                    <label style={{ marginRight: '5px', marginLeft: '15px', fontSize: '18px', fontWeight: 'bold' }}>Tháng:</label>
                    <input type="number"
                        style={{
                            width: '90px',
                            textAlign: 'center'
                        }}
                        value={month}  // Đặt giá trị mặc định
                        onChange={handleMonthChange}
                    />
                </div>
                <div>
                    <label style={{ marginRight: '2px', marginLeft: '39px', fontSize: '18px', fontWeight: 'bold' }}>Năm:</label>
                    <input type="number"
                        style={{
                            width: '90px',
                            textAlign: 'center'
                        }}
                        value={year}  // Đặt giá trị mặc định
                        onChange={handleYearChange}
                    />
                </div>
                <div>
                    <button onClick={() => loadRevenueMonth(month, year)} style={{ marginLeft: '25px', backgroundColor: 'cyan' }}>Thống kê</button>
                </div>
            </div>
            <div style={{ marginTop: '30px', marginLeft: '47px' }}>
                <label style={{ marginLeft: '92px', fontSize: '20px', fontWeight: 'bold' }}>
                    Doanh thu trong tháng {month} của năm {year}:
                </label>
                <input
                    type="text"
                    style={{ marginLeft: '10px', fontSize: '20px', padding: '5px', width: '180px', textAlign: 'right' }}
                    value={formatterPrice.format(revenueMonth)}
                    readOnly
                />
            </div>
        </div>
    );
};

const QuarterTab = () => {
    const { currentQuarter, currentYear } = getCurrentTimeValues();
    const [quarter, setQuarter] = useState(currentQuarter);
    const [year, setYear] = useState(currentYear);
    const [revenueQuarter, setRevenueQuarter] = useState(null);

    const loadRevenueQuarter = async (quarter, year) => {
        const url = `${endpoints["revenue-quarter"]}?quarter=${quarter}&year=${year}`;
        let res = await authAPI().get(url);
        if (res.data.data !== null) {
            setRevenueQuarter(res.data.data);
        }
        if (res.status === 204) {
            setRevenueQuarter(0);
        }
    }

    const handleQuarterChange = handlePositiveNumberChange(setQuarter);
    const handleYearChange = handlePositiveNumberChange(setYear)

    return (
        <div style={{ textAlign: 'center' }}>
            <h2 style={{ marginBottom: '20px', color: 'Highlight' }}>THỐNG KÊ THEO QUÝ</h2>
            <div style={{ display: 'flex', justifyContent: 'center', marginBottom: '10px', marginTop: '50px' }}>
                <div style={{ marginRight: '10px' }}>
                    <label style={{ marginRight: '5px', marginLeft: '15px', fontSize: '18px', fontWeight: 'bold' }}>Quý:</label>
                    <input type="number"
                        style={{
                            width: '90px',
                            textAlign: 'center'
                        }}
                        value={quarter}  // Đặt giá trị mặc định
                        onChange={handleQuarterChange}
                    />
                </div>
                <div>
                    <label style={{ marginRight: '2px', marginLeft: '39px', fontSize: '18px', fontWeight: 'bold' }}>Năm:</label>
                    <input type="number"
                        style={{
                            width: '90px',
                            textAlign: 'center'
                        }}
                        value={year}  // Đặt giá trị mặc định
                        onChange={handleYearChange}
                    />
                </div>
                <div>
                    <button onClick={() => loadRevenueQuarter(quarter, year)} style={{ marginLeft: '25px', backgroundColor: 'cyan' }}>Thống kê</button>
                </div>
            </div>
            <div style={{ marginTop: '30px', marginLeft: '47px' }}>
                <label style={{ marginLeft: '90px', fontSize: '20px', fontWeight: 'bold' }}>
                    Doanh thu trong quý {quarter} của năm {year}:
                </label>
                <input
                    type="text"
                    style={{ marginLeft: '10px', fontSize: '20px', padding: '5px', width: '180px', textAlign: 'right' }}
                    value={formatterPrice.format(revenueQuarter)}
                    readOnly
                />
            </div>
        </div>
    );
};

const YearTab = () => {
    const { currentYear } = getCurrentTimeValues();
    const [year, setYear] = useState(currentYear);
    const [revenueYear, setRevenueYear] = useState(null);

    const loadRevenueYear = async (year) => {
        const url = `${endpoints["revenue-year"]}?year=${year}`;
        let res = await authAPI().get(url);
        if (res.data.data !== null) {
            setRevenueYear(res.data.data);
        }
        if (res.status === 204) {
            setRevenueYear(0);
        }
    }

    const handleYearChange = handlePositiveNumberChange(setYear)

    return (
        <div style={{ textAlign: 'center' }}>
            <h2 style={{ marginBottom: '20px', color: 'Highlight' }}>THỐNG KÊ THEO NĂM</h2>
            <div style={{ display: 'flex', justifyContent: 'center', marginBottom: '10px', marginTop: '50px' }}>
                <div>
                    <label style={{ marginRight: '2px', marginLeft: '39px', fontSize: '18px', fontWeight: 'bold' }}>Năm:</label>
                    <input
                        type="number"
                        style={{
                            width: '90px',
                            textAlign: 'center'
                        }}
                        value={year}
                        onChange={handleYearChange}
                    />
                </div>
                <div>
                    <button onClick={() => loadRevenueYear(year)} style={{ marginLeft: '25px', backgroundColor: 'cyan' }}>Thống kê</button>
                </div>
            </div>
            <div style={{ marginTop: '30px', textAlign: 'center' }}>
                <label style={{ fontSize: '20px', fontWeight: 'bold' }}>
                    Doanh thu trong năm {year}:
                </label>
                <input
                    type="text"
                    style={{ fontSize: '20px', padding: '5px', width: '180px', marginLeft: '10px', textAlign: 'right' }}
                    value={formatterPrice.format(revenueYear)}
                    readOnly
                />
            </div>
        </div>
    );
};

export default Revenue;