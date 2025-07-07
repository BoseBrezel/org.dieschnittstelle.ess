package org.dieschnittstelle.ess.entities.erp;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

import org.apache.logging.log4j.Logger;

@Entity
public class PointOfSale implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3397160788902953608L;
	
	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(PointOfSale.class);
	
	@Id
	@GeneratedValue
	private long id;

	public PointOfSale() {
		logger.debug("<constructor>");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	/*
	 * lifecycle logging
	 */
	
	@PostLoad
	public void onPostLoad() {
		logger.info("@PostLoad: " + this);
	}
	
	@PostPersist
	public void onPostPersist() {
		logger.info("@PostPersist: " + this);		
	}
	
	@PostRemove
	public void onPostRemove() {
		logger.info("@PostRemove: " + this);
	}

	@PostUpdate
	public void onPostUpdate() {
		logger.info("@PostUpdate: " + this);
	}
	
	@PrePersist
	public void onPrePersist() {
		logger.info("@PrePersist: " + this);
	}

	@PreRemove
	public void onPreRemove() {
		logger.info("@PreRemove: " + this);
	}

	@PreUpdate
	public void onPreUpdate() {
		logger.info("@PreUpdate: " + this);		
	}


}
