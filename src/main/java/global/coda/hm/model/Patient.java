package global.coda.hm.model;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private int patinetId;
    private String patientName;
    private List<String> patientAddress;

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

    public void setPatinetId(int patinetId) {
        this.patinetId = patinetId;
    }

    public List<String> getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(List<String> patientAddress) {
        this.patientAddress = patientAddress;
    }

    public Patient() {
        patientAddress = new ArrayList<String>();
    }
}
