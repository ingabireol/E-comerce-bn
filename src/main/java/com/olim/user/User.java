package com.olim.user;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
    @Column(name = "is_active",
            columnDefinition = "BOOLEAN DEFAULT TRUE"
            )
    private boolean is_active = true;

}
