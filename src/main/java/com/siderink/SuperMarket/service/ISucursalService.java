package com.siderink.SuperMarket.service;

import com.siderink.SuperMarket.dto.SucursalDTO;
import com.siderink.SuperMarket.model.Sucursal;

import java.util.List;

public interface ISucursalService {

    List<SucursalDTO> traerSucursales();
    SucursalDTO crearSucursal(SucursalDTO sucursalDTO);
    SucursalDTO actualizarSucursal(Long id,SucursalDTO sucursalDTO);
    void eliminarSucursal(Long id);
}
