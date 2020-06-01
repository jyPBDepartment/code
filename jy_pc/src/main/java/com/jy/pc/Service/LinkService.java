package com.jy.pc.Service;

import com.jy.pc.Entity.LinkEntity;

public interface LinkService {
	public void save(LinkEntity linkEntity);
	public void update(LinkEntity linkEntity);
	public void delete(String id);
	public LinkEntity findBId(String salesId);
}
