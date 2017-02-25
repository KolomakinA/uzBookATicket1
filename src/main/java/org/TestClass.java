package org;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by kolom on 26.02.2017.
 */
public class TestClass {

    public static void main(String[] args) {
        TestClass t = new TestClass();
        t.test();
    }

    private void test(){
        WebDriver driver = new ChromeDriver();
        driver.get("http://booking.uz.gov.ua/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        BookingUzGovUa book = PageFactory.initElements(driver, BookingUzGovUa.class);
        //-----------------------------------
        book.setUkrVersion();
        Assert.assertEquals(book.getPageTitle(driver),"Покупка - Онлайн резервування та придбання квиткiв - Укрзалізниця");
        book.setFromStation();
        book.setTillStation();
        book.setDepDate(driver);
        book.searchForTrain();
        if(!book.checkIfThereAreTrains(driver)){
            System.out.println("There are no trains available :( ");
            System.exit(1);
        }
        book.selectATrainWithFreePlaces();
        //-----------------------------------
        //driver.quit();
    }
}
