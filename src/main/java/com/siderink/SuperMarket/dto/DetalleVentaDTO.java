package com.siderink.SuperMarket.dto;

import com.siderink.SuperMarket.model.Producto;
import com.siderink.SuperMarket.model.Venta;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDTO {
    private Long id;
    private String nombreProducto;
    private Integer cantidad;
    private Double precio;
    private Double subTotal;
}
