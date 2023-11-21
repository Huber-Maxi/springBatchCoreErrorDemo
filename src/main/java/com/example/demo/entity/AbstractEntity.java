package com.example.demo.entity;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
public abstract class AbstractEntity<T extends AbstractEntity<T>> implements Serializable {

    public static final String NO_ID_FILTER = "noIdFilter";

    @Id
    @jakarta.persistence.Id
    @Column(name = "ID")
    protected String id;

    @Version
    @Setter
    protected Long version;


    protected void setId(String id) {
        this.id = id;
    }

    public void replaceId(AbstractEntity<T> abstractEntity) {
        this.id = abstractEntity.getId();
    }

    protected abstract String getIdPrefix();

    public abstract boolean containsChanges(T other);

    public void generateId() {
        if (StringUtils.isEmpty(this.id)) {
            this.id = ("%s-%s".formatted(getIdPrefix(), UUID.randomUUID()));
        }
    }
}