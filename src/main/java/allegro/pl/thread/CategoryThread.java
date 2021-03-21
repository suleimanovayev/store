package allegro.pl.thread;

import allegro.pl.CategoryData;
import allegro.pl.Constants;
import allegro.pl.JsoupConnection;
import allegro.pl.ScraperUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class CategoryThread implements Callable<CategoryData> {
    private final String categoryUrl;

    public CategoryThread(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    @Override
    public CategoryData call() {
        CategoryData categoryData = new CategoryData(categoryUrl);
        Document document = JsoupConnection.connect(categoryUrl);

        Elements products =document.select(Constants.SELECT_PRODUCTS) ;

        List<String> productsUrls = products.stream()
                .map(ScraperUtil::selectLink)
                .collect(Collectors.toList());

        categoryData.setProductsUrl(productsUrls);
        return categoryData;
    }
}
