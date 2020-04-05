package entity;

public class Account {
    private String account;
    private String password;
    private String identity;

    public Account() {
    }

    public Account(String identity) {
        this.identity = identity;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
