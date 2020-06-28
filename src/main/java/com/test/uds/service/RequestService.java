package com.test.uds.service;

import com.test.uds.domain.Acai;
import com.test.uds.domain.Flavor;
import com.test.uds.domain.Request;
import com.test.uds.domain.Size;
import com.test.uds.repository.AcaiRepository;
import com.test.uds.repository.FlavorRepository;
import com.test.uds.repository.RequestRepository;
import com.test.uds.repository.SizeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final AcaiRepository acaiRepository;
    private final FlavorRepository flavorRepository;
    private final SizeRepository sizeRepository;

    public RequestService(RequestRepository requestRepository, AcaiRepository acaiRepository, FlavorRepository flavorRepository, SizeRepository sizeRepository) {
        this.requestRepository = requestRepository;
        this.acaiRepository = acaiRepository;
        this.flavorRepository = flavorRepository;
        this.sizeRepository = sizeRepository;
    }

    public Request create(Request request) {
        Flavor flavor = this.flavorRepository.findById(request.getAcai().getFlavor().getId()).get();
        Size size = this.sizeRepository.findById(request.getAcai().getSize().getId()).get();
        request.getAcai().setFlavor(flavor);
        request.getAcai().setSize(size);
        request.setValue(size.getValue());
        request.setSetup_time(size.getTime() + flavor.getExtra_time());
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
