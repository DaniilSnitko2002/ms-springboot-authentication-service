package com.springboot.msauthenticationservice.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Prefix that is putted before the encoding of a password
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PrefixPasswordConstant {
    public static final String PASSWORD_PREFIX = "dAniPrefIX";
}
