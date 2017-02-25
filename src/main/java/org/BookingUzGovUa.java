package org;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Andrii Kolomakin on 25.02.2017.
 */
public class BookingUzGovUa {


    @FindBy(xpath = "//a[@href='http://booking.uz.gov.ua/'][@title='Українська']/b")
    private WebElement ukrVersion;

    @FindBy(xpath = "//div[@class='stations']/div[@id='station_from']/input")
    private WebElement stationFromInputField;
    @FindBy(xpath = "//div[@class='stations']/div[@id='station_from']/div[@class='autosuggest']/div[@title='Київ']")
    private WebElement stationFromAutosuggestion;

    @FindBy(xpath = "//div[@class='stations']/div[@id='station_till']/input")
    private WebElement stationTillInputField;
    @FindBy(xpath = "//div[@class='stations']/div[@id='station_till']/div[@class='autosuggest']/div[@title='Жмеринка-Пас.']")
    private WebElement stationTillAutosuggestion;

    @FindBy(xpath = "//form[@name='train_search_form']//div[@class='options']//input[@id='date_dep']")
    private WebElement departureDateInputField;

    @FindBy(xpath = "//div[@id='middle']/div[1]")
    private WebElement pageBody;

    @FindBy(xpath = "//form[@name='train_search_form']//button[@name='search']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@id='ts_res_not_found']")
    private WebElement noTrainsErrorMessage;

    @FindBy(xpath = "//div[@id='ts_res']//table/tbody/tr[1]/td[@class='place']/div[1]/b[1]")
    private WebElement placeTypeFreePlacesCounter;
    @FindBy(xpath = "//div[@id='ts_res']//table/tbody/tr[1]/td[@class='place']/div[1]/button")
    private WebElement trainShowPlaces;
    @FindBy(xpath = "//div[@id='ts_res']//table/tbody/tr[1]/td[@class='place']/div[2]/b[1]")
    private WebElement placeTypeFreePlacesCounter2;
    @FindBy(xpath = "//div[@id='ts_res']//table/tbody/tr[1]/td[@class='place']/div[2]/button")
    private WebElement trainShowPlaces2;

    private WebElement freePlace;
    private WebElement occPalce;

    private WebElement passangerFNameInout;
    private WebElement passangerLNameInput;

    private WebElement toCartButton;

    private WebElement trainNumber;
    private WebElement wagonNumber;
    private WebElement placeNumber;
    private WebElement price;

    private WebElement cancelOrederButton;

    //-----------------------------------------------------------------------------------------------------------

    void setUkrVersion(){
        ukrVersion.click();
    }

    String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    void setFromStation(){
        stationFromInputField.clear();
        stationFromInputField.sendKeys("Київ");
        stationFromInputField.click();
        stationFromAutosuggestion.click();
    }

    void setTillStation(){
        stationTillInputField.clear();
        stationTillInputField.sendKeys("Жмеринка-Пас.");
        stationTillInputField.click();
        stationTillAutosuggestion.click();
    }

    void setDepDate(WebDriver driver){
        departureDateInputField.clear();
        departureDateInputField.sendKeys("16.03.2017");
        pageBody.click();
        synchronized (driver){
            try {
                driver.wait(1000);
            } catch (InterruptedException e) {}
        }
    }

    void searchForTrain(){
        searchButton.click();
    }

    boolean checkIfThereAreTrains(WebDriver driver){
        synchronized (driver){
            try {
                driver.wait(3000);
            } catch (InterruptedException e) {}
        }
        try{
            noTrainsErrorMessage.isDisplayed();
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    void selectATrainWithFreePlaces(){
        try {
            if (Integer.parseInt(placeTypeFreePlacesCounter.getText()) == 0) {
                if (Integer.parseInt(placeTypeFreePlacesCounter2.getText()) == 0){
                    System.out.println("There are no free places in this train, go to the next one");
                    System.exit(1);
                }
                trainShowPlaces2.click();
            }
            trainShowPlaces.click();
        } catch (NoSuchElementException e){}
    }

}
