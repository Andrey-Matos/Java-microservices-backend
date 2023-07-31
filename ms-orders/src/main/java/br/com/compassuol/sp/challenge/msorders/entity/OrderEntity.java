package br.com.compassuol.sp.challenge.msorders.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "orders")
@DynamicUpdate
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	@Setter(AccessLevel.NONE)
	private Long id;

	@Column(nullable = false)
	private Long userId;

	@Column(name = "status", nullable = false)
	private String status = "PENDING";

	@ElementCollection
	@Column(nullable = false)
	private ArrayList<Object> products;

	@Column(nullable = false)
	private String enderecoEntrega;

	public OrderEntity(Long userId, ArrayList<Object> products, String enderecoEntrega) {
		this.userId = userId;
		this.products = products;
		this.enderecoEntrega = enderecoEntrega;
	}
	public void addProduct(Object product) {
		this.products.add(product);
	}
}