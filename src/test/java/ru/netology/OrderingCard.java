package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;

public class OrderingCard {

    LocalDate today =  LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Test
    void validityOfTheForm() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate futureDay = today.plusDays(4);
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").setValue(futureDay.format(formatter));
        $("[data-test-id=\"name\"] .input__control").setValue("Иванов Иван");
        $("[data-test-id='phone'] .input__control").setValue("+79198935892");
        $("[data-test-id='agreement']").click();
        $(".button_view_extra ").click();
        $x("//*[contains(text(),\"Встреча успешно забронирована на \")]").should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void checkingTelaphoneNumber() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate futureDay = today.plusDays(4);
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").setValue(futureDay.format(formatter));
        $("[data-test-id=\"name\"] .input__control").setValue("Иванов Иван");
        $("[data-test-id='phone'] .input__control").setValue("+7919893589");
        $("[data-test-id='agreement']").click();
        $(".button_view_extra ").click();
        $x("//*[contains(text(),\"Телефон указан неверно. Должно быть 11 цифр\")]");
    }

    @Test
    void checkingFIO() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate futureDay = today.plusDays(4);
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").setValue(futureDay.format(formatter));
        $("[data-test-id=\"name\"] .input__control").setValue("Ivanov Ivan");
        $("[data-test-id='phone'] .input__control").setValue("+79198935892");
        $("[data-test-id='agreement']").click();
        $(".button_view_extra ").click();
        $x("//*[contains(text(),\"Имя и Фамилия указаные неверно\")]");
    }

    @Test
    void checkingCity() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").setValue("Moskva");
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate futureDay = today.plusDays(4);
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").setValue(futureDay.format(formatter));
        $("[data-test-id=\"name\"] .input__control").setValue("Иванов Иван");
        $("[data-test-id='phone'] .input__control").setValue("+7919893589");
        $("[data-test-id='agreement']").click();
        $(".button_view_extra ").click();
        $x("//*[contains(text(),\"Доставка в выбранный город недоступна\")]");
    }

    @Test
    void checkingDate() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate futureDay = today.plusDays(4);
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").setValue(today.format(formatter));
        $("[data-test-id=\"name\"] .input__control").setValue("Иванов Иван");
        $("[data-test-id='phone'] .input__control").setValue("+7919893589");
        $("[data-test-id='agreement']").click();
        $(".button_view_extra ").click();
        $x("//*[contains(text(),\"Заказ на выбранную дату невозможен\")]");
    }

    @Test
    void checkingEmptyDate() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate futureDay = today.plusDays(4);
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").setValue("");
        $("[data-test-id=\"name\"] .input__control").setValue("Иванов Иван");
        $("[data-test-id='phone'] .input__control").setValue("+7919893589");
        $("[data-test-id='agreement']").click();
        $(".button_view_extra ").click();
        $x("//*[contains(text(),\"Неверно введена дата\")]");
    }
}

//java -jar "D:\IDEA\Projects\OrderingCard\artifacts\app-card-delivery.jar"