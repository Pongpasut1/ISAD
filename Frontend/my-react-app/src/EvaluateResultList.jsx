// EvaluateResultList.jsx
import React, { useEffect, useState } from "react";
import axios from "axios";
import "./evaluateResultList.css"; // ตรวจสอบว่าไฟล์นี้มีอยู่จริงในตำแหน่งเดียวกัน

function EvaluateResultList() {
    const [employeeEvaluations, setEmployeeEvaluations] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchEmployeeEvaluations = async () => {
            try {
                const response = await axios.get('http://localhost:8081/hr/employees/evaluations');
                setEmployeeEvaluations(response.data);
                setLoading(false);
            } catch (error) {
                console.error('Error fetching employee evaluations:', error);
                setError("เกิดข้อผิดพลาดในการดึงข้อมูลการประเมิน");
                setLoading(false);
            }
        };

        fetchEmployeeEvaluations();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div className="error-message">{error}</div>;
    }

    return (
        <div className="evaluate-result-list-container">
            <Header />
            <h1>ผลการประเมินพนักงานทั้งหมด</h1>
            <table>
                <thead>
                    <tr>
                        <th>Employee ID</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Department</th>
                        <th>Total Score</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {employeeEvaluations.map((evaluation, index) => (
                        <tr key={index}>
                            <td>{evaluation.empId}</td>
                            <td>{evaluation.name}</td>
                            <td>{evaluation.surname}</td>
                            <td>{evaluation.department}</td>
                            <td>{evaluation.total_score}</td>
                            <td>
                                <a href={`/hr/evaluation/evaluem/result/${evaluation.empId}`}>
                                    <button className="view-button">ดูผลการประเมิน</button>
                                </a>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

const Header = () => (
    <header className="header">
        <h1>นายเองก็เป็นได้นะ HRน่ะ</h1>
        <div className="profile-circle"></div>
    </header>
);

export default EvaluateResultList;
