package br.com.compassuol.sp.challenge.msorders.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "cep-client", url = "https://viacep.com.br")
public interface CepClient {
    @GetMapping(value = "/ws/{cep}/json", params = "json")
    @ResponseBody
    Map<String, Object> search(@PathVariable String cep);
}