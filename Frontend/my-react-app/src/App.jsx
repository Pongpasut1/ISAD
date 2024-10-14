// App.jsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './login';
import EmployeePage from './employeepage';
import HrPage from './hrpage';
import ChiefPage from './chief';
import HrData from './hrdata';
import EmDetail from './emdetail';
import HrEvaluation from './hrevaluation';
import MoEmdetail from './moemdetail';
import Department from './department';
import Profile from './profile';
import Evaluem from './evaluem';
import Evaluate from './evaluate';
import EvaluateResult from './EvaluateResult'; // เพิ่ม EvaluateResult
import EvaluateResultList from './EvaluateResultList';

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/employee" element={<EmployeePage />} />
          <Route path="/hr" element={<HrPage />} />
          <Route path="/chief" element={<ChiefPage />} />
          <Route path="/employee/:id/profile" element={<Profile />} />
          <Route path="/hr/hrdata" element={<HrData />} />
          <Route path="/hr/hrdata/employee/:id" element={<EmDetail />} />
          <Route path="/hr/evaluation" element={<HrEvaluation />} />
          <Route path="/hr/evaluation/criteria" element={<Department />} />
          <Route path="/hr/hrdata/employee/:id/moemdetail" element={<MoEmdetail />} />
          <Route path="/hr/evaluation/evaluem" element={<Evaluem />} />
          <Route path="/hr/evaluation/evaluem/evaluate/:empId" element={<Evaluate />} />
          <Route path="/hr/evaluation/evaluem/result/:empId" element={<EvaluateResult />} />
          <Route path="/hr/evaluation/evaluem/result" element={<EvaluateResultList />} />
          {/* เพิ่ม Route สำหรับผลการประเมิน */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
