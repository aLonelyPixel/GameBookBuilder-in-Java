package model.saveload;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.GameBookInterface;

public class MySaverLoader implements CanSaveAndLoad{

	@Override
	public GameBookInterface load(String path) {
		try (final ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))){
			final GameBookInterface gb = (GameBookInterface)in.readObject();
			return gb;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean save(GameBookInterface gb, String path) {
		try(final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))){ 
			out.writeObject(gb);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
