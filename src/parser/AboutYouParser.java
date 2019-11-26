package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AboutYouParser implements Parser {

    private static final String attribute = "data-test-id";

    public List<Item> parse(String url) {
        List<Item> items = new ArrayList<>();
        int counterRequest = 0;
        int counterItems = 0;

        try {
            Document doc = Jsoup.connect(url).get();
            ++counterRequest;
            Elements elements = doc.getElementsByAttributeValue(attribute, "ProductTile");
            counterItems = elements.size();
            for (Element element : elements) {
                String linkHref = element.attr("href");
                Document item = Jsoup.connect("https://www.aboutyou.de" + linkHref).get();

                Thread.sleep(700); //imitation of behavior like a human being
                                         // normally browsing through the website

                ++counterRequest;
                items.add(parseItem(item));
            }
        } catch (IOException ex) {
            System.out.println("Problems with connection");
        } catch (InterruptedException ex) {
            System.out.println("Unexpected interrupt");
        }

        System.out.println("Amount of triggered HTTP requests: " + counterRequest);
        System.out.println("Amount of extracted products: " + counterItems);

        return items;
    }

    private Item parseItem(Document item) {
        String productName = item.getElementsByAttributeValue(attribute, "ProductName").text();

        String brand = item.getElementsByAttributeValue(attribute, "BrandLogo").attr("alt");

        Elements colorEl = item.getElementsByAttributeValue(attribute, "ColorVariantColorInfo");

        List<String> colors = colorEl.stream().map(color -> color.text()).collect(Collectors.toList());

        Element priceEl = item.getElementsByAttributeValue(attribute, "ProductPriceFormattedBasePrice").first();
        String price = priceEl == null
                ? item.getElementsByAttributeValue(attribute, "FormattedSalePrice").text() : priceEl.text();

        String articleID = item.getElementsByAttributeValue(attribute, "ArticleNumber")
                .text().replaceFirst("Art.-Nr. ", "");

        return new Item(productName, brand, colors, price, articleID);
    }
}
