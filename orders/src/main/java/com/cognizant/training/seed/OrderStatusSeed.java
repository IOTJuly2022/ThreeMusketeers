package com.cognizant.training.seed;

import com.cognizant.training.model.OrderStatus;
import com.cognizant.training.repository.OrderStatusRepository;

import java.util.Optional;

/**
 * Seeds the Order Status table in the database.
 *
 * @author William Simpson
 */
public class OrderStatusSeed implements IDatabaseSeed {

    /**
     *
     */
    private String[] defaultOrderStatusNames = {"CREATING", "SHIPPING", "SHIPPED", "REFUND", "REFUNDED"};

    /**
     * Order Status repository used to save seeded statuses to the database.
     */
    private OrderStatusRepository orderStatusRepository;

    public OrderStatusSeed(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public boolean run() {
        boolean createdNew = false;

        for (String name : defaultOrderStatusNames) {
            Optional<OrderStatus> optionalOS = this.orderStatusRepository.findByName(name);
            if (optionalOS.isEmpty()) {
                createdNew = true;
                this.orderStatusRepository.saveAndFlush(new OrderStatus(name));
            }
        }

        return createdNew;
    }
}
