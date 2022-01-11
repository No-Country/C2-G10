import { NavLink, Link } from "react-router-dom";

const Footer = () => {
    return (
    <footer>
         <img src="/assets/appointment.png" alt="" />
        <div className="navBar">
          <NavLink exact to="/">
            <p>Home</p>
          </NavLink>
          <NavLink exact to="/">
            <p>Crear cuenta</p>
          </NavLink>
          <NavLink exact to="/">
            <p>Ingresar</p>
          </NavLink>
        </div>

    </footer>
    )
  
  }
  
  export default Footer