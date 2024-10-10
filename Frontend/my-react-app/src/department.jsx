import React, { useState } from 'react';
import './department.css';

function Department() {
  const [departments, setDepartments] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [newDepartment, setNewDepartment] = useState('');
  const [deptToDelete, setDeptToDelete] = useState('');

  const handleAddDepartment = (dept) => {
    setDepartments([...departments, dept]);
    setNewDepartment('');
    setCurrentPage(1); // Return to the main page
  };

  const handleDeleteDepartment = () => {
    setDepartments(departments.filter(dept => dept !== deptToDelete));
    setDeptToDelete('');
    setCurrentPage(1); // Return to the main page
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
        />
      )}
      {currentPage === 2 && (
        <SecondPage
          onSave={handleAddDepartment}
          onCancel={() => setCurrentPage(1)}
          newDepartment={newDepartment}
          setNewDepartment={setNewDepartment}
        />
      )}
      {currentPage === 3 && (
        <ThirdPage
          department={deptToDelete}
          onDelete={handleDeleteDepartment}
          onReturn={() => setCurrentPage(1)}
        />
      )}
    </div>
  );
}


const HrCriteria = ({ departments, onAdd, onDelete }) => {
  return (
    <div className='container'>
      <Header />
      <SearchBar onAdd={onAdd} />
      <div className="employee-list">
        {departments.length === 0 ? (
          <div>ไม่มีเกณฑ์การประเมิน</div>
        ) : (
          departments.map((dept, index) => (
            <div key={index} className="employee-item">
              {dept}
              <button className="delete-btn" onClick={() => onDelete(dept)}>ลบ</button>
            </div>
          ))
        )}
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

const SearchBar = ({ onAdd }) => (
  <div className="search-bar">
    <input type="text" placeholder="แผนก" />
    <button className="search-btn">&#128269;</button>
    <button className="sort-btn">&#8645;</button>
    <button className="add-btn" onClick={onAdd}>+</button>
  </div>
);

function SecondPage({ onSave, onCancel, newDepartment, setNewDepartment }) {
  return (
    <div className="page second-page">
      <h2>เพิ่มเกณฑ์การประเมิน</h2>
      <input
        type="text"
        value={newDepartment}
        onChange={(e) => setNewDepartment(e.target.value)}
        placeholder="ใส่ชื่อแผนก"
      />
      <div className="button-group">
        <button className="save-button" onClick={() => onSave(newDepartment)}>บันทึก</button>
        <button className="cancel-button" onClick={onCancel}>ยกเลิก</button>
      </div>
    </div>
  );
}

function ThirdPage({ department, onDelete, onReturn }) {
  return (
    <div className="page third-page">
      <h2>ลบเกณฑ์การประเมิน</h2>
      <p>คุณต้องการลบเกณฑ์: <strong>{department}</strong> ใช่ไหม?</p>
      <div className="button-group">
        <button className="delete-button" onClick={onDelete}>ลบ</button>
        <button className="return-button" onClick={onReturn}>กลับไปหน้าแรก</button>
      </div>
    </div>
  );
}

export default Department;
