package com.thomas.netty.protocol.http.xml;

/**
 * @创建人 thomas_liu
 * @创建时间 2019/1/7 15:00
 * @描述 TODO
 */
public class OrderFactory {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================


    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================
    public static Order create(long orderID){
        Order order = new Order();
        order.setOrderNumber(orderID);
        order.setTotal(9999.999f);
        Address address = new Address();
        address.setCity("深圳市");
        address.setCountry("中国");
        address.setPostCode("123321");
        address.setState("广东省");
        address.setStreet1("梅龙大道");
        order.setBillTo(address);
        Customer customer = new Customer();
        customer.setFirstName("刘");
        customer.setLastName("托马斯");
        order.setCustomer(customer);
        order.setShipping(Shipping.INTERNATIONAL_EXPRESS);
        order.setShipTo(address);
        return order;
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
