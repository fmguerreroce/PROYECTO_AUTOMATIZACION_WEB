package com.testing.task;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.model.util.EnvironmentVariables;
import static net.serenitybdd.screenplay.Tasks.instrumented;
public class NavigateToTask implements Task {

    EnvironmentVariables environmentVariables;

    private final String url;

    public NavigateToTask(String url) {
        this.url = url;
    }

    @Override
    @Step("{0} Inicia la página #url")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(url))
        );
    }

    public static Task sauceDemoPage(){
        String url = "webdriver.base.url";
        return instrumented(NavigateToTask.class, url);
    }
}
