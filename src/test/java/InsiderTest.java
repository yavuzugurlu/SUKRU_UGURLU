import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InsiderTest {

    private WebDriver driver;
    private HomePage homePage;
    private QAJobsPage qaJobsPage;
    private JobApplyPage jobApplyPage;
    private CareersPage careersPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        qaJobsPage = new QAJobsPage(driver);
        jobApplyPage = new JobApplyPage(driver);
    }

    @Test
    public void testCareerPageAndJobApplication() {
        // Case-1:
        System.out.println("Case-1: ");
        homePage.visitHomePage();

        // Case-2:
        System.out.println("Case-2: ");
        careersPage = homePage.clickCompanyMenu();
        Assertions.assertTrue(careersPage.isLocationsBlockVisible(), "Locations block is not visible");
        Assertions.assertTrue(careersPage.isTeamsBlockVisible(), "Teams block is not visible");
        Assertions.assertTrue(careersPage.isLifeAtInsiderBlockVisible(), "Life at Insider block is not visible");

        // Case-3:
        System.out.println("Case-3: ");
        qaJobsPage.clickSeeQAJobsButton();

        // Case-4:
        System.out.println("Case-4:");
        qaJobsPage.areJobsListed();

        // Case-5:
        System.out.println("Case-5:");
        qaJobsPage.openJob();
        jobApplyPage.visitJobApplyPage();
    }

    @AfterEach
    public void tearDown() {
        // Test sonrası tarayıcıyı kapat
        if (driver != null) {
            driver.quit();
        }
    }
}
