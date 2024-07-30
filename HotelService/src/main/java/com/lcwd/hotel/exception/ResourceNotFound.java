package com.lcwd.hotel.exception;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(){
        super("Resource Not Found on Server !!");
    }

    public ResourceNotFound(String message){
        super(message);
    }
}
