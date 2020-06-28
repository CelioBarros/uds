package com.test.uds.service;

import com.test.uds.domain.Acai;
import com.test.uds.repository.AcaiRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcaiService {

    private final AcaiRepository acaiRepository;

    public AcaiService(AcaiRepository acaiRepository) {
        this.acaiRepository = acaiRepository;
    }

    public Acai create(Acai acai) {
        return acaiRepository.save(acai);
    }

    public Optional<Acai> getById(Long id) {
        return acaiRepository.findById(id);
    }

    public Acai update(Acai acai) {
        return acaiRepository.save(acai);
    }

    public void delete(Long id) {
        acaiRepository.deleteById(id);
    }
}
