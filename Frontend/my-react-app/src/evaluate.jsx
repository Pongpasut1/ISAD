import React, { useEffect, useState } from 'react';
import axios from 'axios'; // หากใช้ axios
import './people.css';

function Evaluate() {
    const [criteriaList, setCriteriaList] = useState([]);
    const [error, setError] = useState(null); // เพิ่ม state สำหรับข้อผิดพลาด
    const [inputs, setInputs] = useState({}); // เพิ่ม state สำหรับเก็บข้อมูล input

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

    const onSave = () => {
        console.log("ข้อมูลที่จะส่ง:", inputs); // สามารถเปลี่ยนเป็นการส่งข้อมูลไปยัง backend ได้
        // คุณสามารถใช้ axios เพื่อส่งข้อมูลไปยัง backend ที่นี่
    };

    return (
        <div className='container'>
            <Header />
            <div>
                {error && <p>{error}</p>} {/* แสดงข้อความข้อผิดพลาด */}
                {criteriaList.length > 0 ? (
                    <ul>
                        {criteriaList.map((criteria) => (
                            <li key={criteria.criteriaId}>
                                <p>ID: {criteria.criteriaId}</p>
                                <p>ชื่อเกณฑ์: {criteria.criteria}</p>
                                <input
                                    type="text"
                                    onChange={(e) => handleInputChange(criteria.criteriaId, e.target.value)} // จัดการเมื่อมีการเปลี่ยนแปลง input
                                />
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>ยังไม่มีเกณฑ์</p> // แสดงข้อความหากไม่มีเกณฑ์
                )}
            </div>
            <button onClick={onSave}>ส่ง</button>
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
