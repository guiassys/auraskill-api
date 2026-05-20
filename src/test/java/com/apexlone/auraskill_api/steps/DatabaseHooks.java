package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import io.cucumber.java.Before;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class DatabaseHooks {

    @Autowired
    private ProfessionalRepository professionalRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Before(order = 1)
    @Transactional
    public void cleanDatabase() {
        // 1. Limpa o cache de primeiro nível do Hibernate para desanexar entidades antigas
        entityManager.clear();

        // 2. Apaga os dados. Se Professional tiver @OneToMany com CascadeType.ALL / orphanRemoval,
        // o deleteAll() vai limpar as tabelas filhas automaticamente.
        professionalRepository.deleteAll();

        // 3. Força a execução dos deletes no banco imediatamente antes do início do teste
        entityManager.flush();
    }
}