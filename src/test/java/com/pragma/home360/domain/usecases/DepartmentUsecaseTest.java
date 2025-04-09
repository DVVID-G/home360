package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.exceptions.DepartmentAlreadyExistsException;
import com.pragma.home360.domain.exceptions.DepartmentCannotBeNullException;
import com.pragma.home360.domain.model.DepartmentModel;
import com.pragma.home360.domain.ports.out.DepartmentPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentUsecaseTest {

    @Mock
    private DepartmentPersistencePort departmentPersistencePort;

    @InjectMocks
    private DepartmentUsecase departmentUsecase;

    private DepartmentModel validDepartment;

    @BeforeEach
    void setUp() {
        validDepartment = new DepartmentModel(1L, "Antioquia", "Departamento ubicado en la región noroccidental");
        validDepartment.setName("Antioquia");
        validDepartment.setDescription("Departamento ubicado en la región noroccidental");
    }

    @Test
    void testSave_ThrowsExceptionWhenDepartmentNameIsBlank() {
        validDepartment.setName("   ");

        assertThatThrownBy(() -> departmentUsecase.save(validDepartment))
                .isInstanceOf(DepartmentCannotBeNullException.class)
                .hasMessageContaining("El departamento no puede ser nulo");

        verify(departmentPersistencePort, never()).existsByName(anyString());
        verify(departmentPersistencePort, never()).save(any(DepartmentModel.class));
    }


    @Test
    void testSave_ThrowsExceptionWhenDepartmentDescriptionIsBlank() {
        validDepartment.setDescription("   ");

        assertThatThrownBy(() -> departmentUsecase.save(validDepartment))
                .isInstanceOf(DepartmentCannotBeNullException.class)
                .hasMessageContaining("La descripcion no puede ser nula");

        verify(departmentPersistencePort, never()).existsByName(anyString());
        verify(departmentPersistencePort, never()).save(any(DepartmentModel.class));
    }

    @Test
    void testSave_ThrowsExceptionWhenDepartmentAlreadyExists() {
        when(departmentPersistencePort.existsByName(validDepartment.getName())).thenReturn(true);

        assertThatThrownBy(() -> departmentUsecase.save(validDepartment))
                .isInstanceOf(DepartmentAlreadyExistsException.class)
                .hasMessageContaining("ya existe");

        verify(departmentPersistencePort, times(1)).existsByName(validDepartment.getName());
        verify(departmentPersistencePort, never()).save(any(DepartmentModel.class));
    }

    @Test
    void testSave_Successful() {
        when(departmentPersistencePort.existsByName(validDepartment.getName())).thenReturn(false);

        departmentUsecase.save(validDepartment);

        ArgumentCaptor<DepartmentModel> departmentCaptor = ArgumentCaptor.forClass(DepartmentModel.class);
        verify(departmentPersistencePort, times(1)).save(departmentCaptor.capture());
        DepartmentModel savedDepartment = departmentCaptor.getValue();

        assertThat(savedDepartment.getName()).isEqualTo(validDepartment.getName());
        assertThat(savedDepartment.getDescription()).isEqualTo(validDepartment.getDescription());
    }

    @Test
    void testGetDepartments_ReturnsListOfDepartments() {
        List<DepartmentModel> expectedDepartments = Collections.singletonList(validDepartment);
        when(departmentPersistencePort.getDepartments(0, 10, true)).thenReturn(expectedDepartments);

        List<DepartmentModel> actualDepartments = departmentUsecase.getDepartments(0, 10, true);
        assertThat(actualDepartments).isEqualTo(expectedDepartments);
        verify(departmentPersistencePort, times(1)).getDepartments(0, 10, true);
    }
}
