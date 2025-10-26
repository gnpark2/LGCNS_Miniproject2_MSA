package user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(name="uk_users_email", columnNames="email"),
           @UniqueConstraint(name="uk_users_nickname", columnNames="nickname")
       })
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(length=100, nullable=false)
    private String email;

    @Column(length=50, nullable=false)
    private String username;

    @Column(length=50, nullable=false)
    private String nickname;

    @Column(name="password_hash", length=255, nullable=false)
    private String passwordHash;
}
