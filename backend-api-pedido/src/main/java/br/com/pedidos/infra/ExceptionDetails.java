package br.com.pedidos.infra;

import lombok.Data;

@Data
public class ExceptionDetails {

    private int status;
    private String message;
}
