package io.bayrktlihn.template.client.tcmb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Currency")
public class CurrencyDto {

    @XmlAttribute(name = "CrossOrder")
    private String crossOrder;

    @XmlAttribute(name = "Kod")
    private String kod;

    @XmlAttribute(name = "CurrencyCode")
    private String currencyCode;


    @XmlElement(name = "Unit")
    private String unit;

    @XmlElement(name = "Isim")
    private String isim;

    @XmlElement(name = "CurrencyName")
    private String currencyName;

    @XmlElement(name = "ForexBuying")
    private String forexBuying;

    @XmlElement(name = "ForexSelling")
    private String forexSelling;

    @XmlElement(name = "BanknoteBuying")
    private String banknoteBuying;

    @XmlElement(name = "BanknoteSelling")
    private String banknoteSelling;

    @XmlElement(name = "CrossRateUSD")
    private String crossRateUsd;

    @XmlElement(name = "CrossRateOther")
    private String crossRateOther;
}
