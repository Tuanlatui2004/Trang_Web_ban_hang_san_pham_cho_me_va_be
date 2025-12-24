package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import  com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import java.beans.ConstructorProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    Integer id;
    String name;
    String sku;
    String description;
    Boolean is_active;
    Integer category_id;
    Integer brand_id;
    Integer no_of_views;
    Integer no_of_sold;
    Integer image_id;
    Integer price;  // option
    Integer stock;  //option
    Integer option_id;
    String category_name;
    String image_url;

    Integer height;
    Integer length;
    Integer width;
    Integer weight;

    @ConstructorProperties({"id", "name", "sku", "description", "is_active", "category_id", "brand_id"
            , "no_of_views", "no_of_sold", "image_id", "price", "stock","option_id", "category_name","image_url", "height", "length", "width", "weight"
    })
    public Product(
            @ColumnName("id") Integer id,
            @ColumnName("name") @Nullable String name,
            @ColumnName("sku") @Nullable String sku,
            @ColumnName("description") @Nullable String description,
            @ColumnName("is_active") @Nullable Boolean is_active,
            @ColumnName("category_id") @Nullable Integer category_id,
            @ColumnName("brand_id") @Nullable Integer brand_id,
            @ColumnName("no_of_views") @Nullable Integer no_of_views,
            @ColumnName("no_of_sold") @Nullable Integer no_of_sold,
            @ColumnName("image_id") @Nullable Integer image_id,
            @ColumnName("price") @Nullable Integer price,
            @ColumnName("stock") @Nullable Integer stock,
            @ColumnName("option_id") @Nullable Integer option_id,
            @ColumnName("category_name") @Nullable String category_name,
            @ColumnName("image_url") @Nullable String image_url,
            @ColumnName("height") @Nullable Integer height,
            @ColumnName("length") @Nullable Integer length,
            @ColumnName("width") @Nullable Integer width,
            @ColumnName("weight") @Nullable Integer weight

    ) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.is_active = is_active;
        this.category_id = category_id;
        this.brand_id = brand_id;
        this.no_of_views = no_of_views;
        this.no_of_sold = no_of_sold;
        this.image_id = image_id;
        this.price = price;
        this.stock = stock;
        this.option_id = option_id;
        this.category_name = category_name;
        this.image_url = image_url;
        this.height = height;
        this.length = length;
        this.width = width;
        this.weight = weight;
    }


    public Product(
            Integer id,
            @Nullable String name,
            @Nullable String sku,
            @Nullable String description,
            @Nullable Boolean is_active,
            @Nullable Integer category_id,
            @Nullable Integer brand_id,
            @Nullable Integer no_of_views,
            @Nullable Integer no_of_sold,
            @Nullable Integer image_id,
            @Nullable Integer price,
            @Nullable Integer stock,
            @Nullable Integer option_id,
            @Nullable String category_name,
            @Nullable String image_url


    ) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.is_active = is_active;
        this.category_id = category_id;
        this.brand_id = brand_id;
        this.no_of_views = no_of_views;
        this.no_of_sold = no_of_sold;
        this.image_id = image_id;
        this.price = price;
        this.stock = stock;
        this.option_id = option_id;
        this.category_name = category_name;
        this.image_url = image_url;
    }


    public Product() {
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getOption_id() {
        return option_id;
    }

    public void setOption_id(Integer option_id) {
        this.option_id = option_id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public Integer getNo_of_sold() {
        return no_of_sold;
    }

    public void setNo_of_sold(Integer no_of_sold) {
        this.no_of_sold = no_of_sold;
    }

    public Integer getNo_of_views() {
        return no_of_views;
    }

    public void setNo_of_views(Integer no_of_views) {
        this.no_of_views = no_of_views;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", description='" + description + '\'' +
                ", is_active=" + is_active +
                ", category_id=" + category_id +
                ", brand_id=" + brand_id +
                ", no_of_views=" + no_of_views +
                ", no_of_sold=" + no_of_sold +
                ", image_id=" + image_id +
                ", price=" + price +
                ", stock=" + stock +
                ", option_id=" + option_id +
                ", category_name='" + category_name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", height=" + height +
                ", length=" + length +
                ", width=" + width +
                ", weight=" + weight +
                '}';
    }
}

