package model;

import java.util.List;

public class MutterLogic {

	public void post(Mutter mutter, List<Mutter> mutterList) {
		
		mutterList.add(0, mutter);
	}
}
