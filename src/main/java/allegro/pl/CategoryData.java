package allegro.pl;

import java.util.List;

public class CategoryData {
    private String categoryUrl;
    private List<String> productsUrl;

    public CategoryData(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public List<String> getProductsUrl() {
        return productsUrl;
    }

    public void setProductsUrl(List<String> productsUrl) {
        this.productsUrl = productsUrl;
    }
}
