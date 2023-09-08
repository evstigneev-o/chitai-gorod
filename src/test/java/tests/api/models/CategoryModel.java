package tests.api.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryModel {
    private Integer id;
    private String title;
    private String url;
    private String slug;
}
