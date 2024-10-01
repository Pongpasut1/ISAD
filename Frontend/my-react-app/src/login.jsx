import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css'; // For CSS styling

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

      //ตรวจ Username
      if (response.ok) {
        console.log(data.message);
        setErrorMessage('');
        if (username.toLowerCase().startsWith('em')){
          navigate('/employee'); // นำทางไปยังหน้าสำหรับพนักงาน
        } else if (username.toLowerCase().startsWith('ad')) {
          navigate('/hr'); // นำทางไปยังหน้าhr
        } else if (username.toLocaleLowerCase().startsWith('ch')){
          navigate('/chef'); //นำทางไปยังหน้าหัวหน้า
        }
      } else {
        setErrorMessage(data.error);
      }
    } catch (error) {
      console.error('Error:', error);
      setErrorMessage('Error Try again!');
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
          placeholder="username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Sign in</button>
      </form>
    </div>
  );
};

export default Login;
