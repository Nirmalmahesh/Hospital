package PatientUtilChecker;

import PatientUtilChecker.constants.PatientConstants;
import global.coda.hm.model.Patient;
import global.coda.hm.service.impl.PatientServiceImpl;
import global.coda.hm.exception.NoMoreAdmissionException;

import global.coda.hm.exception.PatientNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.ResourceBundle;

public class CrudChecker {

    PatientServiceImpl patientUtil;
    Logger LOGGER;
    ResourceBundle patientResource;
    Patient patient;

    @BeforeMethod
    public void setup()
    {
        patientUtil = new PatientServiceImpl();
        LOGGER = LoggerFactory.getLogger(CrudChecker.class);
        patientResource = ResourceBundle.getBundle("patient_info_test");
        Patient patient = PatientConstants.getPatient();
    }

    @Test
    public void createPatientChecker()
    {
            LOGGER.info(patientResource.getString("HM_IT001"));
        try {
            Patient getted = patientUtil.createPatient(patient);
            if(getted!=null)
            {
                LOGGER.debug(patientResource.getString("HM_IT002"));
                Assert.assertEquals(PatientConstants.getPatient().getPatinetId(),getted.getPatinetId());
            }

        } catch (Exception e) {
                LOGGER.error(patientResource.getString("HM_IT500"));
        }

    }

    @Test
    public void readAllPatientChecker(){
        LOGGER.info(patientResource.getString("HM_IT004"));
        PatientServiceImpl patientUtil = new PatientServiceImpl();

        Patient patient = patientUtil.createPatient(PatientConstants.getPatient());
        Assert.assertTrue(patientUtil.readAllPatients().size()!=0);
    }
    @Test
    public void readPatientRightChecker(){
        PatientServiceImpl patientUtil = new PatientServiceImpl();

        try {
            patientUtil.readPatient(PatientConstants.getPatient().getPatinetId());
            Assert.assertTrue(patientUtil.readPatient(PatientConstants.getPatient().getPatinetId()).getPatinetId() == PatientConstants.getPatient().getPatinetId());
        } catch (PatientNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    @Test
    public void readPatientWrongChecker(){
        PatientServiceImpl patientUtil = new PatientServiceImpl();
        try {
            patientUtil.readPatient(patient.getPatinetId());
            patient.setPatinetId(404);
            Assert.assertFalse(patient.getPatinetId() == PatientConstants.getPatient().getPatinetId());
        } catch (PatientNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    @Test
    public void checkUpdatePatient(){
        PatientServiceImpl patientUtil = new PatientServiceImpl();

            patientUtil.createPatient(PatientConstants.getPatient());


        Patient newPatient = PatientConstants.getPatient();
        newPatient.setPatientName("Updated Name");
        Patient updatedPatient = patientUtil.updatePatient(PatientConstants.getPatient().getPatinetId(),newPatient);
        LOGGER.info(updatedPatient.toString());
        Assert.assertTrue(updatedPatient.getPatinetId() == newPatient.getPatinetId());

    }
    @Test
    public void checkDeletePatientTrue(){
        PatientServiceImpl patientUtil = new PatientServiceImpl();

            patientUtil.createPatient(PatientConstants.getPatient());

        Boolean flag = patientUtil.deletePatient(PatientConstants.getPatient());
        Assert.assertTrue(flag);
    }
    @Test
    public void checkDeletePatientFalse(){
        PatientServiceImpl patientUtil = new PatientServiceImpl();

            patientUtil.createPatient(PatientConstants.getPatient());

        Patient patient = PatientConstants.getPatient();
        patient.setPatinetId(404);
        Boolean flag = patientUtil.deletePatient(patient);
        Assert.assertFalse(flag);
    }



}
