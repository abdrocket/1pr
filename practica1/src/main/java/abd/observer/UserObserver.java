package abd.observer;

import abd.model.Usuario;

public interface UserObserver {

	public abstract void onUserAccessAccept();

	public abstract void onUserAccessRefused();

	public abstract void onCreate();

	public abstract void onCurrentUserSetting(Usuario u);

}
