# spring-boot-ws-xfire

### org/codehaus/xfire/spring/xfire.xml
```
Caused by: org.xml.sax.SAXParseException: Attribute "singleton" must be declared for element type "bean".
```
delete singleton="true"



### org/codehaus/xfire/spring/customEditors.xml
```
Caused by: java.lang.IllegalArgumentException: Cannot convert value of type 'org.codehaus.xfire.spring.editors.ServiceFactoryEditor' to required type 'java.lang.Class' for property 'customEditors[org.codehaus.xfire.service.ServiceFactory]': PropertyEditor [org.springframework.beans.propertyeditors.ClassEditor] returned inappropriate value of type 'org.codehaus.xfire.spring.editors.ServiceFactoryEditor'
```
```
<entry key="org.codehaus.xfire.service.ServiceFactory">
<bean class="org.codehaus.xfire.spring.editors.ServiceFactoryEditor">
<property name="transportManager" ref="xfire.transportManager" />
</bean>
</entry>

modify to

<entry key="org.codehaus.xfire.service.ServiceFactory"
        value="org.codehaus.xfire.spring.editors.ServiceFactoryEditor">
</entry>

```

### META-INF/xfire/services.xml
```
Caused by: org.springframework.beans.factory.BeanDefinitionStoreException: Unrecognized xbean element mapping: beans in namespace http://xfire.codehaus.org/config/1.0
```
```
<beans xmlns="http://xfire.codehaus.org/config/1.0">
<service>
</service>
</beans>

modify to

<beans>
<service xmlns="http://xfire.codehaus.org/config/1.0">
</service>
</beans>

```
