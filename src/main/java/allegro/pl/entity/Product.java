package allegro.pl.entity;


import java.util.List;

public class Product {
    private String url;
    private String name;
    private double fullPrice;
    private double stockPrice;
    private double deliveryPrice;
    private int purchased;
    private String rating;
    private List<String> photoUrl;

    public Product() {
    }

    public Product(Builder builder) {
        this.url = builder.url;
        this.name = builder.name;
        this.fullPrice = builder.fullPrice;
        this.stockPrice = builder.stockPrice;
        this.deliveryPrice = builder.deliveryPrice;
        this.purchased = builder.purchased;
        this.rating = builder.rating;
        this.photoUrl = builder.photoUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public int getPurchased() {
        return purchased;
    }

    public String getRating() {
        return rating;
    }

    public List<String> getPhotoUrl() {
        return photoUrl;
    }

    public static class Builder {
        private String url;
        private String name;
        private double fullPrice;
        private double stockPrice;
        private double deliveryPrice;
        private int purchased;
        private String rating;
        private List<String> photoUrl;

        public Builder() {
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setFullPrice(double fullPrice) {
            this.fullPrice = fullPrice;
            return this;
        }

        public Builder setStockPrice(double stockPrice) {
            this.stockPrice = stockPrice;
            return this;
        }

        public Builder setDeliveryPrice(double deliveryPrice) {
            this.deliveryPrice = deliveryPrice;
            return this;
        }

        public Builder setPurchased(int purchased) {
            this.purchased = purchased;
            return this;
        }

        public Builder setRating(String rating) {
            this.rating = rating;
            return this;
        }

        public Builder setPhotoUrl(List<String> photoUrl) {
            this.photoUrl = photoUrl;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
