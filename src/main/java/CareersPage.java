import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CareersPage {
    private WebDriver driver;

    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

    // "Our Locations" bloğunun görünürlüğünü kontrol et
    public boolean isLocationsBlockVisible() {
        try {
            WebElement ourLocationsDiv = driver.findElement(By.xpath("//div[contains(@class, 'col-12 col-md-6')]//h3[contains(text(), 'Our Locations')]"));
            if (ourLocationsDiv.isDisplayed()) {
                System.out.println("Our Locations block bulundu ve görünür!");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Our Locations block bulunamadı veya görünür değil!");
        }
        return false;
    }

    // "Find your calling" bloğunun görünürlüğünü kontrol et
    public boolean isTeamsBlockVisible() {
        try {
            WebElement isLifeAtInsiderDiv = driver.findElement(By.xpath("//div[contains(@class, 'col-12 mb-xl-5 py-xl-4 py-2 text-center')]//h3[contains(text(), 'Find your calling')]"));
            if (isLifeAtInsiderDiv.isDisplayed()) {
                System.out.println("Find your calling (Teams) block bulundu ve görünür!");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Find your calling (Teams) block bulunamadı veya görünür değil!");
        }
        return false;
    }

    // "Life at Insider" bloğunun görünürlüğünü kontrol et
    public boolean isLifeAtInsiderBlockVisible() {
        try {
            WebElement isLifeAtInsiderDiv = driver.findElement(By.xpath("//div[contains(@class, 'elementor-widget-container')]//h2[contains(text(), 'Life at Insider')]"));
            if (isLifeAtInsiderDiv.isDisplayed()) {
                System.out.println("Life at Insider block bulundu ve görünür!");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Life at Insider block bulunamadı veya görünür değil!");
        }
        return false;
    }
}
