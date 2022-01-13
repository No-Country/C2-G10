import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import Header from '../../components/Header';

import './Auth.css'

const Auth = () => {
    const [showLogin, setShowLogin] = useState(false);
    const [userRole, setUserRole] = useState('')

    const history = useNavigate()

    const login = () => {
        localStorage.setItem('user', userRole)
        history('/')
    }

    return (
        <>
            <Header />
            <div className={`LoginSignUp ${showLogin && 'active'}`}>
                <div className="LoginSignUp-container">
                    <div className='blueBg'>
                        <div className='box signin'>
                            <h2>Ya tienes una cuenta?</h2>
                            <button
                                onClick={() => setShowLogin(!showLogin)}
                                className="signinBtn"
                            >Iniciar</button>
                        </div>
                        <div className='box signup'>
                            <h2>No tienes una cuenta?</h2>
                            <button
                                onClick={() => setShowLogin(!showLogin)}
                                className="signupBtn"
                            >Registrate Aqui</button>
                        </div>
                    </div>
                    <div className={`formBx ${showLogin && 'active'}`}>
                        <div className='form signinForm'>
                            <form>
                                <h3>Iniciar Sesion</h3>
                                <input type="text" placeholder='Email' onChange={(e) => setUserRole(e.target.value)} value={userRole} />
                                <input type="password" placeholder='Contraseña' />
                                <input onClick={login} type="submit" value="Iniciar" />
                                <a href='/' className='forgot'>Olvidaste Tu Contraseña?</a>
                            </form>
                        </div>

                        <div className='form signupForm'>
                            <form>
                                <h3>Registrarse</h3>
                                <input type="text" placeholder='Nombre' />
                                <input type="text" placeholder='Email' />
                                <input type="password" placeholder='Contraseña' />
                                <input type="password" placeholder='Confirmar Contraseña' />
                                <input type="submit" value="Registrar" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Auth
