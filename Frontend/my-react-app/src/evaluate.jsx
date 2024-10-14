import React, { useEffect, useState } from 'react';
import axios from 'axios'; // หากใช้ axios
import './people.css';

function Evaluate() {
    const [criteriaList, setCriteriaList] = useState([]);
    const [error, setError] = useState(null); // เพิ่ม state สำหรับข้อผิดพลาด
    const [inputs, setInputs] = useState({}); // เพิ่ม state สำหรับเก็บข้อมูล input
    const [showPopup, setShowPopup] = useState(false);

    useEffect(() => {
        const fetchCriteria = async () => {
            try {
                const response = await axios.get('http://localhost:8081/api/criteria'); // แก้ไข URL ให้ถูกต้อง
                setCriteriaList(response.data);
            } catch (error) {
                console.error("Error fetching criteria:", error);
                setError("ไม่สามารถดึงข้อมูลเกณฑ์ได้ กรุณาลองใหม่ในภายหลัง."); // แสดงข้อความข้อผิดพลาด
            }
        };

        fetchCriteria();
    }, []);

    const handleInputChange = (criteriaId, value) => {
        setInputs((prevInputs) => ({
            ...prevInputs,
            [criteriaId]: value, // เก็บค่าที่พิมพ์ใน input
        }));
    };

    const onSave = async () => {
        try {
            const response = await axios.post('http://localhost:8081/api/save-evaluation', inputs);
            console.log("ส่งข้อมูลสำเร็จ:", response.data);
            setShowPopup(true); // เปิด pop-up เมื่อบันทึกสำเร็จ
        } catch (error) {
            console.error("Error sending data:", error);
            alert("เกิดข้อผิดพลาดในการบันทึกข้อมูล!"); // pop-up ข้อผิดพลาด
        }
    };
    
    const closePopup = () => {
        setShowPopup(false); // ปิด pop-up
    };
    
    return (
        <div className='container'>
            <Header />
            <div>
                {error && <p>{error}</p>}
                {criteriaList.length > 0 ? (
                    <ul>
                        {criteriaList.map((criteria) => (
                            <li key={criteria.criteriaId}>
                                <p>ID: {criteria.criteriaId}</p>
                                <p>ชื่อเกณฑ์: {criteria.criteria}</p>
                                <input
                                    type="text"
                                    onChange={(e) => handleInputChange(criteria.criteriaId, e.target.value)}
                                />
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>ยังไม่มีเกณฑ์</p>
                )}
            </div>
            <button onClick={onSave}>ส่ง</button>
    
            {showPopup && (
                <div className="popup">
                    <div className="popup-content">
                        <p>บันทึกข้อมูลสำเร็จ!</p>
                        <button onClick={closePopup}>ตกลง</button>
                    </div>
                </div>
            )}
        </div>
    );
}

const Header = () => (
    <header className="header">
        <h1>นายเองก็เป็นได้นะ HRน่ะ</h1>
        <div className="profile-circle"></div>
    </header>
);

export default Evaluate;
