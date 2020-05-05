package webApp.entities.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginrequestDto {
    @NotBlank
    @Size(min=7, max = 60)
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}