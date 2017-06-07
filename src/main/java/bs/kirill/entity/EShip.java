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
    private Long shipID;

    @Column(name = "battle_id")
    private Long battleID;

    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "prow_xx")
    private Integer prowXX;

    @Column(name = "prow_yy")
    private Integer prowYY;

    @Column(name = "stern_xx")
    private Integer sternXX;

    @Column(name = "stern_yy")
    private Integer sternYY;

    @Column(name = "length")
    private Integer length;

    public Long getShipID() {
        return shipID;
    }

    public void setShipID(Long shipID) {
        this.shipID = shipID;
    }

    public Long getBattleID() {
        return battleID;
    }

    public void setBattleID(Long battleID) {
        this.battleID = battleID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getProwXX() {
        return prowXX;
    }

    public void setProwXX(Integer prowXX) {
        this.prowXX = prowXX;
    }

    public Integer getProwYY() {
        return prowYY;
    }

    public void setProwYY(Integer prowYY) {
        this.prowYY = prowYY;
    }

    public Integer getSternXX() {
        return sternXX;
    }

    public void setSternXX(Integer sternXX) {
        this.sternXX = sternXX;
    }

    public Integer getSternYY() {
        return sternYY;
    }

    public void setSternYY(Integer sternYY) {
        this.sternYY = sternYY;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
