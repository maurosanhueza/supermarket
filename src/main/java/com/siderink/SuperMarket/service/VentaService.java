package com.siderink.SuperMarket.service;

import com.siderink.SuperMarket.dto.DetalleVentaDTO;
import com.siderink.SuperMarket.dto.VentaDTO;
import com.siderink.SuperMarket.exception.NotFoundException;
import com.siderink.SuperMarket.mapper.Mapper;
import com.siderink.SuperMarket.model.DetalleVenta;
import com.siderink.SuperMarket.model.Producto;
import com.siderink.SuperMarket.model.Sucursal;
import com.siderink.SuperMarket.model.Venta;
import com.siderink.SuperMarket.repository.ProductoRepository;
import com.siderink.SuperMarket.repository.SucursalRepository;
import com.siderink.SuperMarket.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    SucursalRepository sucursalRepository;

    @Override
    public List<VentaDTO> traerVentas() {

        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<>();

        VentaDTO ventaDTO;
        for(Venta v : ventas){
            ventaDTO = Mapper.toDTO(v);
            ventasDTO.add(ventaDTO);
        }

        return ventasDTO;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {
        if(ventaDTO == null) throw new RuntimeException("VentaDTO es null");
        if(ventaDTO.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if(ventaDTO.getDetalle() == null || ventaDTO.getDetalle().isEmpty()) throw new RuntimeException("Debe incluir al menos un producto");

        //buscar la sucursal
        Sucursal sucursal = sucursalRepository.findById(ventaDTO.getIdSucursal()).orElse(null);
        if(sucursal == null) throw new NotFoundException("Sucursal no existe");

        //crear la venta
        Venta venta = new Venta();
        venta.setFecha(ventaDTO.getFecha());
        venta.setEstado(ventaDTO.getEstado());
        venta.setSucursal(sucursal);
        venta.setTotal(ventaDTO.getTotal());

        // ahora cargamos el detalle de la venta
        List<DetalleVenta> detalles = new ArrayList<>();
        for(DetalleVentaDTO detalleVentaDTO: ventaDTO.getDetalle()){
            Producto producto = productoRepository.findById(detalleVentaDTO.getIdProducto()).orElse(null);
            if(producto == null) throw new RuntimeException("Producto no encontrado: " + detalleVentaDTO.getIdProducto());

            //creamos el detalla para el producto
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setProducto(producto);
            detalleVenta.setCantidad(detalleVentaDTO.getCantidad());
            detalleVenta.setPrecio(detalleVentaDTO.getPrecio());

            detalles.add(detalleVenta);
        }

        //ahora seteamos la lista de detalles
        venta.setDetalle(detalles);

        return Mapper.toDTO(ventaRepository.save(venta));
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        return null;
    }

    @Override
    public void eliminarVenta(Long id) {

    }
}
