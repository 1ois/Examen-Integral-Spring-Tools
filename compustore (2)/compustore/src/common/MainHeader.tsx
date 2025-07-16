import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import "./MainHeader.css";
import AuthModal from "../functions/AuthModal"; // Asegúrate de importar correctamente

function MainHeader() {
  const [mostrarInput, setMostrarInput] = useState(false);
  const [mostrarLogin, setMostrarLogin] = useState(false);
  const [usuarioNombre, setUsuarioNombre] = useState<string | null>(null);

  const manejarBusquedaClick = () => {
    setMostrarInput(!mostrarInput);
  };

  useEffect(() => {
    const nombre = localStorage.getItem("usuarioNombre");
    if (nombre) setUsuarioNombre(nombre);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("usuarioNombre");
    localStorage.removeItem("usuarioCorreo");
    setUsuarioNombre(null);
  };

  const handleAuthSuccess = (nombre: string) => {
    localStorage.setItem("usuarioNombre", nombre);
    setUsuarioNombre(nombre);
    setMostrarLogin(false);
  };

  return (
    <>
      <nav
        id="nav"
        className="navbar container m-2 w-75 d-flex justify-content-center mx-auto navbar-expand-lg"
      >
        <div className="container-fluid">
          <Link className="navbar-brand" to="/presentacion">
            CampusStore
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <a className="nav-link" href="/producto">
                  Producto
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/carrito">
                  Carrito
                </a>
              </li>
            </ul>

            <form className="d-flex" role="search">
              {usuarioNombre ? (
                <>
                  <span className="me-2 fw-bold text-primary">Hola, {usuarioNombre}</span>
                  <button
                    type="button"
                    className="btn btn-link text-danger text-decoration-none"
                    onClick={handleLogout}
                  >
                    Cerrar sesión
                  </button>
                </>
              ) : (
                <i
                  className="bi bi-person-circle icon-large"
                  onClick={() => setMostrarLogin(true)}
                  style={{ cursor: "pointer" }}
                  title="Iniciar sesión"
                ></i>
              )}

              <i className="bi bi-instagram icon-large"></i>
              <i className="bi bi-vimeo icon-large"></i>
              <i
                className="bi bi-search icon-large"
                onClick={manejarBusquedaClick}
                style={{ cursor: "pointer" }}
              ></i>

              {mostrarInput && (
                <input
                  type="text"
                  className="search-input"
                  placeholder="Buscar..."
                />
              )}
            </form>
          </div>
        </div>
      </nav>

      <AuthModal
        isOpen={mostrarLogin}
        onClose={() => setMostrarLogin(false)}
        onAuthSuccess={handleAuthSuccess}
      />
    </>
  );
}

export default MainHeader;
