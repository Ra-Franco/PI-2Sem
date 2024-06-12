import React, { useState } from 'react';
import { CSSTransition, TransitionGroup } from 'react-transition-group';
import axios from 'axios';

function LoginApp({ onLogin }) {
  const [template, setTemplate] = useState('login');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleTemplateChange = (newTemplate) => {
    setTemplate(newTemplate);
  };

  const handleRegister = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/cadastrar', {
        email: username,
        senha: password
      });
      if (response.status === 201) {
        alert('Usuário registrado com sucesso');
        setTemplate('login');
      }
    } catch (error) {
      console.error('Erro ao registrar usuário:', error);
      alert('Erro ao registrar usuário');
    }
  };


  const handleLogin = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/login', {
        email: username,
        senha: password
      });
      if (response.status === 200) {
        console.log(response);
        const token = response.data.token; 
        const id = response.data.id;
        localStorage.setItem('token', token);
        localStorage.setItem('Id', id);
        onLogin();
      }
    } catch (error) {
      console.error('Erro ao fazer login:', error);
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
