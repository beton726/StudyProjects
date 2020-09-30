package ru.view;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.controller.Controller;
import vo.Vacancy;

import java.io.*;
import java.util.List;

public class HtmlView implements View {

    private Controller controller;
    private final String filePath = "./" + this.getClass().getPackage().getName().replace('.','/') + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        System.out.println(vacancies.size());
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) throws IOException {
        Document document;

        try {
            document = getDocument();
            Element element = document.getElementsByClass("template").first();
            // Элемент - шаблон, для добавления новой строки в таблицу вакансий.
            Element template = element.clone();
            template.removeAttr("style");
            template.removeClass("template");
            document.select("tr[class=vacancy]").remove();

            for (Vacancy vac : vacancies) {

                Element element1 = template.clone();
                element1.getElementsByClass("city").first().text(vac.getCity());
                element1.getElementsByClass("companyName").first().text(vac.getCompanyName());
                element1.getElementsByClass("salary").first().text(vac.getSalary());

                Element linkElem = element1.getElementsByTag("a").first();
                linkElem.text(vac.getTitle());
                linkElem.attr("href",vac.getUrl());

                element.before(element1.outerHtml());

            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Some exception occurred";
        }

        return document.html();
    }

    private void updateFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(fileName);
        writer.close();
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

}