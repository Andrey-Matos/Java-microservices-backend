package br.com.compassuol.sp.challenge.msorders.entity;

import br.com.compassuol.sp.challenge.msorders.entity.DetailsDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class DetailedOrderDto {
    private Long id;
    private Long userId;
    private List<DetailsDto> productsDetails;
    private String deliveryAddress;
    private String status = "PENDING";
}