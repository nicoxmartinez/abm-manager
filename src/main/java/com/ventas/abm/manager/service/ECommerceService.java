package com.ventas.abm.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.repository.ECommerceRepository;

@Service
public class ECommerceService {

	@Autowired
	ECommerceRepository ecommerceRepository;
}
