package br.com.compassuol.sp.challenge.msorders.service;


import br.com.compassuol.sp.challenge.msorders.entity.OrderEntity;
import br.com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    public  OrderService(){}
    private OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderEntity createOrder(OrderEntity product) {
        return orderRepository.save(product);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderEntity updateOrder(Long id, String status) {
        OrderEntity temp = orderRepository.getById(id);
        temp.setStatus(status);
        return orderRepository.save(temp);

    }

    public List<OrderEntity> getOrders(int page, int linesPerPage, String direction, String orderBy) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), orderBy);
        Pageable pageable = PageRequest.of(page - 1, linesPerPage, sort);
        Page<OrderEntity> pageIterator = orderRepository.findAll(pageable);
        return pageIterator.getContent();
    }

}