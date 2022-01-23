
package plus.knowing.distribute.jws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the plus.knowing.distribute.ws package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Method1_QNAME = new QName("http://ws.distribute.knowing.plus/", "method1");
    private final static QName _Method2_QNAME = new QName("http://ws.distribute.knowing.plus/", "method2");
    private final static QName _Method1Response_QNAME = new QName("http://ws.distribute.knowing.plus/", "method1Response");
    private final static QName _Method2Response_QNAME = new QName("http://ws.distribute.knowing.plus/", "method2Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: plus.knowing.distribute.ws
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Method1 }
     *
     */
    public Method1 createMethod1() {
        return new Method1();
    }

    /**
     * Create an instance of {@link Method2 }
     *
     */
    public Method2 createMethod2() {
        return new Method2();
    }

    /**
     * Create an instance of {@link Method1Response }
     *
     */
    public Method1Response createMethod1Response() {
        return new Method1Response();
    }

    /**
     * Create an instance of {@link Method2Response }
     *
     */
    public Method2Response createMethod2Response() {
        return new Method2Response();
    }

    /**
     * Create an instance of {@link Dto }
     *
     */
    public Dto createDto() {
        return new Dto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Method1 }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.distribute.knowing.plus/", name = "method1")
    public JAXBElement<Method1> createMethod1(Method1 value) {
        return new JAXBElement<Method1>(_Method1_QNAME, Method1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Method2 }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.distribute.knowing.plus/", name = "method2")
    public JAXBElement<Method2> createMethod2(Method2 value) {
        return new JAXBElement<Method2>(_Method2_QNAME, Method2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Method1Response }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.distribute.knowing.plus/", name = "method1Response")
    public JAXBElement<Method1Response> createMethod1Response(Method1Response value) {
        return new JAXBElement<Method1Response>(_Method1Response_QNAME, Method1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Method2Response }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.distribute.knowing.plus/", name = "method2Response")
    public JAXBElement<Method2Response> createMethod2Response(Method2Response value) {
        return new JAXBElement<Method2Response>(_Method2Response_QNAME, Method2Response.class, null, value);
    }

}
