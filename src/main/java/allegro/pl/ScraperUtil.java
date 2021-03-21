package allegro.pl;

import org.jsoup.nodes.Element;

import java.net.URL;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

public class ScraperUtil {

    public static String selectLink(Element element) {
        String link = element.attr("href");
        link = isEmpty(link) ? element.attr("src") : link;

        try {
            new URL(link).toURI();
        } catch (Exception e) {
            link = substringBeforeLast(Constants.MAIN_URL, "/") + link;
        }
        return link;
    }
}
