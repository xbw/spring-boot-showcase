package com.spring.boot.jpa;

import org.springframework.test.context.ActiveProfiles;


/**
 * FIXME
 * WARN
 * {@code {@link org.hibernate.engine.jdbc.spi.TypeInfo}   : HHH000273: Error accessing type info result set}
 * <p>
 * ERROR
 * {@code {@link org.hibernate.engine.jdbc.spi.SqlExceptionHelper}   : SQL Error: 17056, SQLState: 99999}
 */
@ActiveProfiles("oracle")
class JpaOracleTests extends JpaTests {

}