package com.siderink.SuperMarket.mapper;

import com.siderink.SuperMarket.dto.DetalleVentaDTO;
import com.siderink.SuperMarket.dto.ProductoDTO;
import com.siderink.SuperMarket.dto.SucursalDTO;
import com.siderink.SuperMarket.dto.VentaDTO;
import com.siderink.SuperMarket.model.DetalleVenta;
import com.siderink.SuperMarket.model.Producto;
import com.siderink.SuperMarket.model.Sucursal;
import com.siderink.SuperMarket.model.Venta;

import java.util.stream.Collectors;

public class Mapper {

    public static ProductoDTO toDTO(Producto p){
        if(p == null) return null;

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .build();
    }

    public static SucursalDTO toDTO(Sucursal s){
        if(s == null) return null;

        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();
    }

    public static VentaDTO toDTO(Venta v){
        if(v == null) return null;

        var detalleVenta = v.getDetalle().stream().map(det -> DetalleVentaDTO.builder()
                .id(det.getProducto().getId())
                .nombreProducto(det.getProducto().getNombre())
                .cantidad(det.getProducto().getCantidad())
                .precio(det.getProducto().getPrecio())
                .subTotal(det.getProducto().getPrecio()*det.getProducto().getCantidad())
                .build()
        ).collect(Collectors.toList());

        var total = detalleVenta.stream()
                .map(DetalleVentaDTO::getSubTotal)
                .reduce(0.0, Double::sum);

        return VentaDTO.builder()
                .id(v.getId())
                .fecha(v.getFecha())
                .idSucursal(v.getSucursal().getId())
                .estado(v.getEstado())
                .detalle(detalleVenta)
                .total(total)
                .build();

    }
}
