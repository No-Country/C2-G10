import { NavLink, Link } from "react-router-dom";

const Header = () => {
  return (
    <header>
      <div className="header">
        <img src="/assets/appointment.png" alt="" />
        <div className="navBar">
          <NavLink to="/">
            <p>Home</p>
          </NavLink>
          <Link to="/auth">
            <p>Crear cuenta</p>
          </Link>
          <Link to="/auth">
            <p>Ingresar</p>
          </Link>
        </div>
      </div>
    </header>
  );
};

export default Header;
