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
            <svg xmlns="http://www.w3.org/2000/svg" enableBackground="new 0 0 24 24" viewBox="0 0 24 24" id="calender" width="40" height="40">
                <path fill="#a2a1ff" d="M22 10H2v9a3 3 0 0 0 3 3h14a3 3 0 0 0 3-3v-9zM7 8a1 1 0 0 1-1-1V3a1 1 0 0 1 2 0v4a1 1 0 0 1-1 1zm10 0a1 1 0 0 1-1-1V3a1 1 0 0 1 2 0v4a1 1 0 0 1-1 1z"></path>
                <path fill="#6563ff" d="M19 4h-1v3a1 1 0 0 1-2 0V4H8v3a1 1 0 0 1-2 0V4H5a3 3 0 0 0-3 3v3h20V7a3 3 0 0 0-3-3z"></path>
              </svg>
              <span className='user-greeting' style={{ fontFamily: 'Roboto Condensed', fontSize: '4vh' }}>Olá, {userName}</span>
            </div>
          </header>
          <Calendario />
        </>
      )}
    </div>
  );
}

export default App;
