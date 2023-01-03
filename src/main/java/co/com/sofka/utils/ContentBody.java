package co.com.sofka.utils;

public enum ContentBody {
    JSON_BODY("{\n" +
            "    \"email\": \"eve.holt@reqres.in\",\n" +
            "    \"password\": \"pistol\"\n" +
            "}");


    private final String value;

    ContentBody(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


