/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.exodusproject.iommu.model.entity.report;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.exodusproject.iommu.model.entity.AbstractBaseEntity;
import net.exodusproject.iommu.model.entity.iommu.IommuGroupEntity;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Entity
@Table(name = "report")
@Getter
@Setter
@ToString(callSuper = true)
public class ReportEntity extends AbstractBaseEntity {

	@Column(name = "raw_report", length = 65535, updatable = false, nullable = false)
	private String rawReport;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.DETACH}, orphanRemoval = true, fetch = FetchType.EAGER, optional = false)
	private ReportMetadataEntity metadata;

	@OneToMany(mappedBy = "report", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.DETACH}, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<IommuGroupEntity> iommuGroups = new HashSet<>();

}
