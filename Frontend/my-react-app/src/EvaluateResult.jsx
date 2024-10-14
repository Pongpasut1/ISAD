// EvaluateResult.jsx
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import './evaluateResult.css'; // เพิ่ม CSS ตามต้องการ
import axios from 'axios';

function EvaluateResult() {
    const { empId } = useParams();
    const [evaluations, setEvaluations] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchEvaluationResults = async () => {
            try {
                const response = await axios.get(`http://localhost:8081/hr/evaluation/results/${empId}`);
                setEvaluations(response.data);
                setLoading(false);
            } catch (error) {
                console.error('Error fetching evaluation data:', error);
                setError("เกิดข้อผิดพลาดในการดึงข้อมูลการประเมิน");
                setLoading(false);
            }
        };

        fetchEvaluationResults();
    }, [empId]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div className="error-message">{error}</div>;
    }

    if (evaluations.length === 0) {
        return <div>ไม่พบข้อมูลการประเมินสำหรับพนักงานนี้</div>;
    }

    return (
        <div className="container">
            <Header />
            <div className="evaluate-result-container">
                <h2>ผลการประเมินพนักงาน: {empId}</h2>
                {evaluations.map((evaluation, index) => (
                    <div key={index} className="evaluation-item">
                        <h2>การประเมินครั้งที่ {index + 1}</h2>
                        <p><strong>วันที่ประเมิน:</strong> {new Date(evaluation.evaluationDate).toLocaleDateString()}</p>
                        <p><strong>Total Score:</strong> {evaluation.total_score}</p>
                        <p><strong>KPI Score:</strong> {evaluation.KPI_score}</p>
                        <p><strong>Ability Score:</strong> {evaluation.ability_score}</p>
                        <p><strong>Attendance Score:</strong> {evaluation.attendance_score}</p>
                        <p><strong>Comment:</strong> {evaluation.comment}</p>
                        {/* คุณสามารถเพิ่มข้อมูลอื่นๆ ตามที่ต้องการ */}
                    </div>
                ))}
            </div>
        </div>
    );
}

const Header = () => (
    <header className="header">
        <h1>นายเองก็เป็นได้นะ HRน่ะ</h1>
        <div className="profile-circle"></div>
    </header>
);

export default EvaluateResult;
