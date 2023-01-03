package co.com.sofka.utils.soap.add;

public enum Patch {

    ADD(System.getProperty("user.dir")+
            "\\src\\test\\resources\\files\\add.xml");
    private final String value;
    Patch(String value) {this.value = value;}

    public String getValue() { return value;}
}
