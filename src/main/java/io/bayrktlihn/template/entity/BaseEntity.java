package io.bayrktlihn.template.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@XmlElement(name = "Id")
	private long id;

	@Column(name = "created_date", columnDefinition = "timestamp default localtimestamp(6)")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlElement(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlElement(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "active", columnDefinition = "bool default true")
	@XmlElement(name = "Active")
	private boolean active;

	@Version
	@Column(name = "version", columnDefinition = "bigint default 0")
	@XmlElement(name = "Version")
	private long version;

	@PrePersist
	private void prePersist() {
		createdDate = new Date();
		active = true;
	}

	@PreUpdate
	private void preUpdate()  {
		updatedDate = new Date();
	}
}
