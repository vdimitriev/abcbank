package mk.dmt.abc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    /**
     * Auto-generated ID.
     */
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itc_rate_id_seq")
    @SequenceGenerator(name = "itc_rate_id_seq", sequenceName = "itc_rate_id_seq", allocationSize = 1)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + id + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        final AbstractEntity that = (AbstractEntity) o;

        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        int hashCode = 17;

        hashCode += getId() == null ? 0 : getId().hashCode() * 31;

        return hashCode;
    }
}