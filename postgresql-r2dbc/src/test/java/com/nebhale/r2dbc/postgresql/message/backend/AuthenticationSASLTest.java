/*
 * Copyright 2017-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nebhale.r2dbc.postgresql.message.backend;

import org.junit.Test;

import java.util.Collections;

import static com.nebhale.r2dbc.postgresql.message.backend.BackendMessageAssert.assertThat;
import static io.netty.util.CharsetUtil.UTF_8;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public final class AuthenticationSASLTest {

    @Test
    public void constructorNoAuthenticationMechanisms() {
        assertThatNullPointerException().isThrownBy(() -> new AuthenticationSASL(null))
            .withMessage("authenticationMechanisms must not be null");
    }

    @Test
    public void decode() {
        assertThat(AuthenticationSASL.class)
            .decoded(buffer -> {
                buffer.writeCharSequence("test-authentication-mechanism", UTF_8);
                buffer.writeByte(0);

                buffer.writeByte(0);

                return buffer;
            })
            .isEqualTo(new AuthenticationSASL(Collections.singletonList("test-authentication-mechanism")));
    }

}
