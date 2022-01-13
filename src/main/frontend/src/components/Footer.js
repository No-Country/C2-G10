import { NavLink, Link } from "react-router-dom";

const Footer = () => {
    return (
    <footer>
        <div className="navBar">
          <NavLink to="/">
            <p>Home</p>
          </NavLink>
          <NavLink to="/">
            <p>Crear cuenta</p>
          </NavLink>
          <NavLink to="/">
            <p>Ingresar</p>
          </NavLink>
        </div>
        <img className="footerLogo" src="/assets/appointment.png" alt="" />
        <div>
               <a href='https://www.instagram.com/' target='_blank' rel='noreferrer'><img src='/assets/instagram.png' alt=""/></a>
               <a href='https://www.facebook.com/' target='_blank' rel='noreferrer'><img src='/assets/facebook.png' alt=""/></a>
               <a href='https://twitter.com/' target='_blank' rel='noreferrer'><img src='/assets/twitter.png' alt=""/></a>
               <a href='https://ar.linkedin.com/' target='_blank' rel='noreferrer'><img src='/assets/linkedin.png' alt=""/></a>
        </div>
        <p className="copyright">© Copyright 2022 | C2-10-M.</p>
    </footer>
    )
  
  }
  
  export default Footer