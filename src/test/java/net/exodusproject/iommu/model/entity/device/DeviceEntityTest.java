/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.exodusproject.iommu.model.entity.device;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
public class DeviceEntityTest {

	public DeviceEntityTest() {
	}

	@Test
	public void testSetVendorId() {
		DeviceEntity instance = new DeviceEntity();
		instance.setVendorId("8086");

		assertEquals(instance.getVendorId(), "8086");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetVendorId_null() {
		DeviceEntity instance = new DeviceEntity();
		instance.setVendorId(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetVendorId_tooShort() {
		DeviceEntity instance = new DeviceEntity();
		instance.setVendorId("808");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetVendorId_tooLong() {
		DeviceEntity instance = new DeviceEntity();
		instance.setVendorId("80860");
	}

	@Test
	public void testSetDeviceId() {
		DeviceEntity instance = new DeviceEntity();
		instance.setDeviceId("3c80");

		assertEquals(instance.getDeviceId(), "3c80");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDeviceId_null() {
		DeviceEntity instance = new DeviceEntity();
		instance.setDeviceId(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDeviceId_tooShort() {
		DeviceEntity instance = new DeviceEntity();
		instance.setDeviceId("3c8");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDeviceId_tooLong() {
		DeviceEntity instance = new DeviceEntity();
		instance.setDeviceId("3c800");
	}

}
