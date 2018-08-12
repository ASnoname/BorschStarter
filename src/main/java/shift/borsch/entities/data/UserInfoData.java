package shift.borsch.entities.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "UserInfoData")
public class UserInfoData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idData",unique = true)
    private long idData;

    @Column(name = "id",unique = true)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "city")
    private String city;

    @Column(name = "unversity")
    private String university;

    @Column(name = "dormitory")
    private String dormitory;

    @Column(name = "room")
    private String room;

    @Column(name = "linkToVk")
    private String linkToVk;

    @Column(name = "linkToTelegram")
    private String linkToTelegram;

    @Column(name = "cookRate")
    private Double cookRate;

    @Column(name = "eatRate")
    private Double eatRate;
}
