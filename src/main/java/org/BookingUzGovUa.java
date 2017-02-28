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
    @FindBy(xpath = "//div[@class='stations']/div[@id='station_from']/div[@class='autosuggest']//li[1]")
    private WebElement stationFromAutosuggestion;

    @FindBy(xpath = "//div[@class='stations']/div[@id='station_till']/input")
    private WebElement stationTillInputField;
    @FindBy(xpath = "//div[@class='stations']/div[@id='station_till']/div[@class='autosuggest']//li[1]")
    private WebElement stationTillAutosuggestion;

    @FindBy(xpath = "//form[@name='train_search_form']//div[@class='options']//input[@id='date_dep']")
    private WebElement departureDateInputField;

    @FindBy(xpath = "//div[@class='main-news'][1]")
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

    @FindBy(xpath = "//div[@class='vToolsPopup coachScheme']//div[@class='floor floor-1']/div[@class='up place fr']")
    private WebElement freePlace;

    @FindBy(xpath = "//div[@class='fio']//input[@class='firstname']")
    private WebElement passangerFNameInput;
    @FindBy(xpath = "//div[@class='fio']//input[@class='lastname']")
    private WebElement passangerLNameInput;
    @FindBy(xpath = "//button[@class='complex_btn']/span")
    private WebElement toCartButton;

    @FindBy(xpath = "//table[@id='cart_table']//td[@class='data']/div/b[1]")
    private WebElement trainNumber;
    @FindBy(xpath = "//table[@id='cart_table']//td[@class='data']/div/b[2]")
    private WebElement wagonNumber;
    @FindBy(xpath = "//table[@id='cart_table']//td[@class='data']/div/b[3]")
    private WebElement placeNumber;
    @FindBy(xpath = "//table[@id='cart_table']//td[@class='price']")
    private WebElement price;

    @FindBy(xpath = "//table[@id='cart_table']//td[@class='cart-btn']")
    private WebElement cancelOrderRow;
    @FindBy(xpath = "//table[@id='cart_table']//td[@class='cart-btn']/a")
    private WebElement cancelOrederButton;
    @FindBy(xpath = "//div[@class='vToolsPopup ']/center[@class='vToolsPopupToolbar']/button[1]")
    private WebElement cancelingConfirmationOk;

    //-----------------------------------------------------------------------------------------------------------

    void setUkrVersion(){
        ukrVersion.click();
    }

    String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    void setFromStation(String from){
        stationFromInputField.clear();
        stationFromInputField.sendKeys(from);
        stationFromInputField.click();
        stationFromAutosuggestion.click();
    }

    void setTillStation(String till){
        stationTillInputField.clear();
        stationTillInputField.sendKeys(till);
        stationTillInputField.click();
        stationTillAutosuggestion.click();
    }

    void setDepDate(WebDriver driver, String date){
        departureDateInputField.clear();
        departureDateInputField.sendKeys(date);
        pageBody.click();
//        synchronized (driver){
//            try {
//                driver.wait(1000);
//            } catch (InterruptedException e) {}
//        }
    }

    void searchForTrain(){
        searchButton.click();
    }

    boolean checkIfThereAreTrains(WebDriver driver){
//        synchronized (driver){
//            try {
//                driver.wait(3000);
//            } catch (InterruptedException e) {}
//        }
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
                else trainShowPlaces2.click();
            }
            else trainShowPlaces.click();
        } catch (NoSuchElementException e){}
    }

    void selectAFreePlace(){
        freePlace.click();
    }

    void enterFioAndSubmit(String lName, String fName){
        passangerLNameInput.sendKeys(lName);
        passangerFNameInput.sendKeys(fName);
        toCartButton.click();
    }

    String[] getTicketData(){
        String[] arr = {trainNumber.getText(),wagonNumber.getText(),placeNumber.getText(),price.getText()};
        return arr;
    }

    void cancelOrder(){
        cancelOrderRow.click();
        cancelOrederButton.click();
        cancelingConfirmationOk.click();
    }

    void closeBrowser(WebDriver driver){
        driver.quit();
    }

}
