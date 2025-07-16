import './Presentation.css';

function Presentation() {
  return (
    <section className="presentation-container">
      <div className="presentation-content">
        <h1>Bienvenido a CampusStore</h1>
        <p>
          Somos la tienda oficial de la universidad, pensada para estudiantes, docentes y personal administrativo. Aquí encontrarás productos tecnológicos, ropa, accesorios, artículos escolares y mucho más.
        </p>

        <div className="highlight-box">
          <h2>¿Por qué elegirnos?</h2>
          <ul>
            <li>✅ Productos 100% originales</li>
            <li>🚚 Entrega rápida dentro del campus</li>
            <li>📞 Atención personalizada para estudiantes</li>
            <li>💳 Descuentos con tu carnet universitario</li>
          </ul>
        </div>

        <div className="about-box">
          <h2>Sobre Nosotros</h2>
          <p>
            En CampusStore creemos en apoyar la vida universitaria con productos de calidad, útiles y accesibles. Nuestro compromiso es facilitar tu día a día con todo lo que necesitas, desde laptops hasta cuadernos.
          </p>
        </div>

        <div className="testimonios-box">
          <h2>Testimonios</h2>
          <blockquote>
            “Gracias a CampusStore pude comprar mi laptop con descuento. ¡Lo mejor es que me lo entregaron el mismo día!”  
            <footer>— Ana R., Estudiante de Ingeniería</footer>
          </blockquote>
          <blockquote>
            “Siempre encuentro artículos útiles para mis clases y proyectos. ¡Muy buena atención y precios!”  
            <footer>— Luis M., Estudiante de Diseño</footer>
          </blockquote>
        </div>
      </div>
    </section>
  );
}

export default Presentation;

