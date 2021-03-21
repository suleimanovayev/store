package allegro.pl;

import allegro.pl.entity.Product;
import org.jsoup.nodes.Document;

public class ProductFactory {
    private final ProductParser productParser;

    public ProductFactory(ProductParser productParser) {
        this.productParser = productParser;
    }

    public Product createProduct(String url) {
        Document document = JsoupConnection.connect(url);

        return new Product.Builder()
                .setName(productParser.resolveName(document))
                .setFullPrice(productParser.resolveFullPrice(document))
                .setDeliveryPrice(productParser.resolveDeliveryPrice(document))
                .setStockPrice(productParser.resolveStockPrice(document))
                .setRating(productParser.resolveRating(document))
                .setPurchased(productParser.resolvePurchased(document))
                .setPhotoUrl(productParser.resolvePhotosUrl(document))
                .setUrl(url)
                .build();
    }
}
