package global.coda.hm.service.impl;

import global.coda.hm.constants.PatientConstants;
import global.coda.hm.exception.PatientNotFoundException;
import global.coda.hm.model.Patient;
import global.coda.hm.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.*;

/**
 * Implementation of Interface PatientService
 * Performing CRUD operatrions
 */
public class PatientServiceImpl implements PatientService {

    Scanner scan;
    Logger LOGGER;
    private Map<Integer,Patient> patients;
    ResourceBundle resourcePatient;
    int input;


    public PatientServiceImpl() {
        scan = new Scanner(System.in);

        LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);
        resourcePatient = ResourceBundle.getBundle("patient_info");
        patients = new TreeMap<Integer, Patient>();
    }
    @Override
    public Patient createPatient(Patient patient) {
        patients.putIfAbsent(patient.getPatinetId(),patient);
        LOGGER.info(MessageFormat.format(resourcePatient.getString(PatientConstants.PatientEnum.HM_I006.toString()),patient.getPatientName()));
        return patient;
    }
    @Override
    public Patient readPatient(int patientId) throws PatientNotFoundException {
        Patient finded = null;
        try {
             finded = patients.get(patientId);
             return finded;
        }catch (NullPointerException e){
          throw new PatientNotFoundException(PatientConstants.PATIENT_NOT_FOUND);
        }
    }
    @Override
    public Map<Integer, Patient> readAllPatients() {
        return patients;
    }
    @Override
    public Patient updatePatient(int patientId, Patient update) {
        int index;
        try {
            Patient oldPatient = patients.get(patientId);
            if(oldPatient==null)
            {
                throw new PatientNotFoundException(PatientConstants.PATIENT_NOT_FOUND);
            }
            patients.put(patientId,update);
            return patients.get(patientId);

        } catch (PatientNotFoundException e) {
            LOGGER.info(PatientConstants.PATIENT_NOT_FOUND);
        }
    return null;
    }

    @Override
    public boolean deletePatient(Patient patient) {
        try {
            Patient oldPatient = patients.get(patient.getPatinetId());
            if(oldPatient==null)
            {
                throw new PatientNotFoundException(PatientConstants.PATIENT_NOT_FOUND);
            }
            patients.remove(patient.getPatinetId());
            LOGGER.debug(MessageFormat.format(resourcePatient.getString(PatientConstants.PatientEnum.HM_I009.toString()),patient.getPatinetId()));
            return true;
        } catch (PatientNotFoundException e) {
            LOGGER.error(PatientConstants.PATIENT_NOT_FOUND);
            return false;
        }
    }

    @Override
    public int getNumberOfPateintsAvailable(Patient[] patients) {
        int patientCount = 0;
        int count = 0;
        while(patients.length>count && patients[count]!=null)
        {
            count++;
            patientCount++;
        }
        return patientCount;
    }



    @Override
    public void triggerApplication() {
            Scanner sc=new Scanner(System.in);
            LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_001.toString()));
            LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_002.toString()));
            LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_003.toString()));
            LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_004.toString()));
            LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_011.toString()));
            LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_008.toString()));
            LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_009.toString()));
            try {
                input = sc.nextInt();
            }catch (InputMismatchException e){

                LOGGER.error(PatientConstants.INVALID_INPUT);
                triggerApplication();
            }
            switch (input)
            {
                case 1:
                {
                    inputForCreateNewUser();
                    triggerApplication();
                    break;
                }
                case 2:
                {
                    readPatientWithUserInput();
                    triggerApplication();
                    break;
                }
                case 3:
                {
                    updateUserWithUserInput();
                    triggerApplication();
                    break;
                }
                case 4:
                {
                    deletePatientWithUserInout();
                    triggerApplication();
                    break;
                }
                case 5:
                {
                    getAllPatients();
                    triggerApplication();;
                    break;
                }
                case 6:
                    System.exit(0);
                default:
                    triggerApplication();

            }
    }

    private void getAllPatients() {
        Iterator<Integer> itr = patients.keySet().iterator();
        if(patients.size() == 0)
        {
            LOGGER.debug(resourcePatient.getString("HM_IT005"));
        }
        while (itr.hasNext()){
            LOGGER.info(itr.next().toString());
        }
    }

    private void updateUserWithUserInput() {
        List<String> address = new ArrayList<>();
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_010.toString()));
        Patient oldUser = readPatientWithUserInput();
        Patient newUser = oldUser;
        scan.nextLine();
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_006.toString()));
        newUser.setPatientName(scan.nextLine());
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_007.toString()));
        address.add(scan.nextLine());
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_012.toString()));
        address.add(scan.nextLine());
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_013.toString()));
        address.add(scan.nextLine());
        newUser.setPatientAddress(address);
        updatePatient(oldUser.getPatinetId(),newUser);
        triggerApplication();
    }
    public void deletePatientWithUserInout()
    {
        int index;
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_005.toString()));
        Patient patient = new Patient();
        patient.setPatinetId(scan.nextInt());
       deletePatient(patient);
    }

    private void displayPatient(Patient patient){
        LOGGER.info(patient.getPatinetId().toString());
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_DECO_001.toString()));
        LOGGER.info(patient.getPatientName());
        LOGGER.info(patient.getPatientAddress().toString());
    }
    private Patient readPatientWithUserInput() {
        Patient patient = new Patient();
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_005.toString()));
        patient.setPatinetId(scan.nextInt());
        try {
            patient =readPatient(patient.getPatinetId());
            displayPatient(patient);
            return patient;
        } catch (NullPointerException e) {
            LOGGER.error(PatientConstants.PATIENT_NOT_FOUND);
        }catch (PatientNotFoundException e){
            LOGGER.error(PatientConstants.PATIENT_NOT_FOUND);
        }
        scan.nextLine();
        return null;
    }

    private void inputForCreateNewUser() {
        Patient patient = new Patient();
        List<String> address = new ArrayList<>();
        int index = 0;
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_005.toString()));
        patient.setPatinetId(Integer.parseInt(scan.nextLine()));
        //scan.nextLine();
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_006.toString()));
        patient.setPatientName(scan.nextLine());
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_007.toString()));
        address.add(scan.nextLine());
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_012.toString()));
        address.add(scan.nextLine());
        LOGGER.info(resourcePatient.getString(PatientConstants.PatientEnum.HM_INPUT_013.toString()));
        address.add(scan.nextLine());
        patient.setPatientAddress(address);
        Patient oldRecord = patients.get(patient.getPatinetId());

        if(oldRecord==null)
        {
            createPatient(patient);
        }else{
            LOGGER.error(PatientConstants.USER_ID_DUBKICATION);
            triggerApplication();
        }


    }
}
