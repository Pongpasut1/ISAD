// Evaluem.jsx
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './hrdata.css';

function Evaluem() {
    const [employees, setEmployees] = useState([]);

    useEffect(() => {
        // เรียกใช้ API จาก Spring Backend
        fetch('http://localhost:8081/hr/manageData')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => setEmployees(data))
            .catch(error => console.error('Error fetching employee data:', error));
    }, []);

    return (
        <div className='container'>
            <Header />
            <SearchBar />
            <div className="employee-list">
                {employees.map(employee => (
                    <div key={employee.empId} className="employee-item">
                        <h2 className="employee-name">
                            <Link to={`/hr/hrdata/employee/${employee.empId}`}>
                                {employee.name} {employee.surname}
                            </Link>
                        </h2>
                        <div className="employee-actions">
                            <Link to={`/hr/evaluation/evaluem/evaluate/${employee.empId}`}>
                                <button className="button-inlist">ประเมิน</button>
                            </Link>
                        </div>
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
