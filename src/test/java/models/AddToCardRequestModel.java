package models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddToCardRequestModel {
    private Integer id;
    private AdDataModel adData;
}

