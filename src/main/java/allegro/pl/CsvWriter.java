package allegro.pl;

import allegro.pl.entity.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

public class CsvWriter {

    public static void write(List<Product> products) throws IOException {

        StringJoiner joiner = new StringJoiner(",");

        FileWriter csvWriter = new FileWriter("product.csv");
        csvWriter.append("name");
        csvWriter.append(",");
        csvWriter.append("fullPrice");
        csvWriter.append(",");
        csvWriter.append("stockPrice");
        csvWriter.append(",");
        csvWriter.append("deliveryPrice");
        csvWriter.append(",");
        csvWriter.append("purchased");
        csvWriter.append(",");
        csvWriter.append("rating");
        csvWriter.append(",");
        csvWriter.append("photoUrl");
        csvWriter.append("\n");

        for (Product product : products) {

            joiner.add(product.getName())
                    .add(String.valueOf(product.getFullPrice()))
                    .add(String.valueOf(product.getStockPrice()))
                    .add(String.valueOf(product.getDeliveryPrice()))
                    .add(String.valueOf(product.getPurchased()))
                    .add(product.getRating())
                    .add(product.getPhotoUrl().isEmpty() ? "" : product.getPhotoUrl().get(0));

            csvWriter.append(joiner.toString());
        }

        csvWriter.flush();
        csvWriter.close();
    }
}
