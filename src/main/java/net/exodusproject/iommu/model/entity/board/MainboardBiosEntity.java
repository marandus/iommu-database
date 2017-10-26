/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.exodusproject.iommu.model.entity.board;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.exodusproject.iommu.model.entity.AbstractBaseEntity;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Entity
@Table(name = "mainboard_bios")
@Getter
@Setter
@ToString(callSuper = true)
public class MainboardBiosEntity extends AbstractBaseEntity {

	@Column(name = "version", nullable = false)
	private String version;

	@Column(name = "release_date", nullable = true)
	private LocalDate releaseDate;

	@Column(name = "notes", nullable = true)
	private String additionalNotes;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private MainboardEntity mainboard;
}
