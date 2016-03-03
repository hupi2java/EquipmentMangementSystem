package com.hp.serviceInter;

import java.util.List;

import com.hp.base.service.BaseServiceInter;
import com.hp.domain.Machine;

public interface MachineServiceInter extends BaseServiceInter {

	public  List<Machine> getAll();


}
