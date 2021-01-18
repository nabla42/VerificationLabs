package ru.kinoPoisk;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class CinemaSearchTest {
    private ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Маша\\Downloads\\chromedriver1.exe");

        driver = new ChromeDriver();
        driver.get("https://www.kinopoisk.ru/s/");
    }

    @After
    public  void close() {
        driver.quit();
    }

    // Название фильма
    @Test
    public void testCase1() {
        String s = "Операция «Ы» и другие приключения Шурика";
        driver.findElement(By.id("find_film")).sendKeys(s);
        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElements(By.xpath(".//a[text() = '" + s + "']")).size()!=0);
    }

    @Test
    public void testCase2() {
        String s ="1+1";
        driver.findElement(By.id("find_film")).sendKeys(s);
        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElements(By.xpath(".//a[text() = '" + s + "']")).size()!=0);
    }

    @Test
    public void testCase3() {
        String s = "Chingachgook, die größe Schlange";
        driver.findElement(By.id("find_film")).sendKeys(s);
        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElements(By.xpath(".//a[text() = '" + s + "']")).size()!=0);
    }

    @Test
    public void testCase4() {
        String orig = "Don't Be a Menace to South Central While Drinking Your Juice in the Hood";
        driver.findElement(By.id("find_film")).sendKeys(orig);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class='search_results_topText']/b")).getText();
        Assert.assertTrue(s.equals("Dont Be a Menace to South Central While Drinking Y"));
    }
    @Test
    public void testCase5() {
        driver.findElement(By.id("find_film")).sendKeys("^&*=~@$%/\\|<>");
        driver.findElement(By.className("el_18")).click();

        Assert.assertFalse(driver.findElement(By.xpath("//*[@class='search_results_topText']/b")).isDisplayed());
    }

    @Test
    public void testCase6() {
        String s = "1999";
        driver.findElement(By.id("year")).sendKeys(s);
        driver.findElement(By.className("el_18")).click();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='year' and text()='1999']")).size()!=0);
    }

    // Год создания
    @Test
    public void testCase7() {
        String s = "123";
        driver.findElement(By.id("year")).sendKeys(s);
        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h2")).isDisplayed());
    }

    @Test
    public void testCase8() {
        String s = "19999";
        driver.findElement(By.id("year")).sendKeys(s);
        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h2")).isDisplayed());
    }
    @Test
    public void testCase9() {
        String s = "кек1999";
        driver.findElement(By.id("year")).sendKeys(s);
        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h2")).isDisplayed());
    }
    @Test
    public void testCase10() {
        String s = "19к5ек2";
        driver.findElement(By.id("year")).sendKeys(s);
        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h2")).isDisplayed());
    }
    @Test
    public void testCase11() {
        String s = "1999кек";
        driver.findElement(By.id("year")).sendKeys(s);
        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h2")).isDisplayed());
    }
    @Test
    public void testCase12() {
        String s = "ккек";
        driver.findElement(By.id("year")).sendKeys(s);
        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h2")).isDisplayed());
    }

    //Актер
    @Test
    public void testCase13() {
        String act = "Портман";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[actor]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }
    @Test
    public void testCase14() {
        String act = "Portman";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[actor]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }
    @Test
    public void testCase15() {
        String act = "Портман, Кунис";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[actor]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }
    @Test
    public void testCase16() {
        String act = "Jürgens";
        String film = "И Бог создал женщину";
        driver.findElement(By.id("find_film")).sendKeys("и Бог создал");
        driver.findElement(By.name("m_act[actor]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }
    @Test
    public void testCase17() {
        String act = "12%3Портман";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[actor]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }

    @Test
    public void testCase18() {
        String act = "Портман12%3";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[actor]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }
    @Test
    public void testCase19() {
        String act = "~!@$Портман%^&*";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[actor]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }

    @Test
    public void testCase20() {
        String act = "~!По@$рт%ма^н&*";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[actor]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }
    @Test
    public void testCase21() {
        String act = "Хеннинг Моритцен, Харриет Андерссон, Кари Сюльван, Ингрид Тулин";
        String film = "Шепоты и крики";
        driver.findElement(By.id("find_film")).sendKeys("шепоты");
        driver.findElement(By.name("m_act[actor]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }

    //Создатель
    @Test
    public void testCase22() {
        String act = "Даррен";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[cast]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }
    @Test
    public void testCase23() {
        String act = "Дарен Аронофски";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[cast]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }
    @Test
    public void testCase24() {
        String act = "Андрес Хайнц, Марк Хейман";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[cast]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }
    @Test
    public void testCase25() {
        String act = "Uwe Schafer";
        String film = "Турецкий для начинающих (сериал 2006 – 2008)";
        driver.findElement(By.id("find_film")).sendKeys("турецкий");
        driver.findElement(By.name("m_act[cast]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }
    @Test
    public void testCase26() {
        String act = "Schäfer";
        String film = "Турецкий для начинающих";
        driver.findElement(By.id("find_film")).sendKeys("турецкий");
        driver.findElement(By.name("m_act[cast]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }
    @Test
    public void testCase27() {
        String act = "12%3Даррен";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[cast]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }

    @Test
    public void testCase28() {
        String act = "Даррен12%3";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[cast]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }

    @Test
    public void testCase29() {
        String act = "~Д!а@рр$%е^н&*";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[cast]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        String s = driver.findElement(By.xpath("//*[@class = 'moviename-big']")).getText();
        Assert.assertTrue(s.equals(film));
    }
    @Test
    public void testCase30() {
        String act = "Андрес Хайнц, Марк Хейман, Джон Дж. МакЛафлин, Скотт Франклин, Майк Медавой, Брайан Оливер";
        String film = "Чёрный лебедь";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[cast]")).sendKeys(act);
        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElements(By.xpath(".//*[text() = '" + film + "']")).size() != 0);
    }

    // Сборы
    @Test
    public void testCase31() {
        String money = "320";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[box]")).sendKeys(money);

        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']/*[@value='1']")).click(); //больше
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_type]']/*[@value='world']")).click(); //в Мире

        driver.findElement(By.className("el_18")).click();
        Assert.assertTrue(driver.findElements(By.xpath(".//a[text() = 'Чёрный лебедь']")).size()!=0);
    }
    @Test
    public void testCase32() {
        String money = "300";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[box]")).sendKeys(money);

        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']/*[@value='2']")).click(); //меньше
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_type]']/*[@value='world']")).click(); //в Мире

        driver.findElement(By.className("el_18")).click();

        Assert.assertFalse(driver.findElements(By.xpath(".//a[text() = 'Чёрный лебедь']")).size()!=0);
    }

    @Test
    public void testCase33() {
        String money = "100";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[box]")).sendKeys(money);

        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']/*[@value='1']")).click(); //больше
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_type]']/*[@value='usa']")).click(); //в США

        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElements(By.xpath(".//a[text() = 'Чёрный лебедь']")).size()!=0);
    }

    @Test
    public void testCase34() {
        String money = "110";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[box]")).sendKeys(money);

        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']/*[@value='2']")).click(); //меньше
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_type]']/*[@value='usa']")).click(); //в США

        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElements(By.xpath(".//a[text() = 'Чёрный лебедь']")).size()!=0);
    }
    @Test
    public void testCase35() {
        String money = "2 000 000 000";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[box]")).sendKeys(money);

        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']/*[@value='2']")).click(); //меньше
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_type]']/*[@value='world']")).click(); //в США

        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElements(By.xpath("//h2")).size()!=0);
    }
    @Test
    public void testCase36() {
        String money = "abcd";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[box]")).sendKeys(money);

        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']/*[@value='2']")).click(); //меньше
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_type]']/*[@value='world']")).click(); //в США

        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElements(By.xpath("//h2")).size()!=0);
    }
    @Test
    public void testCase37() {
        String money = "1*5+6";
        driver.findElement(By.id("find_film")).sendKeys("черный");
        driver.findElement(By.name("m_act[box]")).sendKeys(money);

        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']/*[@value='2']")).click(); //меньше
        driver.findElement(By.xpath("//*[@name='m_act[box_vector]']")).click();
        driver.findElement(By.xpath("//*[@name='m_act[box_type]']/*[@value='world']")).click(); //в США

        driver.findElement(By.className("el_18")).click();

        Assert.assertTrue(driver.findElements(By.xpath("//h2")).size()!=0);
    }
}
