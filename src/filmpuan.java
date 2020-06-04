


public class filmpuan {

    
    private String Ad;
    private String tür;
    private int puan;
    
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
    
    public int getpuan() {
        return puan;
    }

    public void setpuan(int puan) {
        this.puan = puan;
    }
    
    
    public filmpuan(String Ad, String tür, int puan ) {
        
        this.Ad = Ad;
        this.tür = tür;
        this.puan = puan;
    }
    
    
}
