package com.subir.archivos.model;

import lombok.Data;

@Data
public class MyFile {
    
    private String name;
    private String url;
    
    public MyFile(String name, String url){
        this.name = name;
        this.url = url;
    }
    
}
