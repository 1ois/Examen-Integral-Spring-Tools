export interface Categoria {
  idcategoria: number;
  nombre: string;
  descripcion: string;
}

export interface Product {
  idproducto: number;
  nombre: string;
  precio: number;
  stock: number;
  categoria: Categoria;
  imagen: string;
}
