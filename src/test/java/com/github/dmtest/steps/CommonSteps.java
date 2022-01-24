package com.github.dmtest.steps;

import io.cucumber.java.ru.Тогда;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class CommonSteps {

    @Тогда("сумма чисел {} и {} даст результат {}")
    public void checkSum(int num1, int num2, int resultExpected) throws InterruptedException {
        log.info("тест стартует");
        Thread.sleep(5000);
        int resultActual = num1 + num2;
        Assert.assertEquals("Сумма чисел не соответствует ожидаемому результату", resultExpected, resultActual);
        log.info("тест завершен");
    }
}
