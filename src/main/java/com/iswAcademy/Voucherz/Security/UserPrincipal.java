package com.iswAcademy.Voucherz.Security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iswAcademy.Voucherz.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//this class handles authorisation and authentication
public class UserPrincipal implements UserDetails {
    private  Long id;

    //this username will be the user email
    @JsonIgnore
    private String Username;

    @JsonIgnore
    private String Password;

    private String Email;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id,  String username, String password, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;

        Username = username;
        Password = password;
        Email = email;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user){
//        List<GrantedAuthority> authorities = user.getRoles().stream().map(role->
//                new SimpleGrantedAuthority(role.getName().name())
//        ).collect(Collectors.toList());

        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));


        return new UserPrincipal(

                user.getId(),
                user.getFirstName(),
                user.getPassword(),
                user.getEmail(),
                authorities
        );

    }


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    @Override
    public String getUsername() {
        return Username;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    //because its implementing the UserDetails it has to implement all the method in the class

//    @Override
//    public String getUsername() {
//        return username;
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(Username, that.Username) &&
                Objects.equals(Password, that.Password) &&
                Objects.equals(authorities, that.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Username, Password, authorities);
    }
}
