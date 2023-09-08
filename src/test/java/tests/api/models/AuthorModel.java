package tests.api.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorModel {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String url;
    private Boolean isForeignAgent;
}
