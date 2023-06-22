package io.bayrktlihn.template.dto.tcmb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Tarih_Date")
public class TarihDateDto {

    @XmlAttribute(name = "Tarih")
    private String tarih;

    @XmlAttribute(name = "Date")
    private String date;

    @XmlAttribute(name = "Bulten_No")
    private String bultenNo;

    @XmlElement(name = "Currency")
    private List<CurrencyDto> currencies;

}
