package com.ml.service_account.infrastrucyure.Repository;

import com.ml.service_account.infrastrucyure.model.ScAccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ScAccountInfoRepository extends JpaRepository<ScAccountInfo,Integer>, CrudRepository<ScAccountInfo,Integer> {
}
