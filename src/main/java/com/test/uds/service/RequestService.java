package com.test.uds.service;

import com.test.uds.domain.*;
import com.test.uds.repository.*;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final CustomizationRepository customizationRepository;
    private final FlavorRepository flavorRepository;
    private final SizeRepository sizeRepository;

    public RequestService(RequestRepository requestRepository, CustomizationRepository customizationRepository, FlavorRepository flavorRepository, SizeRepository sizeRepository) {
        this.requestRepository = requestRepository;
        this.customizationRepository = customizationRepository;
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
        return requestRepository.save(saveCustomizations(request));
    }
    private Request saveCustomizations(Request request) {
        request.getAcai().setCustomizations(
                request.getAcai().getCustomizations()
                    .stream()
                    .map(customization -> {
                        Customization newCustomization = customizationRepository.findById(customization.getId()).get();
                        request.setValue(request.getValue() + newCustomization.getExtra_value());
                        request.setSetup_time(request.getSetup_time() + newCustomization.getExtra_time());
                        return newCustomization;
                    })
                    .collect(Collectors.toSet())
        );
        return request;
    }

    public Optional<Request> getById(Long id) {
        return requestRepository.findById(id);
    }
}
