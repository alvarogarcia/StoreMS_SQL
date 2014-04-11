package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.FindAllMechanics;
import uo.ri.business.impl.admin.UpdateMechanic;

public class AdminServiceImpl implements AdminService {

	@Override
	public void newMechanic(String nombre, String apellidos) {
		new AddMechanic(nombre, apellidos).execute();

	}

	@Override
	public void deleteMechanic(Long idMecanico) {
		new DeleteMechanic(idMecanico).execute();

	}

	@Override
	public void updateMechanic(Long id, String nombre, String apellidos) {
		new UpdateMechanic(id, nombre, apellidos).execute();
	}

	@Override
	public List<Map<String, Object>> findAllMechanics() {
		return new FindAllMechanics().execute();
	}

}
