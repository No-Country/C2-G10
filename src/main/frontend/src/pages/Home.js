import React from 'react'
import { Link } from "react-router-dom";

import Header from '../components/Header';
import Footer from "../components/Footer";

const Home = () => {
    return (
        <>
            <Header />
            <div className='container'>
                <div className="hero">
                    <div>
                        <h1>Gestioná tus turnos de la manera más simple.</h1>
                        <p>Soy profesional</p>
                        <p>Soy paciente</p>
                    </div>
                    <div>
                        <img src="/assets/calendar.png" alt="" />
                    </div>
                </div>
                <div className="features">
                    <div>
                        <img src="/assets/rapido.png" alt="" />
                        <p>Rápido</p>
                    </div>
                    <div>
                        <img src="/assets/simple.png" alt="" />
                        <p>Simple</p>
                    </div>
                    <div>
                        <img src="/assets/comodo.png" alt="" />
                        <p>Cómodo</p>
                    </div>
                </div>
                <p>Appointment es una app que te permite gestionar tus turnos, ya sea como paciente o como profesional, en un sólo lugar y de la manera más cómoda.</p>
                <div className="profesionales">
                    <h3>Conocé la red de profesionales de appointment haciendo click <Link to="/staff">aquí</Link>.</h3>
                </div>
            </div>
            <Footer />
        </>
    )
}

export default Home