package com.finalproject.order.order.service;

import com.finalproject.order.order.dao.CustomerRepository;
import com.finalproject.order.order.dao.OrderRepository;
import com.finalproject.order.order.dto.*;
import com.finalproject.order.order.entity.Customer;
import com.finalproject.order.order.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.Date;


@Service
public class OrderService {
    @Value("${product.url}")
    private String product_url;
    @Value("${notification.url}")
    private String notification_url;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public OrderCreateCmdDTO createNewOrder(OrderCreateCmdDTO orderCreateCmdDTO) {

        Customer customer = customerRepository.findCustomer(orderCreateCmdDTO.getNumber());

        if(customer == null){
            customer = new Customer();
            customer.setName(orderCreateCmdDTO.getName());
            customer.setPhone_number(orderCreateCmdDTO.getNumber());
            customerRepository.save(customer);
        }
        else{
            customer.setName(orderCreateCmdDTO.getName());
            customerRepository.save(customer);
        }

        Order order = new Order();

        order.setCustomer(customer);

        InventoryCheckDTO inventoryCheckDTO = new InventoryCheckDTO();

        inventoryCheckDTO.setId(orderCreateCmdDTO.getProduct_id());
        inventoryCheckDTO.setStock(orderCreateCmdDTO.getQuantity());

        OrderProductResult orderProductResult = checkProductStock(inventoryCheckDTO);

        if(orderProductResult.getSuccess()){
            order.setDate(new Date());
            order.setProduct_id(orderProductResult.getId());
            order.setQuantity(orderProductResult.getQuantity());
            orderRepository.save(order);

            CostumerNotificationRequestDTO costumerNotificationRequestDTO= new CostumerNotificationRequestDTO();
            costumerNotificationRequestDTO.setTo(orderCreateCmdDTO.getNumber());
            costumerNotificationRequestDTO.setSender_id("filipe");
            costumerNotificationRequestDTO.setMessage("encomenda efetuada com sucesso " + orderCreateCmdDTO.getProduct_id());
            sendSMStoCustomer(costumerNotificationRequestDTO);
        }
        return orderCreateCmdDTO;
    }

    private OrderProductResult checkProductStock(InventoryCheckDTO inventoryCheckDTO){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<InventoryCheckDTO> requestEntity = new HttpEntity<>(inventoryCheckDTO,headers);

        try {
            System.out.println("chegou ao orders!");
            ResponseEntity<OrderProductResult> orderProductResultResponseEntity = restTemplate.postForEntity(product_url, requestEntity, OrderProductResult.class);
            return orderProductResultResponseEntity.getBody();
        } catch (HttpClientErrorException e) {
            return e.getResponseBodyAs(OrderProductResult.class);
        }
    }

    private CostumerNotificationResponseDTO sendSMStoCustomer(CostumerNotificationRequestDTO costumerNotificationRequestDTO){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CostumerNotificationRequestDTO> requestEntity = new HttpEntity<>(costumerNotificationRequestDTO,headers);

        try {
            System.out.println("chegou ao order");
            ResponseEntity<CostumerNotificationResponseDTO> costumerNotificationResponseDTOResponseEntity = restTemplate.postForEntity(notification_url, requestEntity, CostumerNotificationResponseDTO.class);
            return costumerNotificationResponseDTOResponseEntity.getBody();
        }
        catch (HttpClientErrorException e){
            return e.getResponseBodyAs(CostumerNotificationResponseDTO.class);}
        }

    }

