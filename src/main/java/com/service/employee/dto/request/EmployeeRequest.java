package com.service.employee.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRequest {

    private String id;
    private String name;
    private String position;
}
