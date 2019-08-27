package global.coda.hm;

import global.coda.hm.service.impl.PatientServiceImpl;

public class HospitalManagement {
  public static void main(String[] args){
      PatientServiceImpl util = new PatientServiceImpl();
      util.triggerApplication();
  }
}
