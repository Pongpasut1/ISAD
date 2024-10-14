import React from 'react';
import { Link } from 'react-router-dom';
import './hrevaluation.css'

const HrEvaluation = () => {
  return (
    <div className="container">
      <header className="header">
        <h1>นายเองก็เป็นได้นะ HR น่ะ</h1>
        <div className="circle"> </div>
      </header>
      <div className="buttons-container">
        <Link to="/hr/evaluation/criteria">
          <button className="button">เกณฑ์การประเมิน</button>
        </Link>
        <Link to="/hr/evaluation/evaluem">
          <button className="button">ประเมินพนักงาน</button>
        </Link>
        <Link to="">
          <button className="button">ประเมินตัวเอง</button>
        </Link>
        <Link to="/hr/evaluation/evaluem/result">
          <button className="button">ผลการประเมิน</button>
        </Link>
      </div>
    </div>
  );
};

export default HrEvaluation;