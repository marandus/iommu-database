/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.exodusproject.iommu.model.entity.chipset;

import net.exodusproject.iommu.model.entity.ManufacturerEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.exodusproject.iommu.model.entity.AbstractBaseEntity;
import net.exodusproject.iommu.model.enums.ManufacturerType;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Entity
@Table(name = "chipset", indexes = {
	@Index(columnList = "name")
}, uniqueConstraints = {
	@UniqueConstraint(columnNames = {"name", "manufacturer_id"})
})
@Getter
@Setter
@ToString(callSuper = true)
public class ChipsetEntity extends AbstractBaseEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, optional = false, fetch = FetchType.EAGER)
	private ManufacturerEntity manufacturer;

	/**
	 * Set the manufacturer for this chipset. The provided manufacturer must own the type
	 * {@link ManufacturerType#CHIPSET} to be accepted.
	 *
	 * @param manufacturer Manufacturer entity to set
	 *
	 * @throws IllegalArgumentException if provided manufacturer does not own the type {@link ManufacturerType#CHIPSET}
	 */
	public void setManufacturer(ManufacturerEntity manufacturer) {
		if (!manufacturer.getTypes().contains(ManufacturerType.CHIPSET)) {
			throw new IllegalArgumentException("Manufacturer must have type CHIPSET");
		}

		this.manufacturer = manufacturer;
	}

}
