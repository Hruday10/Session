package com.example.exception;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	// Java recommends that classes extending Throwable (like exceptions) implement the Serializable interface, and therefore should declare a serialVersionUID.
	// This helps during serialization/deserialization, especially if your application sends exceptions across a network or stores them
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
