package br.com.pegasus.api.restful.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_LOGIN")
public class LoginEntity {

    @EmbeddedId
    private LoginId id;

    @Column(nullable = false, length = 150)
    private String password;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class LoginId implements Serializable {

        @Column(name = "login", length = 150, nullable = false, unique = true)
        private String login;

    }
}
