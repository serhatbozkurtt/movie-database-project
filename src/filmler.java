


public class filmler {

    private int id;
    private String Ad;
    private String tür;
    
    
    
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String Ad) {
        this.Ad = Ad;
    }

    public String getTür() {
        return tür;
    }

    public void setTür(String tür) {
        this.tür = tür;
    }

    public filmler(int id, String Ad, String tür) {
        this.id = id;
        this.Ad = Ad;
        this.tür = tür;
    }
    
}
