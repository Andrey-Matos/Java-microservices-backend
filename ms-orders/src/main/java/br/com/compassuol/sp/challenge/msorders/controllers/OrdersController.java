package br.com.compassuol.sp.challenge.msorders.controllers;

import br.com.compassuol.sp.challenge.msorders.entity.OrderDto;
import br.com.compassuol.sp.challenge.msorders.entity.OrderEntity;
import br.com.compassuol.sp.challenge.msorders.service.CepClient;
import br.com.compassuol.sp.challenge.msorders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class OrdersController {

	@Autowired
	private  OrderService orderService;
	@Autowired
	private  CepClient client;
	@Autowired
	private ClientController rabbitClient;


	@GetMapping("/{id}")
	public ResponseEntity<OrderEntity> getProductById(@PathVariable Long id) {
		OrderEntity product = orderService.getOrderById(id);
		if (product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderDto order) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setUserId(order.userId());
		Integer[] temp = order.products();
		for (Integer i : temp) {
			if (!Objects.equals(rabbitClient.checkProduct(i), "invalid product")) {
				orderEntity.addProduct(rabbitClient.checkProduct(i));
			}
		}
		orderEntity.setEnderecoEntrega(client.search(order.enderecoEntrega()).toString());
		OrderEntity createdProduct = orderService.createOrder(orderEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrderEntity> updateOrder(@PathVariable Long id, @RequestBody String status) {
		OrderEntity savedOrder = orderService.updateOrder(id, status);
		if (savedOrder != null) {
			return ResponseEntity.ok(savedOrder);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<OrderEntity>> getOrders(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int linesPerPage,
			@RequestParam(defaultValue = "ASC") String direction,
			@RequestParam(defaultValue = "name") String orderBy
	) {
		List<OrderEntity> products = orderService.getOrders(page, linesPerPage, direction, orderBy);
		return ResponseEntity.ok(products);
	}
}


