
public class Device{

  private String deviceId;
  private String deviceName;
  private String userName;
  private String userEmail;
  private String password;
  private Location currentLocation; // Might want to do this, might now. Just a thought
  private boolean isLost;


  public Device(){

  }

  public Device(String deviceId, String deviceName, User owner, Location location){
    this.deviceId = deviceId;
    this.deviceName = deviceName;
    this.currentLocation = location;
    this.isLost = false;
  }

  public String getDeviceId(){
    return this.deviceId;
  }

  public void setDeviceId(String deviceId){
    this.deviceId = deviceId;
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
}
