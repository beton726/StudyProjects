package ru.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {

    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    // private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:66.0)";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> listVacancy = new ArrayList<>();
        String siteName = "https://moikrug.ru";
        int pageNumber = 0;

        String url = String.format(URL_FORMAT, searchString, pageNumber);

        try {

            Document doc;

            while (true) {
                doc = getDocument(searchString, pageNumber++);

                Elements elements = doc.getElementsByAttributeValue("class", "job");
                elements.addAll( doc.getElementsByAttributeValue("class", "job marked"));

                if (elements.size() != 0) {
                    for (Element element : elements) {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(element.select("div[class=title]").text().trim());

                        vacancy.setSalary(element.select("div[class=salary]").text().trim());
                        vacancy.setCity(element.select("span[class=location]").text().trim());
                        vacancy.setCompanyName(element.select("span[class=company_name]").text().trim());
                        vacancy.setSiteName(siteName);
                        vacancy.setUrl(siteName + element.select("div[class=title]").first().getElementsByTag("a").attr("href").trim());

                        listVacancy.add(vacancy);
                    }
                }
                else break;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return listVacancy;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent(USER_AGENT)
                .referrer("")
                .get();
    }

}