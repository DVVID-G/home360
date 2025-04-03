package com.pragma.home360.application.services;

import com.pragma.home360.application.dto.request.SaveDeparmentRequest;
import com.pragma.home360.application.dto.response.SaveDeparmentResponse;
import com.pragma.home360.domain.model.DeparmentModel;

import java.util.List;

public interface DeparmentService {
    SaveDeparmentResponse save(SaveDeparmentRequest request);
    List<DeparmentModel> getDeparments(Integer page, Integer size, boolean orderAsc);
}
