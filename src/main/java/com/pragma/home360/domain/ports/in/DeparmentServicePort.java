package com.pragma.home360.domain.ports.in;

import com.pragma.home360.domain.model.DeparmentModel;

import java.util.List;

public interface DeparmentServicePort {
    void save(DeparmentModel deparmentModel);
    List<DeparmentModel> getDeparments(Integer page, Integer size, boolean orderAsc);
}
