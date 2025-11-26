package com.siderink.SuperMarket.service;

import com.siderink.SuperMarket.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> traerProductos();
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO actualizarProducto(Long id,ProductoDTO productoDTO);
    void eliminarProducto(Long id);
}
