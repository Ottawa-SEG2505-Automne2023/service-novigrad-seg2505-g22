package com.example.segprojet;

public class Services {
    private String name;
    private String firstName;
    private String LastName;
    private String DateOfBirth;
    private String Adress;
    private String LicenseType;
    private String AdressProof;
    private String ProofOfStatus;
    private String Photo;

    public Services() {
    }

    public Services(String name, String firstName, String lastName, String dateOfBirth, String adress, String licenseType, String adressProof, String proofOfStatus, String photo) {
        this.name = name;
        this.firstName = firstName;
        LastName = lastName;
        DateOfBirth = dateOfBirth;
        Adress = adress;
        LicenseType = licenseType;
        AdressProof = adressProof;
        ProofOfStatus = proofOfStatus;
        Photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getLicenseType() {
        return LicenseType;
    }

    public void setLicenseType(String licenseType) {
        LicenseType = licenseType;
    }

    public String getAdressProof() {
        return AdressProof;
    }

    public void setAdressProof(String adressProof) {
        AdressProof = adressProof;
    }

    public String getProofOfStatus() {
        return ProofOfStatus;
    }

    public void setProofOfStatus(String proofOfStatus) {
        ProofOfStatus = proofOfStatus;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
