/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.model.entity;

import com.github.marandus.iommu.model.enums.ManufacturerType;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Entity
@Table(name = "manufacturer", indexes = {
	@Index(columnList = "code")
})
@Getter
@Setter
@ToString(callSuper = true)
public class ManufacturerEntity extends AbstractBaseEntity {

	@Column(name = "short_name", unique = true, nullable = false)
	private String shortName;

	@Column(name = "full_name", length = 1000, unique = true, nullable = false)
	private String fullName;

	@Column(name = "code", unique = true, updatable = false, nullable = false)
	private String code;

	@ElementCollection(fetch = FetchType.EAGER, targetClass = ManufacturerType.class)
	@Enumerated(EnumType.STRING)
	@JoinTable(name = "manufacturer_type", joinColumns = {
		@JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
	})
	private Set<ManufacturerType> types;

}
