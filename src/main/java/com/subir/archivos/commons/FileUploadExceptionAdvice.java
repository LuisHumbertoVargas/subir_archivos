package com.subir.archivos.commons;

import com.subir.archivos.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileUploadExceptionAdvice {
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Response> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        String msg = "Verifica el tamaño de los archivos";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(msg));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception ex){
        String msg = "Hubo un error en la petición";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(msg));
    }
    
}
