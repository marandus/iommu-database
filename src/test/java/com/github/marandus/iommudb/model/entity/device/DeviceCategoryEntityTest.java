/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommudb.model.entity.device;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
public class DeviceCategoryEntityTest {

	public DeviceCategoryEntityTest() {
	}

	@Test
	public void testSetName() {
		DeviceCategoryEntity instance = new DeviceCategoryEntity();
		instance.setName("PCI bridge");

		assertEquals("PCI bridge", instance.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetName_null() {
		DeviceCategoryEntity instance = new DeviceCategoryEntity();
		instance.setName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetName_empty() {
		DeviceCategoryEntity instance = new DeviceCategoryEntity();
		instance.setName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetName_whitespace() {
		DeviceCategoryEntity instance = new DeviceCategoryEntity();
		instance.setName(" ");
	}

	@Test
	public void testSetDeviceClass() {
		DeviceCategoryEntity instance = new DeviceCategoryEntity();
		instance.setDeviceClass("06");

		assertEquals("06", instance.getDeviceClass());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDeviceClass_null() {
		DeviceCategoryEntity instance = new DeviceCategoryEntity();
		instance.setDeviceClass(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDeviceClass_tooShort() {
		DeviceCategoryEntity instance = new DeviceCategoryEntity();
		instance.setDeviceClass("0");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDeviceClass_tooLong() {
		DeviceCategoryEntity instance = new DeviceCategoryEntity();
		instance.setDeviceClass("060");
	}

	@Test
	public void testSetDeviceSubclass() {
		DeviceCategoryEntity instance = new DeviceCategoryEntity();
		instance.setDeviceSubclass("04");

		assertEquals("04", instance.getDeviceSubclass());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDeviceSubclass_null() {
		DeviceCategoryEntity instance = new DeviceCategoryEntity();
		instance.setDeviceSubclass(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDeviceSubclass_tooShort() {
		DeviceCategoryEntity instance = new DeviceCategoryEntity();
		instance.setDeviceSubclass("0");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDeviceSubclass_tooLong() {
		DeviceCategoryEntity instance = new DeviceCategoryEntity();
		instance.setDeviceSubclass("060");
	}

}
