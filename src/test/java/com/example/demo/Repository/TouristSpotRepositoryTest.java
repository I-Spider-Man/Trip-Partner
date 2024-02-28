package com.example.demo.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.Model.SpotAddress;
import com.example.demo.Model.TouristSpot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
public class TouristSpotRepositoryTest {

    @Autowired
    private TouristSpotRepository touristSpotRepository;

    private TouristSpot savedSpot;

    @BeforeEach
    public void setUp() {
        // Create and save a spot with address before each test
        SpotAddress address = new SpotAddress();
        address.setStreet("123 Main St");
        address.setCity("City");
        address.setState("State");
        address.setCountry("Country");
        address.setPostalCode("12345");

        TouristSpot spot = new TouristSpot();
        spot.setSpotName("TestSpot");
        spot.setDescription("Test description");
        spot.setLocation(address);
        savedSpot = touristSpotRepository.save(spot);
    }

    @AfterEach
    public void tearDown() {
        touristSpotRepository.deleteAll();
    }

    @Test
    public void testFindBySpotName() {

        TouristSpot foundSpot = touristSpotRepository.findBySpotName("TestSpot").orElse(null);


        assertNotNull(foundSpot);
        assertEquals(savedSpot.getSpotName(), foundSpot.getSpotName());
    }

    @Test
    public void testFindByLocationPostalCode() {
        TouristSpot foundSpot = touristSpotRepository.findAllByLocationPostalCode("12345").get(0);
        assertNotNull(foundSpot);
        assertEquals(savedSpot.getLocation().getPostalCode(), foundSpot.getLocation().getPostalCode());
    }
}
