package com.xbw.spring.boot.ws;

import com.xbw.spring.boot.samples.userguide.example5.Order;
import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * @author xbw
 */
public class SamplesTests {
    @Test
    void example4() throws ServiceException, RemoteException {

        Service  service = new Service();
        Call     call    = (Call) service.createCall();

        call.setTargetEndpointAddress( "http://localhost:8080/services/LogTestService" );
        call.setOperationName( new QName("LogTestService", "testMethod") );

        String res = (String) call.invoke( new Object[] {} );

        System.out.println( res );
    }

    @Test
    void example5() throws ServiceException, RemoteException {

        Order order = new Order();
        order.setCustomerName("Glen Daniels");
        order.setShippingAddress("275 Grove Street, Newton, MA");

        String[] items = new String[]{"mp3jukebox", "1600mahBattery"};
        int[] quantities = new int[]{1, 4};

        order.setItemCodes(items);
        order.setQuantities(quantities);

        Service service = new Service();
        Call call = (Call) service.createCall();
        QName qn = new QName("urn:OrderProcessor", "Order");

        call.registerTypeMapping(Order.class, qn,
                new org.apache.axis.encoding.ser.BeanSerializerFactory(Order.class, qn),
                new org.apache.axis.encoding.ser.BeanDeserializerFactory(Order.class, qn));
        String result;
        try {
            call.setTargetEndpointAddress("http://localhost:8080/services/OrderProcessor");
            call.setOperationName(new QName("OrderProcessor", "processOrder"));
            call.addParameter("arg1", qn, ParameterMode.IN);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);

            result = (String) call.invoke(new Object[]{order});
        } catch (AxisFault fault) {
            result = "Error : " + fault;
        }

        System.out.println(result);
    }
}
