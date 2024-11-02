import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import './emdetail.css';

function MoEmDetail() {
  const { id } = useParams();
  console.log('Received id from useParams:', id);
  const [employee, setEmployee] = useState({
    id: 0,
    empId: '',
    nametitle: '',
    firstName: '',
    lastName: '',
    role: '',
    department: '',
    hiredate: '',
    dob: '',
    address: '',
    phone: '',
    email: '',
    username: '',
    password: '',
  });

  useEffect(() => {
    const fetchEmployee = async () => {
      try {
        const response = await fetch(`http://localhost:8081/hr/employee/${id}`); // สมมติว่า API ของคุณใช้ endpoint นี้ในการดึงข้อมูลพนักงานตาม id
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setEmployee({
          id: data.id,
          empId: data.empId,
          username: data.username,
          password: data.password,
          nametitle: data.nametitle,
          name: data.name,
          surname: data.surname,
          role: data.role,
          department: data.department,
          hiredate: data.hiredate,
          dob: data.dob,
          address: data.address,
          phoneNumber: data.phoneNumber,
          email: data.email,
        });
      } catch (error) {
        console.error('เกิดข้อผิดพลาดในการดึงข้อมูลพนักงาน:', error);
      }
    };
  
    fetchEmployee();
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEmployee({ ...employee, [name]: value });
  };

  const handleSave = async () => {
    try {
      const response = await fetch('http://localhost:8081/employee/update', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          id: employee.id,
          empId: employee.empId,
          username: employee.username, // สมมติว่า username คือ firstName
          password: employee.password,
          role: employee.role, // สมมติว่า role คือ department
          name: employee.name,
          surname: employee.surname,
          dob: employee.dob,
          email: employee.email,
          phoneNumber: employee.phoneNumber,
          department: employee.department,
          hiredate: employee.hiredate,
          address: employee.address,
          nametitle: employee.nametitle,
        }),
      });

      if (!response.ok) {
        throw new Error('Network response was not ok ${response.status}');
      }

      const data = await response.json();
      console.log('อัปเดตข้อมูลพนักงานสำเร็จ:', data);
      // อาจจะมีการนำทางหรือแสดงข้อความแจ้งเตือนความสำเร็จ
    } catch (error) {
      console.error('เกิดข้อผิดพลาดในการอัปเดตข้อมูลพนักงาน:', error);
    }
  };

  return (
    <div className="container">
      <Header />
      <div className="employee-detail">
        <h2 className="employee-name">{employee.title} {employee.firstName} {employee.lastName}</h2>
        <form className="employee-form">
          <div className="form-group">
          <label>id</label>
            <input
              type="text"
              name="id"
              value={employee.id || ""}readonly
            />
            <label>คำนำหน้าชื่อ</label>
            <input
              type="text"
              name="title"
              value={employee.nametitle || ""}
              onChange={(e) => setEmployee({ ...employee, nametitle: e.target.value })} 
            />
            <label>ชื่อ</label>
            <input
              type="text"
              name="firstName"
              value={employee.name || ""}
              onChange={(e) => setEmployee({ ...employee, name: e.target.value })} 
            />
            <label>นามสกุล</label>
            <input
              type="text"
              name="lastName"
              value={employee.surname || ""}
              onChange={(e) => setEmployee({ ...employee, surname: e.target.value })} 
            />
          </div>
          <div className="form-group">
            <label>ตำแหน่งงาน</label>
            <input
              type="text"
              name="role"
              value={employee.role || ""}
              onChange={(e) => setEmployee({ ...employee, role: e.target.value })} 
            />
            <label>แผนก</label>
            <input
              type="text"
              name="department"
              value={employee.department || ""}
              onChange={(e) => setEmployee({ ...employee, department: e.target.value })} 
            />
          </div>
          <div className="form-group">
            <label>วันเข้าทำงาน</label>
            <input
              type="date"
              name="hireDate"
              value={employee.hiredate || ""}
              onChange={(e) => setEmployee({ ...employee, hiredate: e.target.value })}
            />
            <label>วัน/เดือน/ปีเกิด</label>
            <input
              type="date"
              name="birthDate"
              value={employee.dob || ""}
              onChange={(e) => setEmployee({ ...employee, dob: e.target.value })} 
            />
          </div>
          <div className="form-group full-width">
            <label>ที่อยู่</label>
            <input
              type="text"
              name="address"
              value={employee.address || ""}
              onChange={(e) => setEmployee({ ...employee, address: e.target.value })} 
            />
          </div>
          <div className="form-group">
            <label>เบอร์โทรศัพท์</label>
            <input
              type="text"
              name="phone"
              value={employee.phoneNumber || ""}
              onChange={(e) => setEmployee({ ...employee, phoneNumber: e.target.value })}
            />
            <label>Email</label>
            <input
              type="text"
              name="email"
              value={employee.email || ""}
              onChange={(e) => setEmployee({ ...employee, email: e.target.value })} 
            />
          </div>

          <div className="form-group">
            <label>Username</label>
            <input
              type="text"
              name="username"
              value={employee.username || ""}
              onChange={(e) => setEmployee({ ...employee, username: e.target.value })} 
            />
            <label>Password</label>
            <input
              type="password"
              name="password"
              value={employee.password || ""}
              onChange={(e) => setEmployee({ ...employee, password: e.target.value })} 
            />
            <label>รหัสพนักงาน</label>
            <input
              type="text"
              name="empId"
              value={employee.empId || ""} readonly
            />
          </div>
          
          <div className="button-group">
            <button type="button" className="save-btn" onClick={handleSave}>SAVE</button>
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
