package tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductModel {
    private String title;
    private List<AuthorModel> authors;
    private List<AuthorModel> coauthors;
    private Integer goodsId;
    private Integer id;
    private Integer stock;
    private Integer quantity;
    private Integer fullPrice;
    private Integer weight;
    private String url;
    private String publisher;
    private String picture;
    private Boolean sale;
    private Boolean preOrder;
    private Boolean isBookmarks;
    private Integer price;
    private Boolean inSubscription;
    private Integer cost;
    private Boolean disabledBonuses;
    private Integer fullCost;
    private Boolean isMagic;
    private Object nForM;
    private String status;
    private CategoryModel category;
    private AdDataModel adData;
}
