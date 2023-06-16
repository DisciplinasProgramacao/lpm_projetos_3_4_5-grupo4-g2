package models;

public class Audiencia {
    private String user;
    private String paraVer;
    private String midiaID;

    // CONSTRUCTOR

    public Audiencia(String user, String paraVer, String midiaID) {
        this.user = user;
        this.paraVer = paraVer;
        this.midiaID = midiaID;
    }

    // GETTERS
    public String getUser() {
        return user;
    }
    public String getMidiaID() {
        return midiaID;
    }
    public String getParaVer() {
        return paraVer;
    }

    // SETTERS
    public void setUser(String user) {
        this.user = user;
    }
    public void setParaVer(String paraVer) {
        this.paraVer = paraVer;
    }
    public void setMidiaID(String midiaID) {
        this.midiaID = midiaID;
    }
}
