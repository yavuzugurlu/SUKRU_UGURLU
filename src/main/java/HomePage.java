import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Sayfayı ziyaret et ve URL doğrula
    public void visitHomePage() {
        driver.get("https://useinsider.com/");
        verifyPageUrl("https://useinsider.com/");
    }

    // URL doğrulama metodu
    private void verifyPageUrl(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals(expectedUrl)) {
            System.out.println(expectedUrl + " doğru bir şekilde açıldı!");
        } else {
            System.out.println(expectedUrl + " URL'si beklenen ile uyuşmuyor.");
        }
    }

    // Şirket menüsüne tıkla ve Kariyerler sayfasına git
    public CareersPage clickCompanyMenu() {
        openHomePage(); // Ana sayfayı aç

        // "Company" menüsünü bul ve tıkla
        WebElement companyMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//nav//a[contains(text(),'Company')]")));
        companyMenu.click();


        // Kariyerler linkine tıkla
        WebElement careersLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='dropdown-sub' and text()='Careers']")));
        careersLink.click();

        // Kariyer sayfasına geçişi doğrula
        wait.until(ExpectedConditions.urlContains("careers"));
        System.out.println("Current URL after clicking 'Careers': " + driver.getCurrentUrl());

        return new CareersPage(driver);
    }

    // Ana sayfayı aç
    private void openHomePage() {
        driver.get("https://useinsider.com/");
    }
}
