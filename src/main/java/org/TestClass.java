package org;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Andrii Kolomakin on 26.02.2017.
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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        BookingUzGovUa book = PageFactory.initElements(driver, BookingUzGovUa.class);
        //-----------------------------------
        book.setUkrVersion();
        Assert.assertEquals(book.getPageTitle(driver),"Покупка - Онлайн резервування та придбання квиткiв - Укрзалізниця");
        book.setFromStation("Київ");
        book.setTillStation("Жмеринка-Пас.");
        book.setDepDate(driver,"16.03.2017");
        book.searchForTrain();
        if(!book.checkIfThereAreTrains(driver)){
            System.out.println("There are no trains available :( ");
            System.exit(1);
        }
        book.selectATrainWithFreePlaces();
        book.selectAFreePlace();
        book.enterFioAndSubmit("Тимошенко","Юлія");
        String[] ticketData = book.getTicketData();
        System.out.println("Номер поезда: " + ticketData[0]);
        System.out.println("Номер вагона: " + ticketData[1]);
        System.out.println("Номер места: " + ticketData[2]);
        System.out.println("Цена билета: " + ticketData[3] + "грн");
        book.cancelOrder();
        book.closeBrowser(driver);
        System.out.println("Заказ был отменен успешно.");
    }
}
