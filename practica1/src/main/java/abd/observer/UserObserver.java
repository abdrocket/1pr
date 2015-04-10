package abd.observer;

import abd.model.Usuario;

public interface UserObserver {

	public abstract void onOpenCrossword(Integer crossId,String user);
	
	public abstract void onSearchCrossword();
	
	public abstract void onUserAccessAccept();

	public abstract void onUserAccessRefused();

	public abstract void onCreate();

	public abstract void onCurrentUserSetting(Usuario u);

}
