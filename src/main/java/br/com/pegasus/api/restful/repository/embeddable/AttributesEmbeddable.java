package br.com.pegasus.api.restful.repository.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Embeddable
public final class AttributesEmbeddable {

    @Column(name = "LIFE")
    private Integer life;

    @Embedded
    private DamageEntity damage;

    @Embedded
    private ResistanceEntity resistence;

    @Column(name = "SKILLS")
    private List<String> skiLls = new ArrayList<>();

}
