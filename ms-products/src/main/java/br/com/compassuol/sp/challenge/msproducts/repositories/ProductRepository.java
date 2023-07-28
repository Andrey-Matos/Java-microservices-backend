package br.com.compassuol.sp.challenge.msproducts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compassuol.sp.challenge.msproducts.models.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

}
