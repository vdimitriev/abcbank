package mk.dmt.abc.entity;

import javax.persistence.*;

@Entity
@Table(name = "country",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_country_country_name",
                        columnNames = {"country_name"}
                )
        })
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_seq")
    @SequenceGenerator(name = "country_id_seq", sequenceName = "country_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "country_name")
    private String name;

    @Column(name = "country_code")
    private String code;

    @Column(name = "enabled")
    private Boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
