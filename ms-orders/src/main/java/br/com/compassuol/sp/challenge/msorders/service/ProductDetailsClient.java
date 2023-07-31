package br.com.compassuol.sp.challenge.msorders.service;

import br.com.compassuol.sp.challenge.msorders.entity.DetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-products", path = "/products/")
public interface ProductDetailsClient {

    @GetMapping("/{id}")
    DetailsDto getProductById(@PathVariable Long id);
}