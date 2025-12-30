package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import java.util.List;
public class ProductDTO {
   private Integer id;
    private  String name;
    private String sku;
    private String description;
    private  Boolean is_active;
    private Integer category_id;
    private Integer brand_id;
    private  Integer no_of_views;
    private Integer no_of_sold;
    private Integer image_id;
    private Integer price;  // option
    private Integer stock;  //option
    private Integer option_id;
    private  String category_name;
    private  String image_url;
    private List<Variant> variants;
    Integer height;
    Integer length;
    Integer width;
    Integer weight;

    public ProductDTO(Product product, List<Variant> variants) {
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
        this.variants = variants;
        this.height = height;
        this.length = length;
        this.width = width;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getNo_of_views() {
        return no_of_views;
    }

    public void setNo_of_views(Integer no_of_views) {
        this.no_of_views = no_of_views;
    }

    public Integer getNo_of_sold() {
        return no_of_sold;
    }

    public void setNo_of_sold(Integer no_of_sold) {
        this.no_of_sold = no_of_sold;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getOption_id() {
        return option_id;
    }

    public void setOption_id(Integer option_id) {
        this.option_id = option_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
