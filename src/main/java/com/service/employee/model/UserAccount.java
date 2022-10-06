package com.service.employee.model;


import com.service.employee.repository.UserAccountRepository;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = UserAccountRepository.table)
public class UserAccount {

    @Id
    @Column(nullable = false)
    private Integer id;
    private String username;
    private String password;

}
