package com.siderink.SuperMarket.repository;

import com.siderink.SuperMarket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
