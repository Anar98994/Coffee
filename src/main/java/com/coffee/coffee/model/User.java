package com.coffee.coffee.model;

import com.coffee.coffee.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User
{
//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    @NotBlank(message = "First name is required")
    private String firstName;

    @Column(nullable=false)
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Column(nullable=false, unique=true)
    @NotBlank(message = "Username is required")
    private String username;

    @Column(nullable=false)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(unique = true)
    @NotBlank(message = "Password is required")
    private String email;

    @ManyToMany
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

    @Column(nullable = false)
    private Double balance;


    @OneToOne(mappedBy = "user")
    private Cart cart;


}
