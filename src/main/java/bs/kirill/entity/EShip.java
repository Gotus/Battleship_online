package bs.kirill.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.StringJoiner;

/**
 * Created by Администратор on 22.12.2016.
 */
@Entity
@Table(name = "ship", schema = "public")
public class EShip
{


    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ship_id", unique = true,nullable = false)
    private Long ship_id;

    @Column(name = "battle_id")
    private Long battleID;

    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "prow")
    private  String prow;

    @Column(name = "stern")
    private String stern;

    @Column(name = "length")
    private Integer length;

    public Long getShip_id() {
        return ship_id;
    }

    public void setShip_id(Long ship_id) {
        this.ship_id = ship_id;
    }
    public Long getBattle_ID(){return battleID;}
    public void setBattle_ID(Long B_ID){battleID = B_ID;}
    public Integer getUser_ID(){return userID;}
    public void setUser_ID(Integer U_ID){userID = U_ID;}
    public String getProw(){return prow;}
    public void setProw(String P){prow = P;}
    public String getStern(){return stern;}
    public void setStern(String Strn){stern = Strn;}
    public Integer getLength(){return length;}
    public void setLength(Integer Len){length = Len;}

    public Long getId() {
        return ship_id;
    }

    public void setId(Long id) {
        this.ship_id = id;
    }
}
