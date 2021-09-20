package bg.softuni.mobilele.models.dtos;

import javax.persistence.Column;
import java.time.Instant;

public abstract class BaseDto {
    protected Long id;

    protected Instant created;

    protected Instant updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }
}
