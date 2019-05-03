
package org.springapp.api.request.model;

import java.util.List;

public class OrderRequestModel {
    private UserRequestModel user;
    private List<ProductInfo> productList;    
    private Long paymentId;
}
