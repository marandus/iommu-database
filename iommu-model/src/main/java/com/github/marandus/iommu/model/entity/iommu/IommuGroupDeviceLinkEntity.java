/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.model.entity.iommu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.github.marandus.argval.ArgumentValidator;
import com.github.marandus.argval.enums.NumberCompareOperator;
import com.github.marandus.iommu.model.entity.AbstractBaseEntity;
import com.github.marandus.iommu.model.entity.device.DeviceEntity;

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

    @Column(name = "pci_domain", length = 2, updatable = false, nullable = false)
    private String pciDomain = "0";

    @Column(name = "pci_bus", length = 2, updatable = false, nullable = false)
    private String pciBus;

    @Column(name = "pci_device", length = 2, updatable = false, nullable = false)
    private String pciDevice;

    @Column(name = "pci_function", length = 1, updatable = false, nullable = false)
    private String pciFunction;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private IommuGroupEntity iommuGroup;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private DeviceEntity device;

    /**
     * Set the PCI domain for this device link. Provided String cannot be empty, null or
     * whitespace-only. It must be exactly 1 or 2 characters in length.
     *
     * @param domain PCI domain
     */
    public void setPciDomain(String domain) {
        ArgumentValidator.requireNonBlank(domain, "PCI domain");
        ArgumentValidator.requireStringLength(domain, 2, NumberCompareOperator.LESS_EQUAL, "PCI domain");

        this.pciDomain = domain;
    }

    /**
     * Set the PCI bus for this device link. Provided String cannot be empty, null or
     * whitespace-only. It must be exactly 2 characters in length.
     *
     * @param bus PCI bus
     */
    public void setPciBus(String bus) {
        ArgumentValidator.requireNonBlank(bus, "PCI bus");
        ArgumentValidator.requireStringLength(bus, 2, NumberCompareOperator.EQUAL, "PCI bus");

        this.pciBus = bus;
    }

    /**
     * Set the PCI device for this device link. Provided String cannot be empty, null or
     * whitespace-only. It must be exactly 2 characters in length.
     *
     * @param device PCI device
     */
    public void setPciDevice(String device) {
        ArgumentValidator.requireNonBlank(device, "PCI device");
        ArgumentValidator.requireStringLength(device, 2, NumberCompareOperator.EQUAL, "PCI device");

        this.pciDevice = device;
    }

    /**
     * Set the PCI function for this device link. Provided String cannot be empty, null or
     * whitespace-only. It must be exactly 1 character in length.
     *
     * @param function PCI function
     */
    public void setPciFunction(String function) {
        ArgumentValidator.requireNonBlank(function, "PCI function");
        ArgumentValidator.requireStringLength(function, 1, NumberCompareOperator.EQUAL, "PCI function");

        this.pciFunction = function;
    }

}
