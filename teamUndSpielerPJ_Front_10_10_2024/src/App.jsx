// src/App.jsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Signup from './pages/Signup';
import InitializeTeam from './pages/InitializeTeam';
import CreateMatch from './pages/CreateMatch';
import Home from './pages/Home';
import Login from './pages/Login';
import Layout from './components/Layout';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="signup" element={<Signup />} />
          <Route path="login" element={<Login />} /> {/* 添加 Login 路由 */}
          <Route path="initializeTeam" element={<InitializeTeam />} />
          <Route path="createMatch" element={<CreateMatch />} />
          <Route path="*" element={<Navigate to="/" />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default App;
