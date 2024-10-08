import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css'; // สำหรับการจัดแต่ง CSS

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8081/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password }),
      });

      const data = await response.json();

      if (response.ok) {
        console.log(data.message);
        setErrorMessage('');
        localStorage.setItem('userRole', data.role);

        switch (data.role.toLowerCase()) {
          case 'employee':
            navigate('/employee');
            break;
          case 'hr':
            navigate('/hr');
            break;
          case 'chief':
            navigate('/chief');
            break;
          default:
            setErrorMessage('Unknown role!');
            break;
        }
      } else {
        setErrorMessage(data.error);
      }
    } catch (error) {
      console.error('Error:', error);
      setErrorMessage('Error! Please try again.');
    }
  };


  return (
    <div className="login-container">
      <form onSubmit={handleSubmit} className="login-form">
        <div className="avatar-circle"></div>
        <h2>Please login</h2>
        {errorMessage && <p className="error">{errorMessage}</p>}
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit">Sign in</button>
      </form>
    </div>
  );
};

export default Login;
