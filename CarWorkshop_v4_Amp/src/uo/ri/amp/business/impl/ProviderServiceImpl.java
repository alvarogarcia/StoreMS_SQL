package uo.ri.amp.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.amp.business.ProviderService;
import uo.ri.amp.business.impl.admin.AddProvider;
import uo.ri.amp.business.impl.admin.AddReplacementToProvider;
import uo.ri.amp.business.impl.admin.CheckWhatProviderToUpdate;
import uo.ri.amp.business.impl.admin.CheckWhatReplacementToUpdateOrDelete;
import uo.ri.amp.business.impl.admin.DeleteProvider;
import uo.ri.amp.business.impl.admin.DeleteReplacementFromProvider;
import uo.ri.amp.business.impl.admin.ListAllProviders;
import uo.ri.amp.business.impl.admin.UpdateProvider;
import uo.ri.amp.business.impl.admin.UpdateReplacementFromProvider;

/**
 * @author Álvaro García
 * 
 */
public class ProviderServiceImpl implements ProviderService {

	@Override
	public void addProvider(String providerName) {
		new AddProvider(providerName).execute();

	}

	@Override
	public void deleteProvider(Long providerCode) {
		new DeleteProvider(providerCode).execute();

	}

	@Override
	public void updateProvider(Long providerCode, String providerName) {
		new UpdateProvider(providerCode, providerName).execute();

	}

	@Override
	public void addReplacementToProvider(Long replacementCode,
			Long providerCode, Double price) {
		new AddReplacementToProvider(replacementCode, providerCode, price)
				.execute();

	}

	@Override
	public void deleteReplacementFromProvider(Long replacementCode,
			Long providerCode) {
		new DeleteReplacementFromProvider(replacementCode, providerCode)
				.execute();

	}

	@Override
	public List<Map<String, Object>> checkWhatReplacementToUpdateOrDelete(
			Long replacementCode, Long providerCode, boolean isReplacementCode) {
		return new CheckWhatReplacementToUpdateOrDelete(replacementCode,
				providerCode, isReplacementCode).execute();
	}

	@Override
	public void updateReplacementFromProvider(Long replacementCode,
			Long providerCode, Double newPrice) {
		new UpdateReplacementFromProvider(replacementCode, providerCode,
				newPrice).execute();

	}

	@Override
	public List<Map<String, Object>> checkWhatProviderToUpdate(
			String nameProviderBeforeUpdating) {
		return new CheckWhatProviderToUpdate(nameProviderBeforeUpdating)
				.execute();
	}

	@Override
	public List<Map<String, Object>> listAllProviders() {
		return new ListAllProviders().execute();
	}

}
