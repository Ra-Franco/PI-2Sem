import React, { useState } from 'react';
import './App.css';
import Calendario from './page/Calendario';
import LoginApp from './loginApp';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './page/components/Components-Calendario-css.css';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userName, setUserName] = useState('');

  const handleLogin = (username) => {
    setIsLoggedIn(true);
    setUserName(username);
  };

  return (
    <div>
      {!isLoggedIn ? (
        <LoginApp onLogin={handleLogin} />
      ) : (
        <>
          <header>
            <h1 style={{ fontFamily: 'Roboto Condensed', marginLeft: '8vh', fontSize: '6vh' }}>On-noted</h1>
            <div className="user-info">
              <img src="https://via.placeholder.com/30" alt="Ícone de usuário" />
              <span>Olá, {userName}</span>
            </div>
          </header>
          <Calendario />
        </>
      )}
    </div>
  );
}

export default App;
