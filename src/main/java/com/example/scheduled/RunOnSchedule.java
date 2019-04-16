package com.example.scheduled;


import static org.springframework.data.jpa.domain.Specification.where;

import com.example.entity.Case;
import com.example.repository.CaseRepository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RunOnSchedule {
  private CaseRepository caseRepository;

  public RunOnSchedule(CaseRepository caseRepository) {
    this.caseRepository = caseRepository;
  }

  @Scheduled(fixedDelay = 60000)
  public void wakeUpAndDoStuff() {
    String[] treatmentCodeArray = {"HH_57483_W", "HH_12334_E", "HH_12334_E", "HH_12334_W"};
    String[] postcodeArray = {"ZZ1 2AB"};
    String[] lsoaArray = {"789"};
    String[] addressLine1Array = {"123 Fake Street"};

    Map<String, List<String>> classifiers = new HashMap<>();

    classifiers.put("treatmentCode", Arrays.asList(treatmentCodeArray));
    classifiers.put("postcode", Arrays.asList(postcodeArray));
    classifiers.put("lsoa", Arrays.asList(lsoaArray));
    classifiers.put("addressLine1", Arrays.asList(addressLine1Array));

    Specification<Case> specification = null;

    for (Map.Entry<String, List<String>> classifier : classifiers.entrySet()) {
      if (specification == null) {
        specification = where(isClassifierIn(classifier.getKey(), classifier.getValue()));
      } else {
        specification = specification.and(isClassifierIn(classifier.getKey(), classifier.getValue()));
      }
    }

    List<Case> caseList = caseRepository.findAll(specification);

//    List<Case> caseList = caseRepository.findAll(where(isTreatmentCodeEndWithE()).or(isTreatmentCodeEndWithW()));
//    List<Case> caseList = caseRepository.findAll(
//        where(isClassifierIn("treatmentCode", Arrays.asList(treatmentCodeArray)))
//        .and(isClassifierIn("postcode", Arrays.asList(postcodeArray))));

//    List<Case> caseList = caseRepository.findAll(where(isTreatmentCodeEndsWithE()));

    for (Case caze : caseList) {
      System.out.println(caze.getAddressLine1());
    }
  }

  public static Specification<Case> isTreatmentCodeEndsWithE() {
    return (Specification<Case>) (root, query, builder) -> builder.like(root.get("treatmentCode"), "%E");
  }

  public static Specification<Case> isTreatmentCodeEndsWithW() {
    return (Specification<Case>) (root, query, builder) -> builder.like(root.get("treatmentCode"), "%W");
  }

  public static Specification<Case> isClassifierIn(final String fieldName, final List<String> inClauseValues) {
    return (Specification<Case>) (root, query, builder) -> {
      In<String> inClause = builder.in(root.get(fieldName));
      for (String inClauseValue : inClauseValues) {
        inClause.value(inClauseValue);
      }
      return inClause;
    };
  }

}
