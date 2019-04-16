package com.example.config;

import com.example.entity.Case;
import com.example.repository.CaseRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class AppConfig {

  @Autowired
  CaseRepository caseRepository;

  @PostConstruct
  public void setUpSomeDataItems() {
    Case newCase = new Case();
    newCase.setAddressLine1("123 Fake Street");
    newCase.setTreatmentCode("HH_12334_E");
    newCase.setPostcode("AB1 2XY");
    newCase.setLsoa("789");
    caseRepository.save(newCase);

    newCase = new Case();
    newCase.setAddressLine1("666 Devil Road");
    newCase.setTreatmentCode("HH_57483_W");
    newCase.setPostcode("ZZ1 2AB");
    newCase.setLsoa("444");
    caseRepository.save(newCase);

    newCase = new Case();
    newCase.setAddressLine1("1 Buckingham Palace Drive");
    newCase.setTreatmentCode("HH_12334_E");
    newCase.setPostcode("FF1 2ID");
    newCase.setLsoa("876");
    caseRepository.save(newCase);

    newCase = new Case();
    newCase.setAddressLine1("999 The Wales Innit");
    newCase.setTreatmentCode("HH_12334_W");
    newCase.setPostcode("FF1 6OB");
    newCase.setLsoa("789");
    caseRepository.save(newCase);
  }

}
