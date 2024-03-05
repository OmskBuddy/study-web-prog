package ru.itmo.wp.form;

import javax.validation.constraints.*;

public class UserRegisterCredentials {
    @NotBlank
    @NotNull
    @Size(min = 2, max = 24)
    @Pattern(regexp = "[a-zA-Z]{2,24}", message = "Expected latin letters")
    private String login;

    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 30)
    @Pattern(regexp = "[\\sa-zA-Z]{2,30}", message = "Expected latin letters")
    private String name;

    @NotEmpty
    @Size(min = 1, max = 60)
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
