import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './login';
import EmployeePage from './employeepage';

function App() {
  return (
    <Router>
        <div>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/employee" element={<EmployeePage />} />
        </Routes>
      </div>
    </Router>

  );
}

export default App;

