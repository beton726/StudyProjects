package ru.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import vo.Vacancy;

import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    // page=%d - номер страницы, page=0 - первая страница, page=1 - вторая страница и т.д.
    // text=java+%s - то, что вводится в поиск. text=java+QA - введётся java QA.
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36 OPR/70.0.3728.189 (Edition Yx 05)";
    private static final String REFERRER = "no-referrer-when-downgrade";
    private static final String SITE_NAME = "http://hh.ua";

    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> list = new ArrayList<>();

        try {
            for (int i = 0; i < 100; i++) { // страницы
                Document document = getDocument(searchString, i);
                Elements element = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if(element.size() == 0) break;
                System.out.println("Количество страниц: " + element.size());
                for (int j = 0; j < element.size(); j++) { // вакансии
                    Element elem = element.get(j); // на текущей вакансии, далее её парсим и создаём объект

                    Vacancy vacancy = new Vacancy();
                    vacancy.setCity(elem.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-address").text());
                    vacancy.setCompanyName(elem.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-employer").text());
                    vacancy.setSiteName(SITE_NAME);
                    vacancy.setTitle(elem.getElementsByAttributeValueContaining("data-qa", "title").text());
                    vacancy.setUrl(elem.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-title").attr("href"));
                    vacancy.setSalary(elem.getElementsByAttributeValueContaining("data-qa", "compensation").text().length()==0?"":elem.getElementsByAttributeValueContaining("data-qa", "compensation").text());
                    list.add(vacancy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent(USER_AGENT)
                .referrer(REFERRER)
                .get();
    }
}