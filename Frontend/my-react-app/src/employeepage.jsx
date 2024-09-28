import React from 'react';
import './employee.css'

// ** ยังไม่เสร็จ **
const EmployeePage = () => {
  return (
    <div className="container">
      <header className="header">
        <h1>ข้าคือผู้หลงทางในความมืดมิด</h1>
        <div className="circle"></div>
      </header>
      <div className="buttons-container">
        <button className="button">จัดการข้อมูลพนักงาน</button>
        <button className="button">ประเมิน</button>
        <button className="button">เงินเดือน</button>
      </div>
    </div>
  );
};

export default EmployeePage;
