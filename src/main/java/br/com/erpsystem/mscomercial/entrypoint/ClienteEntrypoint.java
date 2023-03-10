package br.com.erpsystem.mscomercial.entrypoint;

import br.com.erpsystem.mscomercial.core.usecase.gateway.ClienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class ClienteEntrypoint {

    private final ClienteGateway clienteGateway;

}
