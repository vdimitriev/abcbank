package mk.dmt.abc.model;

public class CustomerPassword {

    public CustomerPassword(String encryptedPassword, String plainPassword) {
        this.encryptedPassword = encryptedPassword;
        this.plainPassword = plainPassword;
    }

    private String encryptedPassword;
    private String plainPassword;

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }
}
