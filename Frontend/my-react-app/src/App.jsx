import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './login';
import EmployeePage from './employeepage';
import HrPage from './hrpage';
import ChiefPage from './chief';
import HrData from './hrdata';
import EmDetail from './emdetail';

function App() {
  return (
    <Router>
        <div>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/employee" element={<EmployeePage />} />
          <Route path="/hr" element={<HrPage />} />
          <Route path="/chief" element={<ChiefPage />} />
          <Route path="/hr/hrdata" element={<HrData />} />
          <Route path="/hr/hrdata/employee/:id" element={<EmDetail />} />
        </Routes>
      </div>
    </Router>

  );
}

export default App;

