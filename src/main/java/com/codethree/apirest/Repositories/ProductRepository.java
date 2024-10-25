package com.codethree.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codethree.apirest.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

    
}
