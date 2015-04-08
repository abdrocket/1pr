package abd.observer;

public interface UserObserver {

	public abstract void onUserAccessAccept();

	public abstract void onUserAccessRefused();

	public abstract void onCreate();

}
