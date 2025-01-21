import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class InsiderTest {
    WebDriver driver = new ChromeDriver();
    HomePage homePage = new HomePage(driver);
    QAJobsPage qaJobsPage=new QAJobsPage(driver);
    JobApplyPage jobApplyPage=new JobApplyPage(driver);
    private Assertions Assert;

    @Test
    public void inTest() {
        //Test-1
        System.out.println("Case-1\n");
        homePage.visitHomePage();
        //Test-2
        System.out.println("Case-2\n");
        CareersPage careersPage2 = homePage.clickCompanyMenu();
        Assert.assertTrue(careersPage2.isLocationsBlockVisible());
        Assert.assertTrue(careersPage2.isTeamsBlockVisible());
        Assert.assertTrue(careersPage2.isLifeAtInsiderBlockVisible());
        //Test-3
        System.out.println("Case-3\n");
        qaJobsPage.clickSeeQAJobsButton();
        //Test-4
        System.out.println("Case-4\n");
        qaJobsPage.areJobsListed();
        //Test-5
        System.out.println("Case-5\n");
        qaJobsPage.openJob();
        jobApplyPage.visitJobApplyPage();
    }
}
