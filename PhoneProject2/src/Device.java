
public class Device{

  private String id;
  private String deviceName;
  private String userName;
  private String userEmail;
  private String password;
  private Float longitude;
  private Float latitude;
  private boolean isLost;


  public Device(){

  }

  public Device(String deviceId, String deviceName, String owner, Float longitude, Float latitude){
    this.id = deviceId;
    this.deviceName = deviceName;
    this.userName=owner;
    this.longitude = longitude;
    this.latitude = latitude;
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
	  this.deviceName=name;
  }
  
  public String getDeviceName() {
	  return this.deviceName;
  }

  public Float getLongitude(){
    return this.longitude;
  }

  public void setLongitude(Float longitude){
    this.longitude = longitude;
  }
  public Float getLatitude(){
    return this.latitude;
  }

  public void setLatitude(Float latitude){
  this.latitude = latitude;
  }

}
