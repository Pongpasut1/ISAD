import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './hrdata.css';

function HrData() {
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
                <Link to={`/hr/hrdata/employee/${employee.id}`}>
                  {employee.title} {employee.firstName} {employee.lastName}
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

export default HrData;
