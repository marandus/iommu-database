/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.exodusproject.iommu.model.entity.iommu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.exodusproject.iommu.model.entity.AbstractBaseEntity;
import net.exodusproject.iommu.model.entity.device.DeviceEntity;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Entity
@Table(name = "iommu_group_device")
@Getter
@Setter
@ToString(callSuper = true)
public class IommuGroupDeviceLinkEntity extends AbstractBaseEntity {

	// TODO: Write setter (and test) that verifies length of 2
	@Column(name = "pci_domain", length = 2, updatable = false, nullable = false)
	private String pciDomain;

	// TODO: Write setter (and test) that verifies length of 2
	@Column(name = "pci_bus", length = 2, updatable = false, nullable = false)
	private String pciBus;

	// TODO: Write setter (and test) that verifies length of 2
	@Column(name = "pci_device", length = 2, updatable = false, nullable = false)
	private String pciDevice;

	// TODO: Write setter (and test) that verifies length of 1
	@Column(name = "pci_function", length = 1, updatable = false, nullable = false)
	private String pciFunction;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private IommuGroupEntity iommuGroup;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private DeviceEntity device;

}
