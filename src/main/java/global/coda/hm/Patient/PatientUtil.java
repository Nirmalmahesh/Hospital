package global.coda.hm.Patient;

import com.sun.javafx.binding.StringFormatter;
import global.coda.hm.constants.PatientConstants;
import global.coda.hm.exception.NoMoreAdmissionException;
import global.coda.hm.exception.PatientNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Implementation of Interface PatientInter
 * Performing CRUD operatrions
 */
public class PatientUtil implements PatientInter {
    Scanner scan;
    Logger LOGGER;
    Integer number_of_patients;
    private Patient[] patients;
    ResourceBundle RESOURCE_PATIENT;

    public Scanner getScan() {
        return scan;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public Logger getLOGGER() {
        return LOGGER;
    }

    public void setLOGGER(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }

    public Integer getNumber_of_patients() {
        return number_of_patients;
    }

    public void setNumber_of_patients(Integer number_of_patients) {
        this.number_of_patients = number_of_patients;
    }

    public Patient[] getPatients() {
        return patients;
    }

    public void setPatients(Patient[] patients) {
        this.patients = patients;
    }

    public ResourceBundle getRESOURCE_PATIENT() {
        return RESOURCE_PATIENT;
    }

    public void setRESOURCE_PATIENT(ResourceBundle RESOURCE_PATIENT) {
        this.RESOURCE_PATIENT = RESOURCE_PATIENT;
    }

    public PatientUtil(Integer number_of_patients) {
        scan = new Scanner(System.in);
        this.number_of_patients = number_of_patients;
        LOGGER = LoggerFactory.getLogger(PatientUtil.class);
        RESOURCE_PATIENT = ResourceBundle.getBundle("patient_info");
        patients = new Patient[number_of_patients];
        LOGGER.info(MessageFormat.format(RESOURCE_PATIENT.getString("HM_I002"),number_of_patients));

    }

    public PatientUtil()
    {
        scan = new Scanner(System.in);
        LOGGER = LoggerFactory.getLogger(PatientUtil.class);
        RESOURCE_PATIENT = ResourceBundle.getBundle("patient_info");
        LOGGER.info(RESOURCE_PATIENT.getString("HM_I001"));
        number_of_patients = scan.nextInt();
        patients = new Patient[number_of_patients];
        LOGGER.info(MessageFormat.format(RESOURCE_PATIENT.getString("HM_I002"),number_of_patients));
    }
    @Override
    public Patient createPatient(Patient patient) throws NoMoreAdmissionException {
        Integer length = patients.length;
        Integer available = getNumberOfPateintsAvailable(patients);
        LOGGER.info(MessageFormat.format(RESOURCE_PATIENT.getString("HM_I006"),patient.getPatientName()));
       if(available < patients.length)
       {
           patients[available] = patient;
           return patient;
       }else{
           throw new NoMoreAdmissionException(PatientConstants.NO_SPACE);
       }

    }

    @Override
    public Patient readPatient(Patient patient) throws PatientNotFoundException {
        Integer length = getNumberOfPateintsAvailable(patients);
        Integer count = 0;
        while (patients[count]!=null && count<length){

            if(patients[count].getPatinetId() == patient.getPatinetId())
            {
                return patient;
            }else if(count == length-1)
            {
                throw new PatientNotFoundException(PatientConstants.PATIENT_NOT_FOUND);
            }
        }
        return null;
    }

    @Override
    public Patient[] readAllPatients() {
        return patients;
    }

    @Override
    public Patient UpdatePatient(Patient patient, Patient update) {
        return null;
    }

    @Override
    public boolean deletePatient(Patient patient) {
        return false;
    }

    @Override
    public Patient getPatient() {
        LOGGER.info(RESOURCE_PATIENT.getString("HM_I003"));
        Patient patient = new Patient();
        patient.setPatinetId(scan.nextInt());
        patient.setPatientName(scan.nextLine());
        patient.setHomeTown(scan.nextLine());
        return patient;
    }

    @Override
    public Integer getNumberOfPateintsAvailable(Patient[] patients) {
        Integer patientCount = 0;
        Integer count = 0;
        while(patients.length>count && patients[count]!=null)
        {
            count++;
            patientCount++;
        }
        return patientCount;
    }

    @Override
    public Integer getIndexOfPatient(Patient patient) {
       return null;
    }
}
