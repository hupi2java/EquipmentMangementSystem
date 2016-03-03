package com.hp.serviceInter;

import java.util.List;

import com.hp.base.service.BaseServiceInter;
import com.hp.domain.Datecycle;

public interface DatecycleServiceInter extends BaseServiceInter {

	public List<Datecycle> getAll();
}
