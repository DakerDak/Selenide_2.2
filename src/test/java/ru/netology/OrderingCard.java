package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class OrderingCard {

    String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    String planningDate = generateDate(4, "dd.MM.yyyy");

    @BeforeEach
    public void openForm() {
        open("http://localhost:9999");
    }

    @Test
    void validityOfTheForm() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иванов Иван");
        $("[data-test-id='phone'] .input__control").setValue("+79198935892");
        $("[data-test-id='agreement']").click();
        $(".button_view_extra ").click();
//        $x("//*[contains(text(),\"Встреча успешно забронирована на \")]").should(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    @Test
    void checkingTelaphoneNumber() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иванов Иван");
        $("[data-test-id='phone'] .input__control").setValue("+7919893589");
        $("[data-test-id='agreement']").click();
        $(".button_view_extra ").click();
        $x("//*[contains(text(),\"Телефон указан неверно. Должно быть 11 цифр\")]").shouldBe(Condition.visible);
    }

    @Test
    void checkingFIO() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Ivanov Ivan");
        $("[data-test-id='phone'] .input__control").setValue("+79198935892");
        $("[data-test-id='agreement']").click();
        $(".button_view_extra ").click();
        $x("//*[contains(text(),\"Имя и Фамилия указаные неверно\")]").shouldBe(Condition.visible);
    }

    @Test
    void checkingCity() {
        $("[data-test-id=\"city\"] .input__control").setValue("Moskva");
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иванов Иван");
        $("[data-test-id='phone'] .input__control").setValue("+7919893589");
        $("[data-test-id='agreement']").click();
        $(".button_view_extra ").click();
        $x("//*[contains(text(),\"Доставка в выбранный город недоступна\")]").shouldBe(Condition.visible);
    }

    @Test
    void checkingDate() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").setValue("27.11.2022");
        $("[data-test-id=\"name\"] .input__control").setValue("Иванов Иван");
        $("[data-test-id='phone'] .input__control").setValue("+7919893589");
        $("[data-test-id='agreement']").click();
        $(".button_view_extra ").click();
        $x("//*[contains(text(),\"Заказ на выбранную дату невозможен\")]").shouldBe(Condition.visible);
    }

    @Test
    void checkingEmptyDate() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=\"date\"] [placeholder='Дата встречи']").setValue("");
        $("[data-test-id=\"name\"] .input__control").setValue("Иванов Иван");
        $("[data-test-id='phone'] .input__control").setValue("+7919893589");
        $("[data-test-id='agreement']").click();
        $(".button_view_extra ").click();
        $x("//*[contains(text(),\"Неверно введена дата\")]").shouldBe(Condition.visible);
    }

}

//java -jar "D:\IDEA\Projects\OrderingCard\artifacts\app-card-delivery.jar"
