package com.siderink.SuperMarket.service;

import com.siderink.SuperMarket.dto.VentaDTO;
import com.siderink.SuperMarket.model.Venta;
import com.siderink.SuperMarket.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    VentaRepository ventaRepository;

    @Override
    public List<VentaDTO> traerVentas() {

        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<>();

        for(Venta v : ventas){
        }
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {
        return null;
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        return null;
    }

    @Override
    public void eliminarVenta(Long id) {

    }
}
