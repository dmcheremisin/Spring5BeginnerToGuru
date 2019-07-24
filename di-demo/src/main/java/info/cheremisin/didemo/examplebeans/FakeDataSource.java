package info.cheremisin.didemo.examplebeans;

public class FakeDataSource {

    private String user;
    private String password;
    private String url;
    private String environmentProperty; // set by [edit configuration -> Environment variables in idea, system variables]

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEnvironmentProperty() {
        return environmentProperty;
    }

    public void setEnvironmentProperty(String environmentProperty) {
        this.environmentProperty = environmentProperty;
    }
}
