package com.siderink.SuperMarket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDTO {
    private Long id;
    private Long idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private Double precio;
    private Double subTotal;
}
