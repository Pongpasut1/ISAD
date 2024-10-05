import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import './emdetail.css';

function EmDetail() {
  const { id } = useParams();
  const [employee, setEmployee] = useState(null);

  useEffect(() => {
    // จำลองการดึงข้อมูลพนักงานตาม id
    const employees = [
      { id: 1, firstName: 'ก', lastName: 'ข', title: 'นาย', department: 'แผนก' },
      { id: 2, firstName: 'ก', lastName: 'ข', title: 'นาย', department: 'แผนก' },
      { id: 3, firstName: 'ก', lastName: 'ข', title: 'นาย', department: 'แผนก' }
    ];
    const selectedEmployee = employees.find(emp => emp.id === parseInt(id));
    setEmployee(selectedEmployee);
  }, [id]);

  if (!employee) {
    return <div className="loading">กำลังโหลดข้อมูล...</div>;
  }

  return (
    <div className="container">
      <Header />
      <div className="employee-detail">
        <h2 className="employee-name">{employee.title} {employee.firstName} {employee.lastName}</h2>
        <form className="employee-form">
          <div className="form-group">
            <label>คำนำหน้าชื่อ</label>
            <input type="text" value={employee.title} readOnly />
            <label>ชื่อ</label>
            <input type="text" value={employee.firstName} readOnly />
            <label>นามสกุล</label>
            <input type="text" value={employee.lastName} readOnly />
          </div>
          <div className="form-group">
            <label>ตำแหน่งงาน</label>
            <input type="text" value={employee.department} readOnly />
            <label>แผนก</label>
            <input type="text" value={employee.department} readOnly />
          </div>
          <div className="form-group">
            <label>วันเข้าทำงาน</label>
            <input type="date" />
            <label>วัน/เดือน/ปีเกิด</label>
            <input type="date" />
          </div>
          <div className="form-group full-width">
            <label>ที่อยู่</label>
            <textarea rows="3"></textarea>
          </div>
          <div className="form-group">
            <label>เบอร์โทรศัพท์</label>
            <input type="text" />
            <label>Email</label>
            <input type="email" />
          </div>
          <div className="button-group">
            <button type="button" className="edit-btn">แก้ไขข้อมูล</button>
            <button type="button" className="delete-btn">ลบข้อมูล</button>
          </div>
        </form>
      </div>
      <div className="link-back">
        <Link to="/hr/hrdata">กลับไปหน้ารายชื่อพนักงาน</Link>
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

export default EmDetail;