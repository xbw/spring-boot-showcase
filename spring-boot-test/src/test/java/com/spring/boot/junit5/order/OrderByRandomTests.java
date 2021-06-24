package com.spring.boot.junit5.order;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
class OrderByRandomTests extends OrderByDefaultTests {

}
