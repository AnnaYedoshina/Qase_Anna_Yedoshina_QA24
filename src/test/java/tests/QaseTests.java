package tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Configuration.*;

public class QaseTests {
    public static final String USERNAME = "yedoshina.java@gmail.com";
    public static final String PASSWORD = "Anna6178777!";


    @BeforeClass
    public void initialise() {
        baseUrl = "https://app.qase.io";
        headless = false;
        timeout = 20000;
        reportsFolder = "target/reports";
        screenshots = true;
        webdriverLogsEnabled = true;
        open("/login");

    }

    @Test
    public void createNewProjectTest() {
        String projectName = "New project";
        $("[name = email]").shouldBe(enabled).setValue(USERNAME);
        $("[name = password]").should(enabled).setValue(PASSWORD);
        $("button[type = submit]").shouldHave(enabled).click();
        $(By.id("createButton")).shouldBe(visible, Duration.ofSeconds(20)).click();
        $("#project-name").shouldBe(enabled).setValue(projectName);
        $("button[type = submit]").shouldHave(enabled).click();
        $(By.xpath("//a[text() = 'Projects']")).shouldBe(visible).click();
        $$(" .defect-title").shouldHave(size(3)).shouldHave(texts("Demo Project", "ShareLane", "New project")).get(2).click();
        $("#create-case-button").shouldBe(enabled, Duration.ofSeconds(20));

    }

    @Test
    public void createCaseTest() {
        String title = "New testCase";
        String projectName = "New project";
        $("[name = email]").shouldBe(enabled).setValue(USERNAME);
        $("[name = password]").should(enabled).setValue(PASSWORD);
        $("button[type = submit]").shouldHave(enabled).click();
        $(By.id("createButton")).shouldBe(visible, Duration.ofSeconds(20)).click();
        $("#project-name").shouldBe(enabled).setValue(projectName);
        $("button[type = submit]").shouldHave(enabled).click();
        $(By.xpath("//a[text()='Projects']")).click();
        $$(" .defect-title").shouldHave(size(3)).shouldHave(texts("Demo Project", "ShareLane", "New project")).get(2).click();
        $("#create-case-button").shouldBe(enabled, Duration.ofSeconds(20)).click();
        $("#title").shouldBe(enabled).setValue(title);
        $("#save-case").shouldBe(enabled).click();
        $$(".cae34Y").shouldHave(size(1)).shouldHave(texts("NP-1")).get(0).click();
        $(By.xpath("//button[text() = 'General']")).shouldBe(visible);

    }

    @Test
    public void createDefectTest() {
        String defectTitle = "New defect";
        String actualResult = "Failed";
        $("[name = email]").shouldBe(enabled).setValue(USERNAME);
        $("[name = password]").should(enabled).setValue(PASSWORD);
        $("button[type = submit]").shouldHave(enabled).click();
        $$(" .defect-title").shouldHave(size(3)).shouldHave(texts("Demo Project", "ShareLane", "New project")).get(2).click();
        $(By.xpath("//div/a[@title='Defects']")).shouldBe(enabled).click();
        $(By.xpath("//a[@class = 'btn btn-primary']")).shouldBe(enabled).click();
        $("#title").shouldBe(enabled).setValue(defectTitle);
        $(By.xpath("//p[@class='gYZSEd']")).shouldBe(enabled).setValue(actualResult);
        $(By.xpath("//button[@type = 'submit']")).shouldBe(enabled).click();
        $$(By.xpath("//tr[@class='project-row']")).shouldHave(size(1)).shouldHave(texts("New defect")).get(0).click();
        $(By.xpath("//a[@title='Edit defect']")).shouldBe(enabled);


    }
}
