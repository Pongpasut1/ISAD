import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import './emdetail.css';

function MoEmDetail() {
  const { id } = useParams();
  const [employee, setEmployee] = useState({
    title: '',
    firstName: '',
    lastName: '',
    department: '',
    hireDate: '',
    birthDate: '',
    address: '',
    phone: '',
    email: ''
  });

  useEffect(() => {
    // จำลองการดึงข้อมูลพนักงานตาม id
    const employees = [
      { id: 1, title: 'นาย', firstName: 'ก', lastName: 'ข', department: 'แผนก', hireDate: '2023-01-01', birthDate: '1990-01-01', address: 'ที่อยู่ตัวอย่าง', phone: '0123456789', email: 'example@company.com' },
      { id: 2, title: 'นาย', firstName: 'ก', lastName: 'ข', department: 'แผนก', hireDate: '2023-01-01', birthDate: '1990-01-01', address: 'ที่อยู่ตัวอย่าง', phone: '0123456789', email: 'example@company.com' },
      { id: 3, title: 'นาย', firstName: 'ก', lastName: 'ข', department: 'แผนก', hireDate: '2023-01-01', birthDate: '1990-01-01', address: 'ที่อยู่ตัวอย่าง', phone: '0123456789', email: 'example@company.com' }
    ];
    const selectedEmployee = employees.find(emp => emp.id === parseInt(id));
    if (selectedEmployee) {
      setEmployee(selectedEmployee);
    }
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEmployee({ ...employee, [name]: value });
  };

  return (
    <div className="container">
      <Header />
      <div className="employee-detail">
        <h2 className="employee-name">{employee.title} {employee.firstName} {employee.lastName}</h2>
        <form className="employee-form">
          <div className="form-group">
            <label>คำนำหน้าชื่อ</label>
            <input
              type="text"
              name="title"
              value={employee.title}
              onChange={handleChange}
            />
            <label>ชื่อ</label>
            <input
              type="text"
              name="firstName"
              value={employee.firstName}
              onChange={handleChange}
            />
            <label>นามสกุล</label>
            <input
              type="text"
              name="lastName"
              value={employee.lastName}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label>ตำแหน่งงาน</label>
            <input
              type="text"
              name="department"
              value={employee.department}
              onChange={handleChange}
            />
            <label>แผนก</label>
            <input
              type="text"
              name="department"
              value={employee.department}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label>วันเข้าทำงาน</label>
            <input
              type="date"
              name="hireDate"
              value={employee.hireDate}
              onChange={handleChange}
            />
            <label>วัน/เดือน/ปีเกิด</label>
            <input
              type="date"
              name="birthDate"
              value={employee.birthDate}
              onChange={handleChange}
            />
          </div>
          <div className="form-group full-width">
            <label>ที่อยู่</label>
            <input
              type="text"
              name="address"
              value={employee.address}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label>เบอร์โทรศัพท์</label>
            <input
              type="text"
              name="phone"
              value={employee.phone}
              onChange={handleChange}
            />
            <label>Email</label>
            <input
              type="email"
              name="email"
              value={employee.email}
              onChange={handleChange}
            />
          </div>
          <div className="button-group">
            <button type="button" className="save-btn">SAVE</button>
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

export default MoEmDetail;
