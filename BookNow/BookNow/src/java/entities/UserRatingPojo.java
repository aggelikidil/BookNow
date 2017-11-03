package entities;


public class UserRatingPojo {
    
    // 1. properties
    private Long id;
    private Long user_rate;
    private Long user_rated;
    private Integer value;
    
    // 2. default c-tor
    public UserRatingPojo() {
        
    }
    
    // 3. user-defined c-tor 
    public UserRatingPojo(Long id, Long user_rate, Long user_rated , Integer value) {
        this.id = id;
        this.user_rate = user_rate;
        this.user_rated = user_rated;
        this.value = value;
    }
    
    // 4. getters / setters 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_rate() {
        return user_rate;
    }

    public void setUser_rate(Long user_rate) {
        this.user_rate = user_rate;
    }
    
    public Long getUser_rated() {
        return user_rated;
    }

    public void setUser_rated(Long user_rated) {
        this.user_rated = user_rated;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
    // 5. to html
    public String toHTML() {
        String html = "";
    
        html += "<li>" + "Id : " + id + "</li>";
        html += "<li>" + "user rate : " + user_rate + "</li>";
        html += "<li>" + "user rated : " + user_rated + "</li>";
        html += "<li>" + "Value : " + value + "</li>";
        
        return html;
    }
    
}
