package com.service.employee.model;


import com.service.employee.repository.EmployeeRepository;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = EmployeeRepository.table)
public class Employee {

    @Id
    @Column(nullable = false)
    private String id;
    private String name;
    private String position;

}
