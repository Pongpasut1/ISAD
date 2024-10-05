import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';

function EmDetail() {
  const { id } = useParams();
  const [employee, setEmployee] = useState(null);

  useEffect(() => {
    // Simulate fetching employee by id
    const employees = [
      { id: 1, firstName: 'ก', lastName: 'ข', title: 'นาย' },
      { id: 2, firstName: 'ก', lastName: 'ข', title: 'นาย' },
      { id: 3, firstName: 'ก', lastName: 'ข', title: 'นาย' }
    ];
    const selectedEmployee = employees.find(emp => emp.id === parseInt(id));
    setEmployee(selectedEmployee);
  }, [id]);

  if (!employee) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <Header />
      <div className="employee-form">
        <h2>{employee.title} {employee.firstName} {employee.lastName}</h2>
        <label>คำนำหน้าชื่อ</label>
        <input type="text" value={employee.title} readOnly />
        <label>ชื่อ</label>
        <input type="text" value={employee.firstName} readOnly />
        <label>นามสกุล</label>
        <input type="text" value={employee.lastName} readOnly />
        {/* Add more fields as needed */}
        <button className="edit-btn">แก้ไขข้อมูล</button>
        <button className="delete-btn">ลบข้อมูล</button>
      </div>
      <Link to="/hr/hrdata">กลับไปหน้ารายชื่อพนักงาน</Link>
    </div>
  );
}

const Header = () => (
  <header className="header">
    <h1>นายเองก็เป็นได้นะ HRน่ะ</h1>
    <div className="profile-circle"></div>
  </header>
);

export default EmDetail;
