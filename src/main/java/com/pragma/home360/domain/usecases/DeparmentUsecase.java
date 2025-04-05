package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.model.DeparmentModel;
import com.pragma.home360.domain.ports.in.DeparmentServicePort;
import com.pragma.home360.domain.ports.out.DeparmentPersistencePort;

import java.util.List;


public class DeparmentUsecase implements DeparmentServicePort {
    private final DeparmentPersistencePort deparmentPersistencePort;

    public DeparmentUsecase(DeparmentPersistencePort deparmentPersistencePort) {
        this.deparmentPersistencePort = deparmentPersistencePort;
    }

    @Override
    public void save(DeparmentModel deparmentModel) {

        deparmentPersistencePort.save(deparmentModel);
    }
    @Override
    public List<DeparmentModel> getDeparments(Integer page, Integer size, boolean orderAsc) {
        return deparmentPersistencePort.getDeparments(page, size, orderAsc);
    }

}
