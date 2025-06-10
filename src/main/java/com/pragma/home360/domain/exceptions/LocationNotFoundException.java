package com.pragma.home360.domain.exceptions;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String locationName
    ) {
        super("Barrio no encontrado: " + locationName
        );
    }
}
