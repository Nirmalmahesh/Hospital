package global.coda.hm;

import global.coda.hm.model.Patient;
import global.coda.hm.serviceImpl.PatientServiceImpl;

public class HospitalManagement {
  public static void main(String[] args){
      PatientServiceImpl util = new PatientServiceImpl(0);
      util.triggerApplication();
  }



}
