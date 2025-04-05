package com.pragma.home360.application.services.implementation;

import com.pragma.commons.configurations.utils.Constants;
import com.pragma.home360.application.dto.request.SaveDeparmentRequest;
import com.pragma.home360.application.dto.response.DeparmentResponse;
import com.pragma.home360.application.dto.response.SaveDeparmentResponse;
import com.pragma.home360.application.mappers.DeparmentDtoMapper;
import com.pragma.home360.application.services.DeparmentService;
import com.pragma.home360.domain.ports.in.DeparmentServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeparmentServiceImpl implements DeparmentService {
    private final DeparmentServicePort deparmentServicePort;
    private final DeparmentDtoMapper deparmentDtoMapper;

    @Override
    public SaveDeparmentResponse save(SaveDeparmentRequest request) {
        deparmentServicePort.save(deparmentDtoMapper.requestToModel(request));
        return new SaveDeparmentResponse(Constants.SAVE_DEPARMENT_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public List<DeparmentResponse> getDeparments(Integer page, Integer size, boolean orderAsc) {
        return deparmentDtoMapper.modelListToResponseList(deparmentServicePort.getDeparments(page, size, orderAsc));
    }

}
