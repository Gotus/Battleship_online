package bs.kirill.entity;

import bs.web.model.entities.Battle;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Администратор on 22.12.2016.
 */

@Entity
@Table(name = "battle", schema = "public")
public class EBattle {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "battle_id")
    private Long battleID;

    @Column(name = "host_id")
    private Integer hostID;

    @Column(name = "opponent_id")
    private Integer opponentID;

    @Column(name = "replay")
    private String replay;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @Column(name = "date_of_joining")
    private Date dateOfJoining;

    @Column(name = "date_of_start")
    private Date dateOfStart;

    @Column(name = "date_of_ending")
    private Date dateOfEnding;

    @Column(name = "last_actioner_id")
    private Long lastActionerID;

    @Column(name = "date_of_last_action")//, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)

    private Date dateOfLastAction;

    public Long getBattle_ID(){return battleID;}
    public void setBattle_ID(Long B_ID){battleID = B_ID;}
    public Integer getHost_ID(){return hostID;}
    public void setHost_ID(Integer H_ID){hostID = H_ID;}
    public Integer getOpponent_ID(){return opponentID;}
    public void setOpponent_ID(Integer O_ID){opponentID = O_ID;}
    public String getReplay(){return replay;}
    public void setReplay(String Rep){replay = Rep;}
    public Date getDate_of_creation(){return dateOfCreation;}
    public void setDate_of_creation(Date D_of_C){dateOfCreation = D_of_C;}
    public Date getDate_of_joining(){return dateOfJoining;}
    public void setDate_of_joining(Date D_of_J){dateOfJoining = D_of_J;}
    public Date getDate_of_start(){return dateOfStart;}
    public void setDate_of_start(Date D_of_S){dateOfStart = D_of_S;}
    public Date getDate_of_ending(){return dateOfEnding;}
    public void setDate_of_ending(Date D_of_E){dateOfEnding = D_of_E;}

    public void setLastActionerID(Long lastActionerID) {this.lastActionerID = lastActionerID;}
    public Long getLastActionerID() {return lastActionerID;}

    public void setDate_of_last_action(Date dateOfLastAction) {this.dateOfLastAction = dateOfLastAction;}
    public Date getDate_of_last_action() {return dateOfLastAction;}
}