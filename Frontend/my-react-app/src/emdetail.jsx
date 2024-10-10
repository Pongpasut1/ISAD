import React, { useState, useEffect } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom'; // เพิ่ม useNavigate
import './emdetail.css';

function EmDetail() {
  const { id } = useParams();
  const navigate = useNavigate(); // สร้างฟังก์ชันสำหรับการนำทาง
  const [employee, setEmployee] = useState(null);

  useEffect(() => {
    // จำลองการดึงข้อมูลพนักงานตาม id
    const employees = [
      { id: 1, firstName: 'ก', lastName: 'ข', title: 'นาย', department: 'แผนก', hireDate: '2023-01-01', birthDate: '1990-01-01', address: 'ที่อยู่ตัวอย่าง', phone: '0123456789', email: 'example@company.com' },
      { id: 2, firstName: 'ก', lastName: 'ข', title: 'นาย', department: 'แผนก', hireDate: '2023-01-01', birthDate: '1990-01-01', address: 'ที่อยู่ตัวอย่าง', phone: '0123456789', email: 'example@company.com' },
      { id: 3, firstName: 'ก', lastName: 'ข', title: 'นาย', department: 'แผนก', hireDate: '2023-01-01', birthDate: '1990-01-01', address: 'ที่อยู่ตัวอย่าง', phone: '0123456789', email: 'example@company.com' }
    ];
    const selectedEmployee = employees.find(emp => emp.id === parseInt(id));
    setEmployee(selectedEmployee);
  }, [id]);

  if (!employee) {
    return <div className="loading">กำลังโหลดข้อมูล...</div>;
  }

  const handleButtonClick = () => {
    // นำทางไปยังหน้า moemdetail โดยใช้ id ของพนักงาน
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
            <input type="date" value={employee.hireDate} readOnly />
            <label>วัน/เดือน/ปีเกิด</label>
            <input type="date" value={employee.birthDate} readOnly />
          </div>
          <div className="form-group full-width">
            <label>ที่อยู่</label>
            <textarea rows="3" value={employee.address} readOnly></textarea>
          </div>
          <div className="form-group">
            <label>เบอร์โทรศัพท์</label>
            <input type="text" value={employee.phone} readOnly />
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
