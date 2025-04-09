package entity;

public class Agency {
    private int agencyId;
    private String agencyName;
    private String jurisdiction;
    private String address;
    private String phoneNumber;

    public Agency() {
    }

    public Agency(int agencyId, String agencyName, String jurisdiction,
                  String address, String phoneNumber) {
        this.agencyId = agencyId;
        this.agencyName = agencyName;
        this.jurisdiction = jurisdiction;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
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

    public String toString() {
        return String.format("Agency [ID=%d, Name=%s, Jurisdiction=%s]",
                agencyId, agencyName, jurisdiction);
    }
}
