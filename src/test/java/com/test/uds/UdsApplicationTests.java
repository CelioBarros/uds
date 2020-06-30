package com.test.uds;

import com.test.uds.domain.Flavor;
import com.test.uds.repository.FlavorRepository;
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
    public void testFlavors(){
        List<Flavor> flavorList = flavorRepository.findAll();
        Assertions.assertThat(flavorList.size()).isEqualTo(3);
        Assertions.assertThat(flavorList.get(0).getName()).isEqualTo("Morango");
        Assertions.assertThat(flavorList.get(0).getId()).isEqualTo(1L);

        Assertions.assertThat(flavorList.get(2).getName()).isEqualTo("Kiwi");
        Assertions.assertThat(flavorList.get(2).getId()).isEqualTo(3L);

        Assertions.assertThat(flavorList.get(1).getName()).isEqualTo("Banana");
        Assertions.assertThat(flavorList.get(1).getId()).isEqualTo(2L);
    }


}
