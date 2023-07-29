package com.rampagemc.authorization.application.integration.limboauth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

/**
 * Модель данных, формат которой определяется плагином LimboAuth.
 * В эту модель включены только необходимые для работы плагина поля.
 * Источник: <a href="https://github.com/Elytrium/LimboAuth/blob/e6a790b52b8313e0fc242bcd35e6aa2dd5e7ce94/src/main/java/net/elytrium/limboauth/model/RegisteredPlayer.java">RegisteredPlayer</a>
 */
@SuppressWarnings("unused")
@Data
@NoArgsConstructor
@Entity
@Table(name = "AUTH")
public class LimboAuthUser {

    public static final String NICKNAME_FIELD = "NICKNAME";
    public static final String LOWERCASE_NICKNAME_FIELD = "LOWERCASENICKNAME";
    public static final String HASH_FIELD = "HASH";
    public static final String IP_FIELD = "IP";
    public static final String LOGIN_IP_FIELD = "LOGINIP";
    public static final String TOTP_TOKEN_FIELD = "TOTPTOKEN";
    public static final String REG_DATE_FIELD = "REGDATE";
    public static final String LOGIN_DATE_FIELD = "LOGINDATE";
    public static final String UUID_FIELD = "UUID";
    public static final String PREMIUM_UUID_FIELD = "PREMIUMUUID";
    public static final String TOKEN_ISSUED_AT_FIELD = "ISSUEDTIME";

    @Id
    @Column(name = LOWERCASE_NICKNAME_FIELD)
    private String lowercaseNickname;

    @NotNull
    @Column(name = NICKNAME_FIELD)
    private String nickname;

    @NotNull
    @Column(name = HASH_FIELD)
    private String hash = "";

    @Column(name = IP_FIELD)
    private String ip;

    @Column(name = TOTP_TOKEN_FIELD)
    private String totpToken = "";

    @Column(name = REG_DATE_FIELD)
    private Long regDate = System.currentTimeMillis();

    @Column(name = UUID_FIELD)
    private String uuid = "";

    @Column(name = PREMIUM_UUID_FIELD)
    private String premiumUuid = "";

    @Column(name = LOGIN_IP_FIELD)
    private String loginIp;

    @Column(name = LOGIN_DATE_FIELD)
    private Long loginDate = System.currentTimeMillis();

    @Column(name = TOKEN_ISSUED_AT_FIELD)
    private Long tokenIssuedAt = System.currentTimeMillis();

    public LimboAuthUser(String nickname, String hashedPassword) {
        this.nickname = nickname;
        this.lowercaseNickname = nickname.toLowerCase(Locale.ROOT);
        this.hash = hashedPassword;
    }
}
