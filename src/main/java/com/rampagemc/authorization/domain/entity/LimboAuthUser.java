package com.rampagemc.authorization.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/**
 * An entity model from the LimboAuth plugin, described in
 *  <a href="https://github.com/Elytrium/LimboAuth/blob/e6a790b52b8313e0fc242bcd35e6aa2dd5e7ce94/src/main/java/net/elytrium/limboauth/model/RegisteredPlayer.java">RegisteredPlayer</a>
 */
@SuppressWarnings("unused")
@Getter
@Setter
@Entity
@Table(name = "`AUTH`")
@NoArgsConstructor
public class LimboAuthUser {

    @Id
    @Column(name = "`LOWERCASENICKNAME`")
    private String lowercaseNickname;

    @NotNull
    @Column(name = "`NICKNAME`")
    private String nickname;

    @NotNull
    @Column(name = "`HASH`")
    private String hash = "";

    @Column(name = "`IP`")
    private String ip;

    @Column(name = "`TOTPTOKEN`")
    private String totpToken = "";

    @Column(name = "`REGDATE`")
    private Long regDate = System.currentTimeMillis();

    @Column(name = "`UUID`")
    private String uuid = "";

    @Column(name = "`PREMIUMUUID`")
    private String premiumUuid = "";

    @Column(name = "`LOGINIP`")
    private String loginIp;

    @Column(name = "`LOGINDATE`")
    private Long loginDate = System.currentTimeMillis();

    @Column(name = "`ISSUEDTIME`")
    private Long tokenIssuedAt = System.currentTimeMillis();

    public LimboAuthUser(String nickname, String hashedPassword, UUID uuid) {
        this.nickname = nickname;
        this.lowercaseNickname = nickname.toLowerCase(Locale.ROOT);
        this.hash = hashedPassword;
        this.uuid = uuid.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof LimboAuthUser other)) return false;
        return Objects.equals(getLowercaseNickname(), other.getLowercaseNickname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowercaseNickname);
    }
}
