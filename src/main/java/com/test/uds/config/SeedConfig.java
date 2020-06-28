package com.test.uds.config;

import com.test.uds.domain.Flavor;
import com.test.uds.domain.Size;
import com.test.uds.repository.FlavorRepository;
import com.test.uds.repository.SizeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SeedConfig {

    private final Logger logFlavor = LoggerFactory.getLogger(Flavor.class);

    private final Logger logSize = LoggerFactory.getLogger(Size.class);


    @Resource
    private FlavorRepository flavorRepository;

    @Resource
    private SizeRepository sizeRepository;

    @EventListener
    public void seedData(ContextRefreshedEvent event) {
        seedFlavor();
        seedSize();
    }

    private void seedFlavor() {
        List<Flavor> flavorList = flavorRepository.findAll();
        if(flavorList == null || flavorList.size() <= 0) {
            Flavor flavor1 = new Flavor();
            flavor1.setId(1L);
            flavor1.setName("Morango");
            flavor1.setExtra_time(0);

            Flavor flavor2 = new Flavor();
            flavor2.setId(1L);
            flavor2.setName("Banana");
            flavor2.setExtra_time(0);

            Flavor flavor3 = new Flavor();
            flavor3.setId(1L);
            flavor3.setName("Kiwi");
            flavor3.setExtra_time(5);

            flavorRepository.saveAll(Arrays.asList(flavor1, flavor2, flavor3));

            logFlavor.info("Flavor Seeded");
        } else {
            logFlavor.info("Flavor Seeding Not Required");
        }
    }

    private void seedSize() {
        List<Size> sizeList = sizeRepository.findAll();
        if(sizeList == null || sizeList.size() <= 0) {
            Size size1 = new Size();
            size1.setId(1L);
            size1.setName("pequeno (300ml)");
            size1.setValue(10);
            size1.setTime(5);

            Size size2 = new Size();
            size2.setId(2L);
            size2.setName("médio (500ml)");
            size2.setValue(13);
            size2.setTime(7);

            Size size3 = new Size();
            size3.setId(3L);
            size3.setName("grande (700ml)");
            size3.setValue(15);
            size3.setTime(10);

            sizeRepository.saveAll(Arrays.asList(size1, size2, size3));

            logSize.info("Size Seeded");
        } else {
            logSize.info("Size Seeding Not Required");
        }
    }
}
