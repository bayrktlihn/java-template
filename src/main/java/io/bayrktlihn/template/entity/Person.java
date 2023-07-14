package io.bayrktlihn.template.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.bayrktlihn.template.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "person")
@XmlRootElement(name = "Person")
@XmlAccessorType(XmlAccessType.FIELD)
@DynamicUpdate
public class Person extends BaseEntity {

	@Column(name = "first_name")
	@XmlElement(name = "FirstName")
	private String firstName;

	@Column(name = "last_name")
	@XmlElement(name = "LastName")
	private String lastName;

	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "BirthDate")
	private Date birthDate;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	@XmlElement(name = "Gender")
	private Gender gender;
}
