import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JobApplyPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public JobApplyPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // 10 saniye bekleme süresi
    }

    public void visitJobApplyPage() {
        try {
            // Sayfanın URL'sini kontrol et
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);

            // Sayfanın tam olarak yüklendiğinden emin olmak için öğeyi bekle
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.sort-by-team.posting-category.medium-category-label.capitalize-labels.department")
            ));

            // Öğenin metnini kontrol et
            if (element.getText().contains("Quality Assurance")) {
                System.out.println("Doğru bir şekilde iş ilanına yönlendirildi!");
            } else {
                System.out.println("İş ilanına yönlendirilemedi!");
            }
            try {
                // Butonun üzerine tıklamadan önce görünür olduğundan emin olalım
                WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.postings-btn.template-btn-submit.shamrock")));

                // Butona tıklama işlemi
                applyButton.click();
                System.out.println("Apply for this job butonuna tıklandı!");
            } catch (Exception e) {
                System.out.println("Butona tıklanırken bir hata oluştu: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Hata oluştu: " + e.getMessage());
            e.printStackTrace();  // Hata detaylarını ekrana yazdır
        }
    }
}
