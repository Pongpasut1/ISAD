import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './hrdata.css';

function Evaluem() {
    const [employees] = useState([
        { id: 1, firstName: 'ก', lastName: 'ข', title: 'นาย' },
        { id: 2, firstName: 'ก', lastName: 'ข', title: 'นาย' },
        { id: 3, firstName: 'ก', lastName: 'ข', title: 'นาย' }
      ]);
    
    return (
        <div className='container'>
          <Header />
          <SearchBar />
          <div className="employee-list">
            {employees.map(employee => (
              <div key={employee.id} className="employee-item">
                <Link to={``}>
                  {employee.title} {employee.firstName} {employee.lastName}
                </Link>
                <h2></h2>
                <h2></h2>
                <h2></h2>
                <Link to="/hr/evaluation/evaluem/evaluate">
                    <button className="button-inlist">ประเมิน</button>
                </Link>
                <Link to="">
                    <button className="button-inlist">ผลการประเมิน</button>
                </Link>
              </div>
            ))}
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
    
const SearchBar = () => (
    <div className="search-bar">
        <input type="text" placeholder="รายชื่อพนักงาน" />
        <button className="search-btn">&#128269;</button>
        <button className="sort-btn">&#8645;</button>
        <button className="add-btn">+</button>
    </div>
);

export default Evaluem;
