package entities;


public class UserHasRentPojo {
    
    // 1. properties
    private Long user_id;
    private Long rent_id;
    
    // 2. default c-tor
    public UserHasRentPojo() {
        
    }
    
    // 3. user-defined c-tor 
    public UserHasRentPojo(Long user_id, Long rent_id) {
        this.user_id = user_id;
        this.rent_id = rent_id;
    }
    
    // 4. getters / setters 
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getRent_id() {
        return rent_id;
    }

    public void setRent_id(Long rent_id) {
        this.rent_id = rent_id;
    }
    
    // 5. to html
    public String toHTML() {
        String html = "";
        
        html += "<li>" + "User id : " + user_id + "</li>";
        html += "<li>" + "Rent id :" + rent_id + "</li>";
        
        return html;
    }
    
}
