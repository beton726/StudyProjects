package ru.model;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import vo.Vacancy;

import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.List;

public class HHStrategy implements Strategy {
    // page=%d - номер страницы, page=0 - первая страница, page=1 - вторая страница и т.д.
    // text=java+%s - то, что вводится в поиск. text=java+QA - введётся java QA.
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36 OPR/70.0.3728.189 (Edition Yx 05)";
    private static final String REFERRER = "no-referrer-when-downgrade";

    public List<Vacancy> getVacancies(String searchString) {

        try {
            for (int i = 0; i < 100; i++) {
                Document document = getDocument(searchString, i);
                Elements element = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");

                if(element != null) {
                    for (int j = 0; j < element.size(); j++) {



                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent(USER_AGENT)
                .referrer(REFERRER)
                .get();
    }
}