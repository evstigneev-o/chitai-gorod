package tests.api.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponseModel {
    private Integer cost;
    private Integer costWithSale;
    private Integer costWithBonuses;
    private Integer discount;
    private Integer weight;
    private Integer addBonuses;
    private String promoCode;
    private List<ProductModel> products;
    private List<Object> disabledProducts;
    private List<Object> preorderProducts;
    private List<Object> gifts;
}
