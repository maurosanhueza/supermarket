package com.siderink.SuperMarket.service;

import com.siderink.SuperMarket.dto.ProductoDTO;
import com.siderink.SuperMarket.exception.NotFoundException;
import com.siderink.SuperMarket.mapper.Mapper;
import com.siderink.SuperMarket.model.Producto;
import com.siderink.SuperMarket.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> traerProductos() {
        return productoRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        var prod = Producto.builder()
                .nombre(productoDTO.getNombre())
                .categoria(productoDTO.getCategoria())
                .precio(productoDTO.getPrecio())
                .cantidad(productoDTO.getCantidad())
                .build();

        return Mapper.toDTO(productoRepository.save(prod));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {

        Producto prod = productoRepository.findById(id).orElseThrow(()->new NotFoundException("producto no encontrado"));

        prod.setNombre(productoDTO.getNombre());
        prod.setCategoria(productoDTO.getCategoria());
        prod.setCantidad(productoDTO.getCantidad());
        prod.setPrecio(productoDTO.getPrecio());

        return Mapper.toDTO(productoRepository.save(prod));

    }

    @Override
    public void eliminarProducto(Long id) {
        if(productoRepository.existsById(id)){
            throw new NotFoundException("Producto no encontrado para eliminar");
        }
        productoRepository.deleteById(id);
    }
}
