/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommudb.model.entity.board;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.github.marandus.iommudb.model.entity.AbstractBaseEntity;
import com.github.marandus.iommudb.model.entity.ManufacturerEntity;
import com.github.marandus.iommudb.model.entity.chipset.ChipsetEntity;
import com.github.marandus.iommudb.model.enums.ManufacturerType;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Entity
@Table(name = "mainboard", uniqueConstraints = {
	@UniqueConstraint(columnNames = {"name", "chipset_id", "manufacturer_id"})
})
@Getter
@Setter
@ToString(callSuper = true)
public class MainboardEntity extends AbstractBaseEntity {

	@Column(name = "name", length = 1000, nullable = false)
	private String name;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, optional = false, fetch = FetchType.EAGER)
	private ChipsetEntity chipset;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, optional = false, fetch = FetchType.EAGER)
	private ManufacturerEntity manufacturer;

	@OneToMany(mappedBy = "mainboard", cascade = {CascadeType.PERSIST, CascadeType.DETACH}, orphanRemoval = true)
	private Set<MainboardBiosEntity> biosVersions = new HashSet<>();

	/**
	 * Set the manufacturer for this chipset. The provided manufacturer must own the type
	 * {@link ManufacturerType#MAINBOARD} to be accepted.
	 *
	 * @param manufacturer Manufacturer entity to set
	 *
	 * @throws IllegalArgumentException if provided manufacturer does not own the type
	 *                                  {@link ManufacturerType#MAINBOARD}
	 */
	public void setManufacturer(ManufacturerEntity manufacturer) {
		if (!manufacturer.getTypes().contains(ManufacturerType.MAINBOARD)) {
			throw new IllegalArgumentException("Manufacturer must have type MAINBOARD");
		}

		this.manufacturer = manufacturer;
	}
}
