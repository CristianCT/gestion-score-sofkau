package co.com.sofka.wsscore.usecases;

import co.com.sofka.wsscore.domain.category.Category;
import co.com.sofka.wsscore.domain.category.commands.AssignProductCommand;
import co.com.sofka.wsscore.domain.generic.DomainEvent;
import co.com.sofka.wsscore.domain.generic.EventStoreRepository;
import org.jboss.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

@Dependent
public class ExtractProductUseCase implements Function<AssignProductCommand, List<DomainEvent>> {

    private static final String URL_BASE = "https://www.dafiti.com.co/";
    private final EventStoreRepository repository;

    private static final Logger LOG = Logger.getLogger("EXTRACT");

    public ExtractProductUseCase(EventStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AssignProductCommand command) {

        var category = Category.from(
                command.getCategoryId(),
                repository.getEventsBy("category", command.getCategoryId())
        );

        Document baseDocument = getHtmlDocument(URL_BASE + category.gender() + "/" + category.title());
        Element pagesString = baseDocument.select("body > #page #content > .l-pageWrapper > .mtm > .l-main > .pbm > .rfloat > .searchResult > span").get(1);
        int pages = Integer.parseInt(pagesString.text());

        IntStream.range(1, pages).forEach(page -> {
            Document document = getHtmlDocument(URL_BASE + category.gender() + "/" + category.title() + "/?page=" + page);
            Elements products = document.select("body > #page #content > .l-pageWrapper > .mtm > .l-main > .catalog-products > section > #productsCatalog li");
            addProducts(products, category);
        });

        return category.getUncommittedChanges();
    }

    private void addProducts(Elements products, Category category){
        products.stream()
            .filter(product -> !product.attr("id").isBlank())
            .forEach(product -> {
                Element productInfo = product.select(".itm-product-main-info a").get(0);
                String id = product.id();
                String name = productInfo.getElementsByClass("itm-brand").text();
                String description = productInfo.getElementsByClass("itm-title").text();
                Double price = Double.parseDouble(productInfo.getElementsByClass("itm-priceBox").attr("data-price"));
                String link = productInfo.attr("href");
                String image = productInfo.select(".lazyImage > .slides li > img").attr("data-src");

                category.assignProduct(id, name,description, price, link, image);
            });
    }

    private Connection.Response getResponse(String path) throws IOException {
        return Jsoup.connect(path)
                .userAgent("Mozilla/5.0")
                .timeout(100000)
                .method(Connection.Method.POST)
                .followRedirects(true)
                .execute();
    }

    private static Document getHtmlDocument(String url){
        Document document = null;
        try {
            document = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100*1000).get();
        } catch (IOException e) {
            LOG.error("[ERROR]: " + e.getMessage());
            throw new ExtractProductException();
        }
        return document;
    }

    private static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}