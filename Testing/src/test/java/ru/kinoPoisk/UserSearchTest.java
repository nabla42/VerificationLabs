package ru.kinoPoisk;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class UserSearchTest {
    private ChromeDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Мария\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.kinopoisk.ru/s/");
    }
    @After
    public  void close() {
        driver.quit();
    }

    private By saveButton = By.xpath("//*[@value='сохранить все']");
    private By submitYa = By.xpath("//button");

    private void login(String log, String pass) {
       driver.findElement(By.xpath("//button[text()='Войти']")).click();
        driver.findElement(By.xpath(".//*[@name='login']")).sendKeys(log);
        driver.findElement(submitYa).click();
        driver.findElement(By.xpath(".//*[@name='passwd']")).sendKeys(pass);
        driver.findElement(submitYa).click();
    }
    private void profileIn() {
        driver.findElement(By.xpath(".//*[@class='header-fresh-user-partial" +
                                    "-component__avatar-hover']")).click();
        driver.findElement(By.xpath(".//*[@class='header-fresh-user-partial-component" +
                                    "__dropdown-navigation-link-text']")).click();
        driver.findElement(By.xpath(".//*[@class='addFriend']")).click();
    }
    private void changeInfo(String name, String surname) {
        driver.findElement(By.name("edit[main][first_name]")).clear();
        driver.findElement(By.name("edit[main][first_name]")).sendKeys(name);

        driver.findElement(By.name("edit[main][last_name]")).clear();
        driver.findElement(By.name("edit[main][last_name]")).sendKeys(surname);

        driver.findElement(saveButton).click();
    }
    private void changeVK(String name) {
        driver.findElement(By.name("edit[main][social][vkontakte]")).clear();
        driver.findElement(By.name("edit[main][social][vkontakte]")).sendKeys(name);

        driver.findElement(saveButton).click();
    }
    private void changeCity(String city) {
        driver.findElement(By.name("edit[main][city]")).clear();
        driver.findElement(By.name("edit[main][city]")).sendKeys(city);

        driver.findElement(saveButton).click();
    }

    // Login
    @Test
    public void testCase38() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom";
        driver.findElement(By.id("find_user")).sendKeys(log);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        String s = driver.findElement(By.xpath("//*[@class='nick_name']")).getText();
        Assert.assertTrue(s.equals(log));
    }
    @Test
    public void testCase39() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "1aaaaaaaaaabbbbbbbbbbccccccccc";
        driver.findElement(By.id("find_user")).sendKeys(log);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        //Assert.assertTrue(driver.findElements(By.xpath(".//*[text() = '"+ log +"' or text()='"+ log +" ']")).size()!=0);
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='nick_name']")).size()!=0);
    }
    @Test
    public void testCase40() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "abs_0";
        driver.findElement(By.id("find_user")).sendKeys(log);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertTrue(driver.findElements(By.xpath(".//a[text() = 'abs_0']")).size()!=0);
    }
    @Test
    public void testCase41() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "a8a1aa9aa2bb7bb4bbbb4cccccccccdddddd6dddeeeeeeeeee12345678";
        driver.findElement(By.id("find_user")).sendKeys(log);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        String s = driver.findElement(By.xpath("//*[@class='search_results_topText']/b")).getText();
        Assert.assertTrue(s.equals("a8a1aa9aa2bb7bb4bbbb4cccccccccdddddd6dddeeeeeeeeee12345"));
    }
    @Test
    public void testCase42() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.id("find_user")).sendKeys("^&*=~@$%/\\|<>");
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertFalse(driver.findElement(By.xpath("//*[@class='search_results_topText']/b")).isDisplayed());
    }
    //Имя, фамилия
    @Test
    public void testCase43() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String n = "Иван", sn = "Крузенштерн";
        login(log, pass);
        profileIn();
        changeInfo(n, sn);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.name("m_act[name]")).sendKeys(n);
        driver.findElement(By.name("m_act[surname]")).sendKeys(sn);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertTrue(driver.findElements(By.xpath(".//a[text() = '" + n + " " + sn + "']")).size()!=0);
    }
    @Test
    public void testCase44() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String n = "John", sn = "Johnov";
        login(log, pass);
        profileIn();
        changeInfo(n, sn);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.name("m_act[name]")).sendKeys(n);
        driver.findElement(By.name("m_act[surname]")).sendKeys(sn);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertTrue(driver.findElements(By.xpath(".//a[text() = '" + n + " " + sn + "']")).size()!=0);
    }
    @Test
    public void testCase45() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String n = "Preuß", sn = "Preußen";
        login(log, pass);
        profileIn();
        changeInfo(n, sn);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.name("m_act[name]")).sendKeys(n);
        driver.findElement(By.name("m_act[surname]")).sendKeys(sn);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertTrue(driver.findElements(By.xpath(".//a[text() = '" + n + " " + sn + "']")).size()!=0);
    }
    @Test
    public void testCase46() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String n = "aaa", sn = "aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeegggg";
        login(log, pass);
        profileIn();
        changeInfo(n, sn);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.name("m_act[name]")).sendKeys(n);
        driver.findElement(By.name("m_act[surname]")).sendKeys(sn);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertTrue(driver.findElements(By.xpath("//h2")).size()!=0);
    }
    @Test
    public void testCase47() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String n = "aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeegggg", sn = "aaa";
        login(log, pass);
        profileIn();
        changeInfo(n, sn);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.name("m_act[name]")).sendKeys(n);
        driver.findElement(By.name("m_act[surname]")).sendKeys(sn);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertTrue(driver.findElements(By.xpath("//h2")).size()!=0);
    }
    //Ссылка
    @Test
    public void testCase48() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String s = "https://vk.com/monegon";
        login(log, pass);
        profileIn();
        changeVK(s);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.name("m_act[social]")).sendKeys(s);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertFalse(driver.findElements(By.xpath("//h2")).size()!=0);
    }
    @Test
    public void testCase49() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String s = "https://vk.com/mon_egon";
        login(log, pass);
        profileIn();
        changeVK(s);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.name("m_act[social]")).sendKeys(s);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertFalse(driver.findElements(By.xpath("//h2")).size()!=0);
    }

    @Test
    public void testCase50() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String s = "https://vk.com/monegon123";
        login(log, pass);
        profileIn();
        changeVK(s);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.name("m_act[social]")).sendKeys(s);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertFalse(driver.findElements(By.xpath("//h2")).size()!=0);
    }
    @Test
    public void testCase51() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String s = "https://vk.com/moooooooooooooooneeeeeeeeeegoooooooooooon";
        login(log, pass);
        profileIn();
        changeVK(s);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.name("m_act[social]")).sendKeys(s);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertTrue(driver.findElements(By.xpath("//h2")).size()!=0);
    }
    // Город
    @Test
    public void testCase52() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String c = "Москва";
        login(log, pass);
        profileIn();
        changeCity(c);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.id("find_user")).sendKeys(log);
        driver.findElement(By.name("m_act[city]")).sendKeys(c);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        String s = driver.findElement(By.xpath("//*[@class='nick_name']")).getText();
        Assert.assertTrue(s.equals(log));

    }

    @Test
    public void testCase53() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String c = "Санкт – Петербург";
        login(log, pass);
        profileIn();
        changeCity(c);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.id("find_user")).sendKeys(log);
        driver.findElement(By.name("m_act[city]")).sendKeys(c);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        String s = driver.findElement(By.xpath("//*[@class='nick_name']")).getText();
        Assert.assertTrue(s.equals(log));

    }
    @Test
    public void testCase54() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String c = "Midgar";
        login(log, pass);
        profileIn();
        changeCity(c);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.id("find_user")).sendKeys(log);
        driver.findElement(By.name("m_act[city]")).sendKeys(c);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        String s = driver.findElement(By.xpath("//*[@class='nick_name']")).getText();
        Assert.assertTrue(s.equals(log));

    }
    @Test
    public void testCase55() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String c = "Düsseldorf";
        login(log, pass);
        profileIn();
        changeCity(c);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.id("find_user")).sendKeys(log);
        driver.findElement(By.name("m_act[city]")).sendKeys(c);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        String s = driver.findElement(By.xpath("//*[@class='nick_name']")).getText();
        Assert.assertTrue(s.equals(log));

    }
    @Test
    public void testCase56() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String c = "123Moscow";
        login(log, pass);
        profileIn();
        changeCity(c);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.id("find_user")).sendKeys(log);
        driver.findElement(By.name("m_act[city]")).sendKeys(c);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertTrue(driver.findElements(By.xpath("//h2")).size()!=0);

    }
    @Test
    public void testCase57() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String c = "!@#$%^&*()-+=[]{};':\",/<>?\\|;";
        login(log, pass);
        profileIn();
        changeCity(c);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.id("find_user")).sendKeys(log);
        driver.findElement(By.name("m_act[city]")).sendKeys(c);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertTrue(driver.findElements(By.xpath("//h2")).size()!=0);

    }
    @Test
    public void testCase58() {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String log = "nogenom", pass = "testtest0";
        String c = "Моооооооссссссссссскваааааааааааааа";
        login(log, pass);
        profileIn();
        changeCity(c);

        driver.get("https://www.kinopoisk.ru/s/");
        driver.findElement(By.id("find_user")).sendKeys(log);
        driver.findElement(By.name("m_act[city]")).sendKeys(c);
        driver.findElement(By.cssSelector("#searchAdv > form.form_4 > input.el_9.submit.nice_button")).click();

        Assert.assertTrue(driver.findElements(By.xpath("//h2")).size()!=0);

    }


}
