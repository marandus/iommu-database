/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.model.entity.chipset;

import java.util.HashSet;
import java.util.Set;
import com.github.marandus.iommu.model.entity.ManufacturerEntity;
import com.github.marandus.iommu.model.enums.ManufacturerType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
public class ChipsetEntityTest {

	public ChipsetEntityTest() {
	}

	@Test
	public void testSetManufacturer() {
		Set<ManufacturerType> types = new HashSet<>();
		types.add(ManufacturerType.CHIPSET);

		ManufacturerEntity manufacturer = new ManufacturerEntity();
		manufacturer.setShortName("AMD");
		manufacturer.setFullName("");
		manufacturer.setCode("AMD");
		manufacturer.setTypes(types);

		ChipsetEntity instance = new ChipsetEntity();
		instance.setManufacturer(manufacturer);

		assertEquals(manufacturer, instance.getManufacturer());
	}

	@Test
	public void testSetManufacturer_multipleTypes() {
		Set<ManufacturerType> types = new HashSet<>();
		types.add(ManufacturerType.CHIPSET);
		types.add(ManufacturerType.MAINBOARD);

		ManufacturerEntity manufacturer = new ManufacturerEntity();
		manufacturer.setShortName("AMD");
		manufacturer.setFullName("");
		manufacturer.setCode("AMD");
		manufacturer.setTypes(types);

		ChipsetEntity instance = new ChipsetEntity();
		instance.setManufacturer(manufacturer);

		assertEquals(manufacturer, instance.getManufacturer());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetManufacturer_emptyTypes() {
		Set<ManufacturerType> types = new HashSet<>();

		ManufacturerEntity manufacturer = new ManufacturerEntity();
		manufacturer.setShortName("AMD");
		manufacturer.setFullName("");
		manufacturer.setCode("AMD");
		manufacturer.setTypes(types);

		ChipsetEntity instance = new ChipsetEntity();
		instance.setManufacturer(manufacturer);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetManufacturer_wrongType() {
		Set<ManufacturerType> types = new HashSet<>();
		types.add(ManufacturerType.MAINBOARD);

		ManufacturerEntity manufacturer = new ManufacturerEntity();
		manufacturer.setShortName("AMD");
		manufacturer.setFullName("");
		manufacturer.setCode("AMD");
		manufacturer.setTypes(types);

		ChipsetEntity instance = new ChipsetEntity();
		instance.setManufacturer(manufacturer);
	}

}
