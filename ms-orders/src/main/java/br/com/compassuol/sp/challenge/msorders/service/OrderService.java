package br.com.compassuol.sp.challenge.msorders.service;


import br.com.compassuol.sp.challenge.msorders.entity.DetailedOrderDto;
import br.com.compassuol.sp.challenge.msorders.entity.DetailsDto;
import br.com.compassuol.sp.challenge.msorders.entity.OrderEntity;
import br.com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    public  OrderService(){}

    @Autowired
    private ProductDetailsClient productClient;

    @Autowired
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

    public DetailedOrderDto getDetailedOrderById(Long id) {
        List<DetailsDto> detailsList = new ArrayList<>();
        DetailsDto details;
        DetailedOrderDto detailedOrder = new DetailedOrderDto();

        Optional<OrderEntity> order = orderRepository.findById(id);

        for (Long i : order.get().getProducts()) {
            if (productClient.getProductById(i) != null) {
                detailsList.add(productClient.getProductById(i));
            }
        }

        detailedOrder.setId(order.get().getId());
        detailedOrder.setUserId(order.get().getId());
        detailedOrder.setProductsDetails(detailsList);
        detailedOrder.setDeliveryAddress(order.get().getEnderecoEntrega());
        detailedOrder.setStatus(order.get().getStatus());

        return detailedOrder;
    }
}