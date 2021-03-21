package allegro.pl;

import allegro.pl.entity.Product;
import allegro.pl.thread.CategoryThread;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MAin {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        Document document = JsoupConnection.connect(Constants.MAIN_URL);
        Elements categoryElements = document.select(Constants.SELECT_CATEGORIES);

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<CategoryData>> categoriesFuture = new ArrayList<>();
        List<Future<Product>> productsFuture = new ArrayList<>();
        List<Product> products = new LinkedList<>();
        ProductFactory replace = new ProductFactory(new AllegroProductParser());

        categoryElements.stream().limit(1).forEach(category -> {
            String categoryLink = ScraperUtil.selectLink(category);
            categoriesFuture.add(executorService.submit(new CategoryThread(categoryLink)));
        });

        for (Future<CategoryData> categoryFuture : categoriesFuture) {
            CategoryData categoryData = categoryFuture.get();
            List<String> productsLinks = categoryData.getProductsUrl();

            for (String productLink : productsLinks) {
                Future<Product> productFuture = executorService.submit(new Callable<Product>() {
                    @Override
                    public Product call() throws Exception {
                        return replace.createProduct(productLink);
                    }
                });
                productsFuture.add(productFuture);
            }
        }

        for (Future<Product> productFuture : productsFuture) {
            Product product = productFuture.get();
            System.out.println(product);
            products.add(product);
        }

        CsvWriter.write(products);

        executorService.shutdown();



//        Replace replace = new Replace(new AllegroProductParser());
//      Product product =  replace.createProduct("https://allegro.pl/oferta/ostrze-philips-oneblade-qp220-do-qp2520-qp2620-10092806436?mode=filterReviews&filterRating=&filterSegment=&filterSeasons=&filterLang=ALL");
//        System.out.println(product);
    }
}
