package pages;

import java.util.Objects;

public class Account {

    private String accountName;
    private String phoneNumber;

    public Account(String accountName, String phoneNumber) {
        this.accountName = accountName;
        this.phoneNumber = phoneNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account that = (Account) o;
        return Objects.equals(getAccountName(), that.getAccountName()) && Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountName(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return accountName +", "+ phoneNumber;
    }
}
