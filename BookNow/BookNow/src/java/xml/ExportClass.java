package xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="database")
public class ExportClass {
    @XmlElementWrapper(name = "rooms")
    @XmlElement(name = "room")
    public List<RoomModel> rooms = new ArrayList<>(); 


    
}
