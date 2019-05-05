
public class Device{

  private String id;
  private String deviceName;
  private String userName;
  private String userEmail;
  private String password;
  private Location currentLocation; // Might want to do this, might now. Just a thought
  private boolean isLost;


  public Device(){

  }

  public Device(String deviceId, String deviceName, User owner, Location location){
    this.id = deviceId;
    this.deviceName = deviceName;
    this.currentLocation = location;
    this.isLost = false;
  }

  public String getDeviceId(){
    return this.id;
  }

  public void setDeviceId(String deviceId){
    this.id = deviceId;
  }

  public boolean getIsLost(){
    return this.isLost;
  }

  public void setIsLost(boolean isLost){
    this.isLost = isLost;
  }
  
  public String getUserName(){
    return this.userName;
  }

  public void setUserName(String userName){
    this.userName = userName;
  }
  
  public void setDeviceName(String name) {
	  this.deviceName
  }
}
