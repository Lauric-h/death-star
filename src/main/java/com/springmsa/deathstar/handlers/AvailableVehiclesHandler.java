package com.springmsa.deathstar.handlers;

import com.springmsa.deathstar.httprequest.RequestBuilder;
import com.springmsa.deathstar.model.Booking;
import com.springmsa.deathstar.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AvailableVehiclesHandler {
    @Autowired
    private RestTemplate restTemplate;

    private String request;
    
        @Value("${VEHICLE_SERVER}")
        private String VEHICLE_SERVER;

    public AvailableVehiclesHandler() {}

    public void buildVehicleRequestWithType(Object type) {
        this.request = RequestBuilder.buildUriWithParam(
                RequestBuilder.buildUriWithPath(VEHICLE_SERVER, "/type"),
                "type",
                type);
    }

    private Vehicle[] getVehiclesList() {
        ResponseEntity<Vehicle[]> response = restTemplate.getForEntity(request, Vehicle[].class);
        Vehicle[] vehicles = response.getBody();
        assert vehicles != null;

        return vehicles;
    }
    
    public ArrayList<Vehicle> getAvailableVehiclesArray(List<Booking> bookingList) {
        Vehicle[] vehicleList = this.getVehiclesList();
        ArrayList<Vehicle> availableVehiclesArray = new ArrayList<Vehicle>(Arrays.asList(vehicleList));
        for (int i = 0; i < availableVehiclesArray.size(); i++) {
            for (Booking booking : bookingList) {
                if (availableVehiclesArray.get(i).getId() == booking.getVehicleId()) {
                    availableVehiclesArray.remove(availableVehiclesArray.get(i));
                }
            }
        }
        return availableVehiclesArray;
    }

}
