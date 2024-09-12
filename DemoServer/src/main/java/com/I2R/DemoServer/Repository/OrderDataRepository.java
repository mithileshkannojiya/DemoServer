package com.I2R.DemoServer.Repository;

import com.I2R.DemoServer.Entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDataRepository extends JpaRepository<OrderData, Long> {
    OrderData findByKey(String key);
    // Additional query methods (if needed) can be added here
}
