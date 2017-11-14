/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommudb.model.entity.iommu;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.github.marandus.iommudb.model.entity.AbstractBaseEntity;
import com.github.marandus.iommudb.model.entity.report.ReportEntity;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Entity
@Table(name = "iommu_group")
@Getter
@Setter
@ToString(callSuper = true)
public class IommuGroupEntity extends AbstractBaseEntity implements Comparable<IommuGroupEntity> {

	@Column(name = "group_number", updatable = false, nullable = false)
	private Integer groupNumber;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private ReportEntity report;

	@OneToMany(mappedBy = "iommuGroup", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<IommuGroupDeviceLinkEntity> devices;

	@Override
	public int compareTo(IommuGroupEntity t) {
		return this.groupNumber.compareTo(t.getGroupNumber());
	}

}
