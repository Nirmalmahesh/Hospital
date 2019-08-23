package PatientUtilChecker;

import PatientUtilChecker.constants.PatientConstants;
import global.coda.hm.Patient.Patient;
import global.coda.hm.Patient.PatientUtil;
import global.coda.hm.exception.NoMoreAdmissionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class CrudChecker {

    PatientUtil patientUtil = new PatientUtil(PatientConstants.no_of_patients);
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

        PatientUtil patientUtil = new PatientUtil(PatientConstants.no_of_patients);
        try {
            patientUtil.createPatient(PatientConstants.getPatient());
        } catch (NoMoreAdmissionException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(patientUtil.readAllPatients()[0].getPatinetId() == PatientConstants.getPatient().getPatinetId());
    }



}
