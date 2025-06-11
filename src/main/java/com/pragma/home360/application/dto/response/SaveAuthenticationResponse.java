package com.pragma.home360.application.dto.response;

import java.time.LocalDateTime;

public record SaveAuthenticationResponse (String message, LocalDateTime time) {
}
