package org.hcmus.dao.idao;

import org.hcmus.bus.JPOS_TaskDTO;

public interface IJPOS_Task {
	public JPOS_TaskDTO getTask(int taskId);
}
