package com.rsreu.rsreu.data.entity;

import com.rsreu.rsreu.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "roles")
public class RoleInfo implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Role_GEN")
    @SequenceGenerator(name = "Role_GEN", sequenceName = "Role_SEQ", allocationSize = 20)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }

}
