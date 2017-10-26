/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.exodusproject.iommu.model.entity.report;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.exodusproject.iommu.model.entity.AbstractBaseEntity;
import net.exodusproject.iommu.model.entity.board.MainboardBiosEntity;
import net.exodusproject.iommu.model.entity.board.MainboardEntity;
import net.exodusproject.iommu.service.util.ArgumentValidator;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Entity
@Table(name = "report_metadata")
@Getter
@Setter
@ToString(callSuper = true)
public class ReportMetadataEntity extends AbstractBaseEntity {

	@Column(name = "report_time", updatable = false, nullable = false)
	private LocalDateTime reportTime;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, optional = false, fetch = FetchType.EAGER)
	@Setter(AccessLevel.PROTECTED)
	private MainboardEntity mainboard;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, optional = false, fetch = FetchType.EAGER)
	@Setter(AccessLevel.PROTECTED)
	private MainboardBiosEntity mainboardBios;

	/**
	 * Set information about the mainboard and BIOS version used to create this report. The provided BIOS version
	 * reference must be known by the specified mainboard.
	 *
	 * @param mb Mainboard reference to use
	 * @param bios BIOS version reference to use
	 *
	 * @throws IllegalArgumentException if provided BIOS version is unknown to the specified mainboard
	 */
	public void setMainboardInfo(MainboardEntity mb, MainboardBiosEntity bios) {
		ArgumentValidator.requireNonNull(mb, "Mainboard reference");
		ArgumentValidator.requireNonNull(bios, "Mainboard BIOS reference");

		if (!mb.getBiosVersions().contains(bios)) {
			throw new IllegalArgumentException("Unknown BIOS reference for the specified mainboard");
		}

		this.mainboard = mb;
		this.mainboardBios = bios;
	}
}
