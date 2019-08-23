package global.coda.hm.Patient;

public class Patient {
    private Integer patinetId;
    private String patientName;
    private String homeTown;

    public Integer getPatinetId() {
        return patinetId;
    }

    public void setPatinetId(Integer patinetId) {
        this.patinetId = patinetId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public Patient(Integer patinetId, String patientName, String homeTown) {
        this.patinetId = patinetId;
        this.patientName = patientName;
        this.homeTown = homeTown;
    }

    public Patient() {
    }
}
