//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.14 à 12:36:36 PM CET 
//


package JAXBManager;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="workloadName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="path" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="execConfigurationParameter" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="execType" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="execParameters">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="parameter" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "workloadName",
    "path",
    "execConfigurationParameter",
    "execType",
    "execParameters"
})
@XmlRootElement(name = "WorkloadInformation")
public class WorkloadInformation {

    @XmlElement(required = true)
    protected String workloadName;
    @XmlElement(required = true)
    protected String path;
    @XmlElement(required = true)
    protected BigInteger execConfigurationParameter;
    @XmlElement(required = true)
    protected BigInteger execType;
    @XmlElement(required = true)
    protected WorkloadInformation.ExecParameters execParameters;

    /**
     * Obtient la valeur de la propriété workloadName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkloadName() {
        return workloadName;
    }

    /**
     * Définit la valeur de la propriété workloadName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkloadName(String value) {
        this.workloadName = value;
    }

    /**
     * Obtient la valeur de la propriété path.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPath() {
        return path;
    }

    /**
     * Définit la valeur de la propriété path.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPath(String value) {
        this.path = value;
    }

    /**
     * Obtient la valeur de la propriété execConfigurationParameter.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExecConfigurationParameter() {
        return execConfigurationParameter;
    }

    /**
     * Définit la valeur de la propriété execConfigurationParameter.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExecConfigurationParameter(BigInteger value) {
        this.execConfigurationParameter = value;
    }

    /**
     * Obtient la valeur de la propriété execType.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExecType() {
        return execType;
    }

    /**
     * Définit la valeur de la propriété execType.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExecType(BigInteger value) {
        this.execType = value;
    }

    /**
     * Obtient la valeur de la propriété execParameters.
     * 
     * @return
     *     possible object is
     *     {@link WorkloadInformation.ExecParameters }
     *     
     */
    public WorkloadInformation.ExecParameters getExecParameters() {
        return execParameters;
    }

    /**
     * Définit la valeur de la propriété execParameters.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkloadInformation.ExecParameters }
     *     
     */
    public void setExecParameters(WorkloadInformation.ExecParameters value) {
        this.execParameters = value;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="parameter" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "parameter"
    })
    public static class ExecParameters {

        @XmlElement(required = true)
        protected List<String> parameter;

        /**
         * Gets the value of the parameter property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the parameter property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParameter().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getParameter() {
            if (parameter == null) {
                parameter = new ArrayList<String>();
            }
            return this.parameter;
        }

    }

}
