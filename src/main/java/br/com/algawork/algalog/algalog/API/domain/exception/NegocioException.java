package br.com.algawork.algalog.algalog.API.domain.exception;

import org.aspectj.bridge.Message;

public class NegocioException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NegocioException(String message){
        super(message);
    }

}
