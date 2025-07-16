import './Product.css';
import type { Product } from '../type/Producto';
import { useState, useEffect } from 'react';

function ProductView() {
  const [listaproductos, setListaproductos] = useState<Product[]>([]);
  const [productoActual, setProductoActual] = useState<Product | null>(null);
  const [form, setForm] = useState({
    idproducto: 0,
    nombre: "",
    precio: "",
    stock: "",
    imagen: "",
    idcategoria: ""
  });

  useEffect(() => {
    leerServicio();
  }, []);

  const leerServicio = () => {
    fetch('http://localhost:8085/springboot-jpa/producto/controller/producto')
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setListaproductos(data);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
      });
  };

  const guardarProducto = async () => {
    const method = productoActual ? "PUT" : "POST";
    const url = productoActual
      ? `http://localhost:8085/springboot-jpa/producto/controller/producto/${productoActual.idproducto}`
      : `http://localhost:8085/springboot-jpa/producto/controller/producto`;

    const body = {
      nombre: form.nombre,
      precio: parseFloat(form.precio),
      stock: parseInt(form.stock),
      imagen: form.imagen,
      categoria: { idcategoria: parseInt(form.idcategoria) }
    };

    console.log("Enviando producto:", body);

    try {
      const response = await fetch(url, {
        method,
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(body),
      });

      if (!response.ok) {
        const text = await response.text();
        throw new Error(`Error del servidor: ${response.status} - ${text}`);
      }

      leerServicio();
      setProductoActual(null);
      setForm({
        idproducto: 0,
        nombre: "",
        precio: "",
        stock: "",
        imagen: "",
        idcategoria: ""
      });

    } catch (error) {
      console.error("Error al guardar producto:", error);
    }
  };

  const eliminarProducto = async (id: number) => {
    if (!confirm("¿Seguro que deseas eliminar el producto?")) return;
    try {
      await fetch(`http://localhost:8085/springboot-jpa/producto/controller/producto/${id}`, {
        method: "DELETE",
      });
      leerServicio();
    } catch (error) {
      console.error("Error al eliminar producto:", error);
    }
  };

  const cargarProductoEditar = (producto: Product) => {
    setProductoActual(producto);
    setForm({
      idproducto: producto.idproducto || 0,
      nombre: producto.nombre || "",
      precio: producto.precio?.toString() || "",
      stock: producto.stock?.toString() || "",
      imagen: producto.imagen || "",
      idcategoria: producto.categoria?.idcategoria?.toString() || "",
    });
  };

  const agregarCarrito = (producto: Product, cantidad: number) => {
    const carrito = JSON.parse(localStorage.getItem("carrito") || "[]");

    const index = carrito.findIndex((item: any) => item.idproducto === producto.idproducto);
    if (index !== -1) {
      carrito[index].cantidad += cantidad;
    } else {
      carrito.push({ ...producto, cantidad });
    }

    localStorage.setItem("carrito", JSON.stringify(carrito));
    alert("Producto agregado al carrito 🛒");
  };

  return (
  <div className="product-list container mt-4">
    {/* Título y botón arriba del listado */}
    <div className="mb-4">
      <h2 className="mb-3">Productos</h2>
      <button
        className="btn btn-primary"
        data-bs-toggle="offcanvas"
        data-bs-target="#offcanvasProducto"
        onClick={() => {
          setProductoActual(null);
          setForm({
            idproducto: 0,
            nombre: "",
            precio: "",
            stock: "",
            imagen: "",
            idcategoria: ""
          });
        }}
      >
        Nuevo Producto
      </button>
    </div>

    {/* Listado de productos debajo */}
    {listaproductos.map((producto) => (
      <div key={producto.idproducto} className="product-item border rounded p-3 mb-3">
        <h3>{producto.nombre}</h3>
        <p>Precio: ${producto.precio}</p>
        <p>Stock: {producto.stock}</p>
        <p>Categoría: {producto.categoria?.nombre ?? "Sin categoría"}</p>
        <p>Descripción: {producto.categoria?.descripcion ?? "Sin descripción"}</p>

        <div className="d-flex flex-column gap-2 mt-2">
          <button
            className="btn btn-danger"
            onClick={() => eliminarProducto(producto.idproducto)}
          >
            <i className="bi bi-trash-fill me-1"></i> Eliminar
          </button>

          <button
            className="btn btn-warning"
            data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasProducto"
            onClick={() => cargarProductoEditar(producto)}
          >
            <i className="bi bi-pencil-square me-1"></i> Editar
          </button>

          <button
            className="btn btn-success"
            onClick={() => agregarCarrito(producto, 1)}
          >
            <i className="bi bi-cart-plus me-1"></i> Agregar al carrito
          </button>
        </div>
      </div>
    ))}

      <div
        className="offcanvas offcanvas-end"
        tabIndex={-1}
        id="offcanvasProducto"
        aria-labelledby="offcanvasRightLabel"
      >
        <div className="offcanvas-header">
          <h5 className="offcanvas-title" id="offcanvasRightLabel">
            {productoActual ? "Editar Producto" : "Nuevo Producto"}
          </h5>
          <button
            type="button"
            className="btn-close"
            data-bs-dismiss="offcanvas"
            aria-label="Close"
          ></button>
        </div>
        <div className="offcanvas-body">
          <form
            onSubmit={(e) => {
              e.preventDefault();
              guardarProducto();
            }}
          >
            <input
              type="text"
              className="form-control mb-2"
              value={form.nombre}
              onChange={(e) => setForm({ ...form, nombre: e.target.value })}
              placeholder="Nombre"
              required
            />

            <input
              type="number"
              className="form-control mb-2"
              value={form.precio}
              placeholder="Precio"
              onChange={(e) => setForm({ ...form, precio: e.target.value })}
            />

            <input
              type="number"
              className="form-control mb-2"
              value={form.stock}
              placeholder="Stock"
              onChange={(e) => setForm({ ...form, stock: e.target.value })}
            />

            <input
              type="text"
              className="form-control mb-2"
              value={form.imagen}
              onChange={(e) => setForm({ ...form, imagen: e.target.value })}
              placeholder="URL Imagen"
            />

            <input
              type="number"
              className="form-control mb-2"
              value={form.idcategoria}
              placeholder="ID Categoría"
              onChange={(e) => setForm({ ...form, idcategoria: e.target.value })}
            />

            <button type="submit" className="btn btn-success w-100" data-bs-dismiss="offcanvas">
              {productoActual ? "Actualizar" : "Crear"}
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default ProductView;
