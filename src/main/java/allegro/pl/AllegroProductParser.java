package allegro.pl;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.substringBetween;

public class AllegroProductParser implements ProductParser {
    private String url;
    private static final String NAME_SELECTOR = "h4[class*=_18vat._9a071_U7GFO]";
    private static final String NAME_SECOND_SELECTOR = "meta[itemprop*=name]";
    private static final String FULL_PRICE_SELECTOR = "._wy136._15mod._1y62o";
    private static final String STOCK_PRICE_SELECTOR = "._9a071_1tOgC >div >span";
    private static final String DELIVERY_PRICE_SELECTOR = "div[class*=_34279_QRn6o]:matches(Dostawa od)";
    private static final String PURCHASED_SELECTOR = "._15mod._1vryf._1t9p2._9a071_1NC9k";
    private static final String RATING_SELECTOR = "meta[itemprop*=ratingValue]";
    private static final String PHOTOS_SELECTOR = "._b8e15_aMJ-v >img";

    @Override
    public String resolveName(Document document) {
        Elements name = document.select(NAME_SELECTOR);
        return isEmpty(name.text()) ? document.select(NAME_SECOND_SELECTOR).attr("content") : name.text();
    }

    @Override
    public double resolveFullPrice(Document document) {
        url = document.baseUri();
        Elements fullPrice = document.select(FULL_PRICE_SELECTOR);
        return convertToDouble(fullPrice);
    }

    @Override
    public double resolveStockPrice(Document document) {
        url = document.baseUri();
        Elements stockPrice = document.select(STOCK_PRICE_SELECTOR);
        return convertToDouble(stockPrice);
    }

    @Override
    public double resolveDeliveryPrice(Document document) {
        url = document.baseUri();
        Elements delivery = document.select(DELIVERY_PRICE_SELECTOR);
        return convertToDouble(delivery);
    }

    @Override
    public int resolvePurchased(Document document) {
        url = document.baseUri();
        Elements purchased = document.select(PURCHASED_SELECTOR);
        return isNull(purchased) ? 0 : Integer.parseInt(purchased.text().replaceAll("[^0-9]", ""));
    }

    @Override
    public String resolveRating(Document document) {
        return document.select(RATING_SELECTOR).attr("content");
    }

    @Override
    public List<String> resolvePhotosUrl(Document document) {

        return document.select(PHOTOS_SELECTOR)
                .stream()
                .map(ScraperUtil::selectLink)
                .collect(Collectors.toList());
    }

    private double convertToDouble(Elements number) {
        if (isNull(number)) {
            return 0.0;
        }
        String result = number.text().replaceFirst(",", ".");
        result = result.replaceAll("[^.0-9]", "");
        return isEmpty(result) ? 0.0 : Double.parseDouble(result);
    }
}
