package com.lcwd.user.UserService.exception;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(){
        super("Resource Not Found on Server !!");
    }

    public ResourceNotFound(String message){
        super(message);
    }
}
