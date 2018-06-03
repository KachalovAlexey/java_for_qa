package kachalov.javaforqa.addressbook.appmanager;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import kachalov.javaforqa.addressbook.model.Issue;
import org.apache.http.client.fluent.Executor;
import org.openqa.selenium.WebDriver;
import static com.jayway.restassured.RestAssured.*;

public class RestHelper extends HelperBase{

    public RestHelper(WebDriver wd) {
        super(wd);
    }

    public Issue getIssueById(int issueId) {
        String json = get(String.format("http://bugify.stqa.ru/api/issues/%s.json", issueId)).asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issue = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0);
        return new Issue()
                .withId(issue.getAsJsonObject().get("id").getAsInt())
                .withState(issue.getAsJsonObject().get("state").getAsInt())
                .withSubject(issue.getAsJsonObject().get("subject").getAsString())
                .withDescription(issue.getAsJsonObject().get("description").getAsString());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("278bac5e81d71a7490f9adcf001a7032", "");
    }
}
