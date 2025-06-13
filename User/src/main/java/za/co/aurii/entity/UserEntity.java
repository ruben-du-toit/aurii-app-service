package za.co.aurii.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users") // avoid conflicts with reserved keyword "user"
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;
    private String surname;

    private String password; // hash it later

    private String role; // e.g., USER / ADMIN

    // any other fields you want to store
}
