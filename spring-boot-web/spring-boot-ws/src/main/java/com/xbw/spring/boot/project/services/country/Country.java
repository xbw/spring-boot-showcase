//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.3.2 生成的
// 请访问 <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2021.08.23 时间 06:33:44 PM CST 
//


package com.xbw.spring.boot.project.services.country;

import javax.xml.bind.annotation.*;


/**
 * <pre>
 * &lt;complexType name="country"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="population" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="capital" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="currency" type="{http://boot.spring.xbw.com/project/services/country}currency"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "country", propOrder = {
        "name",
        "population",
        "capital",
        "currency"
})
public class Country {

    @XmlElement(required = true)
    protected String name;
    protected int population;
    @XmlElement(required = true)
    protected String capital;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Currency currency;

    /**
     * 获取name属性的值。
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取population属性的值。
     */
    public int getPopulation() {
        return population;
    }

    /**
     * 设置population属性的值。
     */
    public void setPopulation(int value) {
        this.population = value;
    }

    /**
     * 获取capital属性的值。
     * @return possible object is
     * {@link String }
     */
    public String getCapital() {
        return capital;
    }

    /**
     * 设置capital属性的值。
     * @param value allowed object is
     *              {@link String }
     */
    public void setCapital(String value) {
        this.capital = value;
    }

    /**
     * 获取currency属性的值。
     * @return possible object is
     * {@link Currency }
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * 设置currency属性的值。
     * @param value allowed object is
     *              {@link Currency }
     */
    public void setCurrency(Currency value) {
        this.currency = value;
    }

}
