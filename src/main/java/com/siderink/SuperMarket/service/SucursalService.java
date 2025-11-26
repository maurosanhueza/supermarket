package com.siderink.SuperMarket.service;

import com.siderink.SuperMarket.dto.SucursalDTO;
import com.siderink.SuperMarket.exception.NotFoundException;
import com.siderink.SuperMarket.mapper.Mapper;
import com.siderink.SuperMarket.model.Sucursal;
import com.siderink.SuperMarket.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService{

    @Autowired
    SucursalRepository sucursalRepository;

    @Override
    public List<SucursalDTO> traerSucursales() {
        return sucursalRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDTO) {
        var sucursal = Sucursal.builder()
                .nombre(sucursalDTO.getNombre())
                .direccion(sucursalDTO.getDireccion())
                .build();
        return Mapper.toDTO(sucursalRepository.save(sucursal));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDTO) {

        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("sucursal no encontrada"));

        sucursal.setDireccion(sucursalDTO.getDireccion());
        sucursal.setNombre(sucursalDTO.getNombre());

        return Mapper.toDTO(sucursalRepository.save(sucursal));

    }

    @Override
    public void eliminarSucursal(Long id) {
        if (sucursalRepository.existsById(id)){
            throw new NotFoundException("Sucursal no encontrada para eliminar");
        }
        sucursalRepository.deleteById(id);

    }
}
