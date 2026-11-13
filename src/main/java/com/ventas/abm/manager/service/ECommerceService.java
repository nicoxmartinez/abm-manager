package com.ventas.abm.manager.service;

import com.ventas.abm.manager.domain.ECommerce;

public interface ECommerceService {

	ECommerce createECommerce(ECommerce ecommerce) throws Exception;

	ECommerce modifyECommerce(ECommerce ecommerce) throws Exception;

}
