import { useEffect, useState } from "react";
import type { Product } from "../type/Producto";

function Cart() {
  const [carrito, setCarrito] = useState<(Product & { cantidad: number })[]>([]);

  useEffect(() => {
    const datos = localStorage.getItem("carrito");
    if (datos) {
      setCarrito(JSON.parse(datos));
    }
  }, []);

  const eliminarDelCarrito = (index: number) => {
    const nuevoCarrito = [...carrito];
    nuevoCarrito.splice(index, 1);
    setCarrito(nuevoCarrito);
    localStorage.setItem("carrito", JSON.stringify(nuevoCarrito));
  };

  const total = carrito.reduce(
    (acc, item) => acc + item.precio * item.cantidad,
    0
  );

  const enviarCarritoAlServidor = async () => {
    for (const item of carrito) {
      const detalle = {
        cantidad: item.cantidad,
        precioUnitario: item.precio,
        subtotal: item.precio * item.cantidad,
        fechaAgregada: new Date().toISOString().split("T")[0], // "YYYY-MM-DD"
        estadoItem: "Pendiente",
        carrito: { idcarrito: 1 }, // reemplaza con ID real si tienes uno
        producto: { idproducto: item.idproducto },
      };

      try {
        const res = await fetch("http://localhost:8085/springboot-jpa/detallecarrito/controller/detalle", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(detalle)
        });

        if (!res.ok) {
          throw new Error(`Error al guardar: ${res.status}`);
        }
      } catch (error) {
        console.error("Error al enviar detalle:", error);
      }
    }

    alert("🛒 Carrito enviado con éxito al servidor.");
    localStorage.removeItem("carrito");
    setCarrito([]);
  };

  return (
    <div className="container mt-4">
      <h2>🛒 Carrito de Compras</h2>
      {carrito.length === 0 ? (
        <p>No hay productos en el carrito.</p>
      ) : (
        <>
          <div className="table-responsive">
            <table className="table table-bordered align-middle">
              <thead>
                <tr>
                  <th>Nombre</th>
                  <th>Imagen</th>
                  <th>Precio</th>
                  <th>Cantidad</th>
                  <th>Subtotal</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                {carrito.map((item, index) => (
                  <tr key={index}>
                    <td>{item.nombre}</td>
                    <td>
                      <img
                        src={item.imagen}
                        alt={item.nombre}
                        width="80"
                        className="img-thumbnail"
                      />
                    </td>
                    <td>S/ {item.precio.toFixed(2)}</td>
                    <td>{item.cantidad}</td>
                    <td>S/ {(item.precio * item.cantidad).toFixed(2)}</td>
                    <td>
                      <button
                        className="btn btn-danger btn-sm"
                        onClick={() => eliminarDelCarrito(index)}
                      >
                        <i className="bi bi-trash"></i> Eliminar
                      </button>
                    </td>
                  </tr>
                ))}
                <tr>
                  <td colSpan={4} className="text-end fw-bold">Total:</td>
                  <td colSpan={2} className="fw-bold">S/ {total.toFixed(2)}</td>
                </tr>
              </tbody>
            </table>
          </div>

          <div className="text-end mt-3">
            <button className="btn btn-success" onClick={enviarCarritoAlServidor}>
              Enviar al servidor
            </button>
          </div>
        </>
      )}
    </div>
  );
}

export default Cart;
