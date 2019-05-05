
public class Location{
  private Float longitude;
  private Float latitude;

  public Location(){}

  public Location(Float longitude, Float latitude){
    this.longitude = longitude;
    this.latitude = latitude;
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
