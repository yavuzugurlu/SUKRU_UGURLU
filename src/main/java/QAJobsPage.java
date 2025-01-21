import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class QAJobsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final int TIMEOUT = 10;
    private static final int PAGE_LOAD_TIMEOUT = 30; // Sayfa yükleme için 30 saniye

    public QAJobsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
    }

    private WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void scrollPage(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + pixels + ");");
    }

    public QAJobsPage clickSeeQAJobsButton() {
        try {
            driver.get("https://useinsider.com/careers/quality-assurance/");

            // Cookies Accept All button
            waitForElementToBeClickable(By.id("wt-cli-accept-all-btn")).click();

            // all QA jobs button
            waitForElementToBeClickable(By.xpath("//a[contains(text(), 'See all QA jobs')]")).click();

            scrollPage(150);
            Thread.sleep(5000);
            //  "Quality Assurance" option otomatik geldiği için bekliyoruz
            WebElement select2Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("select2-filter-by-department-container")));
            String initialTitle = select2Element.getAttribute("title");
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(select2Element, "title", initialTitle)));

            // Select filters
            selectDropdownByVisibleText(By.id("filter-by-location"), "Istanbul, Turkey");
            selectDropdownByVisibleText(By.id("filter-by-department"), "Quality Assurance");


            System.out.println("Location: Istanbul, Turkey ve Department: Quality Assurance filtreleri uygulandı!");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        } catch (Exception e) {
            System.out.println("Error in clickSeeQAJobsButton: " + e.getMessage());
        }
        return this;
    }

    public boolean areJobsListed() {
        boolean check = true;
        try {
            // Sayfayı kaydırmaya devam et
            scrollPage(250);

            // Filtre sonrası iş ilanlarının görünür hale gelmesini bekleyin
            Thread.sleep(3000);
            // Filtreler sonrasında bulunan iş ilanlarını alıyoruz
            List<WebElement> jobItems = driver.findElements(By.cssSelector("#jobs-list .position-list-item"));
            System.out.println("Toplam kriterlere göre bulunan ilan sayısı : " + jobItems.size());

            // Eğer hiç ilan yoksa
            if (jobItems.isEmpty()) {
                System.out.println("No jobs found for the selected filters.");
                return false;
            }

            // İş ilanlarını kontrol et
            for (WebElement jobItem : jobItems) {
                try {
                    String jobTitle = jobItem.findElement(By.cssSelector(".position-title")).getText();
                    String department = jobItem.findElement(By.cssSelector(".position-department")).getText();
                    String location = jobItem.findElement(By.cssSelector(".position-location")).getText();

                    // Filtrelere uygun olup olmadığını kontrol et
                    if (department.equals("Quality Assurance") && location.equals("Istanbul, Turkey") && jobTitle.contains("Quality Assurance")) {
                        System.out.println("İlgili alan veya alanları içeriyor!");
                        System.out.println("Job Title: " + jobTitle);
                        System.out.println("Department: " + department);
                        System.out.println("Location: " + location);
                    } else {
                        System.out.println("İlgili alan veya alanları içermiyor");
                        System.out.println("Job Title: " + jobTitle);
                        System.out.println("Department: " + department);
                        System.out.println("Location: " + location);
                        check = false;
                    }
                    System.out.println("---------------------------");
                } catch (StaleElementReferenceException e) {
                    // Eğer StaleElementReferenceException alırsak, öğeyi tekrar bulmaya çalışalım
                    System.out.println("Stale Element Exception caught. Retrying...");
                    jobItems = driver.findElements(By.cssSelector("#jobs-list .position-list-item")); // Tekrar öğeleri al
                }
            }
        } catch (Exception e) {
            System.out.println("Error in areJobsListed: " + e.getMessage());
            check = false;
        }
        return check;
    }

    private void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdown = waitForElementToBeClickable(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    public void openJob() {
        try {
            scrollPage(250);
            Thread.sleep(3000);

            WebElement qaLink = driver.findElement(By.cssSelector("#jobs-list .position-list-item"));
            qaLink.click();

            waitForElementToBeClickable(By.xpath("//a[text()='View Role']")).click();

            String mainWindowHandle = driver.getWindowHandle();

            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            switchToNewWindow(mainWindowHandle);

        } catch (Exception e) {
            System.out.println("Error in openJob: " + e.getMessage());
        }
    }

    private void switchToNewWindow(String mainWindowHandle) {
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
}
