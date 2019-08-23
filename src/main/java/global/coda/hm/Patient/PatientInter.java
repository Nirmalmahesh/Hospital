package global.coda.hm.Patient;

import global.coda.hm.exception.NoMoreAdmissionException;
import global.coda.hm.exception.PatientNotFoundException;

public interface PatientInter {
    public Patient createPatient(Patient patient) throws NoMoreAdmissionException;
    public Patient readPatient(Patient patient) throws PatientNotFoundException;
    public Patient[] readAllPatients();
    public Patient UpdatePatient(Patient patient,Patient update);
    public boolean deletePatient(Patient patient);
    public Patient getPatient();
    public Integer getNumberOfPateintsAvailable(Patient[] patients);
    public Integer getIndexOfPatient(Patient patient);

}
