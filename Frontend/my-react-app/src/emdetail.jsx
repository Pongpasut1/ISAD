import React, { useState, useEffect } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom'; // เพิ่ม useNavigate

import './emdetail.css';

function EmDetail() {
  const { id } = useParams();
  console.log(id); // ตรวจสอบค่า id ที่ได้
  const navigate = useNavigate();
  const [employee, setEmployee] = useState(null);

  useEffect(() => {
    // เรียกใช้ API ดึงข้อมูลพนักงานตาม ID
    fetch(`http://localhost:8081/hr/employee/${id}`)
      .then(response => response.json())
      .then(data => setEmployee(data))
      .catch(error => console.error('Error fetching employee data:', error));
  }, [id]);

  if (!employee) {
    return <div className="loading">กำลังโหลดข้อมูล...</div>;
  }

  const handleButtonClick = () => {
    navigate(`/hr/hrdata/employee/${id}/moemdetail`);
  };

  return (
    <div className="container">
      <Header />
      <div className="employee-detail">
        <h2 className="employee-name">{employee.title} {employee.firstName} {employee.lastName}</h2>
        <form className="employee-form">
          <div className="form-group">
            <label>คำนำหน้าชื่อ</label>
            <input type="text" value={employee.nametitle} readOnly />
            <label>ชื่อ</label>
            <input type="text" value={employee.name} readOnly />
            <label>นามสกุล</label>
            <input type="text" value={employee.surname} readOnly />
          </div>
          <div className="form-group">
            <label>ตำแหน่งงาน</label>
            <input type="text" value={employee.role} readOnly />
            <label>แผนก</label>
            <input type="text" value={employee.department} readOnly />
          </div>
          <div className="form-group">
            <label>วันเข้าทำงาน</label>
            <input type="date" value={employee.hiredate} readOnly />
            <label>วัน/เดือน/ปีเกิด</label>
            <input type="date" value={employee.dob} readOnly />
          </div>
          <div className="form-group full-width">
            <label>ที่อยู่</label>
            <textarea rows="3" value={employee.address} readOnly></textarea>
          </div>
          <div className="form-group">
            <label>เบอร์โทรศัพท์</label>
            <input type="text" value={employee.phoneNumber} readOnly />
            <label>Email</label>
            <input type="email" value={employee.email} readOnly />
          </div>
          <div className="button-group">
            <button type="button" className="edit-btn" onClick={handleButtonClick}>แก้ไขข้อมูล</button>
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
