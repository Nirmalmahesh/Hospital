package global.coda.hm.service;

import global.coda.hm.exception.NoMoreAdmissionException;
import global.coda.hm.exception.PatientNotFoundException;
import global.coda.hm.model.Patient;

public interface PatientService {
    public Patient createPatient(Patient patient) throws NoMoreAdmissionException;
    public Patient readPatient(Patient patient) throws PatientNotFoundException;
    public Patient[] readAllPatients();
    public Patient updatePatient(Patient patient,Patient update);
    public boolean deletePatient(Patient patient);
    public Patient getPatient();
    public int getNumberOfPateintsAvailable(Patient[] patients);
    public int getIndexOfPatient(Patient patient) throws PatientNotFoundException;
    public void getUserInput();

}
