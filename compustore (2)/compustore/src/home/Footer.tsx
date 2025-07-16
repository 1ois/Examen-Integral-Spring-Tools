import 'bootstrap/dist/css/bootstrap.min.css';
import './Footer.css';

function Footer() {
  return (
    <footer className="footer bg-dark text-white text-center py-4 mt-5">
      <div className="container">
        <h5 className="mb-3">CampusStore</h5>
        <p className="mb-2">
          Tienda oficial de la universidad. Calidad, confianza y cercanía para la comunidad estudiantil.
        </p>
        <div className="social-icons mb-3">
          <i className="bi bi-facebook me-3"></i>
          <i className="bi bi-instagram me-3"></i>
          <i className="bi bi-twitter-x"></i>
        </div>
        <small className="text-secondary">© 2025 CampusStore. Todos los derechos reservados.</small>
      </div>
    </footer>
  );
}

export default Footer;
// This code defines a simple footer component for a React application.