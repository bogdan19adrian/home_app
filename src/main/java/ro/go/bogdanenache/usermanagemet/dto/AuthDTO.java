package ro.go.bogdanenache.usermanagemet.dto;

import lombok.Data;

@Data

public class AuthDTO {



    private String email;
    private String username;
    private String password;

    public AuthDTO(String username, String password) {
        this.email = username;
        this.password = password;
    }
}