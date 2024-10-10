import React from 'react';
import { Link } from 'react-router-dom';
import './people.css';

// ** ยังไม่เสร็จ **
const HrPage = () => {
  return (
    <div className="container">
      <header className="header">
        <h1>นายเองก็เป็นได้นะ HR น่ะ</h1>
        <div className="circle"> </div>
      </header>
      <div className="buttons-container">
        <Link to="/hr/hrdata">
          <button className="button">ข้อมูลพนักงาน</button>
        </Link>
        <Link to="/hr/evaluation">
          <button className="button">ประเมิน</button>
        </Link>
        <button className="button">เงินเดือน</button>
      </div>
    </div>
  );
};

export default HrPage;
