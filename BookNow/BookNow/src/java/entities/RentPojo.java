package entities;


public class RentPojo {
    
   // 1. properties
   private Long id;
   private Long room_id;
   private String date_from;
   private String date_to;
   
   //related lists
   private RoomPojo room;

   
   // 2. default c-tor

   public RentPojo() {
   
   }
   
   // 3. user-defined c-tor 
    public RentPojo(Long id, Long room_id, String date_from, String date_to) {
        this.id = id;
        this.room_id = room_id;
        this.date_from = date_from;
        this.date_to = date_to;
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
    
    // 5. to html
    public String toHTML() {
        String html = "";
        
        html += "<li>" + "Id : " + id + "</li>";
        html += "<li>" + "Room id : " + room_id + "</li>";
        html += "<li>" + "Date from : " + date_from + "</li>";
        html += "<li>" + "Date to : " + date_to + "</li>";
        
        return html;
    }
    
    
    public RoomPojo getRoom() {
        return room;
    }

    public void setRoom(RoomPojo details) {
        this.room = room;
    }
}
