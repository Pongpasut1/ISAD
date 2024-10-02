import React from 'react';
import './people.css'

// ** ยังไม่เสร็จ **
const ChiefPage = () => {
  return (
    <div className="container">
      <header className="header">
        <h1>ผู้กำชะตากรรมของเหล่าทาส</h1>
        <div className="circle"> </div>
      </header>
      <div className="buttons-container">
        <button className="button">ข้อมูลพนักงาน</button>
        <button className="button">ประเมิน</button>
        <button className="button">เงินเดือนของฉัน</button>
      </div>
    </div>
  );
};

export default ChiefPage;
