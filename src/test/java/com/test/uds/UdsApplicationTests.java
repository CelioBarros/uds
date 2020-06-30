package com.test.uds;

import com.test.uds.domain.*;
import com.test.uds.repository.CustomizationRepository;
import com.test.uds.repository.FlavorRepository;
import com.test.uds.repository.RequestRepository;
import com.test.uds.repository.SizeRepository;
import com.test.uds.service.RequestService;
import org.assertj.core.api.Assertions;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {UdsApplicationTests.Initializer.class})
public class UdsApplicationTests {

    @Autowired
    FlavorRepository flavorRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    CustomizationRepository customizationRepository;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RequestService requestService;

    @ClassRule
    public static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres")
            .withDatabaseName("uds")
            .withUsername("postgres")
            .withPassword("postgres");

    @Test
    public void contextLoads() {
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgres.getJdbcUrl(),
                    "spring.datasource.username=" + postgres.getUsername(),
                    "spring.datasource.password=" + postgres.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    public void testFlavorsSeed(){
        List<Flavor> flavorList = flavorRepository.findAll();
        Assertions.assertThat(flavorList.size()).isEqualTo(3);
        Assertions.assertThat(flavorList.get(0).getName()).isEqualTo("Morango");
        Assertions.assertThat(flavorList.get(0).getId()).isEqualTo(1L);
        Assertions.assertThat(flavorList.get(0).getExtra_time()).isEqualTo(0);

        Assertions.assertThat(flavorList.get(1).getName()).isEqualTo("Banana");
        Assertions.assertThat(flavorList.get(1).getId()).isEqualTo(2L);
        Assertions.assertThat(flavorList.get(1).getExtra_time()).isEqualTo(0);

        Assertions.assertThat(flavorList.get(2).getName()).isEqualTo("Kiwi");
        Assertions.assertThat(flavorList.get(2).getId()).isEqualTo(3L);
        Assertions.assertThat(flavorList.get(2).getExtra_time()).isEqualTo(5);

    }

    @Test
    public void testSizeSeed(){
        List<Size> sizeList = sizeRepository.findAll();
        Assertions.assertThat(sizeList.size()).isEqualTo(3);
        Assertions.assertThat(sizeList.get(0).getName()).isEqualTo("pequeno (300ml)");
        Assertions.assertThat(sizeList.get(0).getId()).isEqualTo(1L);
        Assertions.assertThat(sizeList.get(0).getValue()).isEqualTo(10);
        Assertions.assertThat(sizeList.get(0).getTime()).isEqualTo(5);

        Assertions.assertThat(sizeList.get(1).getName()).isEqualTo("médio (500ml)");
        Assertions.assertThat(sizeList.get(1).getId()).isEqualTo(2L);
        Assertions.assertThat(sizeList.get(1).getValue()).isEqualTo(13);
        Assertions.assertThat(sizeList.get(1).getTime()).isEqualTo(7);

        Assertions.assertThat(sizeList.get(2).getName()).isEqualTo("grande (700ml)");
        Assertions.assertThat(sizeList.get(2).getId()).isEqualTo(3L);
        Assertions.assertThat(sizeList.get(2).getValue()).isEqualTo(15);
        Assertions.assertThat(sizeList.get(2).getTime()).isEqualTo(10);
    }

    @Test
    public void testCustomizationSeed(){
        List<Customization> customizationList = customizationRepository.findAll();
        Assertions.assertThat(customizationList.size()).isEqualTo(3);
        Assertions.assertThat(customizationList.get(0).getName()).isEqualTo("granola");
        Assertions.assertThat(customizationList.get(0).getId()).isEqualTo(1L);
        Assertions.assertThat(customizationList.get(0).getExtra_value()).isEqualTo(0);
        Assertions.assertThat(customizationList.get(0).getExtra_time()).isEqualTo(0);

        Assertions.assertThat(customizationList.get(1).getName()).isEqualTo("paçoca");
        Assertions.assertThat(customizationList.get(1).getId()).isEqualTo(2L);
        Assertions.assertThat(customizationList.get(1).getExtra_value()).isEqualTo(3);
        Assertions.assertThat(customizationList.get(1).getExtra_time()).isEqualTo(3);

        Assertions.assertThat(customizationList.get(2).getName()).isEqualTo("leite ninho");
        Assertions.assertThat(customizationList.get(2).getId()).isEqualTo(3L);
        Assertions.assertThat(customizationList.get(2).getExtra_value()).isEqualTo(3);
        Assertions.assertThat(customizationList.get(2).getExtra_time()).isEqualTo(0);
    }


    @Test
    @Transactional
    public void testSaveRequestWithoutCustomization(){
        int oldRequestListSize = requestRepository.findAll().size();

        Request savedRequest = saveRequest(new HashSet());
        int newRequestListSize = requestRepository.findAll().size();

        Assertions.assertThat(oldRequestListSize + 1).isEqualTo(newRequestListSize);

        Assertions.assertThat(savedRequest.getSetup_time()).isEqualTo(5);
        Assertions.assertThat(savedRequest.getValue()).isEqualTo(10);
        Assertions.assertThat(savedRequest.getAcai().getCustomizations().size()).isEqualTo(0);
    }

    @Test
    @Transactional
    public void testSaveRequestWithCustomization(){
        int oldRequestListSize = requestRepository.findAll().size();

        Set<Customization> customizationSet = new HashSet();
        Customization customization1 = new Customization();
        customization1.setId(1L);
        Customization customization2 = new Customization();
        customization2.setId(2L);
        Customization customization3 = new Customization();
        customization3.setId(3L);
        customizationSet.add(customization1);
        customizationSet.add(customization2);
        customizationSet.add(customization3);

        Request savedRequest = saveRequest(customizationSet);
        int newRequestListSize = requestRepository.findAll().size();

        Assertions.assertThat(oldRequestListSize + 1).isEqualTo(newRequestListSize);

        Assertions.assertThat(savedRequest.getSetup_time()).isEqualTo(8);
        Assertions.assertThat(savedRequest.getValue()).isEqualTo(16);
        Assertions.assertThat(savedRequest.getAcai().getCustomizations().size()).isEqualTo(3);
    }

    @Test
    @Transactional
    public void testGetRequestById(){
        Request savedRequest = saveRequest(new HashSet());
        Request gettedRequest = requestService.getById(savedRequest.getId()).get();

        Assertions.assertThat(gettedRequest.getSetup_time()).isEqualTo(savedRequest.getSetup_time());
        Assertions.assertThat(gettedRequest.getValue()).isEqualTo(savedRequest.getValue());
        Assertions.assertThat(gettedRequest.getAcai()).isEqualTo(savedRequest.getAcai());
    }

    @Test
    @Transactional
    public void testGetUnknownRequest(){
        Optional<Request> request = requestService.getById(1980218309128310230L);

        Assertions.assertThat(request).isEmpty();
    }

    private Request saveRequest(Set<Customization> customizationSet) {
        Request request = new Request();
        request.setAcai(new Acai());
        request.getAcai().setSize(new Size());
        request.getAcai().setFlavor(new Flavor());
        request.getAcai().getSize().setId(1L);
        request.getAcai().getFlavor().setId(1L);
        request.getAcai().setCustomizations(customizationSet);
        return requestService.create(request);
    }
}
