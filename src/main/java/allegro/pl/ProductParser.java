package allegro.pl;

import org.jsoup.nodes.Document;

import java.util.List;

public interface ProductParser {

    String resolveName(Document document);

    double resolveFullPrice(Document document);

    double resolveStockPrice(Document document);

    double resolveDeliveryPrice(Document document);

    int resolvePurchased(Document document);

    String resolveRating(Document document);

    List<String> resolvePhotosUrl(Document document);
}
