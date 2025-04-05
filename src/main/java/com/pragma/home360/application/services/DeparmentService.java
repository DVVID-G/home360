package com.pragma.home360.application.services;

import com.pragma.home360.application.dto.request.SaveDeparmentRequest;
import com.pragma.home360.application.dto.response.DeparmentResponse;
import com.pragma.home360.application.dto.response.SaveDeparmentResponse;

import java.util.List;

public interface DeparmentService {
    SaveDeparmentResponse save(SaveDeparmentRequest request);
    List<DeparmentResponse> getDeparments(Integer page, Integer size, boolean orderAsc);
}
