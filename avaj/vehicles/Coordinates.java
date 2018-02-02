package avaj.vehicles;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates (int longitude, int latitude, int height){
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }
    /**
     * @return the longitude
     */
    public int getLongitude() {
        return this.longitude;
    }

    /**
     * @return the latitude
     */
    public int getLatitude() {
        return this.latitude;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height){
        if (height <= 100 && height >= 0){
            this.height = height;
        }
        else if (height < 0){
            this.height = 0;
        }
        else{
            this.height = 100;
        }
    }

    public void setLongitude(int longitude){
        this.longitude = longitude;
    }

    public void setLatitude(int latitude){
        this.latitude = latitude;
    }
}