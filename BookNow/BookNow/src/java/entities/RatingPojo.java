package entities;


public class RatingPojo {
    
    // 1. properties
    private Long id;
    private Long room_id;
    private Long user_rate;
    private Integer value;

    // 2. default c-tor
    public RatingPojo() {
        
    }
    
    // 3. user-defined c-tor 

    public RatingPojo(Long id, Long room_id, Long user_rate, Integer value) {
        this.id = id;
        this.room_id = room_id;
        this.user_rate = user_rate;
        this.value = value;
    }
    
    // 4. getters / setters 
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
    
    public Long getUser_rate() {
        return user_rate;
    }

    public void setUser_rate(Long user_rate) {
        this.user_rate = user_rate;
    }
    

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
    //5. to html
    public String toHTML() {
        String html = "";
        
        html += "<li>" + "Id : " + id + "</li>";
        html += "<li>" + "Room id : " + room_id + "</li>";
        html += "<li>" + "User rate : " + user_rate + "</li>";
        html += "<li>" + "Value : " + value + "</li>";
    
    return html;
    }

}