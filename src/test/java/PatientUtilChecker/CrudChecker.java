package PatientUtilChecker;

import PatientUtilChecker.constants.PatientConstants;
import global.coda.hm.model.Patient;
import global.coda.hm.serviceImpl.PatientServiceImpl;
import global.coda.hm.exception.NoMoreAdmissionException;

import global.coda.hm.exception.PatientNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.ResourceBundle;

public class CrudChecker {

    PatientServiceImpl patientUtil = new PatientServiceImpl(PatientConstants.no_of_patients);
    Logger LOGGER = LoggerFactory.getLogger(CrudChecker.class);
    ResourceBundle patientResource = ResourceBundle.getBundle("patient_info_test");

    @Test
    public void createPatientChecker()
    {
            LOGGER.info(patientResource.getString("HM_IT001"));
        try {
            Patient getted = patientUtil.createPatient(PatientConstants.getPatient());
            if(getted!=null)
            {
                LOGGER.debug(patientResource.getString("HM_IT002"));
                Assert.assertEquals(PatientConstants.getPatient().getPatinetId(),getted.getPatinetId());
            }

        } catch (NoMoreAdmissionException e) {

        }

    }

    @Test
    public void readAllPatientChecker(){
        LOGGER.info(patientResource.getString("HM_IT004"));
        PatientServiceImpl patientUtil = new PatientServiceImpl(PatientConstants.no_of_patients);
        try {
            patientUtil.createPatient(PatientConstants.getPatient());
        } catch (NoMoreAdmissionException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(patientUtil.readAllPatients()[0].getPatinetId() == PatientConstants.getPatient().getPatinetId());
    }
    @Test
    public void readPatientRightChecker(){
        PatientServiceImpl patientUtil = new PatientServiceImpl(PatientConstants.no_of_patients);
        try {
            patientUtil.createPatient(PatientConstants.getPatient());
        } catch (NoMoreAdmissionException e) {
            e.printStackTrace();
        }
        try {
            patientUtil.readPatient(PatientConstants.getPatient());
            Assert.assertTrue(patientUtil.readPatient(PatientConstants.getPatient()).getPatinetId() == PatientConstants.getPatient().getPatinetId());
        } catch (PatientNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void readPatientWrongChecker(){
        PatientServiceImpl patientUtil = new PatientServiceImpl(PatientConstants.no_of_patients);
        try {
            patientUtil.createPatient(PatientConstants.getPatient());
        } catch (NoMoreAdmissionException e) {
            e.printStackTrace();
        }
        try {
            patientUtil.readPatient(PatientConstants.getPatient());
            Patient patient = PatientConstants.getPatient();
            patient.setPatinetId(404);
            Assert.assertFalse(patient.getPatinetId() == PatientConstants.getPatient().getPatinetId());
        } catch (PatientNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void checkUpdatePatient(){
        PatientServiceImpl patientUtil = new PatientServiceImpl(PatientConstants.no_of_patients);
        try {
            patientUtil.createPatient(PatientConstants.getPatient());
        } catch (NoMoreAdmissionException e) {
            e.printStackTrace();
        }

        Patient newPatient = PatientConstants.getPatient();
        newPatient.setPatientName("Updated Name");
        Patient updatedPatient = patientUtil.updatePatient(PatientConstants.getPatient(),newPatient);
        LOGGER.info(updatedPatient.toString());
        Assert.assertTrue(updatedPatient.getPatinetId() == newPatient.getPatinetId());

    }
    @Test
    public void checkDeletePatientTrue(){
        PatientServiceImpl patientUtil = new PatientServiceImpl(PatientConstants.no_of_patients);
        try {
            patientUtil.createPatient(PatientConstants.getPatient());
        } catch (NoMoreAdmissionException e) {
            e.printStackTrace();
        }
        Boolean flag = patientUtil.deletePatient(PatientConstants.getPatient());
        Assert.assertTrue(flag);
    }
    @Test
    public void checkDeletePatientFalse(){
        PatientServiceImpl patientUtil = new PatientServiceImpl(PatientConstants.no_of_patients);
        try {
            patientUtil.createPatient(PatientConstants.getPatient());
        } catch (NoMoreAdmissionException e) {
            e.printStackTrace();
        }
        Patient patient = PatientConstants.getPatient();
        patient.setPatinetId(404);
        Boolean flag = patientUtil.deletePatient(patient);
        LOGGER.error(Arrays.asList(patientUtil.getPatients()).toString());
        Assert.assertFalse(flag);
    }



}
