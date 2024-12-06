import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {

    WebDriver driver;

    @BeforeEach
    void Setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("http://127.0.0.1:8081/");
        Thread.sleep(1000);
    }

    @Test
    public void a1() throws InterruptedException {
        driver.findElement(By.id("a1")).click();

        Thread.sleep(1000);

        sendBlank();

        sendMessage("n");
        sendMessage("y");

        sendMessage("0");
        sendMessage("6");
        sendMessage("quit");
        sendMessage("1");
        sendMessage("4");
        sendMessage("quit");
        sendMessage("1");
        sendMessage("2");
        sendMessage("3");
        sendMessage("quit");
        sendMessage("1");
        sendMessage("2");
        sendMessage("quit");
        sendBlank();

        sendBlank();
        sendMessage("y");
        sendMessage("y");
        sendMessage("y");
        sendBlank();

        sendMessage("0");
        sendMessage("0");
        sendMessage("0");

        sendBlank();

        sendMessage("4");
        sendMessage("4");
        sendMessage("quit");

        sendMessage("4");
        sendMessage("3");
        sendMessage("quit");

        sendMessage("3");
        sendMessage("5");
        sendMessage("quit");

        sendBlank();
        int latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 1"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendMessage("y");
        sendBlank();
        sendBlank();

        sendMessage("6");
        sendMessage("5");
        sendMessage("quit");

        sendMessage("8");
        sendMessage("3");
        sendMessage("quit");

        sendMessage("5");
        sendMessage("5");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertFalse(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 1"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendBlank();
        sendBlank();

        sendMessage("8");
        sendMessage("5");
        sendMessage("3");
        sendMessage("quit");

        sendMessage("6");
        sendMessage("4");
        sendMessage("5");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendBlank();
        sendBlank();

        sendMessage("6");
        sendMessage("5");
        sendMessage("5");
        sendMessage("quit");

        sendMessage("3");
        sendMessage("3");
        sendMessage("3");
        sendMessage("4");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("0");
        sendMessage("0");
        sendMessage("0");
        sendMessage("0");

        Assertions.assertEquals("0", driver.findElement(By.id("p1shields")).getText());
        Assertions.assertEquals("0", driver.findElement(By.id("p2shields")).getText());
        Assertions.assertEquals("0", driver.findElement(By.id("p3shields")).getText());
        Assertions.assertEquals("4", driver.findElement(By.id("p4shields")).getText());

        Assertions.assertEquals("Player 1's Hand: F5 F10 F15 F15 F30 H10 B15 B15 L20", driver.findElement(By.id("p1hand")).getText());
        Assertions.assertEquals("Player 3's Hand: F5 F5 F15 F30 S10", driver.findElement(By.id("p3hand")).getText());
        Assertions.assertEquals("Player 4's Hand: F15 F15 F40 L20", driver.findElement(By.id("p4hand")).getText());


    }

    @Test
    public void scenario_2() throws InterruptedException {
        driver.findElement(By.id("scenario_2")).click();

        Thread.sleep(1000);

        sendBlank();

        sendMessage("y");

        sendMessage("0");
        sendMessage("quit");
        int latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 1");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 1 (5 BP): F5"));
        sendMessage("0");
        sendMessage("4");
        sendMessage("quit");
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 2");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 2 (10 BP): F5 D5"));
        sendMessage("0");
        sendMessage("3");
        sendMessage("quit");
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 3");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 3 (20 BP): F10 H10"));
        sendMessage("0");
        sendMessage("3");
        sendMessage("quit");
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 4");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 4 (25 BP): F10 B15"));
        sendBlank();

        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendMessage("y");

        sendBlank();

        sendMessage("0");
        sendMessage("0");
        sendMessage("0");

        sendBlank();

        sendMessage("5");
        sendMessage("quit");

        sendMessage("quit");

        sendMessage("5");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertFalse(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendBlank();

        sendBlank();

        sendMessage("3");
        sendMessage("quit");

        sendMessage("4");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendBlank();

        sendBlank();

        sendMessage("4");
        sendMessage("5");
        sendMessage("quit");

        sendMessage("4");
        sendMessage("5");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendBlank();

        sendBlank();

        sendMessage("5");
        sendMessage("5");
        sendMessage("quit");

        sendMessage("5");
        sendMessage("5");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        Assertions.assertEquals("0", driver.findElement(By.id("p1shields")).getText());
        Assertions.assertEquals("4", driver.findElement(By.id("p2shields")).getText());
        Assertions.assertEquals("0", driver.findElement(By.id("p3shields")).getText());
        Assertions.assertEquals("4", driver.findElement(By.id("p4shields")).getText());

        sendMessage("0");
        sendMessage("0");
        sendMessage("0");
        sendMessage("0");

        sendBlank();

        sendMessage("n");
        sendMessage("y");

        sendMessage("0");
        sendMessage("quit");
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 1");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 1 (5 BP): F5"));
        sendMessage("0");
        sendMessage("2");
        sendMessage("quit");
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 2");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 2 (10 BP): F5 D5"));
        sendMessage("0");
        sendMessage("3");
        sendMessage("quit");
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 3");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 3 (15 BP): F5 H10"));
        sendBlank();

        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendMessage("n");

        sendBlank();
        sendBlank();

        sendMessage("5");
        sendMessage("quit");

        sendMessage("5");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");

        sendBlank();
        sendBlank();

        sendMessage("6");
        sendMessage("quit");

        sendMessage("6");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");

        sendBlank();
        sendBlank();

        sendMessage("9");
        sendMessage("quit");

        sendMessage("9");
        sendMessage("quit");

        sendBlank();
        sendBlank();

        sendMessage("0");
        sendMessage("1");
        sendMessage("1");

        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2 has reached 7 shields!"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4 has reached 7 shields!"));

        Assertions.assertEquals("0", driver.findElement(By.id("p1shields")).getText());
        Assertions.assertEquals("7", driver.findElement(By.id("p2shields")).getText());
        Assertions.assertEquals("0", driver.findElement(By.id("p3shields")).getText());
        Assertions.assertEquals("7", driver.findElement(By.id("p4shields")).getText());

        Assertions.assertEquals("Player 1's Hand: F15 F15 F20 F20 F20 F20 F25 F25 F30 H10 B15 L20", driver.findElement(By.id("p1hand")).getText());
        Assertions.assertEquals("Player 2's Hand: F10 F15 F15 F25 F30 F40 F50 L20 L20", driver.findElement(By.id("p2hand")).getText());
        Assertions.assertEquals("Player 3's Hand: F20 F40 D5 D5 S10 H10 H10 H10 H10 B15 B15 L20", driver.findElement(By.id("p3hand")).getText());
        Assertions.assertEquals("Player 4's Hand: F15 F15 F20 F25 F30 F50 F70 L20 L20", driver.findElement(By.id("p4hand")).getText());

        Assertions.assertEquals("Game is over!", driver.findElement(By.id("gameState")).getText());

    }

    @Test
    public void scenario_3() throws InterruptedException {
        driver.findElement(By.id("scenario_3")).click();

        Thread.sleep(1000);

        sendBlank();

        sendMessage("y");

        sendMessage("0");
        sendMessage("quit");
        int latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 1");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 1 (5 BP): F5"));
        sendMessage("1");
        sendMessage("quit");
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 2");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 2 (10 BP): F10"));
        sendMessage("2");
        sendMessage("quit");
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 3");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 3 (15 BP): F15"));
        sendMessage("3");
        sendMessage("quit");
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 4");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 4 (20 BP): F20"));

        sendBlank();

        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendMessage("y");

        sendBlank();

        sendMessage("0");
        sendMessage("0");
        sendMessage("0");

        sendBlank();

        sendMessage("2");
        sendMessage("quit");

        sendMessage("2");
        sendMessage("quit");

        sendMessage("3");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendMessage("y");

        sendBlank();
        sendBlank();

        sendMessage("5");
        sendMessage("quit");

        sendMessage("5");
        sendMessage("quit");

        sendMessage("6");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendMessage("y");

        sendBlank();
        sendBlank();

        sendMessage("7");
        sendMessage("quit");

        sendMessage("7");
        sendMessage("quit");

        sendMessage("8");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendMessage("y");

        sendBlank();
        sendBlank();

        sendMessage("9");
        sendMessage("quit");

        sendMessage("9");
        sendMessage("quit");

        sendMessage("10");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        Assertions.assertEquals("0", driver.findElement(By.id("p1shields")).getText());
        Assertions.assertEquals("4", driver.findElement(By.id("p2shields")).getText());
        Assertions.assertEquals("4", driver.findElement(By.id("p3shields")).getText());
        Assertions.assertEquals("4", driver.findElement(By.id("p4shields")).getText());

        sendMessage("0");
        sendMessage("0");
        sendMessage("1");
        sendMessage("1");

        sendBlank();

        Assertions.assertEquals("2", driver.findElement(By.id("p2shields")).getText());

        sendBlank();

        sendBlank();
        sendMessage("0");
        sendBlank();
        sendMessage("0");
        sendBlank();
        sendMessage("0");
        sendMessage("0");
        sendBlank();
        sendMessage("0");
        sendBlank();

        sendBlank();

        sendMessage("1");
        sendMessage("3");

        sendBlank();

        sendMessage("y");

        sendMessage("0");
        sendMessage("quit");
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 1");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 1 (15 BP): F15"));
        sendMessage("0");
        sendMessage("6");
        sendMessage("quit");
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 2");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 2 (20 BP): F15 D5"));
        sendMessage("3");
        sendMessage("5");
        sendMessage("quit");
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("Stage 3");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Stage 3 (25 BP): F20 D5"));
        sendBlank();

        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendMessage("y");

        sendBlank();

        sendMessage("0");
        sendMessage("0");
        sendMessage("0");

        sendBlank();

        sendMessage("8");
        sendMessage("quit");

        sendMessage("9");
        sendMessage("quit");

        sendMessage("9");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3"));
        Assertions.assertFalse(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");

        sendBlank();
        sendBlank();

        sendMessage("9");
        sendMessage("7");
        sendMessage("quit");

        sendMessage("9");
        sendMessage("5");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3"));
        sendBlank();

        sendMessage("y");
        sendMessage("y");

        sendBlank();
        sendBlank();

        sendMessage("9");
        sendMessage("4");
        sendMessage("quit");

        sendMessage("10");
        sendMessage("quit");

        sendBlank();
        latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3"));
        sendBlank();

        sendMessage("0");
        sendMessage("0");
        sendMessage("0");

        Assertions.assertTrue(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3 has reached 7 shields!"));

        Assertions.assertEquals("0", driver.findElement(By.id("p1shields")).getText());
        Assertions.assertEquals("5", driver.findElement(By.id("p2shields")).getText());
        Assertions.assertEquals("7", driver.findElement(By.id("p3shields")).getText());
        Assertions.assertEquals("4", driver.findElement(By.id("p4shields")).getText());

        Assertions.assertEquals("Player 1's Hand: F25 F25 F35 D5 D5 S10 S10 S10 S10 H10 H10 H10", driver.findElement(By.id("p1hand")).getText());
        Assertions.assertEquals("Player 2's Hand: F15 F25 F30 F40 S10 S10 S10 H10 E30", driver.findElement(By.id("p2hand")).getText());
        Assertions.assertEquals("Player 3's Hand: F10 F25 F30 F40 F50 S10 S10 H10 H10 L20", driver.findElement(By.id("p3hand")).getText());
        Assertions.assertEquals("Player 4's Hand: F25 F25 F30 F50 F70 D5 D5 S10 S10 B15 L20", driver.findElement(By.id("p4hand")).getText());

        Assertions.assertEquals("Game is over!", driver.findElement(By.id("gameState")).getText());

    }

    @Test
    public void scenario_4() throws  InterruptedException {
        driver.findElement(By.id("scenario_4")).click();

        Thread.sleep(1000);

        sendBlank();

        sendMessage("y");

        sendMessage("0");
        sendMessage("1");
        sendMessage("2");
        sendMessage("3");
        sendMessage("4");
        sendMessage("5");
        sendMessage("quit");
        sendMessage("0");
        sendMessage("0");
        sendMessage("0");
        sendMessage("0");
        sendMessage("0");
        sendMessage("0");
        sendMessage("quit");

        sendBlank();
        sendBlank();

        sendMessage("y");
        sendMessage("y");
        sendMessage("y");

        sendBlank();

        sendMessage("0");
        sendMessage("3");
        sendMessage("4");

        sendBlank();

        sendMessage("11");
        sendMessage("quit");
        sendMessage("quit");
        sendMessage("quit");

        sendBlank();

        int latest = driver.findElement(By.id("textbox")).getText().lastIndexOf("These players have passed this stage!");
        Assertions.assertFalse(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 2"));
        Assertions.assertFalse(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 3"));
        Assertions.assertFalse(driver.findElement(By.id("textbox")).getText().substring(latest).contains("Player 4"));

        sendMessage("0");
        sendMessage("0");

        Assertions.assertEquals("0", driver.findElement(By.id("p1shields")).getText());
        Assertions.assertEquals("0", driver.findElement(By.id("p2shields")).getText());
        Assertions.assertEquals("0", driver.findElement(By.id("p3shields")).getText());
        Assertions.assertEquals("0", driver.findElement(By.id("p4shields")).getText());

        Assertions.assertEquals("Player 1's Hand: F15 D5 D5 D5 D5 S10 S10 S10 H10 H10 H10 H10", driver.findElement(By.id("p1hand")).getText());
        Assertions.assertEquals("Player 2's Hand: F5 F5 F10 F15 F15 F20 F20 F25 F30 F30 F40", driver.findElement(By.id("p2hand")).getText());
        Assertions.assertEquals("Player 3's Hand: F5 F5 F10 F15 F15 F20 F20 F25 F25 F30 F40 L20", driver.findElement(By.id("p3hand")).getText());
        Assertions.assertEquals("Player 4's Hand: F5 F5 F10 F10 F15 F20 F20 F25 F25 F30 F50 E30", driver.findElement(By.id("p4hand")).getText());

    }

    public void sendMessage(String message) throws InterruptedException {
        driver.findElement(By.id("in")).sendKeys(message);
        Thread.sleep(100);
        driver.findElement(By.id("submit")).click();
        Thread.sleep(500);
    }

    public void sendBlank() throws InterruptedException {
        driver.findElement(By.id("submit")).click();
        Thread.sleep(500);
    }

}
