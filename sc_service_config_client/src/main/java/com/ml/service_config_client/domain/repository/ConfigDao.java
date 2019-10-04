package com.ml.service_config_client.domain.repository;

import com.ml.service_config_client.domain.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ConfigDao extends JpaRepository<Config,Integer>, CrudRepository<Config,Integer> {

    Config findByConfigId(Integer configId);

}
