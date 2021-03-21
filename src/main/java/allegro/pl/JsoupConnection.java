package allegro.pl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupConnection {
    private static final Logger log = LogManager.getLogger(JsoupConnection.class);

    public static Document connect(String url) {
        Document document = null;
        try {
            Thread.sleep(1000);

            document = Jsoup.connect(url).get();
        } catch (Exception e) {
            log.error("Error to connect to url: {}", url);
        }
        return document;
    }

}
