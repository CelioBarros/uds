package com.test.uds;

import com.test.uds.domain.Customization;
import com.test.uds.domain.Flavor;
import com.test.uds.domain.Size;
import com.test.uds.repository.CustomizationRepository;
import com.test.uds.repository.FlavorRepository;
import com.test.uds.repository.SizeRepository;
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
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

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

}
