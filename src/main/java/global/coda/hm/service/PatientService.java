package global.coda.hm.service;

import global.coda.hm.exception.NoMoreAdmissionException;
import global.coda.hm.exception.PatientNotFoundException;
import global.coda.hm.model.Patient;

import java.util.Map;
import java.util.TreeMap;

public interface PatientService {
    public Patient createPatient(Patient patient) throws NoMoreAdmissionException;
    public Patient readPatient(int patientId) throws PatientNotFoundException;
    public Map<Integer, Patient> readAllPatients();
    public Patient updatePatient(int patientId,Patient update);
    public boolean deletePatient(Patient patient);
    public int getNumberOfPateintsAvailable(Patient[] patients);

    public void triggerApplication();

}
