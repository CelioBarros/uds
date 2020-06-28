package com.test.uds.service;

import com.test.uds.domain.Request;
import com.test.uds.repository.AcaiRepository;
import com.test.uds.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final AcaiRepository acaiRepository;

    public RequestService(RequestRepository requestRepository, AcaiRepository acaiRepository) {
        this.requestRepository = requestRepository;
        this.acaiRepository = acaiRepository;
    }

    public Request create(Request request) {
        return requestRepository.save(request);
    }

    public Optional<Request> getById(Long id) {
        return requestRepository.findById(id);
    }

    public Request update(Request request) {
        return requestRepository.save(request);
    }

    public void delete(Long id) {
        requestRepository.deleteById(id);
    }
}
