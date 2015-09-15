package com.lw.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.search.annotations.DocumentId;

@MappedSuperclass
public class IdentifiableEntity implements Serializable {
	public static final long serialVersionUID = 1L;
	@DocumentId
	private Long id;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof IdentifiableEntity)) {
			return false;
		}

		final IdentifiableEntity e = (IdentifiableEntity) o;
		return id != null ? id.equals(e.getId()) : e.getId() == null;
	}

	@Override
	public int hashCode() {
		return 0;
	}
}
