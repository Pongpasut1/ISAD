// Evaluate.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import './people.css';

function Evaluate() {
    const { empId } = useParams(); // ดึง empId จาก URL
    const navigate = useNavigate();
    const [criteriaList, setCriteriaList] = useState([]);
    const [error, setError] = useState(null);
    const [inputs, setInputs] = useState({
        employeeId: empId, // ตั้งค่า employeeId จาก empId
        startDate: '',
        endDate: '',
        comment: '',
        scores: {}
    });
    const [showPopup, setShowPopup] = useState(false);
    const [isLoading, setIsLoading] = useState(true);
    const [selectedCriteriaDetails, setSelectedCriteriaDetails] = useState(null);

    useEffect(() => {
        const fetchCriteria = async () => {
            try {
                const response = await axios.get('http://localhost:8081/hr/getAllCriteria');
                setCriteriaList(response.data);
                console.log("Criteria List:", response.data);
            } catch (error) {
                console.error("Error fetching criteria:", error);
                setError("ไม่สามารถดึงข้อมูลเกณฑ์ได้ กรุณาลองใหม่ในภายหลัง.");
            } finally {
                setIsLoading(false);
            }
        };

        fetchCriteria();
    }, []);

    const handleInputChange = (field, value) => {
        setInputs((prevInputs) => ({
            ...prevInputs,
            [field]: value,
        }));
    };

    const handleScoreChange = (criterionId, value) => {
        setInputs((prevInputs) => ({
            ...prevInputs,
            scores: {
                ...prevInputs.scores,
                [criterionId]: Number(value),
            },
        }));
    };

    const fetchCriteriaDetails = async (criteriaId) => {
        if (!criteriaId) {
            setSelectedCriteriaDetails(null);
            return;
        }

        try {
            const response = await axios.get(`http://localhost:8081/hr/getCriteriaByID/${criteriaId}`);
            setSelectedCriteriaDetails(response.data);
            console.log("Selected Criteria Details:", response.data);
        } catch (error) {
            console.error("Error fetching criteria details:", error);
            alert("เกิดข้อผิดพลาดในการดึงข้อมูลรายละเอียดเกณฑ์การประเมิน");
        }
    };

    const handleCriteriaChange = (e) => {
        const criteriaId = e.target.value;
        handleInputChange('criteriaId', criteriaId);
        fetchCriteriaDetails(criteriaId);
    };

    const onSave = async () => {
        if (!inputs.employeeId || !inputs.startDate || !inputs.endDate || !inputs.criteriaId) {
            alert("กรุณากรอกข้อมูลให้ครบถ้วน");
            return;
        }

        // ตรวจสอบว่าทุก EvaluationCriterion มีคะแนนที่เลือก
        const selectedCriteria = criteriaList.find(c => c.criteriaId === inputs.criteriaId);
        if (selectedCriteria) {
            for (const criterion of selectedCriteria.evaluationCriteria) {
                if (inputs.scores[criterion.criterionId] === undefined || inputs.scores[criterion.criterionId] === '') {
                    alert(`กรุณาเลือกคะแนนสำหรับเกณฑ์: ${criterion.description}`);
                    return;
                }
            }
        } else {
            alert("ไม่พบเกณฑ์การประเมินที่เลือก");
            return;
        }

        const payload = {
            employeeId: inputs.employeeId,
            criteriaId: inputs.criteriaId, // ใช้ criteriaId ที่เลือกจาก dropdown
            scores: inputs.scores,
            startDate: inputs.startDate,
            endDate: inputs.endDate,
            comment: inputs.comment
        };

        try {
            const response = await axios.post('http://localhost:8081/hr/evaluateEmployee', payload);
            console.log("ส่งข้อมูลสำเร็จ:", response.data);
            setShowPopup(true);
        } catch (error) {
            console.error("Error sending data:", error);
            alert("เกิดข้อผิดพลาดในการบันทึกข้อมูล!");
        }
    };

    const closePopup = () => {
        setShowPopup(false);
        navigate(`/hr/evaluation/evaluem/result/${empId}`); // นำทางไปยังหน้าผลการประเมินหลังจากบันทึกสำเร็จ
    };

    return (
        <div className='container'>
            <Header />
            <div className="form-container">
                {isLoading ? (
                    <p>กำลังโหลดข้อมูล...</p>
                ) : (
                    <>
                        {error && <p className="error-message">{error}</p>}

                        {/* แสดง Employee ID เป็น read-only */}
                        <div className="form-group">
                            <label>Employee ID:</label>
                            <input
                                type="text"
                                value={empId}
                                readOnly
                            />
                        </div>
                        <div className="form-group">
                            <label>Start Date:</label>
                            <input
                                type="date"
                                value={inputs.startDate}
                                onChange={(e) => handleInputChange('startDate', e.target.value)}
                            />
                        </div>
                        <div className="form-group">
                            <label>End Date:</label>
                            <input
                                type="date"
                                value={inputs.endDate}
                                onChange={(e) => handleInputChange('endDate', e.target.value)}
                            />
                        </div>
                        <div className="form-group">
                            <label>Comment:</label>
                            <textarea
                                value={inputs.comment}
                                onChange={(e) => handleInputChange('comment', e.target.value)}
                                placeholder="กรอกความคิดเห็น"
                            ></textarea>
                        </div>

                        {/* Dropdown สำหรับเลือกเกณฑ์การประเมิน */}
                        <div className="form-group">
                            <label>เลือกเกณฑ์การประเมิน:</label>
                            <select
                                value={inputs.criteriaId || ''}
                                onChange={handleCriteriaChange}
                            >
                                <option value="">เลือกเกณฑ์การประเมิน</option>
                                {criteriaList.map((criteria) => (
                                    <option key={criteria.criteriaId} value={criteria.criteriaId}>
                                        {criteria.criteriaId} - {criteria.description}
                                    </option>
                                ))}
                            </select>
                        </div>

                        {/* แสดงรายละเอียดของเกณฑ์ที่เลือก */}
                        {selectedCriteriaDetails && (
                            <div className="criteria-details">
                                <h3>รายละเอียดเกณฑ์การประเมิน:</h3>
                                <p><strong>ID:</strong> {selectedCriteriaDetails.criteriaId}</p>
                                <p><strong>ชื่อเกณฑ์:</strong> {selectedCriteriaDetails.description}</p>
                                <p><strong>ประเภทคะแนน:</strong> {selectedCriteriaDetails.evaluationCriteria.map(c => c.type).join(", ")}</p>
                                <p><strong>คะแนนสูงสุด:</strong> {selectedCriteriaDetails.evaluationCriteria.reduce((sum, c) => sum + c.maxScore, 0)}</p>
                                <p><strong>น้ำหนักคะแนน:</strong> {selectedCriteriaDetails.kpi_weight}% KPI, {selectedCriteriaDetails.ability_weight}% ความสามารถ, {selectedCriteriaDetails.attendance_weight}% ขาด ลา มาสาย</p>
                            </div>
                        )}

                        {/* ฟอร์มสำหรับกรอกคะแนนตามเกณฑ์การประเมินแต่ละตัว */}
                        {selectedCriteriaDetails && (
                            <div className="scores-container">
                                <h2>กรอกคะแนน</h2>
                                {selectedCriteriaDetails.evaluationCriteria.length > 0 ? (
                                    selectedCriteriaDetails.evaluationCriteria.map((criterion) => (
                                        <div key={criterion.criterionId} className="score-item">
                                            <label className="score-label">{criterion.description}:</label>
                                            <select
                                                value={inputs.scores[criterion.criterionId] || ''}
                                                onChange={(e) => handleScoreChange(criterion.criterionId, e.target.value)}
                                                className="score-input"
                                            >
                                                <option value="">เลือกคะแนน</option>
                                                {Array.from({ length: criterion.maxScore + 1 }, (_, i) => i).map((score) => (
                                                    <option key={score} value={score}>
                                                        {score}
                                                    </option>
                                                ))}
                                            </select>
                                        </div>
                                    ))
                                ) : (
                                    <p>ยังไม่มีเกณฑ์การประเมินเพิ่มเติม</p>
                                )}
                            </div>
                        )}
                    </>
                )}
            </div>
            <button className="submit-button" onClick={onSave}>ส่ง</button>

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
