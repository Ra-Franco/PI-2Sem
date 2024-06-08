import React, { useState } from 'react';
import { CSSTransition, TransitionGroup } from 'react-transition-group';


const initialUsers = {
  'testuser': 'password123' // Exemplo de usuário inicial
};

function LoginApp({ onLogin }) {
  const [template, setTemplate] = useState('login');
  const [users, setUsers] = useState(initialUsers);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleTemplateChange = (newTemplate) => {
    setTemplate(newTemplate);
  };

  const handleRegister = (event) => {
    event.preventDefault();
    if (users[username]) {
      alert('Usuário já existe');
    } else {
      setUsers({ ...users, [username]: password });
      alert('Usuário registrado com sucesso');
      setTemplate('login');
    }
  };

  const handleLogin = (event) => {
    event.preventDefault();
    if (users[username] && users[username] === password) {
      onLogin();
    } else {
      alert('Nome de usuário ou senha incorretos');
    }
  };

  return (
    <div className="wrapper">
      <TransitionGroup component={null}>
        <CSSTransition
          key={template}
          timeout={300}
          classNames="fade"
          mountOnEnter
          unmountOnExit
        >
          <div>
            {template === 'login' ? (
              <form onSubmit={handleLogin}>
                <h1>Login</h1>
                <div className="input-box">
                  <input
                    type="text"
                    placeholder="Nome de usuário"
                    required
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                  />
                  <i className="uil uil-user"></i>
                </div>
                <div className="input-box">
                  <input
                    type="password"
                    placeholder="Senha"
                    required
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                  <i className="uil uil-lock"></i>
                </div>
                <button type="submit" className="botao">Entrar</button>
                <div className="registro">
                  <p>
                    Não tem uma conta?⠀
                    <a href="#" onClick={() => handleTemplateChange('register')}>Registre-se</a>
                  </p>
                </div>
              </form>
            ) : (
              <form onSubmit={handleRegister}>
                <h1>Registro</h1>
                <div className="input-box">
                  <input
                    type="text"
                    placeholder="Nome de usuário"
                    required
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                  />
                  <i className="uil uil-user"></i>
                </div>
                <div className="input-box">
                  <input
                    type="password"
                    placeholder="Senha"
                    required
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                  <i className="uil uil-lock"></i>
                </div>
                <button type="submit" className="botao">Registrar</button>
                <div className="registro">
                  <p>
                    Já tem uma conta?⠀
                    <a href="#" onClick={() => handleTemplateChange('login')}>Login</a>
                  </p>
                </div>
              </form>
            )}
          </div>
        </CSSTransition>
      </TransitionGroup>
    </div>
  );
}

export default LoginApp;
