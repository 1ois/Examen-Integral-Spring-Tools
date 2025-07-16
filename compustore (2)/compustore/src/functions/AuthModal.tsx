import React, { useState } from 'react';
import './AuthModal.css'; // Asegúrate de tener un archivo CSS para estilos

interface AuthModalProps {
  isOpen: boolean;
  onClose: () => void;
  onAuthSuccess: (nombre: string) => void;
}

const AuthModal: React.FC<AuthModalProps> = ({ isOpen, onClose, onAuthSuccess }) => {
  const [isLogin, setIsLogin] = useState(true);

  const [formData, setFormData] = useState({
    nombre: '',
    correo: '',
    contrasena: ''
  });

  if (!isOpen) return null;

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const url = isLogin
      ? 'http://localhost:8085/springboot-jpa/usuarios/controller/usuarios/login'
      : 'http://localhost:8085/springboot-jpa/usuarios/controller/usuarios';

    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
      });

      const result = await response.json();

      if (response.ok) {
        alert(result.mensaje || (isLogin ? 'Inicio de sesión exitoso' : 'Usuario creado'));

        // Llama a la función del Header para actualizar estado
        const nombreUsuario = result.nombre || formData.nombre;
        onAuthSuccess(nombreUsuario);

        // Limpia el formulario
        setFormData({ nombre: '', correo: '', contrasena: '' });
      } else {
        alert(result.mensaje || 'Error en la operación');
      }
    } catch (error) {
      console.error('Error al conectar con el backend:', error);
      alert('No se pudo conectar al servidor');
    }
  };

  return (
<div className="auth-modal-overlay">
  <div className="auth-modal-content">

        <h2 className="text-xl font-bold text-center mb-4">
          {isLogin ? 'Iniciar Sesión' : 'Crear Cuenta'}
        </h2>

        <form onSubmit={handleSubmit} className="space-y-4">
          {!isLogin && (
            <input
              type="text"
              name="nombre"
              placeholder="Nombre"
              value={formData.nombre}
              onChange={handleChange}
              className="w-full p-2 border rounded"
              required
            />
          )}

          <input
            type="email"
            name="correo"
            placeholder="Correo"
            value={formData.correo}
            onChange={handleChange}
            className="w-full p-2 border rounded"
            required
          />

          <input
            type="password"
            name="contrasena"
            placeholder="Contraseña"
            value={formData.contrasena}
            onChange={handleChange}
            className="w-full p-2 border rounded"
            required
          />

          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700"
          >
            {isLogin ? 'Iniciar Sesión' : 'Crear Cuenta'}
          </button>
        </form>

        <p className="text-center text-sm mt-4">
          {isLogin ? '¿No tienes cuenta?' : '¿Ya tienes cuenta?'}{' '}
          <button
            type="button"
            className="text-blue-500 underline"
            onClick={() => setIsLogin(!isLogin)}
          >
            {isLogin ? 'Crear una' : 'Iniciar sesión'}
          </button>
        </p>

   <button className="auth-modal-close" onClick={onClose}>×</button>

      </div>
    </div>
  );
};

export default AuthModal;
