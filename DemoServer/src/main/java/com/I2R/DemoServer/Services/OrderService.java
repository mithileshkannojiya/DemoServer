package com.I2R.DemoServer.Services;

import com.I2R.DemoServer.Dto.OrderParserDto;
import com.I2R.DemoServer.Entity.OrderData;
import com.I2R.DemoServer.Repository.OrderDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderDataRepository orderDataRepository;

    @Autowired
    private OrderParserDto orderParserDto;

    @Transactional
    public void saveXmlDataAsKeyValue(String xmlFilePath) throws Exception {
        // Parse the XML file and convert it into a map of key-value pairs
        Map<String, String> xmlDataMap = orderParserDto.parseXml(xmlFilePath);

        // Store each entry as a key-value pair in the database
        for (Map.Entry<String, String> entry : xmlDataMap.entrySet()) {
            // Check if an OrderData entity with the same key already exists
            OrderData orderData = orderDataRepository.findByKey(entry.getKey());
            if (orderData == null) {
                // Create a new OrderData entity if it doesn't exist
                orderData = new OrderData();
                orderData.setKey(entry.getKey());
            }
            // Update the value of the OrderData entity
            orderData.setValue(entry.getValue());

            // Save the OrderData entity to the database
            orderDataRepository.save(orderData);
        }
    }
}