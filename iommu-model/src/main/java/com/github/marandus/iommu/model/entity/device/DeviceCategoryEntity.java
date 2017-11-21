/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.model.entity.device;

import com.github.marandus.argval.ArgumentValidator;
import com.github.marandus.argval.enums.NumberCompareOperator;
import com.github.marandus.iommu.model.entity.AbstractBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Entity
@Table(name = "device_category")
@Getter
@Setter
@ToString(callSuper = true)
public class DeviceCategoryEntity extends AbstractBaseEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "device_class", length = 2, updatable = false, nullable = false)
	private String deviceClass;

	@Column(name = "device_subclass", length = 2, updatable = false, nullable = false)
	private String deviceSubclass;

	/**
	 * Set the name for this category. Provided String cannot be empty, null or whitespace-only.
	 *
	 * @param name Category name
	 */
	public void setName(String name) {
		ArgumentValidator.requireNonBlank(name, "Device category name is blank");

		this.name = name;
	}

	/**
	 * Set the PCI device class for this category. Provided String cannot be empty, null or whitespace-only. It must be
	 * exactly 2 characters in length.
	 *
	 * @param deviceClass Device class, must be a 2 character string
	 */
	public void setDeviceClass(String deviceClass) {
		ArgumentValidator.requireStringLength(deviceClass, 2, NumberCompareOperator.EQUAL, "PCI device class");

		this.deviceClass = deviceClass;
	}

	/**
	 * Set the PCI device subclass for this category. Provided String cannot be empty, null or whitespace-only. It must be
	 * exactly 2 characters in length.
	 *
	 * @param deviceSubclass Device subclass, must be a 2 character string
	 */
	public void setDeviceSubclass(String deviceSubclass) {
		ArgumentValidator.requireStringLength(deviceSubclass, 2, NumberCompareOperator.EQUAL, "PCI device subclass");

		this.deviceSubclass = deviceSubclass;
	}
}
