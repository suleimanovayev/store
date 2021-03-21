package allegro.pl;

public class Constants {
    public static final String MAIN_URL = "https://allegro.pl/strefaokazji";
    public final static String SELECT_CATEGORIES = "div[data-box-name ^=Plytka_]:not(:contains(okazje)) .mpof_ki.mr3m_1";
    public final static String SELECT_PRODUCTS = "h2 >a[class^=_w7z6o]:not([rel])";
}
