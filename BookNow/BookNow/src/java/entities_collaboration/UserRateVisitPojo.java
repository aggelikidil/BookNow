package entities_collaboration;


public class UserRateVisitPojo {
    private Long id;
    private Long room_id;
    private Double rate;
    private Long visited_value;

    public UserRateVisitPojo() {
    }

    public UserRateVisitPojo(Long id, Long room_id, Double rate, Long visited_value) {
        this.id = id;
        this.room_id = room_id;
        this.rate = rate;
        this.visited_value = visited_value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Long getVisited_value() {
        return visited_value;
    }

    public void setVisited_value(Long visited_value) {
        this.visited_value = visited_value;
    }
    
    
    
}
