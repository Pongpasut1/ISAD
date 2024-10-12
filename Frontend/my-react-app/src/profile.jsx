import React, { useState } from 'react'; // Import useState from React
import { useParams } from 'react-router-dom'; // Import useParams
import './Profile.css';

function Profile() {
  const { id } = useParams(); // ดึง id จาก URL
  const [employees] = useState([
    { id: 1, firstName: 'ก', lastName: 'ข', title: 'นาย', department: 'IT' },
    { id: 2, firstName: 'ก2', lastName: 'ข', title: 'นาย', department: 'HR' },
    { id: 3, firstName: 'ก3', lastName: 'ข', title: 'นาย', department: 'Finance' }
  ]);

  const employee = employees.find(emp => emp.id === Number(id)); // ค้นหาพนักงานตาม ID

  return (
    <div className='container'>
      <Header />
      <div className="profile-container">
        <div className="profile-circle"></div>
        <div className="input-group">
          <label>ชื่อ</label>
          {employee ? (
            <input type="text" value={`${employee.title} ${employee.firstName} ${employee.lastName}`} readOnly />
          ) : (
            <input type="text" value="ไม่พบข้อมูล" readOnly />
          )}
        </div>
        <div className="input-group">
          <label>ตำแหน่งงาน</label>
          <input type="text" value={employee ? employee.title : ''} placeholder="ตำแหน่งงาน" readOnly />
          <label>แผนก</label>
          <input type="text" value={employee ? employee.department : ''} placeholder="แผนก" readOnly />
        </div>
      </div>
    </div>
  );
};

const Header = () => (
  <header className="header">
    <h1>นายเองก็เป็นได้นะ HRน่ะ</h1>
    <div className="profile-circle"></div>
  </header>
);

export default Profile;
