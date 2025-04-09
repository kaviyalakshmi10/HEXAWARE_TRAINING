package entity;

public class Officer {
    private int officerId;
    private String firstName;
    private String lastName;
    private String badgeNumber;
    private String rank;
    private String address;
    private String phoneNumber;
    private int agencyId;

    public Officer() {
    }

    public Officer(int officerId, String firstName, String lastName, String badgeNumber,
                   String rank, String address, String phoneNumber, int agencyId) {
        this.officerId = officerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.badgeNumber = badgeNumber;
        this.rank = rank;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.agencyId = agencyId;
    }

    public int getOfficerId() {
        return officerId;
    }

    public void setOfficerId(int officerId) {
        this.officerId = officerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }
    public String toString() {
        return String.format("Officer [ID=%d, Name=%s %s, Badge=%s, Rank=%s]",
                officerId, firstName, lastName, badgeNumber, rank);
    }
}
