import { NavLink, Link } from "react-router-dom";

const Header = () => {
  return (
    <header>
      <div className="header">
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
      </div>
    </header>
  );
};

export default Header;
