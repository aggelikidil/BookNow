package entities_collaboration;


public class UserRateProfilePojo {
    private  Long id;
    private double avg_rate;
    private Long total_rates;

    public UserRateProfilePojo() {
    }

    public UserRateProfilePojo(Long id, double avg_rate, Long total_rates) {
        this.id = id;
        this.avg_rate = avg_rate;
        this.total_rates = total_rates;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAvg_rate() {
        return avg_rate;
    }

    public void setAvg_rate(double avg_rate) {
        this.avg_rate = avg_rate;
    }

    public Long getTotal_rates() {
        return total_rates;
    }

    public void setTotal_rates(Long total_rates) {
        this.total_rates = total_rates;
    }
    
    
    
}
