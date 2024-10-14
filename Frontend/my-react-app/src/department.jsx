import React, { useState, useEffect } from 'react';
import './department.css';

function Department() {
  const [departments, setDepartments] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [formData, setFormData] = useState({
    criteriaId: '',
    department: '',
    role: '',
    description: '',
    KPI_weight: 0,
    ability_weight: 0,
    attendance_weight: 0,
    attendance_criteria: {
      maxLeaveScore: '',
      maxLateScore: '',
      maxSickLeaveDays: '',
      maxPersonalLeaveDays: '',
      maxVacationLeaveDays: '',
      maxLateMinutes: '',
    },
    evaluationCriteria: [], // This will be an array of objects
  });
  const [deptToDelete, setDeptToDelete] = useState('');
  const [selectedDepartment, setSelectedDepartment] = useState(null); // New state for selected department
  const [selectedCriteria, setSelectedCriteria] = useState(null);

  const handleAddDepartment = () => {
    const newDepartment = {
      criteriaId: formData.criteriaId,
      name: formData.department,
      position: formData.position,
      description: formData.description,
      maxLateMinutes: formData.late,
      lateScore: formData.lateScore,
      sickLeave: formData.sickLeave,
      sickLeaveScore: formData.sickLeaveScore,
      personalLeave: formData.personalLeave,
      personalLeaveScore: formData.personalLeaveScore,
      scoreWeight: formData.scoreWeight,
      criteriaId: formData.criteriaId,
      criteria: formData.criteria,
      scoreType: formData.scoreType, 
      fullScore: formData.fullScore,
      weight: formData.weight,
    };
    setDepartments([...departments, newDepartment]);
    resetFormData();
    setCurrentPage(1);
  };

  const handleDeleteDepartment = () => {
    setDepartments(departments.filter(dept => dept.id !== deptToDelete.id));
    setDeptToDelete('');
    setCurrentPage(1);
  };

  const fetchCriteriaDetails = (criteriaId) => {
    fetch(`http://localhost:8081/hr/getCriteriaByID/${criteriaId}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        setSelectedDepartment(data); // Set department details when selected
        setCurrentPage(4); // Change to DepartmentDetails page
      })
      .catch((error) => console.error("Error fetching department details:", error));
  };

  const handleReturn = () => {
    setSelectedCriteria(null);  // Clear selection when returning
  };

  const resetFormData = () => {
    setFormData({
      criteriaId: '',
      department: '',
      role: '', // ใช้ 'role' แทน 'position'
      description: '',
      KPI_weight: 0,
      ability_weight: 0,
      attendance_weight: 0,
      attendance_criteria: {
        maxLeaveScore: '',
        maxLateScore: '',
        maxSickLeaveDays: '',
        maxPersonalLeaveDays: '',
        maxVacationLeaveDays: '',
        maxLateMinutes: '',
      },
      evaluationCriteria: [], // เป็น array ของ objects
    });
  };

  return (
    <div className="App">
      {currentPage === 1 && (
        <HrCriteria
          departments={departments}
          onAdd={() => setCurrentPage(2)}
          onDelete={(dept) => {
            setDeptToDelete(dept);
            setCurrentPage(3);
          }}
          onSelectDepartment={fetchCriteriaDetails}
        />
      )}
      {currentPage === 2 && (
        <SecondPage
          onSave={handleAddDepartment}
          onCancel={() => {
            resetFormData();
            setCurrentPage(1);
          }}
          formData={formData}
          setFormData={setFormData}
        />
      )}
      {currentPage === 3 && (
        <ThirdPage
          department={deptToDelete.name}
          onDelete={handleDeleteDepartment}
          onReturn={() => setCurrentPage(1)}
        />
      )}
      {currentPage === 4 && selectedDepartment && (
        <DepartmentDetails department={selectedDepartment} onReturn={() => setCurrentPage(1)} />
      )}

    </div>
  );
}

const HrCriteria = ({ onAdd, onDelete, onSelectDepartment }) => {
  const [criteria, setCriteria] = useState([]);

  // Fetch the criteria list when the component is mounted
  useEffect(() => {
    fetch("http://localhost:8081/hr/getAllCriteria")
      .then((response) => response.json())
      .then((data) => setCriteria(data))
      .catch((error) => console.error("Error fetching criteria:", error));
  }, []);

  return (
    <div className='container'>
      <Header />
      <SearchBar onAdd={onAdd} />
      <div className="employee-list">
        {criteria.length === 0 ? (
          <div></div>
        ) : (
          criteria.map((criterion) => (
            <div key={criterion.criteriaId} className="employee-item">
              <span onClick={() => onSelectDepartment(criterion.criteriaId)}>{criterion.criteriaId}</span>
              <button className="delete-btn" onClick={() => onDelete(criterion)}>ลบ</button>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

const DepartmentDetails = ({ department, onReturn }) => {
  return (
    <div className="department-details">
      <h2>รายละเอียดแผนก: {department.department}</h2>
      <p><strong>ID:</strong> {department.criteriaId}</p>
      <p><strong>ตำแหน่ง:</strong> {department.role}</p>
      <p><strong>คำอธิบาย:</strong> {department.description}</p>
      <h3>เกณฑ์คะแนนการเข้างาน:</h3>
      <p><strong>เวลาสาย:</strong> {department.attendance_criteria.maxLateMinutes} นาที</p>
      <p><strong>คะแนน:</strong> {department.attendance_criteria.maxLateScore}</p>
      <p><strong>ลาป่วย:</strong> {department.attendance_criteria.maxSickLeaveDays} ครั้ง</p>
      <p><strong>ลาพักร้อน:</strong> {department.attendance_criteria.maxVacationLeaveDays}</p>
      <p><strong>ลากิจ:</strong> {department.attendance_criteria.maxPersonalLeaveDays} ครั้ง</p>
      <p><strong>คะแนน:</strong> {department.attendance_criteria.maxLeaveScore}</p>
      <p><strong>น้ำหนักคะแนน:</strong> {department.attendance_weight}%</p>
      <h3>เกณฑ์คะแนนการทำงาน:</h3>
      {department.evaluationCriteria && department.evaluationCriteria.length > 0 ? (
        department.evaluationCriteria.map((criterion) => (
          <div key={criterion.criterionId}>
            <p><strong>ID:</strong> {criterion.criterionId}</p>
            <p><strong>คำอธิบาย:</strong> {criterion.description}</p>
            <p><strong>ประเภทคะแนน:</strong> {criterion.type}</p>
            <p><strong>คะแนนเต็ม:</strong> {criterion.maxScore}</p>
            <p><strong>น้ำหนักคะแนน:</strong> {criterion.weight} %</p>
          </div>
        ))
      ) : (
        <p>ไม่มีเกณฑ์คะแนนการทำงาน</p>
      )}
      <button onClick={onReturn}>ย้อนกลับ</button>
    </div>
  );
}

const Header = () => (
  <header className="header">
    <h1>นายเองก็เป็นได้นะ HRน่ะ</h1>
    <div className="profile-circle"></div>
  </header>
);

const SearchBar = ({ onAdd }) => (
  <div className="search-bar">
    <input type="text" placeholder="เกณฑ์การประเมิน" />
    <button className="search-btn">&#128269;</button>
    <button className="sort-btn">&#8645;</button>
    <button className="add-btn" onClick={onAdd}>+</button>
  </div>
);

function SecondPage({ onSave, onCancel, formData, setFormData }) {
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const handleAttendanceChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      attendanceCriteria: {
        ...prevFormData.attendanceCriteria,
        [name]: value,
      }
    }));
  };

  const handleAddCriteria = () => {
    const newCriterion = {
      criterionId: formData.criterionId,
      descriptions: formData.descriptions,
      maxScores: formData.maxScores,
      weights: formData.weights,
      types: formData.types, // "KPI", "Ability", etc.
    };

    setFormData((prevFormData) => ({
      ...prevFormData,
      evaluationCriteria: [...prevFormData.evaluationCriteria, newCriterion],
      criterionId: '',
      description: '',
      maxScore: '',
      weight: '',
      type: '',
    }));
  };

  const handleSaveCriteria = () => {
    const criteriaData = {
      criteriaId: formData.criteriaId,
      description: formData.description,
      department: formData.department,
      role: formData.position,
      KPI_weight: formData.KPI_weight,
      ability_weight: formData.ability_weight,
      attendance_weight: formData.attendance_weight,
      attendance_criteria: {
        maxLateMinutes: formData.attendance_criteria.maxLeaveScore,
        maxLateScore: formData.attendance_criteria.maxLateScore,
        maxSickLeaveDays: formData.attendance_criteria.maxSickLeaveDays,
        maxVacationLeaveDays: formData.attendance_criteria.maxVacationLeaveDays,
        maxPersonalLeaveDays: formData.attendance_criteria.maxPersonalLeaveDays,
        maxLeaveScore: formData.attendance_criteria.maxLeaveScore,
      },
      evaluationCriteria: formData.evaluationCriteria,
    };

    fetch('http://localhost:8081/hr/setCriteria', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(criteriaData),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log('Criteria saved successfully:', data);
      })
      .catch((error) => {
        console.error('Error saving criteria:', error);
      });
  };

  return (
    <div className="page-second-page">
      <h2>เพิ่มเกณฑ์การประเมิน</h2>
      <div className="form-group">
        <label>id เกณฑ์: </label>
        <input
          type="text"
          name="criteriaId"
          value={formData.criteriaId || ''}
          onChange={handleChange}
          placeholder="ใส่ id"
        />
        <label>แผนก: </label>
        <input
          type="text"
          name="department"
          value={formData.department}
          onChange={handleChange}
          placeholder="ใส่ชื่อแผนก"
        />
      </div>

      <div className="form-group">
        <label>ตำแหน่ง: </label>
        <input
          type="text"
          name="role"
          value={formData.role || ''}
          onChange={handleChange}
          placeholder="ใส่ตำแหน่ง"
        />
        <label>คำอธิบาย: </label>
        <input
          type="text"
          name="description"
          value={formData.description}
          onChange={handleChange}
          placeholder="ใส่คำอธิบาย"
        />
      </div>

      <div className="form-group">
        <label>เกณฑ์คะแนนการเข้างาน: </label>
      </div>

      <div className="criteria-section">
        <div className="form-group">
          <label>เวลาสาย ไม่เกิน(นาที): </label>
          <input
            type="text"
            name="late"
            value={formData.attendance_criteria.maxLateMinutes}
            onChange={handleAttendanceChange}
            placeholder="ใส่ค่า"
          />
          <label>คะแนน: </label>
          <input
            type="text"
            name="lateScore"
            value={formData.attendance_criteria.maxLateScore}
            onChange={handleAttendanceChange}
            placeholder="ใส่คะแนน"
          />
        </div>

        <div className="form-group">
          <label>ลาป่วย ไม่เกิน(ครั้ง): </label>
          <input
            type="text"
            name="sickLeave"
            value={formData.maxSickLeaveDays}
            onChange={handleChange}
            placeholder="ใส่ค่า"
          />
          <label>คะแนน: </label>
          <input
            type="text"
            name="vacationLeaveDays"
            value={formData.maxVacationLeaveDays}
            onChange={handleChange}
            placeholder="ใส่ค่า"
          />
        </div>

        <div className="form-group">
          <label>ลากิจ ไม่เกิน(ครั้ง): </label>
          <input
            type="text"
            name="personalLeave"
            value={formData.maxPersonalLeaveDays}
            onChange={handleChange}
            placeholder="ใส่ค่า"
          />
          <label>คะแนนการลารวม: </label>
          <input
            type="text"
            name="leaveScore"
            value={formData.maxLeaveScore}
            onChange={handleChange}
            placeholder="ใส่คะแนน"
          />
        </div>

        <div className="form-group">
          <label>น้ำหนักคะแนน (%): </label>
          <input
            type="text"
            name="attendance_weight"
            value={formData.attendance_weight}
            onChange={handleChange}
            placeholder="ใส่ค่า"
          />
        </div>
      </div>

      <div className="form-group">
        <label>เกณฑ์คะแนนการทำงาน: </label>
      </div>
      
      <div className="criteria-section">
        <div className="form-group">
          <label>id:</label>
          <input
            type="text"
            name="criterionId"
            value={formData.criterionId || ''}
            onChange={handleChange}
            placeholder="ใส่ id"
          />
        </div>

        <div className="form-group">
          <label>ชื่อเกณฑ์:</label>
          <input
            type="text"
            name="descriptions"
            value={formData.descriptions|| ''}
            onChange={handleChange}
            placeholder="ใส่ชื่อเกณฑ์"
          />
        </div>

        <div className="form-group">
          <label>ประเภทคะแนน:</label>
          <label>
            <input
              type="radio"
              name="types"
              value="KPI"
              checked={formData.types === 'KPI'|| ''}
              onChange={handleChange}
            />
            KPI
          </label>
          <label>
            <input
              type="radio"
              name="types"
              value="คะแนนความสามารถ"
              checked={formData.types === 'คะแนนความสามารถ'|| ''}
              onChange={handleChange}
            />
            คะแนนความสามารถ
          </label>
        </div>


        <div className="form-group">
          <label>คะแนนสูงสุด:</label>
          <input
            type="text"
            name="maxScores"
            value={formData.maxScores || ''}
            onChange={handleChange}
            placeholder="ใส่คะแนนสูงสุด"
          />
        </div>

        <div className="form-group">
          <label>น้ำหนักคะแนน(%):</label>
          <input
            type="text"
            name="weights"
            value={formData.weights || ''}
            onChange={handleChange}
            placeholder="ใส่น้ำหนัก"
          />
        </div>

        <button onClick={handleAddCriteria}>เพิ่มเกณฑ์</button>
      </div>

      <div className="added-criteria">
        <h4>เกณฑ์ที่เพิ่มแล้ว:</h4>
        {formData.evaluationCriteria.length > 0 ? (
          <ul>
            {formData.evaluationCriteria.map((criteria, index) => (
              <li key={index}>
                <p>ID: {criteria.criterionId}</p>
                <p>ชื่อเกณฑ์: {criteria.descriptions}</p>
                <p>ประเภทคะแนน: {criteria.types}</p>
                <p>คะแนนสูงสุด: {criteria.maxScores}</p>
                <p>น้ำหนักคะแนน: {criteria.weights}</p>
              </li>
            ))}
          </ul>
        ) : (
          <p>ไม่มีเกณฑ์ที่เพิ่ม</p>
        )}
      </div>

      <div className="button-group">
        <button onClick={handleSaveCriteria}>บันทึก</button>
        <button onClick={onCancel}>ยกเลิก</button>
      </div>
    </div>
  );
}
function ThirdPage({ department, onDelete, onReturn }) {
  return (
    <div>
      <h2>ยืนยันการลบแผนก: {department}</h2>
      <button onClick={onDelete}>ยืนยันการลบ</button>
      <button onClick={onReturn}>ย้อนกลับ</button>
    </div>
  );
}

export default Department;