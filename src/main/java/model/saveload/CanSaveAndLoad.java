package model.saveload;

import model.GameBookInterface;

public interface CanSaveAndLoad {
	
	GameBookInterface load(final String path);
	boolean save(final GameBookInterface gb, final String path);
}
