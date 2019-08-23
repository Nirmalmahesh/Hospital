package global.coda.hm.serviceImpl;

import global.coda.hm.constants.PatientConstants;
import global.coda.hm.exception.NoMoreAdmissionException;
import global.coda.hm.exception.PatientNotFoundException;
import global.coda.hm.model.Patient;
import global.coda.hm.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Implementation of Interface PatientService
 * Performing CRUD operatrions
 */
public class PatientServiceImpl implements PatientService {
    Scanner scan;
    Logger LOGGER;
    Integer number_of_patients;
    private Patient[] patients;
    ResourceBundle RESOURCE_PATIENT;
    int input;

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

    public PatientServiceImpl(Integer number_of_patients) {
        scan = new Scanner(System.in);
        this.number_of_patients = number_of_patients;
        LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);
        RESOURCE_PATIENT = ResourceBundle.getBundle("patient_info");
        patients = new Patient[number_of_patients];
        LOGGER.info(MessageFormat.format(RESOURCE_PATIENT.getString("HM_I002"),number_of_patients));

    }

    public PatientServiceImpl()
    {
        scan = new Scanner(System.in);
        LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);
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
                return patients[count];
            }else if(count == length-1)
            {
                throw new PatientNotFoundException(PatientConstants.PATIENT_NOT_FOUND);
            }
        }
            throw  new PatientNotFoundException(PatientConstants.PATIENT_NOT_FOUND);
    }

    @Override
    public Patient[] readAllPatients() {
        return patients;
    }

    @Override
    public Patient updatePatient(Patient patient, Patient update) {
        Integer index;
        try {
            index = getIndexOfPatient(patient);
            patients[index].setHomeTown(update.getHomeTown());
            patients[index].setPatientName(update.getPatientName());
            patients[index].setPatinetId(update.getPatinetId());
            LOGGER.info(MessageFormat.format(RESOURCE_PATIENT.getString("HM_I007"),update.getPatinetId()));
            return patients[index];

        } catch (PatientNotFoundException e) {
            LOGGER.info(PatientConstants.PATIENT_NOT_FOUND);
        }

    return null;
    }

    @Override
    public boolean deletePatient(Patient patient) {
        try {
            Integer index = getIndexOfPatient(patient);
            int i;
            for( i=index;i<patients.length && patients[i+1]!=null ;i++)
            {
                Patient temp = patients[i];
                patients[i] = patients[i+1];
                patients[i+1] = temp;
            }
            patients[i]=null;
            return true;
        } catch (PatientNotFoundException e) {
            LOGGER.error(PatientConstants.PATIENT_NOT_FOUND);
            return false;
        }
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
    public Integer getIndexOfPatient(Patient patient) throws PatientNotFoundException {
        for(int i=0;patients[i]!=null && i<getNumberOfPateintsAvailable(patients);i++)
        {
            if(patient.getPatinetId() == patients[i].getPatinetId())
            {
                return i;
            }
        }

       throw new PatientNotFoundException(PatientConstants.PATIENT_NOT_FOUND);
    }

    @Override
    public void getUserInput() {
            LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_001"));
            LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_002"));
            LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_003"));
            LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_004"));
            LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_011"));
            LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_008"));
            LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_009"));
            input = scan.nextInt();
            switch (input)
            {
                case 1:
                {
                    inputForCreateNewUser();
                    getUserInput();
                }
                case 2:
                {
                    readPatientWithUserInput();
                    getUserInput();
                }
                case 3:
                {
                    updateUserWithUserInput();
                    getUserInput();
                }
                case 4:
                {
                    deletePatientWithUserInout();
                    getUserInput();
                }
                case 5:
                {
                    getAllPatients();
                    getUserInput();;
                }
                case 6:
                    System.exit(0);
                default:
                    getUserInput();
            }
    }

    private void getAllPatients() {
        for(int i = 0;i<getNumberOfPateintsAvailable(patients);i++)
        {
            LOGGER.info(MessageFormat.format(RESOURCE_PATIENT.getString("HM_I008"),i+1));
            LOGGER.info(patients[i].toString());
        }
    }

    private void updateUserWithUserInput() {
        LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_010"));
        Patient oldUser = readPatientWithUserInput();
        Patient newUser = oldUser;
        scan.nextLine();
        LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_006"));
        newUser.setPatientName(scan.nextLine());
        LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_007"));
        newUser.setHomeTown(scan.nextLine());
        updatePatient(oldUser,newUser);
        getUserInput();
    }
    public void deletePatientWithUserInout()
    {
        int index;
        LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_005"));
        Patient patient = new Patient();
        patient.setPatinetId(scan.nextInt());
       deletePatient(patient);
    }

    private Patient readPatientWithUserInput() {
        Patient patient = new Patient();
        LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_005"));
        patient.setPatinetId(scan.nextInt());
        try {
            LOGGER.info(readPatient(patient).toString());
            return readPatient(patient);
        } catch (PatientNotFoundException e) {
            LOGGER.error(PatientConstants.PATIENT_NOT_FOUND);
        }
        scan.nextLine();
        return null;
    }

    private void inputForCreateNewUser() {
        Patient patient = new Patient();
        int index = 0;
        LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_005"));
        patient.setPatinetId(scan.nextInt());
        scan.nextLine();
        LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_006"));
        patient.setPatientName(scan.nextLine());
        LOGGER.info(RESOURCE_PATIENT.getString("HM_INPUT_007"));
        patient.setHomeTown(scan.nextLine());
        try {
            index = getIndexOfPatient(patient);
            LOGGER.info(PatientConstants.USER_ID_DUBKICATION);

        } catch (PatientNotFoundException e) {
            try {
                createPatient(patient);
            } catch (NoMoreAdmissionException ex) {
                ex.printStackTrace();
            }
        }

    }
}
