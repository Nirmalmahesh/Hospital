package PatientUtilChecker.constants;

import global.coda.hm.model.Patient;

import java.util.Arrays;

public class PatientConstants {

    public static final Integer no_of_patients = 5;
    public static Patient getPatient()
    {
        Patient patient = new Patient();
        patient.setPatinetId(1);
        patient.setPatientName("Nirmalmahesh");
        patient.setPatientAddress(Arrays.asList("velliyanai","karur","639118"));
        return patient;
    }
    public static Patient[] getSixPatients()
    {
        Patient[] patients = new Patient[6];
        patients[0].setPatinetId(1);
        patients[0].setPatientName("Nirmal");

        patients[1].setPatinetId(2);
        patients[1].setPatientName("Sena");

        patients[2].setPatinetId(3);
        patients[2].setPatientName("Rahul");

        patients[3].setPatinetId(4);
        patients[3].setPatientName("Vishal");

        patients[4].setPatinetId(5);
        patients[4].setPatientName("Karthik");

        patients[5].setPatinetId(6);
        patients[5].setPatientName("Hari");

        return patients;

    }

}
