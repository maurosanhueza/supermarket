package com.supermarket.repository;

import com.supermarket.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository <Sucursal, Long> {}

