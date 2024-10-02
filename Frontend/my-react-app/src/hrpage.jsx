import React from 'react';
import './people.css'

// ** ยังไม่เสร็จ **
const HrPage = () => {
  return (
    <div className="container">
      <header className="header">
        <h1>นายเองก็เป็นได้นะ HR น่ะ</h1>
        <div className="circle"> </div>
      </header>
      <div className="buttons-container">
        <button className="button">ข้อมูลพนักงาน</button>
        <button className="button">ประเมิน</button>
        <button className="button">เงินเดือน</button>
      </div>
    </div>
  );
};

export default HrPage;
