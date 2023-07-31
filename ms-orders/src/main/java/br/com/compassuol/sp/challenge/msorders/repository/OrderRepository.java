package br.com.compassuol.sp.challenge.msorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compassuol.sp.challenge.msorders.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}
