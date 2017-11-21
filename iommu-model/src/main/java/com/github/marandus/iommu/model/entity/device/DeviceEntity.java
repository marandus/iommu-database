/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.model.entity.device;

import javax.persistence.CascadeType;
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

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Entity
@Table(name = "device")
@Getter
@Setter
@ToString(callSuper = true)
public class DeviceEntity extends AbstractBaseEntity {

    @Column(name = "name", length = 1000, updatable = false, nullable = false)
    private String name;

    @Column(name = "vendor_id", length = 4, updatable = false, nullable = false)
    private String vendorId;

    @Column(name = "vendor_name", updatable = true, nullable = true)
    private String vendorName;

    @Column(name = "device_id", length = 4, updatable = false, nullable = false)
    private String deviceId;

    @Column(name = "device_name", length = 1000, updatable = true, nullable = true)
    private String deviceName;

    @Column(name = "revision", updatable = false, nullable = false)
    private String revision;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, optional = false, fetch = FetchType.EAGER)
    private DeviceCategoryEntity deviceClass;

    public void setVendorId(String vendorId) {
        ArgumentValidator.requireStringLength(vendorId, 4, NumberCompareOperator.EQUAL, "Vendor ID");

        this.vendorId = vendorId;
    }

    public void setDeviceId(String deviceId) {
        ArgumentValidator.requireStringLength(deviceId, 4, NumberCompareOperator.EQUAL, "Device ID");

        this.deviceId = deviceId;
    }
}
