/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommudb.model.entity.iommu;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Rix (rix@decoit.de)
 */
public class IommuGroupDeviceLinkEntityTest {

    public IommuGroupDeviceLinkEntityTest() {
    }

    @Test
    public void testSetPciDomain() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        assertEquals("0", instance.getPciDomain());

        instance.setPciDomain("1");
        assertEquals("1", instance.getPciDomain());

        instance.setPciDomain("11");
        assertEquals("11", instance.getPciDomain());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciDomain_null() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciDomain(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciDomain_empty() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciDomain("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciDomain_whitespace() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciDomain(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciDomain_tooLong() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciDomain("123");
    }

    @Test
    public void testSetPciBus() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciBus("ff");

        assertEquals("ff", instance.getPciBus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciBus_null() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciBus(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciBus_empty() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciBus("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciBus_whitespace() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciBus(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciBus_tooShort() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciBus("f");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciBus_tooLong() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciBus("fff");
    }

    @Test
    public void testSetPciDevice() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciDevice("ff");

        assertEquals("ff", instance.getPciDevice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciDevice_null() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciDevice(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciDevice_empty() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciDevice("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciDevice_whitespace() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciDevice(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciDevice_tooShort() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciDevice("f");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciDevice_tooLong() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciDevice("fff");
    }

    @Test
    public void testSetPciFunction() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciFunction("1");

        assertEquals("1", instance.getPciFunction());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciFunction_null() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciFunction(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciFunction_empty() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciFunction("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciFunction_whitespace() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciFunction(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPciFunction_tooLong() {
        IommuGroupDeviceLinkEntity instance = new IommuGroupDeviceLinkEntity();
        instance.setPciFunction("11");
    }

}
