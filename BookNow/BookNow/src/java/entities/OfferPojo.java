package entities;


public class OfferPojo {
    
    //1.properties
    private Long id;
    private Long room_id;
    private String date_from;
    private String date_to;
    private Integer cost_per_day;
    
    // 2. default c-tor

    public OfferPojo() {
        
    }
    
    // 3. user-defined c-tor 
    public OfferPojo(Long id, Long room_id, String date_from, String date_to, Integer cost_per_day) {
        this.id = id;
        this.room_id = room_id;
        this.date_from = date_from;
        this.date_to = date_to;
        this.cost_per_day = cost_per_day;
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

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public Integer getCost_per_day() {
        return cost_per_day;
    }

    public void setCost_per_day(Integer cost_per_day) {
        this.cost_per_day = cost_per_day;
    }
    
    // 5. to html
    public String toHTML() {
        String html = "";

        html += "<li>" + "Id : " + id + "</li>";
        html += "<li>" + "Room id : " + room_id + "</li>";
        html += "<li>" + "Date_from : " + date_from + "</li>";
        html += "<li>" + "Date_to : " + date_to + "</li>";
        html += "<li>" + "Cost per day : " + cost_per_day + "</li>";
        
        return html;
    }
}
