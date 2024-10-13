import React, { useState, useEffect } from 'react';
import './department.css';

function Department() {
  const [departments, setDepartments] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [formData, setFormData] = useState({
    id: '',
    department: '',
    position: '',
    description: '',
    late: '',
    lateScore: '',
    sickLeave: '',
    sickLeaveScore: '',
    personalLeave: '',
    personalLeaveScore: '',
    scoreWeight: '',
    criteriaId: '',
    criteria: '',
    scoreType: '',
    fullScore: '',
    weight: '',
  });
  const [deptToDelete, setDeptToDelete] = useState('');
  const [selectedDepartment, setSelectedDepartment] = useState(null); // New state for selected department

  const handleAddDepartment = () => {
    const newDepartment = {
      id: formData.id,
      name: formData.department,
      position: formData.position,
      description: formData.description,
      late: formData.late,
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

  const resetFormData = () => {
    setFormData({
      id: '',
      department: '',
      position: '',
      description: '',
      late: '',
      lateScore: '',
      sickLeave: '',
      sickLeaveScore: '',
      personalLeave: '',
      personalLeaveScore: '',
      scoreWeight: '',
      criteriaId: '',
      criteria: '',
      scoreType: '',
      fullScore: '',
      weight: '',
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
          onSelectDepartment={(dept) => {
            setSelectedDepartment(dept);
            setCurrentPage(4);
          }}
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
              <span onClick={() => onSelectDepartment(criterion)}>{criterion.description}</span>
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
      <h2>รายละเอียดแผนก: {department.name}</h2>
      <p><strong>ID:</strong> {department.id}</p>
      <p><strong>ตำแหน่ง:</strong> {department.position}</p>
      <p><strong>คำอธิบาย:</strong> {department.description}</p>
      <h3>เกณฑ์คะแนนการเข้างาน:</h3>
      <p><strong>เวลาสาย:</strong> {department.late} นาที</p>
      <p><strong>คะแนน:</strong> {department.lateScore}</p>
      <p><strong>ลาป่วย:</strong> {department.sickLeave} ครั้ง</p>
      <p><strong>คะแนน:</strong> {department.sickLeaveScore}</p>
      <p><strong>ลากิจ:</strong> {department.personalLeave} ครั้ง</p>
      <p><strong>คะแนน:</strong> {department.personalLeaveScore}</p>
      <p><strong>น้ำหนักคะแนน:</strong> {department.scoreWeight}%</p>
      <h3>เกณฑ์คะแนนการทำงาน:</h3>
      <p><strong>ID:</strong> {department.criteriaId}</p>
      <p><strong>คำอธิบาย:</strong> {department.criteria}</p>
      <p><strong>ประเภทคะแนน:</strong> {department.scoreType}</p>
      <p><strong>คะแนนเต็ม:</strong> {department.fullScore}</p>
      <p><strong>น้ำหนัก:</strong> {department.weight}</p>

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
    <input type="text" placeholder="แผนก" />
    <button className="search-btn">&#128269;</button>
    <button className="sort-btn">&#8645;</button>
    <button className="add-btn" onClick={onAdd}>+</button>
  </div>
);

function SecondPage({ onSave, onCancel, formData, setFormData }) {
  const [criteriaList, setCriteriaList] = useState([]);
  
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleAddCriteria = () => {
    const newCriteria = {
      criteriaId: formData.criteriaId,
      criteria: formData.criteria,
      scoreType: formData.scoreType,
      fullScore: formData.fullScore,
      weight: formData.weight,
    };

    setCriteriaList([...criteriaList, newCriteria]);

    setFormData({
      ...formData,
      criteriaId: '',
      criteria: '',
      scoreType: '',
      fullScore: '',
      weight: '',
    });
  };

  return (
    <div className="page-second-page">
      <h2>เพิ่มเกณฑ์การประเมิน</h2>
      <div className="form-group">
        <label>id เกณฑ์: </label>
        <input
          type="text"
          name="id"
          value={formData.id}
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
          name="position"
          value={formData.position}
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
            value={formData.late}
            onChange={handleChange}
            placeholder="ใส่ค่า"
          />
          <label>คะแนน: </label>
          <input
            type="text"
            name="lateScore"
            value={formData.lateScore}
            onChange={handleChange}
            placeholder="ใส่คะแนน"
          />
        </div>

        <div className="form-group">
          <label>ลาป่วย ไม่เกิน(ครั้ง): </label>
          <input
            type="text"
            name="sickLeave"
            value={formData.sickLeave}
            onChange={handleChange}
            placeholder="ใส่ค่า"
          />
          <label>คะแนน: </label>
          <input
            type="text"
            name="sickLeaveScore"
            value={formData.sickLeaveScore}
            onChange={handleChange}
            placeholder="ใส่คะแนน"
          />
        </div>

        <div className="form-group">
          <label>ลากิจ ไม่เกิน(ครั้ง): </label>
          <input
            type="text"
            name="personalLeave"
            value={formData.personalLeave}
            onChange={handleChange}
            placeholder="ใส่ค่า"
          />
          <label>คะแนน: </label>
          <input
            type="text"
            name="personalLeaveScore"
            value={formData.personalLeaveScore}
            onChange={handleChange}
            placeholder="ใส่คะแนน"
          />
        </div>

        <div className="form-group">
          <label>น้ำหนักคะแนน (%): </label>
          <input
            type="text"
            name="scoreWeight"
            value={formData.scoreWeight}
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
            name="criteriaId"
            value={formData.criteriaId}
            onChange={handleChange}
            placeholder="ใส่ id"
          />
        </div>

        <div className="form-group">
          <label>ชื่อเกณฑ์:</label>
          <input
            type="text"
            name="criteria"
            value={formData.criteria}
            onChange={handleChange}
            placeholder="ใส่ชื่อเกณฑ์"
          />
        </div>

        <div className="form-group">
          <label>ประเภทคะแนน:</label>
          <label>
            <input
              type="radio"
              name="scoreType"
              value="KPI"
              checked={formData.scoreType === 'KPI'}
              onChange={handleChange}
            />
            KPI
          </label>
          <label>
            <input
              type="radio"
              name="scoreType"
              value="คะแนนความสามารถ"
              checked={formData.scoreType === 'คะแนนความสามารถ'}
              onChange={handleChange}
            />
            คะแนนความสามารถ
          </label>
        </div>


        <div className="form-group">
          <label>คะแนนสูงสุด:</label>
          <input
            type="text"
            name="fullScore"
            value={formData.fullScore}
            onChange={handleChange}
            placeholder="ใส่คะแนนสูงสุด"
          />
        </div>

        <div className="form-group">
          <label>น้ำหนักคะแนน(%):</label>
          <input
            type="text"
            name="weight"
            value={formData.weight}
            onChange={handleChange}
            placeholder="ใส่น้ำหนัก"
          />
        </div>

        <button onClick={handleAddCriteria}>เพิ่มเกณฑ์</button>
      </div>

      <div className="added-criteria">
        <h4>เกณฑ์ที่เพิ่มแล้ว:</h4>
        {criteriaList.length > 0 ? (
          <ul>
            {criteriaList.map((criteria, index) => (
              <li key={index}>
                <p>ID: {criteria.criteriaId}</p>
                <p>ชื่อเกณฑ์: {criteria.criteria}</p>
                <p>ประเภทคะแนน: {criteria.scoreType}</p>
                <p>คะแนนสูงสุด: {criteria.fullScore}</p>
                <p>น้ำหนักคะแนน: {criteria.weight}</p>
              </li>
            ))}
          </ul>
        ) : (
          <p>ไม่มีเกณฑ์ที่เพิ่ม</p>
        )}
      </div>

      <div className="button-group">
        <button onClick={onSave}>บันทึก</button>
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